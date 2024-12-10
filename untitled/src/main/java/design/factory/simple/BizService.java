package design.factory.simple;

public class BizService {
    public static void main(String[] args) {
        Shape circleShape = ShapeFactory.getShape("circle");
        circleShape.draw();

        Shape rectShape = ShapeFactory.getShape("rect");
        rectShape.draw();
    }
}
