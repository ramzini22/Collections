package org.zinnatullin;
public class Car implements Comparable<Car> {
    private String brand;
    private int year;

    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public int compareTo(Car o) {
        return brand.compareTo(o.brand);
    }
}