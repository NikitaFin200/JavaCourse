package ru.academits.findyurov.shapes.shapesAndInterface.rectangle;

import ru.academits.findyurov.shapes.shapesAndInterface.shape.Shape;

import java.util.Arrays;

public class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double rectangleWidth, double rectangleHeight) {
        this.width = rectangleWidth;
        this.height = rectangleHeight;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (height + width);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) obj;
        return (width == rectangle.width) && (height == rectangle.height);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }

    @Override
    public String toString() {
        double[] array = {width, height};
        return Arrays.toString(array);
    }
}
