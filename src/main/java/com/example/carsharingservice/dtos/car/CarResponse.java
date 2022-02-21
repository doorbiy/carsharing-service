package com.example.carsharingservice.dtos.car;

import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;
import com.example.carsharingservice.enums.FuelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;
    private String manufacturer;
    private String model;
    private Double volume;
    private String color;
    private CarStatusEnum carStatus;
    private CarTypeEnum carType;
    private Double pricePerDay;
    private Double fuelPerKm;
    private FuelTypeEnum fuelType;
}
