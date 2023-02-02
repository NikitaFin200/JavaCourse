package ru.academits.findyurov.square;

import ru.academits.findyurov.shape.Shape;

public class Square implements Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }
    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public double getPerimeter() {
        return 4.0 * side;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Square p = (Square) obj;
        return side == p.side;
    }

    @Override
    public String toString() {
        return "[" + side + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(side);
    }
}
