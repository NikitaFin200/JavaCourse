package ru.academits.findyurov.matrix;

import ru.academits.findyurov.vector.Vector;

import static ru.academits.findyurov.vector.Vector.getScalarProduct;

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

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IllegalArgumentException("Illegal index of rows");
        }

        int size = vector.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, vector.getCoordinate(i));
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

    public void transpose() {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        for (int i = 0; i < rowsCount; ++i) {
            for (int j = i; j < columnsCount; ++j) {
                double temp = rows[i].getCoordinate(j);
                rows[i].setCoordinate(j, getRow(j).getCoordinate(i));
                rows[j].setCoordinate(i, temp);
            }
        }
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector element : this.rows) {
            element.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        int quantityRows = getRowsCount();
        int quantityColumns = getColumnsCount();

        if (quantityRows != quantityColumns) {
            throw new UnsupportedOperationException("Determinant can be computed from the elements of a square matrix only. quantityRows = " + quantityRows + ", quantityColumns = " + quantityColumns);
        }

        if (quantityRows == 1) {
            return getRow(0).getCoordinate(0);
        }

        if (quantityRows == 2) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) -
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(0);
        }

        if (quantityRows == 3) {
            return getRow(0).getCoordinate(0) * getRow(1).getCoordinate(1) *
                    getRow(2).getCoordinate(2) + getRow(2).getCoordinate(0) *
                    getRow(0).getCoordinate(1) * getRow(1).getCoordinate(2) +
                    getRow(0).getCoordinate(2) * getRow(1).getCoordinate(0) *
                            getRow(2).getCoordinate(1) - getRow(0).getCoordinate(2) *
                    getRow(1).getCoordinate(1) * getRow(2).getCoordinate(0) -
                    getRow(1).getCoordinate(0) * getRow(0).getCoordinate(1) *
                            getRow(2).getCoordinate(2) - getRow(0).getCoordinate(0) *
                    getRow(2).getCoordinate(1) * getRow(1).getCoordinate(2);
        }

        int decompositionIndex = 0;
        double determinant = 0;

        for (int i = 0; i < quantityRows; i++) {
            determinant += Math.pow(-1, i) * getRow(i).getCoordinate(decompositionIndex) *
                    getMatrixDeterminant(this, i, decompositionIndex);
        }

        return determinant;
    }

    private static double getMatrixDeterminant(Matrix matrix, int indexRow, int indexColumn) {
        int size = matrix.getRowsCount() - 1;
        Matrix result = new Matrix(size, size);

        for (int i = 0, checkColumn = 0; i < matrix.getRowsCount(); i++) {
            for (int j = 0, checkRow = 0; j < matrix.getRowsCount(); j++) {
                if (i != indexRow && j != indexColumn) {
                    result.rows[checkColumn].setCoordinate(checkRow, matrix.getRow(i).getCoordinate(j));
                    checkRow++;

                    if (checkRow == size) {
                        checkRow = 0;
                        checkColumn++;
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
            stringBuilder.append(rows[i]).append(",");
        }

        stringBuilder.append(rows[size - 1]).append("}");
        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        int quantityRows = getRowsCount();
        int quantityColumns = getColumnsCount();
        Vector multiplyResult = new Vector(quantityRows);

        if (quantityRows != getRowsCount() || quantityColumns != getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception. quantityRows = " + quantityRows + "quantityColumns" + quantityColumns);
        }

        for (int i = 0; i < quantityRows; ++i) {
            double sum = 0;

            for (int j = 0; j < quantityColumns; ++j) {
                sum += getRow(i).getCoordinate(j) * vector.getCoordinate(j);
            }

            multiplyResult.setCoordinate(i, sum);
        }

        return multiplyResult;
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception. height = " + rows.length);
        }

        for (int i = 0; i < rows.length; ++i) {
            setRow(i, Vector.getSum(new Vector(rows[i]), matrix.getRow(i)));
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Illegal Argument Exception. height = " + rows.length);
        }
        Vector vector = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            setRow(i, Vector.getDifference(new Vector(rows[i]), matrix.getRow(i)));
        }
    }

    public static Matrix getSubtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsCount() = " + matrix1.getRowsCount() +
                    "matrix2.getRowsCount()" + matrix2.getRowsCount() +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix1.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }


    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsCount() = " + matrix1.getRowsCount() +
                    "matrix2.getRowsCount()" + matrix2.getRowsCount() +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix1.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getComposition(Matrix matrix1, Matrix matrix2) {
        int rowCount1 = matrix1.getRowsCount();
        int rowCount2 = matrix2.getRowsCount();

        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsCount() = " + matrix1.getRowsCount() +
                    "matrix2.getRowsCount() = " + matrix2.getRowsCount() +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix2.getColumnsCount());
        }

        Matrix composition = new Matrix(rowCount1, rowCount2);

        for (int i = 0; i < rowCount1; ++i) {
            for (int j = 0; j < rowCount2; ++j) {
                composition.rows[i].setCoordinate(j, getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j)));
            }
        }

        return composition;
    }
}
