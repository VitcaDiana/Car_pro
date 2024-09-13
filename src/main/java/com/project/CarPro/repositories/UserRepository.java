package com.project.CarPro.repositories;

import com.project.CarPro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u.enodeUserId FROM User u WHERE u.enodeUserId IS NOT NULL")
    List<String> findAllEnodeUserIds();
}
