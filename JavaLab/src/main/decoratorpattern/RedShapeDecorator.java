package main.decoratorpattern;

public class RedShapeDecorator implements Shape {

   private Shape decoratedShape;

   public RedShapeDecorator(Shape decoratedShape) {
      this.decoratedShape = decoratedShape;
   }

   @Override
   public void draw() {
      decoratedShape.draw();	       
      setRedBorder(decoratedShape);
   }

   private void setRedBorder(Shape decoratedShape){
      System.out.println("Border Color: Red");
   }
}