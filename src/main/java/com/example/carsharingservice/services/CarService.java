package com.example.carsharingservice.services;

import com.example.carsharingservice.dtos.car.CarCreateDto;
import com.example.carsharingservice.dtos.car.CarResponse;
import com.example.carsharingservice.entities.Car;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;

import java.util.List;

public interface CarService {

    Car save(CarCreateDto carCreateDto);

    List<Car> getByStatusOrType(CarStatusEnum carStatus, CarTypeEnum carType);

    Car getById(Long carId);
}
