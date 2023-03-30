package ru.academits.findyurov.arraylisthome;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> listString = new ArrayList<>();

        try {
            listString.add(getStringsFromFile("file.txt"));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not find the file");
        }

        System.out.println("List from file");
        System.out.println(listString);

        System.out.println("The list is cleared");
        listString.clear();
        System.out.println();

        ArrayList<Integer> listInteger = new ArrayList<>();

        listInteger.add(10);
        listInteger.add(11);
        listInteger.add(20);
        listInteger.add(30);
        listInteger.add(33);
        listInteger.add(53);
        listInteger.add(100);
        listInteger.add(101);
        listInteger.add(102);

        System.out.println("The list that has just been filled in");
        System.out.println(listInteger);
        System.out.println();

        deleteEvenNumbers(listInteger);
        System.out.println("List without even numbers");
        System.out.println(listInteger);
        System.out.println();

        ArrayList<Integer> listInteger2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 10, 14, 10));
        System.out.println("List with integer numbers, that are repeated");
        System.out.println(listInteger2);
        System.out.println();

        System.out.println("List without repeat numbers");
        System.out.println(getNewListWithoutRepeats(listInteger2));
        System.out.println();
    }

    public static ArrayList<String> getStringsFromFile(String acceptedLine) throws IOException {
        ArrayList<String> exitLine = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(acceptedLine))) {
            String string;

            while ((string = reader.readLine()) != null) {
                if (string.equals("")) {
                    continue;
                }

                exitLine.add(string);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Could not find the file");
        }

        return exitLine;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> numbers) {

        /*Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            int t = iterator.next();
            t = t % 2;

            if (t == 0) {
                iterator.remove();
            }
        }
         */

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.set(i, null);
            }
        }

        numbers.removeIf(Objects::isNull);
    }

    public static ArrayList<Integer> getNewListWithoutRepeats(ArrayList<Integer> list) {
        ArrayList<Integer> withoutRepeatsList = new ArrayList<>();

        list.removeIf(Objects::isNull);

        withoutRepeatsList.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            if (!withoutRepeatsList.contains(list.get(i))) {
                withoutRepeatsList.add(list.get(i));
            }
        }

        return withoutRepeatsList;
    }
}

