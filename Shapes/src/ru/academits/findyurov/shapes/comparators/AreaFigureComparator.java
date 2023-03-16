package ru.academits.findyurov.shapes.comparators;

import ru.academits.findyurov.shapes.Shape;

import java.util.Comparator;

public class AreaFigureComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
