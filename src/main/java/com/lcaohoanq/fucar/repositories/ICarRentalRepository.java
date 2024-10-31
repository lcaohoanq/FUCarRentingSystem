package com.lcaohoanq.fucar.repositories;

import com.lcaohoanq.fucar.models.CarProducer;
import com.lcaohoanq.fucar.models.CarRental;
import java.util.List;

public interface ICarRentalRepository {
    void save(CarRental car);
    List<CarRental> findAll();
    void delete(Integer id);
    void update(CarRental car);
    CarRental findById(Integer id);
}
