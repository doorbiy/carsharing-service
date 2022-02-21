package com.example.carsharingservice.endpoints;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.car.CarCreateDto;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;

public interface CarEndpoint {

    BaseResponse save(CarCreateDto carCreateDto);

    BaseResponse getByTypeOrStatus(CarStatusEnum carStatus, CarTypeEnum carType);
}
