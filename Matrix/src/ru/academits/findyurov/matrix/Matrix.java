package ru.academits.findyurov.matrix;

import ru.academits.findyurov.vector.Vector;

import static ru.academits.findyurov.vector.Vector.getScalarProduct;

public class Matrix {
    private final Vector[] rows;

    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (rowsQuantity < 0) {
            throw new IllegalArgumentException("The number of rows cannot be less than 0.The number must be greater than 0. Your value = " + rowsQuantity);
        }

        if (rowsQuantity == 0) {
            throw new IllegalArgumentException("The number of rows is 0.The number must be greater than 0. Your value = " + rowsQuantity);
        }

        if (columnsQuantity < 0) {
            throw new IllegalArgumentException("The number of columns cannot be less than 0.The number must be greater than 0. Your value = " + rowsQuantity);
        }

        if (columnsQuantity == 0) {
            throw new IllegalArgumentException("The number of columns is 0.The number must be greater than 0. Your value = " + rowsQuantity);
        }

        rows = new Vector[rowsQuantity];

        for (int i = 0; i < rowsQuantity; ++i) {
            rows[i] = new Vector(columnsQuantity);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        /*if (array.length == 0) {
            throw new IllegalArgumentException("Number of rows is 0");
        }*/

        rows = new Vector[array.length];

        int columnsQuantity = 0;

        for (double[] row : array) {
            /*if (string.length == 0) {
                throw new IllegalArgumentException("Cannot create a row of matrix of size 0");
            }*/
            if (row.length > columnsQuantity) {
                columnsQuantity = row.length;
            }

            if (columnsQuantity == 0) {
                throw new IllegalArgumentException("Number of columns is 0");
            }
        }

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(columnsQuantity, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Number of columns is 0");
        }

        rows = new Vector[vectors.length];
        int columnsQuantity = 1;

        for (Vector vector : vectors) {
            if (vector.getSize() > columnsQuantity) {
                columnsQuantity = vector.getSize();
            }
        }

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(columnsQuantity);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return new Vector(rows[0]).getSize();
        //return rows[getRowsCount() - 1].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal index of rows. Your index = " + index + ". Minimal index = 0. "
                    + " Maximal index = " + (rows.length - 1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal index of rows. Your index = " + index + ". Minimal index = 0. "
                    + " Maximal index = " + (rows.length - 1));
        }

        int size = vector.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, vector.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Illegal index of rows. Your index = " + index + ". Minimal index = 0. "
                    + " Maximal index = " + (rows.length - 1));
        }

        int rowsQuantity = rows.length;
        Vector vector = new Vector(rowsQuantity);

        for (int i = 0; i < rowsQuantity; ++i) {
            vector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return vector;
    }

    public void transpose() {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsCount();

        for (int i = 0; i < rowsQuantity; ++i) {
            for (int j = i; j < columnsQuantity; ++j) {
                double temp = rows[i].getCoordinate(j);
                rows[i].setCoordinate(j, rows[j].getCoordinate(i));
                rows[j].setCoordinate(i, temp);
            }
        }
    }

    public void multiplyByScalar(double scalar) {
        for (Vector string : rows) {
            string.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsCount();

        if (rowsQuantity != columnsQuantity) {
            throw new UnsupportedOperationException("Determinant can be computed from the elements of a square matrix only. quantityRows = " + rowsQuantity + ", quantityColumns = " + columnsQuantity);
        }

        if (rowsQuantity == 1) {
            return new Vector(rows[0]).getCoordinate(0);
        }

        if (rowsQuantity == 2) {
            return rows[0].getCoordinate(0) * rows[1].getCoordinate(1)
                    - rows[0].getCoordinate(1) * rows[1].getCoordinate(0);
        }
/*
        if (rowsQuantity == 3) {
            return rows[0].getCoordinate(0) * rows[1].getCoordinate(1)
                    * rows[2].getCoordinate(2) + rows[2].getCoordinate(0)
                    * rows[0].getCoordinate(1) * rows[1].getCoordinate(2)
                    + rows[0].getCoordinate(2) * rows[1].getCoordinate(0)
                    * rows[2].getCoordinate(1) - rows[0].getCoordinate(2)
                    * rows[1].getCoordinate(1) * rows[2].getCoordinate(0)
                    - rows[1].getCoordinate(0) * rows[0].getCoordinate(1)
                    * rows[2].getCoordinate(2) - rows[0].getCoordinate(0)
                    * rows[2].getCoordinate(1) * rows[1].getCoordinate(2);
        }
*/
        int decompositionIndex = 0;
        double determinant = 0;

        for (int i = 0; i < rowsQuantity; i++) {
            determinant += Math.pow(-1, i) * rows[i].getCoordinate(decompositionIndex) *
                    getDeterminant(this, i, decompositionIndex);
        }

        return determinant;
    }

    private static double getDeterminant(Matrix matrix, int rowIndex, int columnIndex) {
        int minorSize = matrix.rows.length - 1;
        Matrix minor = new Matrix(minorSize, minorSize);

        for (int i = 0, columnIndexCheck = 0; i < matrix.rows.length; i++) {
            for (int j = 0, rowIndexCheck = 0; j < matrix.rows.length; j++) {
                if (i != rowIndex && j != columnIndex) {
                    minor.rows[columnIndexCheck].setCoordinate(rowIndexCheck, matrix.rows[i].getCoordinate(j));
                    rowIndexCheck++;

                    if (rowIndexCheck == minorSize) {
                        rowIndexCheck = 0;
                        columnIndexCheck++;
                    }
                }
            }
        }

        return minor.getDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        int size = rows.length - 1;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[size]).append("}");
        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        int rowsQuantity = rows.length;
        int quantityColumns = getColumnsCount();
        Vector multiplyResult = new Vector(rowsQuantity);

        if (quantityColumns != getColumnsCount()) {
            throw new IllegalArgumentException("No epta" + rowsQuantity + "quantityColumns" + quantityColumns);
        }

        for (int i = 0; i < rowsQuantity; ++i) {
            double sum = 0;

            for (int j = 0; j < quantityColumns; ++j) {
                sum += rows[i].getCoordinate(j) * vector.getCoordinate(j);
            }

            multiplyResult.setCoordinate(i, sum);
        }

        return multiplyResult;
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception. height = " + rows.length);
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].setCoordinate(i, Vector.getSum(new Vector(rows[i]), matrix.rows[i]).getCoordinate(i));
            //setRow(i, Vector.getSum(new Vector(rows[i]), matrix.rows[i]));
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception. height = " + rows.length);
        }
        Vector vector = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            rows[i].setCoordinate(i, Vector.getDifference(new Vector(rows[i]), matrix.rows[i]).getCoordinate(i));
            //setRow(i, Vector.getDifference(new Vector(rows[i]), matrix.rows[i]));
        }
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsCount() = " + matrix1.rows.length +
                    "matrix2.getRowsCount()" + matrix2.rows.length +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix1.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }


    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsCount() = " + matrix1.rows.length +
                    "matrix2.getRowsCount()" + matrix2.rows.length +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix1.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getComposition(Matrix matrix1, Matrix matrix2) {
        int rowCount1 = matrix1.rows.length;
        int rowCount2 = matrix2.rows.length;

        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsCount() = " + matrix1.rows.length +
                    "matrix2.getRowsCount() = " + matrix2.rows.length +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix2.getColumnsCount());
        }

        Matrix composition = new Matrix(rowCount1, rowCount2);

        for (int i = 0; i < rowCount1; ++i) {
            for (int j = 0; j < rowCount2; ++j) {
                composition.rows[i].setCoordinate(j, getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return composition;
    }
}
