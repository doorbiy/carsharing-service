package com.example.carsharingservice.endpoints;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.fuel.FuelCreateDto;

public interface FuelEndpoint {

    BaseResponse save(FuelCreateDto fuelCreateDto);
}
