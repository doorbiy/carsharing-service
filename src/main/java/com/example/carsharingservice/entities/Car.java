package com.example.carsharingservice.entities;

import com.example.carsharingservice.entities.base.BaseEntity;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;
import com.example.carsharingservice.enums.FuelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "volume", nullable = false)
    private Double volume;

    @Column(name = "color", nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "carStatus", nullable = false)
    private CarStatusEnum carStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "carType", nullable = false)
    private CarTypeEnum carType;

    @Column(name = "pricePerDay", nullable = false)
    private Double pricePerDay;

    @Column(name = "fuelPerKm", nullable = false)
    private Double fuelPerKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuelType", nullable = false)
    private FuelTypeEnum fuelType;
}
