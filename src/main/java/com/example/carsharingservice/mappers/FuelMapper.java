package com.example.carsharingservice.mappers;

import com.example.carsharingservice.dtos.fuel.FuelResponse;
import com.example.carsharingservice.entities.Fuel;
import org.springframework.stereotype.Component;

@Component
public class FuelMapper {

    public FuelResponse mapToFuelResponse(Fuel fuel) {
        FuelResponse fuelResponse = new FuelResponse();

        fuelResponse.setId(fuel.getId());
        fuelResponse.setPrice(fuel.getPrice());
        fuelResponse.setFuelType(fuel.getFuelType());
        fuelResponse.setActive(fuel.isActive());

        return fuelResponse;
    }
}
