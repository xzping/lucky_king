package design.factory.method;

public class RectShapeFactory implements ShapeFactory {
    @Override
    public Shape getShape() {
        return new RectShape();
    }
}
