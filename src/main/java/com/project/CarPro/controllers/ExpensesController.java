package com.project.CarPro.controllers;

import com.project.CarPro.dto.request.DocumentRequestDTO;
import com.project.CarPro.dto.response.CarExpensesResponseDTO;
import com.project.CarPro.dto.response.FleetExpensesResponseDTO;
import com.project.CarPro.model.Documents;
import com.project.CarPro.model.Expenses;
import com.project.CarPro.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
    private ExpensesService expensesService;

    @Autowired
    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarExpensesResponseDTO> getExpensesbyCar(@PathVariable Long carId) {
        CarExpensesResponseDTO carExpensesResponseDTO = expensesService.getExpensesByCar(carId);
        return ResponseEntity.ok(carExpensesResponseDTO);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Expenses>> getExpensesbyDriver(@PathVariable Long driverId) {
        List<Expenses> expensesList = expensesService.getExpensesByDriver(driverId);
        return ResponseEntity.ok(expensesList);
    }

    @GetMapping("/fleet/{fleetId}")
    public ResponseEntity<FleetExpensesResponseDTO> getExpensesbyFleet(@PathVariable Long fleetId) {
        FleetExpensesResponseDTO fleetExpensesResponseDTO = expensesService.getExpensesByFleet(fleetId);
        return ResponseEntity.ok(fleetExpensesResponseDTO);
    }


    @PostMapping("/add/{carId}")
    public ResponseEntity<Expenses> addExpense(@PathVariable Long carId,@RequestBody Expenses expenses){
        Expenses expenses1 = expensesService.addExpense(carId,expenses);
        return ResponseEntity.ok(expenses1);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        expensesService.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public Expenses updateExpense(@PathVariable Long id, @RequestBody Expenses expenses) {
        return expensesService.updateExpense(id, expenses);
    }
}
