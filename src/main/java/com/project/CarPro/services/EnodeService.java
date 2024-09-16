package com.project.CarPro.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CarPro.dto.request.CarRequestDTO;
import com.project.CarPro.dto.response.CarResponseDTO;
import com.project.CarPro.model.Car;
import com.project.CarPro.repositories.CarRepository;
import com.project.CarPro.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnodeService {

    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final WebClient webClient;
    private UserRepository userRepository;
    private CarService carService;
    private CarRepository carRepository;

    public EnodeService(OAuth2AuthorizedClientManager authorizedClientManager, WebClient.Builder webClientBuilder, UserRepository userRepository, CarService carService, CarRepository carRepository) {
        this.authorizedClientManager = authorizedClientManager;
        this.webClient = webClientBuilder.build();
        this.userRepository = userRepository;
        this.carService = carService;
        this.carRepository = carRepository;
    }

    //TODO
    //de extras logica de obtinere token in metoda diferita\


    //TODO
    //fac un cronjob care ruleaza o data pe zi si imi aduce toate masinile de pe enode
    //daca in raspunul primit exista vin-uri care nu se regasesc in vin-urile masinilor din baza mea de date, atunci salvezi in baza mea de date (apeland metoda deja existenta de addCar) acele masini in plus (cu tot cu id-ul de enode)
    //deci am nevoie ca in entitatea car sa mai adaug VIN, si enodeId

    //TODO
    //e facuta deja functionalitatea de getVehicaleState (din Enode)
    //request-ul nostru la aplicatia noastra poate fi facut dupa id-ul masinii din baza noastra de date
    //dupa care cautam enodeId-ul acelei masini
    //si la enode o sa ii trimiti ca perametru enodeId-ul aflat


    public OAuth2AccessToken getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("enode")
                .principal("enode-client")
                .build();

        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
        if (authorizedClient == null) {
            throw new IllegalStateException("Failed to authorize client");
        }

        return authorizedClient.getAccessToken();
    }


    public String createLinkSession(String userId) {
        OAuth2AccessToken accessToken = getAccessToken();

        String url = "https://enode-api.sandbox.enode.io/users/" + userId + "/link";
        String redirectUri = "localhost:8080/link/callback";

        return this.webClient.post()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken.getTokenValue())
                .header("Content-Type", "application/json")
                .bodyValue("{ \"vendorType\": \"vehicle\", \"scopes\": [\"vehicle:read:data\", \"vehicle:read:location\", \"vehicle:control:charging\"], \"language\": \"en-US\", \"redirectUri\": \"" + redirectUri + "\" }")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void fetchAndUpdateCars() {
        //obtin token
        OAuth2AccessToken accessToken = getAccessToken();
        // iau toate masinile de de pe Enode folosind tokenul
        String userVehiclesResponse = fetchEnodeCarsFromApi(accessToken);
        System.out.println("Response from Enode API: " + userVehiclesResponse);
        //raspuns API -> lista de CarRequestDTO
        List<CarRequestDTO> enodeCars = parseEnodeCars(userVehiclesResponse);
        //iau lista de VIN-uri din baza de date
        List<String> existingVinList = carRepository.findAllVins();
        //gasim masinile din Enode care nu se afla in baza de date dupa VIN si le excludem pe cela fara vin
        List<CarRequestDTO> newCars = enodeCars.stream()
                .filter(car -> car.getVin() != null && !car.getVin().isEmpty())
                .filter(car -> !existingVinList.contains(car.getVin()))
                .collect(Collectors.toList());

        //salvam masinile noi in baza de date
        for (CarRequestDTO newCar : newCars) {
            try {
                Optional<Car> existingCar = carRepository.findByVin(newCar.getVin());
                if (existingCar.isEmpty()) {
                    carService.addCar(newCar, null);
                } else {
                    System.out.println("Car with VIN: " + newCar.getVin() + " already exists.");
                }
            } catch (DataIntegrityViolationException e) {
                System.out.println("Error while adding car: " + e.getMessage());
            }

        }
    }


    public List<CarRequestDTO> parseEnodeCars(String userVehiclesResponse) {
        List<CarRequestDTO> carList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(userVehiclesResponse);
            JsonNode dataNode = rootNode.get("data");
            if (dataNode.isArray()) {
                for (JsonNode carNode : dataNode) {
                    String enodeId = carNode.get("id").asText();
                    String brand = carNode.get("vendor").asText();
                    String vin = carNode.get("information").get("vin").asText();
                    String model = carNode.get("information").get("model").asText();
                    int year = carNode.get("information").get("year").asInt();
                    double mileage = carNode.get("odometer").get("distance").asDouble();

                    //ignoram masinile fara VIN valid
                    if (vin == null || vin.isEmpty()) {
                        System.out.println("Car with enodeId: " + enodeId + " do not have VIN");
                        continue;
                    }

                    CarRequestDTO carRequestDTO = new CarRequestDTO();

                    carRequestDTO.setBrand(brand);
                    carRequestDTO.setModel(model);
                    carRequestDTO.setProductionYear(year);
                    carRequestDTO.setMileage(mileage);
                    carRequestDTO.setVin(vin);
                    carRequestDTO.setEnodeId(enodeId);

                    carList.add(carRequestDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carList;
    }

    public String fetchEnodeCarsFromApi(OAuth2AccessToken accessToken) {
        String url = "https://enode-api.sandbox.enode.io/vehicles";

        return this.webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken.getTokenValue())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    public Mono<String> listLocations(String userId, String after, String before, Integer pageSize) {
        OAuth2AccessToken accessToken = getAccessToken();

        // Construim URL-ul, inclusiv userId
        String url = "https://enode-api.sandbox.enode.io/users/" + userId + "/locations";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if (after != null) {
            uriBuilder.queryParam("after", after);
        }
        if (before != null) {
            uriBuilder.queryParam("before", before);
        }
        if (pageSize != null) {
            uriBuilder.queryParam("pageSize", pageSize);
        }

        // Facem cererea GET pentru a obține locațiile pentru userId
        return this.webClient.get()
                .uri(uriBuilder.toUriString())
                .header("Authorization", "Bearer " + accessToken.getTokenValue())
                .retrieve()
                .bodyToMono(String.class);
    }


    public Mono<String> getUserVehicles(String userId) {
        OAuth2AccessToken accessToken = getAccessToken();

        // Facem cererea GET pentru a obține locațiile pentru userId
        return webClient.get()
                .uri("https://enode-api.sandbox.enode.io/users/{userId}/vehicles", userId)
                .header("Authorization", "Bearer " + accessToken.getTokenValue())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getVehicleState(String enodeId) {
        OAuth2AccessToken accessToken = getAccessToken();
        // Facem cererea GET pentru a obține locațiile pentru userId
        return webClient.get()
                .uri("https://enode-api.sandbox.enode.io/vehicles/{enodeId}", enodeId)
                .header("Authorization", "Bearer " + accessToken.getTokenValue())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getVehicleStateById(Long carId){
        return Mono.justOrEmpty(carRepository.findById(carId))
                .flatMap(car -> {
                    String enodeId = car.getEnodeId();
                    if (enodeId == null || enodeId.isEmpty()){
                        return Mono.error(new IllegalArgumentException("Enode id not found for this car id"));
                    }
                    return getVehicleState(enodeId);
                });
    }
}

