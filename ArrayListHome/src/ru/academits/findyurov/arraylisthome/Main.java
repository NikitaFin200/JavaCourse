package ru.academits.findyurov.arraylisthome;

import java.io.*;
import java.util.*;

public class Main {
    private static final ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<Object> list = new ArrayList<>(/*Main.getStringsFromFile("file.txt")*/);

        try {
            list.add(getStringsFromFile("file.txt"));
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
        System.out.println(list2);
        System.out.println();

        ArrayList<Object> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 10, 14, 10));
        list.clear();
        list.addAll(list3);
        System.out.println("List with integer numbers, that are repeated");
        System.out.println(list);
        System.out.println();

        System.out.println("List without repeat numbers");
        System.out.println(Main.getNewListWithoutRepeats(list));
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
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            int t = iterator.next();
            t = t % 2;
            if (t == 0) {
                iterator.remove();
            }
        }
    }

    public static ArrayList<Object> getNewListWithoutRepeats(ArrayList<Object> list) {
        ArrayList<Object> newList = new ArrayList<>(list);

        for (int i = 0; i < newList.size(); i++) {
            boolean repeat = false;

            for (int j = i + 1; j < newList.size(); j++) {
                if (newList.get(i).equals(newList.get(j))) {
                    repeat = true;
                    break;
                }
            }

            if (repeat) {
                newList.remove(newList.get(i));
            }
        }

        return newList;
    }
}
