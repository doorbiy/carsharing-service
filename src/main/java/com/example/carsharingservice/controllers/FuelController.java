package com.example.carsharingservice.controllers;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.fuel.FuelCreateDto;
import com.example.carsharingservice.endpoints.FuelEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fuel")
public class FuelController {

    private final FuelEndpoint fuelEndpoint;

    public FuelController(FuelEndpoint fuelEndpoint) {
        this.fuelEndpoint = fuelEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<BaseResponse> save(@RequestBody FuelCreateDto fuelCreateDto) {
        return ResponseEntity.ok().body(fuelEndpoint.save(fuelCreateDto));
    }
}
