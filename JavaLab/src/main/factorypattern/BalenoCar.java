package main.factorypattern;

public class BalenoCar implements MaruthiCar {

    String model = "BALENO";

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                '}';
    }
}
