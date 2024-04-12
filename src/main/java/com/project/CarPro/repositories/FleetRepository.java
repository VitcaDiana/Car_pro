package com.project.CarPro.repositories;

import com.project.CarPro.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends JpaRepository<Fleet,Long> {
}
