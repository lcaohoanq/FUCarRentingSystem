package com.lcaohoanq.fucar.services;

import com.lcaohoanq.fucar.models.CarRental;
import java.util.List;

public interface ICarRentalService {
    void save(CarRental car);
    List<CarRental> findAll();
    void delete(Integer id);
    void update(CarRental car);
    CarRental findById(Integer id);
}
