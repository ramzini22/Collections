package org.zinnatullin;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetTest extends Test{
    private final int N_T = ARRAY_SIZE;//   NUMBERS_OF_TESTS
    private TreeSet<Car> cars;


    @BeforeEach
    void setUp() {
        cars = new TreeSet<>();
        Car car = Mockito.mock(Car.class);
        for (int i = 0; i < N_T; i++) {
            cars.add(new Car("A"+i, i));
        }
    }

    @org.junit.jupiter.api.Test
    void add() {
        int size=cars.size();
        Car car1=Mockito.mock(Car.class);
        cars.add(car1);
        cars.add(car1);

        assertEquals(size+1 , cars.size());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        TreeSet<Car> arraySet2=cars;
        Car car=new Car("BMV", 12);

        assertEquals(cars, arraySet2);

        cars.add(car);

        assertEquals(cars, arraySet2);

        arraySet2.add(car);

        assertEquals(cars, arraySet2);
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        int hash=cars.hashCode();
        cars.add(Mockito.mock(Car.class));
        assertEquals(hash, cars.hashCode());
    }

    @org.junit.jupiter.api.Test
    void delete() {
        int size = cars.size();

        Car first=new Car("Del", 1);
        Car second=new Car("Del", 2);

        cars.add(first);
        cars.add(second);
        cars.delete(first);
        cars.delete(second);

        assertEquals(size , cars.size());
    }

    @org.junit.jupiter.api.Test
    void size() {
        int size = cars.size();
        for (int i = 0; i < 20; i++)
            cars.add(Mockito.mock(Car.class));
        assertEquals(cars.size(), size + 20);
    }

    @org.junit.jupiter.api.Test
    void clear() {
        cars.clear();
        assertEquals(cars.size(), 0);
        assertEquals(cars.add(Mockito.mock(Car.class)), true);
    }
}