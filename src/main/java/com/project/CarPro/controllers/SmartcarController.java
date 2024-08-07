//package com.project.CarPro.controllers;
//
//import com.smartcar.sdk.AuthClient;
//import com.smartcar.sdk.Smartcar;
//import com.smartcar.sdk.SmartcarException;
//import com.smartcar.sdk.Vehicle;
//import com.smartcar.sdk.data.Auth;
//import com.smartcar.sdk.data.VehicleIds;
//import com.smartcar.sdk.data.VehicleLocation;
//import com.smartcar.sdk.data.VehicleOdometer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class SmartcarController {
//
//    private static final String CLIENT_ID = "5ccb7158-b08f-4b03-bfcd-c40973905ea6";
//    private static final String CLIENT_SECRET = "5514eae0-c055-42aa-8d30-236f828d342b";
//    private static final String REDIRECT_URI = "http://localhost:8080/callback";
//    private static final String CLIENT_SECRET_2 = "6ffa25b76c18732ed2890e02089175e564b00416";
//    private AuthClient smartcarAuth;
//
//
//    @GetMapping("/authorize")
//    public String authorize() throws Exception {
//        smartcarAuth = new AuthClient.Builder()
//                .clientId(CLIENT_ID)
//                .clientSecret(CLIENT_SECRET)
//                .redirectUri(REDIRECT_URI)
//                .mode("simulated").build();
//
//
//        String[] scope = {"read_battery", "read_fuel", "read_location", "read_odometer", "read_vin","control_security"};
//
//
//        String authUrl = smartcarAuth.authUrlBuilder(scope).approvalPrompt(true)
//                .build();
//
//        return "redirect:" + authUrl;
//    }
//
//    @GetMapping("/callback")
//    public String callback(@RequestParam("code") String code) throws SmartcarException {
//        Auth auth = smartcarAuth.exchangeCode(code);
//
//        String accessToken = auth.getAccessToken();
//        VehicleIds response = Smartcar.getVehicles(accessToken);
//        String[] vehicleIds = response.getVehicleIds();
//        Vehicle vehicle = new Vehicle(vehicleIds[0], accessToken);
//
//        String vin = vehicle.vin().getVin();
//
//// Read the vehicle's odometer
//        VehicleOdometer odometerData = vehicle.odometer();
//        double odometer = odometerData.getDistance();
//
//// Retrieve the vehicle's location
//        VehicleLocation locationData = vehicle.location();
//        String latLong = locationData.getLatitude() + ", " + locationData.getLongitude();
//
//
//        return latLong;
//    }
//
//}
//
////    @GetMapping("/callback")
////    public ResponseEntity<String> callback(@RequestParam("code") String code) {
////        try {
////            Auth auth = smartcarAuth.exchangeCode(code);
////
////            String accessToken = auth.getAccessToken();
////            VehicleIds response = Smartcar.getVehicles(accessToken);
////            String[] vehicleIds = response.getVehicleIds();
////            Vehicle vehicle = new Vehicle(vehicleIds[0], accessToken);
////
////            String vin = vehicle.vin().getVin();
////            VehicleOdometer odometerData = vehicle.odometer();
////            double odometer = odometerData.getDistance();
////            VehicleLocation locationData = vehicle.location();
////            String latLong = locationData.getLatitude() + ", " + locationData.getLongitude();
////
////          //  return "Vehicle VIN: " + vin + ", Odometer: " + odometer + ", Location: " + latLong;
////            String errorMessage = "Your application has insufficient permissions to access the requested resource. Please re-authenticate using Smartcar Connect.";
////            String reAuthenticateLink = "<a href=\"/authorize\">Re-authenticate</a>";
////            String messageWithLink = errorMessage + " " + reAuthenticateLink;
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageWithLink);
////        } catch (Exception e) {
////            // Handle other exceptions
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
////        }
////    }
////}
//
