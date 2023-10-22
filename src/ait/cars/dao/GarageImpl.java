package ait.cars.dao;

import ait.cars.model.Car;

import java.util.function.Predicate;

public class GarageImpl implements Garage {
    private Car[] cars;
    private int size;

    public GarageImpl(int capacity) {
        cars = new Car[capacity];
    }

    @Override
    public boolean addCar(Car car) {
        if (car == null || size == cars.length || findCarByRegNumber(car.getRegNumber()) != null) {
            return false;
        }
        cars[size++] = car;
        return true;
    }

    @Override
    public Car removeCar(String regNumber) {
        for (int i = 0; i < size; i++) {
            if (regNumber.equals(cars[i].getRegNumber())) {
                Car temp = cars[i];
                cars[i] = cars[--size];
                return temp;
            }

        }
        return null;
    }

    @Override
    public Car findCarByRegNumber(String regNumber) {
        for (int i = 0; i < size; i++) {
            if (cars[i].getRegNumber().equals(regNumber)) {
                return cars[i];
            }
        }
        return null;
    }

    @Override
    public Car[] findCarsByModel(String model) {
//        int count = 0;
//        for (int i = 0; i < size; i++) {
//            if (model.equals(cars[i].getModel())) {
//                count++;
//            }
//        }
//        Car[] res = new Car[count];
//        for (int i = 0, j = 0; j < res.length; i++) {
//            if (model.equals(cars[i].getModel())) {
//                res[j++] = cars[i];
//            }
//        }
//        return res;
        return findCarsByPredicate(car -> car.getModel().equals(model));
    }

    @Override
    public Car[] findCarsByCompany(String company) {
//        int count = 0;
//        for (int i = 0; i < size; i++) {
//            if (company.equals(cars[i].getCompany())) {
//                count++;
//            }
//        }
//        Car[] res = new Car[count];
//        for (int i = 0, j = 0; j < res.length; i++) {
//            if (company.equals(cars[i].getCompany())) {
//                res[j++] = cars[i];
//            }
//        }
//        return res;
        return findCarsByPredicate(car -> car.getCompany().equals(company));
    }

    @Override
    public Car[] findCarsByEngine(double min, double max) {
//        int count = 0;
//        for (int i = 0; i < size; i++) {
//            if (cars[i].getEngine() >= min && cars[i].getEngine() < max) {
//                count++;
//            }
//        }
//        Car[] res = new Car[count];
//        for (int i = 0, j = 0; j < res.length; i++) {
//            if (cars[i].getEngine() >= min && cars[i].getEngine() < max) {
//                res[j++] = cars[i];
//            }
//        }
//        return res;
        return findCarsByPredicate(car -> car.getEngine() >= min && car.getEngine() < max);
    }

    @Override
    public Car[] findCarsByColor(String color) {
//        int count = 0;
//        for (int i = 0; i < size; i++) {
//            if (color.equals(cars[i].getColor())) {
//                count++;
//            }
//        }
//        Car[] res = new Car[count];
//        for (int i = 0, j = 0; j < res.length; i++) {
//            if (color.equals(cars[i].getColor())) {
//                res[j++] = cars[i];
//            }
//        }
//        return res;
        return findCarsByPredicate(car -> car.getColor().equals(color));
    }

    private Car[] findCarsByPredicate(Predicate<Car> predicate) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(cars[i])) {
                count++;
            }
        }
        Car[] res = new Car[count];
        for (int i = 0, j = 0; j < res.length; i++) {
            if (predicate.test(cars[i])) {
                res[j++] = cars[i];
            }
        }

        return res;
    }
}
