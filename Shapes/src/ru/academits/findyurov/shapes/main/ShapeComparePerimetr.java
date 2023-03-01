package ru.academits.findyurov.shapes.main;

import ru.academits.findyurov.shapes.Shape;

import java.util.Comparator;

public class ShapeComparePerimetr implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getPerimeter(), o2.getPerimeter());
    }
}
