package com.example.carsharingservice.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {

    private String userPhoneNumber;
    private Long carId;
    private String startDate;
    private String endDate;
    private Double km;
}
