package ru.academits.findyurov.arraylisthome;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> stringsList = new ArrayList<>();

        try {
            stringsList.add(String.valueOf(getStringsFromFile("file.txt")));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find the file");
        }

        System.out.println("Strings from file:");
        System.out.println(stringsList);

        System.out.println("The stringsList is cleared");
        stringsList.clear();
        System.out.println();

        ArrayList<Integer> numbersList1 = new ArrayList<>();

        numbersList1.add(10);
        numbersList1.add(11);
        numbersList1.add(20);
        numbersList1.add(30);
        numbersList1.add(33);
        numbersList1.add(53);
        numbersList1.add(100);
        numbersList1.add(101);
        numbersList1.add(102);

        System.out.println("The stringsList that has just been filled in:");
        System.out.println(numbersList1);
        System.out.println();

        deleteEvenNumbers(numbersList1);
        System.out.println("List without even numbers:");
        System.out.println(numbersList1);
        System.out.println();

        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 10, 14, 10));
        System.out.println("List with integer numbers, that are repeated:");
        System.out.println(numbersList2);
        System.out.println();

        System.out.println("List without repeat numbers:");
        System.out.println(getNewListWithoutRepeats(numbersList2));
        System.out.println();
    }

    public static ArrayList<String> getStringsFromFile(String fileName) throws IOException {
        ArrayList<String> outputStringsList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String string;

        while ((string = reader.readLine()) != null) {
            /* if (string.equals("")) {
                continue;
            }*/

            outputStringsList.add(string);
        }

        return outputStringsList;
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

