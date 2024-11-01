package com.lcaohoanq.fucar.services;

import com.lcaohoanq.fucar.models.Car;
import com.lcaohoanq.fucar.repositories.CarRepository;
import com.lcaohoanq.fucar.repositories.ICarRepository;
import java.util.List;

public class CarService implements ICarService{

    private final ICarRepository carRepository;

    public CarService(String name){
        carRepository = new CarRepository(name);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        carRepository.delete(id);
    }

    @Override
    public void update(Car car) {
        carRepository.update(car);
    }

    @Override
    public Car findById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAllWithCarProducers() {
        return carRepository.findAllWithCarProducers();
    }
}
