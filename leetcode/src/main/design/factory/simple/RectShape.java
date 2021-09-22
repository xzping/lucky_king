package main.design.factory.simple;

public class RectShape implements Shape {
    @Override
    public void draw() {
        System.out.println("Draw: Rect");
    }
}
