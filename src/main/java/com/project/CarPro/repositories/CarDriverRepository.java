package com.project.CarPro.repositories;

import com.project.CarPro.model.CarDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriver,Long> {
}
