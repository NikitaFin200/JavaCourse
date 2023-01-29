package ru.academits.findyurov.range_main;

import ru.academits.findyurov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range1 = new Range(35, 50);
        Range range2 = new Range(20, 40);

        System.out.println("The length of the range from the initial number to the extreme.");
        System.out.print(range1.getLength());           // метод для вычисления длины диапазона

        System.out.println();

        System.out.println("range intersections");
        System.out.println(range1.toString());
        System.out.print("and ");
        System.out.println(range2.toString());

        Range intersection;

        intersection = range1.getIntersections(range1, range2);

        if (intersection != null) {
            System.out.println(intersection.toString());
        } else {
            System.out.println("no intersections");
        }

        System.out.println();

        System.out.print("Unification range: ");
        System.out.println(range1.toString());
        System.out.print("and ");
        System.out.println(range2.toString());
        Range[] union = range1.getUnion(range1, range2);

        Range.arraysPrint(union);

        System.out.println();

        System.out.print("Range difference: ");
        System.out.println(range1.toString());
        System.out.print("and ");
        System.out.print(range2.toString());
        System.out.println();
        Range[] difference = range1.getDifference(range1, range2);

        if (difference.length != 0) {
            Range.arraysPrint(difference);
        } else {
            System.out.print("No difference");
        }
    }
}
