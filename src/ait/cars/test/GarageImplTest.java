package ait.cars.test;

import ait.cars.dao.Garage;
import ait.cars.dao.GarageImpl;
import ait.cars.model.Car;

import static org.junit.jupiter.api.Assertions.*;

class GarageImplTest {
    private Garage garage;
    Car[] cars;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        garage = new GarageImpl(5);
        cars = new Car[4];
        cars[0] = new Car("1000", "Model1", "BMW", 1.4, "White");
        cars[1] = new Car("2000", "Model2", "BMW", 2.5, "Black");
        cars[2] = new Car("3000", "Model3", "Tesla", 1.5, "White");
        cars[3] = new Car("4000", "Model4", "Tesla", 3.0, "Black");
        for (int i = 0; i < cars.length; i++) {
            garage.addCar(cars[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void addCar() {
        assertFalse(garage.addCar(null));
        assertFalse(garage.addCar(cars[1]));
        Car car = new Car("5000", "Model5", "Tesla", 2.4, "Black");
        assertTrue(garage.addCar(car));
        car = new Car("6000", "Model5", "Tesla", 2.4, "White");
        assertFalse(garage.addCar(car));
    }

    @org.junit.jupiter.api.Test
    void removeCar() {
        assertEquals(cars[0], garage.removeCar("1000"));
        assertNull(garage.removeCar("1000"));
    }

    @org.junit.jupiter.api.Test
    void findCarByRegNumber() {
        Car car = garage.findCarByRegNumber("2000");
        assertEquals(cars[1], car);
        assertEquals(cars[3], garage.findCarByRegNumber(new String("4000")));
    }

    @org.junit.jupiter.api.Test
    void findCarsByModel() {
        Car[] expecteds = { cars[1] };
        Car[] actuals = garage.findCarsByModel(new String("Model2"));
        assertArrayEquals(expecteds, actuals);
    }

    @org.junit.jupiter.api.Test
    void findCarsByCompany() {
        Car[] expecteds = { cars[0], cars[1] };
        Car[] actuals = garage.findCarsByCompany("BMW");
        assertArrayEquals(expecteds, actuals);
    }

    @org.junit.jupiter.api.Test
    void findCarsByEngine() {
        Car[] expecteds = { cars[1], cars[3] };
        Car[] actuals = garage.findCarsByEngine(2.0, 3.5);
        assertArrayEquals(expecteds, actuals);
    }

    @org.junit.jupiter.api.Test
    void findCarsByColor() {
        Car[] expecteds = { cars[0], cars[2] };
        Car[] actuals = garage.findCarsByColor(new String("White"));
        assertArrayEquals(expecteds, actuals);
        assertArrayEquals(new Car[0], garage.findCarsByColor("Grey"));
    }
}