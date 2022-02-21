package com.example.carsharingservice.endpoints.impl;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.car.CarCreateDto;
import com.example.carsharingservice.dtos.car.CarResponse;
import com.example.carsharingservice.dtos.user.UserResponse;
import com.example.carsharingservice.endpoints.CarEndpoint;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;
import com.example.carsharingservice.enums.RestStatus;
import com.example.carsharingservice.mappers.BaseMapper;
import com.example.carsharingservice.mappers.CarMapper;
import com.example.carsharingservice.services.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarEndpointImpl implements CarEndpoint {

    private final CarService carService;

    private final BaseMapper baseMapper;

    private final CarMapper carMapper;

    public CarEndpointImpl(CarService carService, BaseMapper baseMapper, CarMapper carMapper) {
        this.carService = carService;
        this.baseMapper = baseMapper;
        this.carMapper = carMapper;
    }

    @Override
    public BaseResponse save(CarCreateDto carCreateDto) {

        try {

            if (Objects.isNull(carCreateDto)
                    || Objects.isNull(carCreateDto.getVolume())
                    || Objects.isNull(carCreateDto.getPricePerDay())
                    || Objects.isNull(carCreateDto.getFuelPerKm())
                    || carCreateDto.getManufacturer().isBlank()
                    || carCreateDto.getModel().isBlank()
                    || carCreateDto.getColor().isBlank()
                    || carCreateDto.getCarStatus().toString().isBlank()
                    || carCreateDto.getCarType().toString().isBlank()
                    || carCreateDto.getFuelType().toString().isBlank()) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели не все данные!",
                        null,
                        RestStatus.FAILED
                );
            }

            CarResponse carResponse = carMapper.mapToCarResponse(carService.save(carCreateDto));

            return baseMapper.mapToBaseResponse(
                    "Машина успешно сохранена!",
                    carResponse,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка на стороне сервера",
                    null,
                    RestStatus.FAILED
            );
        }
    }

    @Override
    public BaseResponse getByTypeOrStatus(CarStatusEnum carStatus, CarTypeEnum carType) {

        try {
            List<CarResponse> carResponseList = carMapper.mapToCarResponseList(
                    carService.getByStatusOrType(carStatus, carType)
            );

            return baseMapper.mapToBaseResponse(
                    "Список машин по вашим критериям!",
                    carResponseList,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка. Возможно вы ввели некорректные данные!",
                    null,
                    RestStatus.FAILED
            );
        }
    }
}
