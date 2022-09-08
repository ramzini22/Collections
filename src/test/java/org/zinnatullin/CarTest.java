package org.zinnatullin;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    private ArrayList<Car> cars=new ArrayList();
    @Test
    void testToString() {
        for(int i=0;i<100;i++){
            cars.add(new Car("A"+i, i));
        }
        int index = 0;
        for (Object car:cars){
            assertEquals(car.toString(), "Car{" + "brand='" + "A"+ index + '\'' + ", year=" + index++ + '}');
        }
    }
}