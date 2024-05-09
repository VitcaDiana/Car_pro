package com.project.CarPro.repositories;

import com.project.CarPro.model.Car;
import com.project.CarPro.model.Documents;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Documents,Long> {
    List<Documents> findByExpirationDateBetween(LocalDate start, LocalDate end);

    List<Documents> findByCar(Car car);
}
