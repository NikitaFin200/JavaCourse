package ru.academits.findyurov.shapes.main;

import ru.academits.findyurov.shapes.shape.Shape;

import java.util.Comparator;

public class ShapeCompareArea implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}
