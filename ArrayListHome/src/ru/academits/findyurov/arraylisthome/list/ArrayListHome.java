package ru.academits.findyurov.arraylisthome.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
}
