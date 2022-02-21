package com.example.carsharingservice.services;

import com.example.carsharingservice.entities.Car;
import com.example.carsharingservice.entities.Order;
import com.example.carsharingservice.entities.User;

public interface OrderService {

    Order save(User mapToUser, Car mapToCar, String startDate, String endDate, double totalSum, Double km);
}
