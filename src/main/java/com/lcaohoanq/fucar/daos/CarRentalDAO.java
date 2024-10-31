package com.lcaohoanq.fucar.daos;

import com.lcaohoanq.fucar.models.Account;
import com.lcaohoanq.fucar.models.CarRental;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CarRentalDAO implements ICarRentalDAO{

    private final SessionFactory sessionFactory;

    public CarRentalDAO(String persistenceName) {
        Configuration cf = new Configuration();
        cf = cf.configure(persistenceName);
        sessionFactory = cf.buildSessionFactory();
    }

    @Override
    public void save(CarRental carRental) {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try (session) {
            t = session.beginTransaction();
            session.persist(carRental);
            t.commit();
            System.out.println("Successfully saved");
        } catch (Exception e) {
            assert t != null;
            t.rollback();
            System.out.println("Error " + e.getMessage());
            // TODO: handle exception
        }
//			sessionFactory.close();
    }

    @Override
    public List<CarRental> findAll() {
        List<CarRental> carRentals = null;
        Session session = sessionFactory.openSession();
        try {
            carRentals = session.createQuery("from CarRental", CarRental.class).list();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return carRentals;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try (session) {
            t = session.getTransaction();
            t.begin();
            Account account = session.get(Account.class, id);
            session.remove(account);
            t.commit();
            System.out.println("Successfully Delete");
        } catch (Exception e) {
            assert t != null;
            t.rollback();
            System.out.println("Error " + e.getMessage());
        }
//			sessionFactory.close();
    }

    @Override
    public void update(CarRental carRental) {
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try (session) {
            t = session.beginTransaction();
            session.merge(carRental);
            t.commit();
            System.out.println("Update Success");
        } catch (Exception e) {
            assert t != null;
            t.rollback();
            System.out.println("Error " + e.getMessage());
        }
//			sessionFactory.close();
    }

    @Override
    public CarRental findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CarRental.class, id);
        }
    }
}
