package com.example.carsharingservice.endpoints.impl;

import com.example.carsharingservice.dtos.base.BaseResponse;
import com.example.carsharingservice.dtos.car.CarResponse;
import com.example.carsharingservice.dtos.order.OrderCreateDto;
import com.example.carsharingservice.dtos.order.OrderResponse;
import com.example.carsharingservice.dtos.order.RentCalculationRequestDto;
import com.example.carsharingservice.dtos.order.RentCalculationResponseDto;
import com.example.carsharingservice.dtos.user.UserResponse;
import com.example.carsharingservice.endpoints.OrderEndpoint;
import com.example.carsharingservice.enums.FuelTypeEnum;
import com.example.carsharingservice.enums.RestStatus;
import com.example.carsharingservice.mappers.BaseMapper;
import com.example.carsharingservice.mappers.CarMapper;
import com.example.carsharingservice.mappers.OrderMapper;
import com.example.carsharingservice.mappers.UserMapper;
import com.example.carsharingservice.services.CarService;
import com.example.carsharingservice.services.FuelService;
import com.example.carsharingservice.services.OrderService;
import com.example.carsharingservice.services.UserService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class OrderEndpointImpl implements OrderEndpoint {

    private final OrderService orderService;

    private final CarService carService;

    private final FuelService fuelService;

    private final UserService userService;

    private final BaseMapper baseMapper;

    private final CarMapper carMapper;

    private final UserMapper userMapper;

    private final OrderMapper orderMapper;

    public OrderEndpointImpl(OrderService orderService, CarService carService, FuelService fuelService, UserService userService, BaseMapper baseMapper, CarMapper carMapper, UserMapper userMapper, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.carService = carService;
        this.fuelService = fuelService;
        this.userService = userService;
        this.baseMapper = baseMapper;
        this.carMapper = carMapper;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public BaseResponse rentCalculation(RentCalculationRequestDto rentRequest) {

        try {
            CarResponse carResponse = carMapper.mapToCarResponse(carService.getById(rentRequest.getCarId()));

            if (Objects.isNull(carResponse)) {

                return baseMapper.mapToBaseResponse(
                        "Машина с такой ID нет в базе!",
                        null,
                        RestStatus.FAILED
                );
            }

            if (Objects.isNull(rentRequest.getStartDate())
                    || Objects.isNull(rentRequest.getEndDate())
                    || Objects.isNull(rentRequest.getKm())) {

                return baseMapper.mapToBaseResponse(
                        "Вы не заполнили все поля!",
                        null,
                        RestStatus.FAILED
                );
            }

            String validateDate = "^\\d{4}-\\d{2}-\\d{2}";

            if (!Pattern.matches(validateDate, rentRequest.getStartDate())
                    || !Pattern.matches(validateDate, rentRequest.getEndDate())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректную дату!",
                        null,
                        RestStatus.FAILED
                );
            }

            int days = calculateDays(rentRequest.getStartDate(), rentRequest.getEndDate());

            double totalSum = calculateRentSum(
                    days,
                    rentRequest.getKm(),
                    carResponse.getPricePerDay(),
                    carResponse.getFuelPerKm(),
                    carResponse.getFuelType()
            );

            RentCalculationResponseDto rentResponseDto = new RentCalculationResponseDto();

            rentResponseDto.setCar(carResponse);
            rentResponseDto.setStartDate(rentRequest.getStartDate());
            rentResponseDto.setEndDate(rentRequest.getEndDate());
            rentResponseDto.setSum(totalSum);
            rentResponseDto.setKm(rentRequest.getKm());

            return baseMapper.mapToBaseResponse(
                    "Стоимость аредны!",
                    rentResponseDto,
                    RestStatus.SUCCESS
            );

        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Ошибка системы!",
                    null,
                    RestStatus.FAILED
            );
        }
    }

    @Override
    public BaseResponse provide(OrderCreateDto orderCreateDto) {

        try {

            if (!Pattern.matches("^(996)?\\s\\d{3}-\\d{3}-\\d{3}", orderCreateDto.getUserPhoneNumber())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректный номер телефона!",
                        null,
                        RestStatus.FAILED
                );
            }

            UserResponse userResponse = userMapper.mapToUserResponse(userService.findByPhoneNumber(orderCreateDto.getUserPhoneNumber()));

            if (Objects.isNull(userResponse)) {

                return baseMapper.mapToBaseResponse(
                        "Пользователя с введенным номером нет в базе, пройдите регистрацию!",
                        null,
                        RestStatus.FAILED
                );
            }

            CarResponse carResponse = carMapper.mapToCarResponse(carService.getById(orderCreateDto.getCarId()));

            if (Objects.isNull(carResponse)) {

                return baseMapper.mapToBaseResponse(
                        "Машина с такой ID нет в базе!",
                        null,
                        RestStatus.FAILED
                );
            }

            String validateDate = "^\\d{4}-\\d{2}-\\d{2}";

            if (!Pattern.matches(validateDate, orderCreateDto.getStartDate())
                    || !Pattern.matches(validateDate, orderCreateDto.getEndDate())) {

                return baseMapper.mapToBaseResponse(
                        "Вы ввели некорректную дату!",
                        null,
                        RestStatus.FAILED
                );
            }

            int days = calculateDays(orderCreateDto.getStartDate(), orderCreateDto.getEndDate());

            double totalSum = calculateRentSum(
                    days,
                    orderCreateDto.getKm(),
                    carResponse.getPricePerDay(),
                    carResponse.getFuelPerKm(),
                    carResponse.getFuelType()
            );

            OrderResponse orderResponse = orderMapper.mapToOrderResponseDto(
                    orderService.save(
                            userMapper.mapToUser(userResponse),
                            carMapper.mapToCar(carResponse),
                            orderCreateDto.getStartDate(),
                            orderCreateDto.getEndDate(),
                            totalSum,
                            orderCreateDto.getKm()
                    )
            );

            return baseMapper.mapToBaseResponse(
                    "Заказ успешно оформлен!",
                    orderResponse,
                    RestStatus.SUCCESS
            );
        } catch (Exception e) {

            return baseMapper.mapToBaseResponse(
                    "Произошла ошибка! Обратитесь к системному аднимнистратору!",
                    null,
                    RestStatus.FAILED
            );
        }
    }

    private double calculateRentSum(int days, double km, double pricePerDay, double fuelPer100Km, FuelTypeEnum fuelType) {

        double sumByDays = days * pricePerDay;
        double fuelPrice = ((fuelPer100Km / 100.0) * km) * fuelService.findFuelPriceByType(fuelType);

        return sumByDays + fuelPrice;
    }

    private int calculateDays(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        Period period = Period.between(start, end);

        System.out.println(period.getDays());
        return period.getDays();
    }
}
