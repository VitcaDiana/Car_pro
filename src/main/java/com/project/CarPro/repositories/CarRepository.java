package com.project.CarPro.repositories;

import com.project.CarPro.model.Car;
import com.project.CarPro.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findCarsByFleetId(Long fleetId);


    @Query("SELECT cd.car FROM CarDriver cd WHERE cd.driver.id = :driverId")
    List<Car> findCarsByDriverId(Long driverId);
}
