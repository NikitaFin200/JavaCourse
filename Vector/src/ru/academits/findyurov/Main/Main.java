package ru.academits.findyurov.Main;

import ru.academits.findyurov.Vector;

import static ru.academits.findyurov.Vector.*;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(2);
        vector1.setCoordinate(0, 1);
        vector1.setCoordinate(1, 0);
        System.out.println("vector 1 = " + vector1.toString());
        System.out.println("Its dimension = " + vector1.getSize());
        System.out.println("Its length = " + vector1.length());
        int i = 1;
        System.out.println("His " + (i + 1) + "coordinates = " + vector1.getCoordinate(i));
        System.out.println();

        Vector vector2 = new Vector(vector1);
        System.out.println("vector 2 (copy vector 1) = " + vector2.toString());
        System.out.println("Its dimension = " + vector2.getSize());
        System.out.println("Its length = " + vector2.length());
        System.out.println();

        double[] support = {1, 2, 3};
        Vector vector3 = new Vector(support);
        System.out.println("Its dimension = " + vector3.getSize());
        System.out.println("Its length = " + vector3.length());
        vector3.subVector(vector1);
        System.out.println("vector 3 = vector 3 - vector 1 = " + vector3.toString());
        System.out.println("vector 1 = " + vector1.toString());
        System.out.println();

        Vector vector4 = new Vector(4, support);
        System.out.println("Its dimension = " + vector4.getSize());
        System.out.println("Its length = " + vector4.length());
        vector4.addVector(vector1);
        System.out.println("vector 4 = vector 4 + vector 1 = " + vector4.toString());
        vector1.addVector(vector4);
        System.out.println("vector 1 = vector 1 + new vector 4 = " + vector1.toString());
        System.out.println();

        int alpha = 6;
        vector1.scalarMul(6);
        System.out.println("vector 1 * " + alpha + " = " + vector1.toString());
        vector1.turnVector();
        System.out.println("vector 1 = expanded vector vector1 * " + alpha + " this " + vector1.toString());
        System.out.println();

        System.out.println("static methods");
        Vector vector5 = new Vector(addVector(vector1, vector3));
        System.out.println("vector 5 = vector 1 + vector 3 = " + vector5.toString());

        Vector vector6 = new Vector(subVector(vector1, vector3));
        System.out.println("vector 6 = vector 1 - vector 3 = " + vector6.toString());

        vector6.addVector(new Vector(new double[]{0, 0, 0, 0, 0}));
        System.out.println("vector 6 = " + vector6.toString());
        System.out.println("vector 5 * vector 6 = " + scalarMul(vector5, vector6));
    }
}
