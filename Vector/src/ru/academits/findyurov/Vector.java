package ru.academits.findyurov;

import java.util.Arrays;

public class Vector {
    public double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        int n = vector.getSize();
        coordinates = Arrays.copyOf(vector.coordinates, n);
    }

    public Vector(double[] coordinates) {
        int n = coordinates.length;

        if (n == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public Vector(int n, double[] coordinates) {
        if (n <= 0 || coordinates.length == 0) {
            throw new IllegalArgumentException("Illegal Argument Exception hello");
        }

        this.coordinates = Arrays.copyOf(coordinates, n);
    }

    public int getSize() {
        return coordinates.length;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = getSize();

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }

        stringBuilder.append(coordinates[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public void addVector(Vector vector) {
        int size = this.getSize();
        int vectorSize = vector.getSize();

        if (size < vectorSize) {
            this.coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; ++i) {
            this.coordinates[i] += vector.coordinates[i];
        }
    }

    public void subVector(Vector vector) {
        int size = this.getSize();
        int vectorSize = vector.getSize();

        if (size < vectorSize) {
            this.coordinates = Arrays.copyOf(coordinates, vectorSize);
        }

        for (int i = 0; i < vectorSize; ++i) {
            this.coordinates[i] -= vector.coordinates[i];
        }
    }

    public void scalarMul(double number) {
        int size = coordinates.length;

        for (int i = 0; i < size; i++) {
            this.coordinates[i] *= number;
        }
    }

    public void turnVector() {
        this.scalarMul(-1.0);
    }

    public double length() {
        double support = 0;

        for (double elem : this.coordinates) {
            support += Math.pow(elem, 2);
        }

        return Math.sqrt(support);
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, double elem) {
        coordinates[index] = elem;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vector p = (Vector) obj;

        if (this.getSize() != p.getSize()) {
            return false;
        }

        int size = coordinates.length;

        for (int i = 0; i < size; i++) {
            if (this.getCoordinate(i) != p.getCoordinate(i)) {
                return false;
            }
        }

        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public static Vector addVector(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.addVector(vector2);
        return vector;
    }

    public static Vector subVector(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subVector(vector2);
        return vector;
    }

    public static double scalarMul(Vector vector1, Vector vector2) {
        double mul = 0;
        int size = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < size; ++i) {
            mul += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return mul;
    }
}
