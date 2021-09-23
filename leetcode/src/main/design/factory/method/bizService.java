package design.factory.method;

public class bizService {
    public static void main(String[] args) {
        ShapeFactory circleShapeFactory = new CircleShapeFactory();
        Shape circle = circleShapeFactory.getShape();
        circle.draw();

        ShapeFactory rectShapeFactory = new RectShapeFactory();
        Shape rect = rectShapeFactory.getShape();
        rect.draw();
    }
}
