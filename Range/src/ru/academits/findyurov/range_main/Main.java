package ru.academits.findyurov.range_main;

import ru.academits.findyurov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range = new Range(15, 30);

        System.out.println("Getter implementation.");
        System.out.println(range.getFrom());                         // реализация Геттера
        System.out.println("Implementation of the setter.");
        range.setFrom(19.5);                                          // реализация Сеттера
        System.out.println("New value after the setter.");
        System.out.println(range.getFrom());

        System.out.println("Getter implementation.");
        System.out.println(range.getTo());
        System.out.println("Implementation of the setter.");
        range.setTo(80.78);
        System.out.println("New value after the setter.");
        System.out.println(range.getTo());

        System.out.println("The length of the range from the initial number to the extreme.");
        System.out.print(range.getLength());           // метод для вычисления длины диапазона

        System.out.println();

        System.out.println("Enter number between first number to last number:");
        double number = scanner.nextDouble();

        System.out.println(range.isInside(number));    // применение метода isInside
    }
}
