package ru.academits.findyurov.vector.vector;

import java.util.Arrays;

public class Vector {
    public double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vector should be more 1! " + size + " < 1");
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        int n = vector.getSize();
        coordinates = Arrays.copyOf(vector.coordinates, n);
    }

    public Vector(double[] coordinates) {
        int n = coordinates.length;

        if (n == 0) {
            throw new IllegalArgumentException("The size of the array cannot be 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public Vector(int size, double[] coordinates) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size cannot be 0");
        }

        this.coordinates = Arrays.copyOf(coordinates, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = coordinates.length - 1;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }

        stringBuilder.append(coordinates[coordinates.length - 1]).append("}");
        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtracting(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplicationByScalarNumber(double number) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= number;
        }
    }

    public void turn() {
        this.multiplicationByScalarNumber(-1.0);
    }

    public double getLength() {
        double sum = 0;

        for (double coordinate : coordinates) {
            sum += coordinate*coordinate;
        }

        return Math.sqrt(sum);
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        coordinates[index] = coordinate;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        if (this.getSize() != vector.getSize()) {
            return false;
        }

        int size = coordinates.length;

        for (int i = 0; i < size; i++) {
            if (coordinates[i] != vector.getCoordinate(i)) {
                return false;
            }
        }

        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.add(vector2);
        return vector;
    }

    public static Vector subtracting(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtracting(vector2);
        return vector;
    }

    public static double multiplicationByScalarNumber(Vector vector1, Vector vector2) {
        double mul = 0;
        int size = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < size; ++i) {
            mul += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return mul;
    }
}
