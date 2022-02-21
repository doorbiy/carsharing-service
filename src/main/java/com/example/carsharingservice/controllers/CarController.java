package com.example.carsharingservice.controllers;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.car.CarCreateDto;
import com.example.carsharingservice.endpoints.CarEndpoint;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/car")
public class CarController {

    private final CarEndpoint carEndpoint;

    public CarController(CarEndpoint carEndpoint) {
        this.carEndpoint = carEndpoint;
    }

    @PostMapping("/save")
    public ResponseEntity<BaseResponse> save(@RequestBody CarCreateDto carCreateDto) {
        return ResponseEntity.ok().body(carEndpoint.save(carCreateDto));
    }

    @GetMapping("/getBy")
    public ResponseEntity<BaseResponse> getBy(@RequestParam(required = false) CarStatusEnum carStatus, @RequestParam(required = false) CarTypeEnum carType) {
        return ResponseEntity.ok().body(carEndpoint.getByTypeOrStatus(carStatus, carType));
    }
}
