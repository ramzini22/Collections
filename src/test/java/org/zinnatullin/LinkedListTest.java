package org.zinnatullin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest extends org.zinnatullin.Test {
    private final int N_T = ARRAY_SIZE;//   NUMBERS_OF_TESTS
    private LinkedList<Car> cars;

    @BeforeEach
    void setUp() {
        cars = new LinkedList();
        Car car = Mockito.mock(Car.class);
        for (int i = 0; i < N_T; i++) {
            cars.add(car);
        }
    }

    @Test
    void add() {
        int size = cars.size();
        Car car1 = new Car("B", 2);
        cars.add(car1);
        assertEquals(car1, cars.get_from_index(cars.size() - 1));
        assertEquals(size + 1, cars.size());

    }

    @Test
    void add_from_index() {
        int size = cars.size();

        Car first = Mockito.mock(Car.class);
        Car middle = Mockito.mock(Car.class);
        Car last = Mockito.mock(Car.class);

        cars.add_from_index(first, 0);
        cars.add_from_index(middle, 50);
        cars.add_from_index(last, cars.size());

        assertEquals(first, cars.get_from_index(0));
        assertEquals(middle, cars.get_from_index(50));
        assertEquals(last, cars.get_from_index(cars.size() - 1));
        assertEquals(size + 3, cars.size());
    }

    @Test
    void delete_from_index() {
        int size = cars.size();

        Car first = cars.get_from_index(10);
        Car second = cars.get_from_index(11);
        Car third = cars.get_from_index(12);
        Car last = cars.get_from_index(cars.size() - 1);

        cars.delete(second);

        assertEquals(first, cars.get_from_index(10));
        assertEquals(third, cars.get_from_index(11));
        assertEquals(last, cars.get_from_index(cars.size() - 1));
        assertEquals(size - 1, cars.size());
    }

    @Test
    void delete() {
        int size = cars.size();

        Car first = cars.get_from_index(0);
        Car second = cars.get_from_index(1);
        Car third = cars.get_from_index(2);

        cars.add(first);
        cars.add(second);
        cars.add(third);
        cars.delete(second);

        assertEquals(first, cars.get_from_index(0));
        assertEquals(third, cars.get_from_index(1));
        assertEquals(size + 2, cars.size());
    }

    @Test
    void get_from_index() {
        int size = cars.size();
        Car car = cars.get_from_index(cars.size() - 1);
        cars.add(car);
        assertEquals(car, cars.get_from_index(cars.size() - 1));
        assertEquals(size + 1, cars.size());
    }

    @Test
    void get() {
        int size = cars.size();
        Car car1 = new Car("w", 34);
        cars.add(car1);
        assertEquals(car1, cars.get(car1));
        assertEquals(size + 1, cars.size());
    }

    @Test
    void size() {
        int size = cars.size();
        for (int i = 0; i < 20; i++)
            cars.add(Mockito.mock(Car.class));
        assertEquals(cars.size(), size + 20);
    }

    @Test
    void clear() {
        cars.clear();
        assertEquals(cars.size(), 0);
        assertNotEquals(new Car("3", 3), cars.get_from_index(2));
        assertEquals(cars.get_from_index(2), null);
    }
}