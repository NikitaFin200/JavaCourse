package ru.academits.findyurov.arraylisthome.main;

import ru.academits.findyurov.arraylisthome.list.ArrayListHome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        ArrayListHome obj = new ArrayListHome();
        ArrayList<String> list = new ArrayList<>(obj.read());
        System.out.println("List from file");
        System.out.println(list);

        System.out.println("The list is cleared");
        obj.clear();

        int number;

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter number");
            number = scanner.nextInt();
            obj.addNumber(number);
        }
        System.out.println("The list that has just been filled in");
        System.out.println(obj.getList());

        obj.deleteEvenNumbers();
        System.out.println("List without even numbers");
        System.out.println(obj.getList());

        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("1", "2", "3", "2", "10", "14", "10"));
        obj.getCopy(list3);
        System.out.println("List with integer numbers, that are repeated");
        System.out.println(obj.getList());
        obj.deleteRepeat();
        System.out.println("List without repeat numbers");
        System.out.println(obj.getList());
    }
}