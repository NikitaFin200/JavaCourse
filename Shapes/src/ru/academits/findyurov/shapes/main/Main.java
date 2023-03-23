package ru.academits.findyurov.shapes.main;

import ru.academits.findyurov.shapes.Shape;
import ru.academits.findyurov.shapes.Circle;
import ru.academits.findyurov.shapes.Rectangle;
import ru.academits.findyurov.shapes.Square;
import ru.academits.findyurov.shapes.Triangle;
import ru.academits.findyurov.shapes.comparators.ShapeAreaComparator;
import ru.academits.findyurov.shapes.comparators.ShapePerimeterComparator;

import java.util.Arrays;

public class Main {
    public static Shape getShapeWithMaxArea(Shape... shapes) {
        if (shapes == null) {
            throw new IllegalArgumentException("There is no array");
        }

        if (shapes.length == 0) {
            throw new IllegalArgumentException("Array size is 0");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        if (shapes == null) {
            throw new IllegalArgumentException("There is no array");
        }

        if (shapes.length == 0) {
            throw new IllegalArgumentException("Array size is 0");
        }

        if (shapes.length == 1) {
            throw new IllegalArgumentException("Array size is 1");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape square = new Square(2);
        Shape triangle = new Triangle(0, 0, 3, 0, 0, 4);
        Shape rectangle = new Rectangle(1, 2);
        Shape circle = new Circle(5);

        Shape[] shapesArray1 = {
                square,
                triangle,
                rectangle,
                circle
        };

        for (Shape shape : shapesArray1) {
            System.out.println(shape);
            System.out.println("area = " + shape.getArea());
            System.out.println("width = " + shape.getWidth());
            System.out.println("height = " + shape.getHeight());
            System.out.println("perimeter = " + shape.getPerimeter());
            System.out.println("hash = " + shape.hashCode());
            System.out.println();
        }

        Shape[] shapesArray2 = {
                new Square(9),
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

        Shape maxAreaShape = getShapeWithMaxArea(shapesArray2);
        System.out.println(maxAreaShape);
        System.out.println("area = " + maxAreaShape.getArea());
        System.out.println("width = " + maxAreaShape.getWidth());
        System.out.println("height = " + maxAreaShape.getHeight());

        System.out.println();

        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapesArray2);
        System.out.println(secondPerimeterShape);
        System.out.println("perimeter = " + secondPerimeterShape.getPerimeter());
        System.out.println("width = " + secondPerimeterShape.getWidth());
        System.out.println("height = " + secondPerimeterShape.getHeight());
    }
}