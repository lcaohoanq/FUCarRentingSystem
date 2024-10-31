package com.lcaohoanq.fucar.repositories;

import com.lcaohoanq.fucar.models.Car;
import java.util.List;

public interface ICarRepository {

    void save(Car car);

    List<Car> findAll();

    void delete(Integer id);

    void update(Car car);

    Car findById(Integer id);
}
