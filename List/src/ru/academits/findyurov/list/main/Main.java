package ru.academits.findyurov.list.main;

import ru.academits.findyurov.list.list.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<java.io.Serializable> lis = new LinkedList<>();

        lis.addValue(2);
        lis.addValue(10);
        lis.addValue(11);
        lis.addValue("rrr");

        System.out.print("Print list");
        lis.print();
        System.out.println();

        System.out.print("Get list size:");
        System.out.println(lis.getSize());

        System.out.print("Get first element:");
        System.out.println(lis.getFirstValue());

        System.out.print("Get element for index:");
        System.out.println(lis.getValue(2));

        System.out.println("Zamena");
        System.out.println(lis.setValue(1, 100));

        System.out.println("remove");
        lis.remove(1);
        lis.print();
        System.out.println();

        System.out.println("first");
        lis.enterElementFirst("rr");
        lis.print();
    }
}