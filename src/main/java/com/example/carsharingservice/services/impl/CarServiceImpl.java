package com.example.carsharingservice.services.impl;

import com.example.carsharingservice.dtos.car.CarCreateDto;
import com.example.carsharingservice.entities.Car;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;
import com.example.carsharingservice.repositories.CarRepository;
import com.example.carsharingservice.services.CarService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(CarCreateDto carCreateDto) {

        Car car = new Car();

        car.setManufacturer(carCreateDto.getManufacturer());
        car.setModel(carCreateDto.getModel());
        car.setVolume(carCreateDto.getVolume());
        car.setColor(carCreateDto.getColor());
        car.setCarStatus(carCreateDto.getCarStatus());
        car.setCarType(carCreateDto.getCarType());
        car.setPricePerDay(carCreateDto.getPricePerDay());
        car.setFuelPerKm(carCreateDto.getFuelPerKm());
        car.setFuelType(carCreateDto.getFuelType());

        return carRepository.save(car);
    }

    @Override
    public List<Car> getByStatusOrType(CarStatusEnum carStatus, CarTypeEnum carType) {

        if (Objects.nonNull(carStatus) && Objects.isNull(carType)) {

            return carRepository.findAllByCarStatus(carStatus)
                    .stream()
                    .sorted(Comparator.comparing(Car::getId))
                    .collect(Collectors.toList());
        }

        if (Objects.nonNull(carType) && Objects.isNull(carStatus)) {

            return carRepository.findAllByCarType(carType)
                    .stream()
                    .sorted(Comparator.comparing(Car::getId))
                    .collect(Collectors.toList());
        }

        if (Objects.nonNull(carStatus) && Objects.nonNull(carType)) {

            return carRepository.findAllByCarStatusAndCarType(carStatus, carType)
                    .stream()
                    .sorted(Comparator.comparing(Car::getId))
                    .collect(Collectors.toList());
        }

        return carRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Car::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Car getById(Long carId) {
        return carRepository.getById(carId);
    }
}
