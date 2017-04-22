package main.factorypattern;

public class SwiftCar implements MaruthiCar {

    String model = "SWIFT";

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                '}';
    }
}
