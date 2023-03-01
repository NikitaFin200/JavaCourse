package ru.academits.findyurov.arraylisthome.main;

import ru.academits.findyurov.arraylisthome.list.ArrayListHome;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayListHome v = new ArrayListHome();
        ArrayList<String> list = new ArrayList<>(v.read());
        System.out.println(list);

    }
}