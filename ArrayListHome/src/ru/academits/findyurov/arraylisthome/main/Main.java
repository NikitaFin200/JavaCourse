package ru.academits.findyurov.arraylisthome.main;

import java.io.*;
import java.util.*;

public class Main {
    private static final ArrayList<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>(Main.getStringsFromFile("file.txt"));
        System.out.println("List from file");
        System.out.println(list);

        System.out.println("The list is cleared");
        list.clear();
        System.out.println();

        list.add(10);
        list.add(11);
        list.add(20);
        list.add(30);
        list.add(33);
        list.add(53);
        list.add(100);
        list.add(101);
        list.add(102);

        System.out.println("The list that has just been filled in");
        System.out.println(list);
        System.out.println();

        Main.deleteEvenNumbers(list);
        System.out.println("List without even numbers");
        System.out.println(list);
        System.out.println();

        ArrayList<Object> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 10, 14, 10));
        list.clear();
        list.addAll(list3);
        System.out.println("List with integer numbers, that are repeated");
        System.out.println(list);
        System.out.println();

        System.out.println("List without repeat numbers");
        System.out.println(Main.deleteRepeat(list));
        System.out.println();
    }

    public static ArrayList<Object> getStringsFromFile(String str) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(str));
            String line;

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Could not find the file");
        }

        return list;
    }

    public static void deleteEvenNumbers(ArrayList<Object> list) {
        list.removeIf(object -> (Integer) object % 2 == 0);
    }

    public static ArrayList<Object> deleteRepeat(ArrayList<Object> list) {
        ArrayList<Object> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
           boolean repeat = false;

            for (int j = i + 1; j < list.size(); j++) {
                if (Arrays.equals(new Object[]{list.get(j)}, new Object[]{list.get(i)})) {
                    repeat = true;
                    break;
                }
            }

            if (!(repeat)) {
                newList.add(list.get(i));
           }
        }

        return newList;
    }
}
