package com.project.CarPro.repositories;

import com.project.CarPro.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findByCar_Id(Long carId);


}
