package com.example.carsharingservice.mappers;

import com.example.carsharingservice.dtos.order.OrderResponse;
import com.example.carsharingservice.entities.Order;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class OrderMapper {

    private final UserMapper userMapper;

    private final CarMapper carMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public OrderMapper(UserMapper userMapper, CarMapper carMapper) {
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    public OrderResponse mapToOrderResponseDto(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setUser(userMapper.mapToUserResponse(order.getUser()));
        orderResponse.setCar(carMapper.mapToCarResponse(order.getCar()));
        orderResponse.setStartDate(formatter.format(order.getStartDate()));
        orderResponse.setEndDate(formatter.format(order.getEndDate()));
        orderResponse.setSum(order.getSum());
        orderResponse.setKm(order.getKm());

        return orderResponse;
    }
}
