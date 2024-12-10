package design.factory.method;

public class CircleShapeFactory implements ShapeFactory {
    @Override
    public Shape getShape() {
        return new CircleShape();
    }
}
