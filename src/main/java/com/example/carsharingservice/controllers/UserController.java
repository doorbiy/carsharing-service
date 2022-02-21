package com.example.carsharingservice.controllers;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.user.UserCreateDto;
import com.example.carsharingservice.endpoints.UserEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserEndpoint userEndpoint;

    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<BaseResponse> save(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok().body(userEndpoint.saveUser(userCreateDto));
    }
}
