package com.lcaohoanq.fucar.services;

import com.lcaohoanq.fucar.models.CarRental;
import com.lcaohoanq.fucar.repositories.CarRentalRepository;
import com.lcaohoanq.fucar.repositories.ICarRentalRepository;
import java.util.List;

public class CarRentalService implements ICarRentalService{

    private final ICarRentalRepository carRentalRepository;

    public CarRentalService(String name){
        carRentalRepository = new CarRentalRepository(name);
    }

    @Override
    public void save(CarRental car) {
        carRentalRepository.save(car);
    }

    @Override
    public List<CarRental> findAll() {
        return carRentalRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        carRentalRepository.delete(id);
    }

    @Override
    public void update(CarRental car) {
        carRentalRepository.update(car);
    }

    @Override
    public CarRental findById(Integer id) {
        return carRentalRepository.findById(id);
    }
}
