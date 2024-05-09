package com.project.CarPro.services;

import com.project.CarPro.dto.response.CarExpensesResponseDTO;
import com.project.CarPro.dto.response.FleetExpensesResponseDTO;
import com.project.CarPro.exceptions.ResourceNotFoundException;
import com.project.CarPro.model.Car;
import com.project.CarPro.model.Expenses;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.repositories.CarRepository;
import com.project.CarPro.repositories.ExpensesRepository;
import com.project.CarPro.repositories.FleetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ExpensesService {
    private ExpensesRepository expensesRepository;
    private CarRepository carRepository;
    private FleetRepository fleetRepository;
    private CarExpensesResponseDTO carExpensesResponseDTO;
    private FleetExpensesResponseDTO fleetExpensesResponseDTO;


    @Autowired
    public ExpensesService(ExpensesRepository expensesRepository, CarRepository carRepository, FleetRepository fleetRepository) {
        this.expensesRepository = expensesRepository;
        this.carRepository = carRepository;
        this.fleetRepository = fleetRepository;
    }

    @Transactional
    public CarExpensesResponseDTO getExpensesByCar(Long carId) {
        //de returnat in CarExpensesReponseDTO care are ca atribute List<Expenses>, double (totalul pe luna) si double (totalul pe an)
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("car not found"));
        List<Expenses> expensesList = expensesRepository.findByCar_Id(car.getId());

        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        double totalPerMonth = getTotalExpensesByCarPerMonth(carId);
        double totalPerYear = getTotalExpensesByCarPerYear(carId);
        //ce iti returneaza apelul de la linia 38 trebuie bagat in DTO la atributul List<Expnses>
        //in plus, mai bagi la atributele totalpeluna si totalpean ce iti returneaza apelul metodelor getTotalExpensesByCarPerMonth si getTotalExpensesByCarPerYear
        //returnezi obiectul de CarExpensesReponseDTO creat
        CarExpensesResponseDTO carExpensesResponseDTO = new CarExpensesResponseDTO();

        carExpensesResponseDTO.setExpensesList(expensesList);
        carExpensesResponseDTO.setTotalPerMonth(totalPerMonth);
        carExpensesResponseDTO.setTotalPerYear(totalPerYear);
        return carExpensesResponseDTO;
    }

    @Transactional
    public List<Expenses> getExpensesByDriver(Long driverId) {

        List<Car> driverCars = carRepository.findCarsByDriverId(driverId);
        return getExpensesByCarList(driverCars);
    }

    public List<Expenses> getExpensesByCarList(List<Car> driverCars) {
        return driverCars.stream()
                .flatMap(car -> car.getListExpenses().stream())
                .collect(Collectors.toList());
    }

    @Transactional
    public FleetExpensesResponseDTO getExpensesByFleet(Long fleetId) {
        /*Fleet fleet = fleetRepository.findById(fleetId).orElseThrow(() -> new ResourceNotFoundException("Fleet not found"));
        //toate masinile din flota
        List<Car> cars = fleet.getListCar();
        List<Expenses> allExpenses = new ArrayList<>();
        for (Car car : cars) {
            List<Expenses> carExpenses = expensesRepository.findByCar_Id(car.getId());
            allExpenses.addAll(carExpenses);
        }
        return allExpenses;*/

//        List<Car> fleetCars = carRepository.findCarsByFleetId(fleetId);
//        return getExpensesByCarList(fleetCars);
        List<Car> fleetCars = carRepository.findCarsByFleetId(fleetId);
        List<Expenses> fleetExpenses = getExpensesByCarList(fleetCars);
        FleetExpensesResponseDTO fleetExpensesResponseDTO = new FleetExpensesResponseDTO();
        fleetExpensesResponseDTO.setExpensesList(fleetExpenses);
        fleetExpensesResponseDTO.setTotalPerMonth(getTotalExpensesFleetPerMonth(fleetCars));
        fleetExpensesResponseDTO.setTotalPerYear(getTotalExpensesFleetPerYear(fleetCars));
        return fleetExpensesResponseDTO;


    }
@Transactional

    public Expenses addExpense(Long id, Expenses expenses) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
        expenses.setCar(car);

        return expensesRepository.save(expenses);
    }

    public void deleteExpense(Long id) {
        expensesRepository.deleteById(id);
    }

    @Transactional
    public Expenses updateExpense(Long id, Expenses expenses) {
        Expenses expense = expensesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        if (expenses.getName() != null) {
            expense.setName(expenses.getName());
        }

        if (expenses.getPrice() != null) {
            expense.setPrice(expenses.getPrice());
        }

        if (expenses.getDate() != null) {
            expense.setDate(expenses.getDate());
        }
        return expensesRepository.save(expense);
    }

    @Transactional
    public double getTotalExpensesByCarPerMonth(Long carId) {
        List<Expenses> expenses = expensesRepository.findByCar_Id(carId);
        //double totalExpenses = 0;
        //filter dupa conditita din if
        //map de la expense la expense.getPrice()
        //reduce ca sa aduni preturile
//        for (Expenses expense : expenses) {
//            if (expense.getDate().getYear() == year && expense.getDate().getMonthValue() == month) {
//                totalExpenses += expense.getPrice();
//            }
//        }
//        return totalExpenses;
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        return expenses.stream()
                .filter(expense -> expense.getDate().getYear() == year && expense.getDate().getMonthValue() == month)
                .map(Expenses :: getPrice)
                .reduce(0.0, Double::sum);
    }

    @Transactional

    public double getTotalExpensesByCarPerYear(Long carId) {
        List<Expenses> expenses = expensesRepository.findByCar_Id(carId);
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
//        double totalExpenses = 0;
//        for (Expenses expense : expenses) {
//            if (expense.getDate().getYear() == year) {
//                totalExpenses += expense.getPrice();
//            }
//        }
//        return totalExpenses;
        //  }
        return expenses.stream()
                .filter(expense -> expense.getDate().getYear() == year)
                .map(Expenses::getPrice)
                .reduce(0.0, Double::sum);
    }
@Transactional
    public double getTotalExpensesFleetPerMonth(List<Car> fleetCars) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();

        return fleetCars.stream()
                .mapToDouble(car -> getTotalExpensesByCarPerMonth(car.getId()))
                .sum();
    }
@Transactional
    public double getTotalExpensesFleetPerYear(List<Car> fleetCars) {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();

        return fleetCars.stream()
                .mapToDouble(car -> getTotalExpensesByCarPerYear(car.getId()))
                .sum();
    }


}
