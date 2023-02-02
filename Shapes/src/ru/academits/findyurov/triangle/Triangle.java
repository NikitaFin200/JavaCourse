package ru.academits.findyurov.triangle;

import ru.academits.findyurov.shape.Shape;

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

    private static double getCutLength(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private double getSide1() {
        return getCutLength(x1, x2, y1, y2);
    }

    private double getSide2() {
        return getCutLength(x2, x3, y2, y3);
    }

    private double getSide3() {
        return getCutLength(x3, x1, y3, y1);
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
        return Math.sqrt(semiPerimeter * (semiPerimeter - this.getSide1()) *
                (semiPerimeter - this.getSide2()) * (semiPerimeter - this.getSide3()));
    }

    @Override
    public double getPerimeter() {
        return this.getSide1() + this.getSide2() + this.getSide3();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Triangle p = (Triangle) obj;
        return (x1 == p.x1) && (x2 == p.x2) && (x3 == p.x3)
                && (y1 == p.y1) && (y2 == p.y2) && (y3 == p.y3);
    }

    @Override
    public String toString() {
        double[] support = {x1, y1, x2, y2, x3, y3};
        return Arrays.toString(support);
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

    private static double getMax(double... numbers) {
        double max = numbers[0];

        for (double elem : numbers) {
            if (elem > max) {
                max = elem;
            }
        }

        return max;
    }

    private static double getMin(double... numbers) {
        double min = numbers[0];

        for (double elem : numbers) {
            if (elem < min) {
                min = elem;
            }
        }

        return min;
    }
}
