package com.example.carsharingservice.endpoints;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.order.OrderCreateDto;
import com.example.carsharingservice.dtos.order.RentCalculationRequestDto;

public interface OrderEndpoint {

    BaseResponse rentCalculation(RentCalculationRequestDto rentRequest);

    BaseResponse provide(OrderCreateDto orderCreateDto);
}
