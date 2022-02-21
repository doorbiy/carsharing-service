package com.example.carsharingservice.dtos.fuel;

import com.example.carsharingservice.enums.FuelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuelCreateDto {

    private Double price;
    private FuelTypeEnum fuelType;
    private boolean active;
}
