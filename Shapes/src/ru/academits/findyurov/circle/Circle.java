package ru.academits.findyurov.circle;

import ru.academits.findyurov.shape.Shape;

public class Circle implements Shape {
    private final double radius;
    private static final int DIAM_COEFFICIENT = 2;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return DIAM_COEFFICIENT * radius;
    }

    @Override
    public double getHeight() {
        return DIAM_COEFFICIENT * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return DIAM_COEFFICIENT * Math.PI * radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Circle p = (Circle) obj;
        return radius == p.radius;
    }

    @Override
    public String toString() {
        return "[" + radius + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(radius);
    }
}
