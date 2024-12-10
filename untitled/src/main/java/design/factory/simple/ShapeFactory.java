package design.factory.simple;

public class ShapeFactory {
    public static Shape getShape(String type) {
        if (type.equals("circle")) {
            return new CircleShape();
        } else if (type.equals("rect")) {
            return new RectShape();
        }
        return null;
    }
}
