package com.example.carsharingservice.repositories;

import com.example.carsharingservice.entities.Car;
import com.example.carsharingservice.enums.CarStatusEnum;
import com.example.carsharingservice.enums.CarTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByCarStatus(CarStatusEnum carStatus);

    List<Car> findAllByCarType(CarTypeEnum carType);

    List<Car> findAllByCarStatusAndCarType(CarStatusEnum carStatus, CarTypeEnum carType);
}
