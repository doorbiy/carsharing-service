package com.example.carsharingservice.controllers;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.order.OrderCreateDto;
import com.example.carsharingservice.dtos.order.RentCalculationRequestDto;
import com.example.carsharingservice.endpoints.OrderEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderEndpoint orderEndpoint;

    public OrderController(OrderEndpoint orderEndpoint) {
        this.orderEndpoint = orderEndpoint;
    }

    @GetMapping("/rentCalculation")
    public ResponseEntity<BaseResponse> rentCalculation(@RequestBody RentCalculationRequestDto rentRequest) {
        return ResponseEntity.ok().body(orderEndpoint.rentCalculation(rentRequest));
    }

    @PostMapping("/provide")
    public ResponseEntity<BaseResponse> provide(@RequestBody OrderCreateDto orderCreateDto) {
        return ResponseEntity.ok().body(orderEndpoint.provide(orderCreateDto));
    }
}
