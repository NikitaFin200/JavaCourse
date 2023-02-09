package ru.academits.findyurov.range_main;

import ru.academits.findyurov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1, 5);
        Range range = new Range(3, 5);

        System.out.println("The length of the range from the initial number to the extreme.");
        System.out.print(range1.getLength());           // метод для вычисления длины диапазона

        System.out.println();

        System.out.print("range intersections: " + range1);
        System.out.print(" and " + range + " = ");

        Range intersection = range1.getIntersection(range);

        if (intersection != null) {
            System.out.println(intersection);
        } else {
            System.out.println("no intersections");
        }

        System.out.println();

        System.out.print("Unification range: " + range1);
        System.out.print(" and " + range + " = ");
        Range[] union = range1.getUnion(range);

        System.out.println(Arrays.toString(union));

        System.out.println();

        System.out.print("Range difference: " + range1);
        System.out.print(" and " + range + " = ");
        Range[] difference = range1.getDifference(range);

        if (difference.length != 0) {
            System.out.println(Arrays.toString(difference));
        } else {
            System.out.print("No difference");
        }
    }
}
