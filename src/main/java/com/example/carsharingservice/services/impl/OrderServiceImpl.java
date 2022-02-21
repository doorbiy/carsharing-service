package com.example.carsharingservice.services.impl;

import com.example.carsharingservice.entities.Car;
import com.example.carsharingservice.entities.Order;
import com.example.carsharingservice.entities.User;
import com.example.carsharingservice.repositories.OrderRepository;
import com.example.carsharingservice.services.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(User user, Car car, String startDate, String endDate, double totalSum, Double km) {
        Order order = new Order();

        order.setUser(user);
        order.setCar(car);
        order.setStartDate(LocalDate.parse(startDate, formatter));
        order.setEndDate(LocalDate.parse(endDate, formatter));
        order.setSum(totalSum);
        order.setKm(km);

        return orderRepository.save(order);
    }
}
