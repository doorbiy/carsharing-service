package com.example.carsharingservice.services.impl;

import com.example.carsharingservice.dtos.fuel.FuelCreateDto;
import com.example.carsharingservice.entities.Fuel;
import com.example.carsharingservice.enums.FuelTypeEnum;
import com.example.carsharingservice.repositories.FuelRepository;
import com.example.carsharingservice.services.FuelService;
import org.springframework.stereotype.Service;

@Service
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;

    public FuelServiceImpl(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Override
    public Fuel save(FuelCreateDto fuelCreateDto) {
        Fuel fuel = new Fuel();

        fuel.setPrice(fuelCreateDto.getPrice());
        fuel.setFuelType(fuelCreateDto.getFuelType());
        fuel.setActive(fuelCreateDto.isActive());

        return fuelRepository.save(fuel);
    }

    @Override
    public double findFuelPriceByType(FuelTypeEnum fuelType) {

        return fuelRepository.findByFuelType(fuelType).getPrice();
    }
}
