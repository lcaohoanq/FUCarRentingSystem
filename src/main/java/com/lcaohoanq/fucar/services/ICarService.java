package com.lcaohoanq.fucar.services;

import com.lcaohoanq.fucar.models.Car;
import java.util.List;

public interface ICarService {
    void save(Car car);

    List<Car> findAll();

    void delete(Integer id);

    void update(Car car);

    Car findById(Integer id);

    List<Car> findAllWithCarProducers();
}
