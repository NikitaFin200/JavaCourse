package ru.academits.findyurov.shapes.main;

import ru.academits.findyurov.shapes.shapesAndInterface.shape.Shape;
import ru.academits.findyurov.shapes.shapesAndInterface.circle.Circle;
import ru.academits.findyurov.shapes.shapesAndInterface.rectangle.Rectangle;
import ru.academits.findyurov.shapes.shapesAndInterface.square.Square;
import ru.academits.findyurov.shapes.shapesAndInterface.triangle.Triangle;

public class Main {
    public static void main(String[] args) {
        Shape square = new Square(2);
        System.out.println("square area = " + square.getArea());
        System.out.println("width = " + square.getWidth());
        System.out.println("height = " + square.getHeight());
        System.out.println("perimeter = " + square.getPerimeter());
        System.out.println(square);
        System.out.println("hash = " + square.hashCode());
        System.out.println();

        Shape triangle = new Triangle(0, 0, 3, 0, 0, 4);
        System.out.println("triangle area = " + triangle.getArea());
        System.out.println("width = " + triangle.getWidth());
        System.out.println("height = " + triangle.getHeight());
        System.out.println("perimeter = " + triangle.getPerimeter());
        System.out.println(triangle);
        System.out.println("hash = " + triangle.hashCode());
        System.out.println(triangle.equals(square));
        System.out.println();

        Shape rectangle = new Rectangle(1, 2);
        System.out.println("rectangle area = " + rectangle.getArea());
        System.out.println("width = " + rectangle.getWidth());
        System.out.println("height = " + rectangle.getHeight());
        System.out.println("perimeter = " + rectangle.getPerimeter());
        System.out.println(rectangle);
        System.out.println("hash = " + rectangle.hashCode());
        System.out.println(rectangle.equals(triangle));
        System.out.println();

        Shape circle = new Circle(5);
        System.out.println("circle area = " + circle.getArea());
        System.out.println("width = " + circle.getWidth());
        System.out.println("height = " + circle.getHeight());
        System.out.println("perimeter = " + circle.getPerimeter());
        System.out.println(circle);
        System.out.println("hash = " + circle.hashCode());
        System.out.println(circle.equals(rectangle));
        System.out.println();
    }
}