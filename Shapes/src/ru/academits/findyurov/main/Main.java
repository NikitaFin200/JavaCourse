package ru.academits.findyurov.main;

import ru.academits.findyurov.shape.Shape;
import ru.academits.findyurov.circle.Circle;
import ru.academits.findyurov.rectangle.Rectangle;
import ru.academits.findyurov.square.Square;
import ru.academits.findyurov.triangle.Triangle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape figure1 = new Square(2);
        System.out.println("square area = " + figure1.getArea());
        System.out.println("width = " + figure1.getWidth());
        System.out.println("height = " + figure1.getHeight());
        System.out.println("perimeter = " + figure1.getPerimeter());
        System.out.println(figure1.toString());
        System.out.println("hash = " + figure1.hashCode());
        System.out.println();

        Shape figure2 = new Triangle(0, 0, 3, 0, 0, 4);
        System.out.println("triangle area = " + figure2.getArea());
        System.out.println("width = " + figure2.getWidth());
        System.out.println("height = " + figure2.getHeight());
        System.out.println("perimeter = " + figure2.getPerimeter());
        System.out.println(figure2.toString());
        System.out.println("hash = " + figure2.hashCode());
        System.out.println(figure2.equals(figure1));
        System.out.println();

        Shape figure3 = new Rectangle(1, 2);
        System.out.println("rectangle area = " + figure3.getArea());
        System.out.println("width = " + figure3.getWidth());
        System.out.println("height = " + figure3.getHeight());
        System.out.println("perimeter = " + figure3.getPerimeter());
        System.out.println(figure3.toString());
        System.out.println("hash = " + figure3.hashCode());
        System.out.println(figure3.equals(figure2));
        System.out.println();

        Shape figure4 = new Circle(5);
        System.out.println("circle area = " + figure4.getArea());
        System.out.println("width = " + figure4.getWidth());
        System.out.println("height = " + figure4.getHeight());
        System.out.println("perimeter = " + figure4.getPerimeter());
        System.out.println(figure4.toString());
        System.out.println("hash = " + figure4.hashCode());
        System.out.println(figure4.equals(figure3));
        System.out.println();
    }
}