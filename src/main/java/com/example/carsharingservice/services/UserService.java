package com.example.carsharingservice.services;

import com.example.carsharingservice.dtos.user.UserCreateDto;
import com.example.carsharingservice.entities.User;

public interface UserService {

    boolean existsByPhoneNumber(String email);

    User save(UserCreateDto userCreateDto);

    User findByPhoneNumber(String userPhoneNumber);
}
