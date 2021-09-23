package design.factory.simple;

public class CircleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("Draw: Circle");
    }
}
