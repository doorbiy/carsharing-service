package com.example.carsharingservice.endpoints.impl;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.fuel.FuelCreateDto;
import com.example.carsharingservice.dtos.fuel.FuelResponse;
import com.example.carsharingservice.endpoints.FuelEndpoint;
import com.example.carsharingservice.enums.RestStatus;
import com.example.carsharingservice.mappers.BaseMapper;
import com.example.carsharingservice.mappers.FuelMapper;
import com.example.carsharingservice.services.FuelService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FuelEndpointImpl implements FuelEndpoint {

    private final FuelService fuelService;

    private final BaseMapper baseMapper;

    private final FuelMapper fuelMapper;

    public FuelEndpointImpl(FuelService fuelService, BaseMapper baseMapper, FuelMapper fuelMapper) {
        this.fuelService = fuelService;
        this.baseMapper = baseMapper;
        this.fuelMapper = fuelMapper;
    }

    @Override
    public BaseResponse save(FuelCreateDto fuelCreateDto) {

        try {
            if (Objects.isNull(fuelCreateDto)
                    || Objects.isNull(fuelCreateDto.getPrice())
                    || Objects.isNull(fuelCreateDto.getFuelType())) {

                return baseMapper.mapToBaseResponse(
                        "Вы не передали цену или тип топлива!",
                        null,
                        RestStatus.FAILED
                );
            }

            FuelResponse fuelResponse = fuelMapper.mapToFuelResponse(fuelService.save(fuelCreateDto));

            return baseMapper.mapToBaseResponse(
                    "Тип топлива успешно сохранен!",
                    fuelResponse,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка. Сохранить не удалось.",
                    null,
                    RestStatus.FAILED
            );
        }
    }
}
