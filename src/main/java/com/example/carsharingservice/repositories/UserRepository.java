package com.example.carsharingservice.repositories;

import com.example.carsharingservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String email);

    User getByPhoneNumber(String userPhoneNumber);
}
