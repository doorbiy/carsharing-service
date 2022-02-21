package com.example.carsharingservice.services;

import com.example.carsharingservice.dtos.fuel.FuelCreateDto;
import com.example.carsharingservice.entities.Fuel;
import com.example.carsharingservice.enums.FuelTypeEnum;

public interface FuelService {

    Fuel save(FuelCreateDto fuelCreateDto);

    double findFuelPriceByType(FuelTypeEnum fuelType);
}
