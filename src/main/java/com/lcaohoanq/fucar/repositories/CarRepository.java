package com.lcaohoanq.fucar.repositories;

import com.lcaohoanq.fucar.daos.CarDAO;
import com.lcaohoanq.fucar.daos.ICarDAO;
import com.lcaohoanq.fucar.models.Car;
import java.util.List;

public class CarRepository implements ICarRepository {

    private ICarDAO cardao;

    public CarRepository(String jpaName) {
        cardao = new CarDAO(jpaName);
    }

    @Override
    public void save(Car customer) {
        cardao.save(customer);
    }

    @Override
    public Car findById(Integer id) {
        return cardao.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return cardao.findAll();
    }

    @Override
    public void delete(Integer id) {
        cardao.delete(id);
    }

    @Override
    public void update(Car customer) {
        cardao.update(customer);
    }
}
