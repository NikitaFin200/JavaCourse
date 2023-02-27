package ru.academits.findyurov.matrix;

import ru.academits.findyurov.vector.Vector;

import java.io.IOException;
import java.util.Arrays;

import static ru.academits.findyurov.vector.Vector.multiplyByScalar;

public class Matrix {

    private Vector[] rows;

    public Matrix(int quantityRows, int quantityColumns) {
        if (quantityRows <= 0) {
            throw new IllegalArgumentException("Number of rows is 0. Your value = " + quantityRows);
        }

        rows = new Vector[quantityRows];

        for (int i = 0; i < quantityRows; ++i) {
            rows[i] = new Vector(quantityColumns);
        }
    }

    public Matrix(Matrix matrix) {
        int rowsCount = matrix.getRowsCount();
        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Number of rows is 0");
        }

        rows = new Vector[array.length];

        int columnsCount = 1;

        for (double[] string : array) {
            if (string.length == 0) {
                throw new IllegalArgumentException("Cannot create a row of matrix of size 0");
            }
            if (string.length > columnsCount) {
                columnsCount = string.length;
            }
        }

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Cannot create a matrix of size 0");
        }

        rows = new Vector[vectors.length];
        int columnsCount = 1;

        for (Vector vector : vectors) {
            if (vector.getSize() > columnsCount) {
                columnsCount = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(columnsCount);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        int rowsCount = getRowsCount();

        return rows[rowsCount - 1].getSize();
    }

    public Vector getRow(int index) {
        try {

        } catch (Throwable e) {
            System.out.println("Illegal index of rows. index = " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int i, Vector vector) {
        if (i < 0 || i >= getRowsCount()) {
            throw new IllegalArgumentException("Illegal index of rows");
        }

        int size = vector.getSize();

        for (int j = 0; j < size; ++j) {
            rows[i].setCoordinate(j, vector.getCoordinate(j));
        }
    }

    public Vector getColumn(int i) {
        if (i < 0 || i >= getColumnsCount()) {
            throw new IllegalArgumentException("Illegal index of columns");
        }

        int height = this.getRowsCount();
        Vector vector = new Vector(height);

        for (int j = 0; j < height; ++j) {
            vector.setCoordinate(j, rows[j].getCoordinate(i));
        }

        return vector;
    }

    public void transposition() {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = i; j < columnsCount; ++j) {
                double support = rows[i].getCoordinate(j);
                rows[i].setCoordinate(j, getRow(j).getCoordinate(i));
                rows[j].setCoordinate(i, support);
            }
        }
    }

    public void scalarMultiplication(double scalar) {
        int rowCount = getRowsCount();
        for (int i = 0; i < rowCount; ++i) {
            this.rows[i].multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        int quantityRows = getRowsCount();

        if (quantityRows != getColumnsCount()) {
            throw new IllegalArgumentException("Determinant can be computed from the elements of a square matrix only");
        }
        if (quantityRows == 1) {
            return getRow(0).getCoordinate(0);
        } else if (quantityRows == 2) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) -
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(0);
        } else if (quantityRows == 3) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) *
                    getRow(2).getCoordinate(2) + getRow(2).getCoordinate(0) *
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(2) +
                    getRow(0).getCoordinate(2) * getRow(1).getCoordinate(0) *
                            getRow(2).getCoordinate(1) - getRow(0).getCoordinate(2) *
                    getRow(1).getCoordinate(1) * getRow(2).getCoordinate(0) -
                    getRow(1).getCoordinate(0) * getRow(0).getCoordinate(1) *
                            getRow(2).getCoordinate(2) - getRow(0).getCoordinate(0) *
                    getRow(2).getCoordinate(1) * getRow(1).getCoordinate(2);
        } else {
            int decompositionIndex = 0;
            double determinant = 0;

            for (int i = 0; i < quantityRows; i++) {
                determinant += Math.pow(-1, i) * getRow(i).getCoordinate(decompositionIndex) *
                        getMinor(this, i, decompositionIndex);
            }

            return determinant;
        }
    }

    private static double getMinor(Matrix matrix, int firstIndex, int secondIndex) {
        int length = matrix.getRowsCount() - 1;
        Matrix result = new Matrix(length, length);

        for (int i = 0, i2 = 0; i < length + 1; i++) {
            for (int j = 0, j2 = 0; j < length + 1; j++) {
                if (i != firstIndex && j != secondIndex) {
                    result.rows[i2].setCoordinate(j2, matrix.getRow(i).getCoordinate(j));
                    j2++;

                    if (j2 == length) {
                        j2 = 0;
                        i2++;
                    }
                }
            }
        }
        return result.getDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = getRowsCount();

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(rows[i].toString()).append(",\n");
        }

        stringBuilder.append(rows[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public Vector mul(Vector vector) {
        int quantityRows = getRowsCount();
        int quantityColumns = getColumnsCount();
        Vector mulResult = new Vector(quantityRows);

        for (int i = 0; i < quantityRows; ++i) {
            double support = 0;

            for (int j = 0; j < quantityColumns; ++j) {
                support += getRow(i).getCoordinate(j) * vector.getCoordinate(j);
            }

            mulResult.setCoordinate(i, support);
        }

        return mulResult;
    }

    public void sum(Matrix matrix) {
        int height = getRowsCount();

        if (height != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        for (int i = 0; i < height; ++i) {
            Vector vector = Vector.getSum(getRow(i), matrix.getRow(i));
            setRow(i, vector);
        }
    }

    public static Matrix sub(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.sub(matrix2);
        return matrix;
    }

    public void sub(Matrix matrix) {
        int height = getRowsCount();

        if (height != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        for (int i = 0; i < height; ++i) {
            Vector vector = Vector.getDifference(getRow(i), matrix.getRow(i));
            setRow(i, vector);
        }
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.sum(matrix2);
        return matrix;
    }

    public static Matrix mul(Matrix matrix1, Matrix matrix2) {
        int rowCount = matrix1.getRowsCount();

        if (rowCount != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        Matrix mul = new Matrix(rowCount, rowCount);

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < rowCount; ++j) {
                double support = multiplyByScalar(matrix1.getRow(i), matrix2.getColumn(j));
                mul.rows[i].setCoordinate(j, support);
            }
        }

        return mul;
    }
}
