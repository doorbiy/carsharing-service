package com.example.carsharingservice.dtos.fuel;

import com.example.carsharingservice.enums.FuelTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuelResponse {

    private Long id;
    private Double price;
    private FuelTypeEnum fuelType;
    private boolean active;
}
