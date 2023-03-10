package ru.academits.findyurov.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size of the vector must be greater than 0. Size = " + size);
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] coordinates) {
        if (coordinates.length == 0) {
            throw new IllegalArgumentException("The size of the array must be greater than 0. Size = " + coordinates.length);
        }

        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector(int size, double[] coordinates) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size cannot be less than or equal to 0. Your size = " + size);
        }

        this.coordinates = Arrays.copyOf(coordinates, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int maxIndex = coordinates.length - 1;

        for (int i = 0; i < maxIndex; i++) {
            stringBuilder.append(coordinates[i]).append(", ");
        }

        stringBuilder.append(coordinates[maxIndex]).append("}");
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

    public void subtract(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= number;
        }
    }

    public void turn() {
        multiplyByScalar(-1.0);
    }

    public double getLength() {
        double sum = 0;

        for (double coordinate : coordinates) {
            sum += coordinate * coordinate;
        }

        return Math.sqrt(sum);
    }

    public double getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, double coordinate) {
        coordinates[index] = coordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        return Arrays.equals(coordinates, vector.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);
        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);
        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;
        int minSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minSize; ++i) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }
}
