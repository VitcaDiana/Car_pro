package com.project.CarPro.repositories;

import com.project.CarPro.model.Car;
import com.project.CarPro.model.CarDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriver,Long> {
    List<CarDriver> findByCar(Car car);

    List<CarDriver> findByCar_Id(Long carId);

    List<CarDriver> findByCar_IdAndDriver_Id(Long carId, Long driverId);
}
