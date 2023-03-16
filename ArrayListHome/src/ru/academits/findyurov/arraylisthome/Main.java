package ru.academits.findyurov.arraylisthome;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<Object> list = new ArrayList<>(/*Main.getStringsFromFile("file.txt")*/);

        try {
            list.add(Main.getStringsFromFile("file.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        }

        System.out.println("List from file");
        System.out.println(list);

        System.out.println("The list is cleared");
        list.clear();
        System.out.println();

        ArrayList<Integer> list2 = new ArrayList<>();

        list2.add(10);
        list2.add(11);
        list2.add(20);
        list2.add(30);
        list2.add(33);
        list2.add(53);
        list2.add(100);
        list2.add(101);
        list2.add(102);

        System.out.println("The list that has just been filled in");
        System.out.println(list2);
        System.out.println();

        Main.deleteEvenNumbers(list2);
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

    public static ArrayList<String> getStringsFromFile(String line) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(line))) {
            String string;

            while ((string = reader.readLine()) != null) {
                list.add(string);
            }
        }
        /*catch (FileNotFoundException e) {
            System.out.println("Could not find the file");
        }*/

        return list;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i : list) {
            if (i % 2 == 0) {
                list.remove(list.get(i));
            }
        }

        //for(ArrayList<Integer> iterator=list.iterator(); iterator.hasN)
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(list.get(i));
            }
        }
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
