package ru.academits.findyurov.arraylisthome.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ArrayListHome {
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> read() throws IOException {
        File file = new File("file.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;

        while ((line = reader.readLine()) != null) {
            list.add(line);
        }

        reader.close();

        return list;
    }

    public void clear() {
        list.clear();
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public void addNumber(int number) {
        list.add(String.valueOf(number));
    }

    public void deleteEvenNumbers() {
        int number;

        for (int i = 0; i <= list.size() - 1; i++) {
            number = Integer.parseInt(list.get(i));

            if (number % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public void deleteRepeat() {
        for (int i = 0; i <= list.size() - 1; i++) {
            for (int j = i + 1; j <= list.size() - 1; j++) {
                if (Objects.equals(list.get(i), list.get(j))) {
                    list.remove(j);
                }
            }
        }
    }

    public void getCopy(ArrayList<String> list) {
        this.list.clear();
        this.list.addAll(list);
    }
}
