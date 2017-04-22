package main.factorypattern;

public class MaruthiCarFactory {


    public MaruthiCar getCar(String name) {

        if (name.equalsIgnoreCase("swift")) {
            return new SwiftCar();
        } else  if (name.equalsIgnoreCase("baleno")) {
            return new BalenoCar();
        }
        return null;
    }
}
