package ru.academits.findyurov.shapes.main;

import ru.academits.findyurov.shapes.Shape;
import ru.academits.findyurov.shapes.Circle;
import ru.academits.findyurov.shapes.Rectangle;
import ru.academits.findyurov.shapes.Square;
import ru.academits.findyurov.shapes.Triangle;
import ru.academits.findyurov.shapes.compare.AreaCompare;
import ru.academits.findyurov.shapes.compare.PerimeterCompare;

import java.util.Arrays;

public class Main {
    public static Shape getShapeWithMaxArea(Shape... shapes) throws IllegalArgumentException {
        if (shapes == null || shapes.length == 0) {
            throw new IllegalArgumentException("Array size is 0");
        }

        Arrays.sort(shapes, new AreaCompare());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondPerimeter(Shape... shapes) throws IllegalArgumentException {
        if (shapes == null || shapes.length == 0) {
            throw new IllegalArgumentException("Array size is 0");
        }

        Arrays.sort(shapes, new PerimeterCompare());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape square = new Square(2);
        Shape triangle = new Triangle(0, 0, 3, 0, 0, 4);
        Shape rectangle = new Rectangle(1, 2);
        Shape circle = new Circle(5);

        Shape[] shapesArray = new Shape[4];
        shapesArray[0] = square;
        shapesArray[1] = triangle;
        shapesArray[2] = rectangle;
        shapesArray[3] = circle;

        for (Shape shape : shapesArray) {
            System.out.println(shape);
            System.out.println("area = " + shape.getArea());
            System.out.println("width = " + shape.getWidth());
            System.out.println("height = " + shape.getHeight());
            System.out.println("perimeter = " + shape.getPerimeter());
            System.out.println("hash = " + shape.hashCode());
            System.out.println();
        }

        Shape[] shapes = {new Square(9),
                new Triangle(0, 1, 4, 2, 1, 6),
                new Rectangle(5, 3),
                new Circle(2),
                new Square(7),
                new Triangle(7, 7, 2, 4, 2, 5),
                new Rectangle(7, 3),
                new Circle(4),
                new Square(2),
                new Triangle(0, 0, 3, 0, 0, 4),
                new Rectangle(1, 2),
                new Circle(5)
        };

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println(maxAreaShape);
        System.out.println("area = " + maxAreaShape.getArea());
        System.out.println("width = " + maxAreaShape.getWidth());
        System.out.println("height = " + maxAreaShape.getHeight());

        System.out.println();

        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapes);
        System.out.println(secondPerimeterShape);
        System.out.println("perimeter = " + secondPerimeterShape.getPerimeter());
        System.out.println("width = " + secondPerimeterShape.getWidth());
        System.out.println("height = " + secondPerimeterShape.getHeight());
    }
}