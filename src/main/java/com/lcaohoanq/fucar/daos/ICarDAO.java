package com.lcaohoanq.fucar.daos;

import com.lcaohoanq.fucar.models.Car;
import com.lcaohoanq.fucar.models.Customer;
import java.util.List;

public interface ICarDAO {
    void save(Car car);
    List<Car> findAll();
    void delete(Integer id);
    void update(Car car);
    Car findById(Integer id);
}