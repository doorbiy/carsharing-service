package com.example.carsharingservice.repositories;

import com.example.carsharingservice.entities.Fuel;
import com.example.carsharingservice.enums.FuelTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

    Fuel findByFuelType(FuelTypeEnum fuelType);
}
