package ru.academits.findyurov.shapes.main;

import ru.academits.findyurov.shapes.shape.Shape;
import ru.academits.findyurov.shapes.shape.Circle;
import ru.academits.findyurov.shapes.shape.Rectangle;
import ru.academits.findyurov.shapes.shape.Square;
import ru.academits.findyurov.shapes.shape.Triangle;

import java.util.Arrays;


public class Main {
    public static Shape getShapeWithMaxArea(Shape... shapes) {
        Arrays.sort(shapes, new ShapeCompareArea());
        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondPerimetr(Shape... shapes) {
        Arrays.sort(shapes, new ShapeComparePerimetr());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape square = new Square(2);
        Shape triangle = new Triangle(0, 0, 3, 0, 0, 4);
        Shape rectangle = new Rectangle(1, 2);
        Shape circle = new Circle(5);

        Shape[] arrShapes = new Shape[4];
        arrShapes[0] = square;
        arrShapes[1] = triangle;
        arrShapes[2] = rectangle;
        arrShapes[3] = circle;

        for (Shape shape : arrShapes) {
            System.out.println(shape);
            System.out.println("area = " + shape.getArea());
            System.out.println("width = " + shape.getWidth());
            System.out.println("height = " + shape.getHeight());
            System.out.println("perimeter = " + shape.getPerimeter());
            System.out.println("hash = " + shape.hashCode());
            System.out.println();
        }

        Shape square2 = new Square(9);
        Shape triangle2 = new Triangle(0, 1, 4, 2, 1, 6);
        Shape rectangle2 = new Rectangle(5, 3);
        Shape circle2 = new Circle(2);

        Shape square3 = new Square(7);
        Shape triangle3 = new Triangle(7, 7, 2, 4, 2, 5);
        Shape rectangle3 = new Rectangle(7, 3);
        Shape circle3 = new Circle(4);

        Shape maxShapeArea = getShapeWithMaxArea(square, triangle, rectangle, circle, square2, triangle2, rectangle2, circle2, square3, triangle3, rectangle3, circle3);
        System.out.println(maxShapeArea);
        System.out.println("area = " + maxShapeArea.getArea());
        System.out.println("width = " + maxShapeArea.getWidth());
        System.out.println("height = " + maxShapeArea.getHeight());

        System.out.println();

        Shape secondShapePerimetr = getShapeWithSecondPerimetr(square, triangle, rectangle, circle, square2, triangle2, rectangle2, circle2, square3, triangle3, rectangle3, circle3);
        System.out.println(secondShapePerimetr);
        System.out.println("perimetr = " + secondShapePerimetr.getPerimeter());
        System.out.println("width = " + secondShapePerimetr.getWidth());
        System.out.println("height = " + secondShapePerimetr.getHeight());
    }
}