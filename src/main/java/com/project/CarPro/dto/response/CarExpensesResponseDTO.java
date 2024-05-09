package com.project.CarPro.dto.response;

import com.project.CarPro.model.Expenses;

import java.util.List;

public class CarExpensesResponseDTO {

    private List<Expenses> expensesList;
    private double totalPerMonth;
    private double totalPerYear;

    public CarExpensesResponseDTO(List<Expenses> expensesList, double totalPerMonth, double totalPerYear) {
        this.expensesList = expensesList;
        this.totalPerMonth = totalPerMonth;
        this.totalPerYear = totalPerYear;
    }

    public CarExpensesResponseDTO() {
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }

    public double getTotalPerMonth() {
        return totalPerMonth;
    }

    public void setTotalPerMonth(double totalPerMonth) {
        this.totalPerMonth = totalPerMonth;
    }

    public double getTotalPerYear() {
        return totalPerYear;
    }

    public void setTotalPerYear(double totalPerYear) {
        this.totalPerYear = totalPerYear;
    }
}
