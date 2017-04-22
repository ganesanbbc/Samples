package main;


import main.builderpattern.Drink;
import main.decoratorpattern.Circle;
import main.decoratorpattern.Rectangle;
import main.decoratorpattern.RedShapeDecorator;
import main.decoratorpattern.Shape;
import main.factorypattern.MaruthiCarFactory;

public class StartupClass {

    public static void main(String[] args) {

        new ExtendClass().demoMEthod();

        Drink coffee = new Drink.Builder("JD").withSoda(
                true).withIce(false).size("Large").build();
        System.out.println(coffee);

        MaruthiCarFactory drinkFactory = new MaruthiCarFactory();
        System.out.println(drinkFactory.getCar("baleno"));


        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();


    }

    public static class ExtendClass {
        public static void demoMEthod() {
            System.out.println("Start");
        }
    }
}
