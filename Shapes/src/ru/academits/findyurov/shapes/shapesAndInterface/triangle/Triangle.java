package ru.academits.findyurov.shapes.shapesAndInterface.triangle;

import ru.academits.findyurov.shapes.shapesAndInterface.shape.Shape;

import java.util.Arrays;

public class Triangle implements Shape {
    private final double x1;
    private final double x2;
    private final double x3;
    private final double y1;
    private final double y2;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private double getSide1() {
        return getSideLength(x1, y1, x2, y2);
    }

    private double getSide2() {
        return getSideLength(x2, y2, x3, y3);
    }

    private double getSide3() {
        return getSideLength(x3, y3, x1, y1);
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double semiPerimeter = this.getPerimeter() * 0.5;
        return Math.sqrt(semiPerimeter * (semiPerimeter - getSide1())
                * (semiPerimeter - getSide2()) * (semiPerimeter - getSide3()));
    }

    @Override
    public double getPerimeter() {
        return getSide1() + getSide2() + getSide3();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) obj;
        return (x1 == triangle.x1) && (x2 == triangle.x2) && (x3 == triangle.x3)
                && (y1 == triangle.y1) && (y2 == triangle.y2) && (y3 == triangle.y3);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x1 + ";" + y1 + "), (" + x2 + ";" + y2 + "), (" + x3 + ";" + y3 + ")";
    }

    private static double getMax(double... numbers) {
        double max = numbers[0];

        for (double digit : numbers) {
            if (digit > max) {
                max = digit;
            }
        }

        return max;
    }

    private static double getMin(double... numbers) {
        double min = numbers[0];

        for (double digit : numbers) {
            if (digit < min) {
                min = digit;
            }
        }

        return min;
    }
}
