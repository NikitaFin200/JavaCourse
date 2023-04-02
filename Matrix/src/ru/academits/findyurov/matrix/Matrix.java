package ru.academits.findyurov.matrix;

import ru.academits.findyurov.vector.Vector;

import java.lang.reflect.Array;
import java.util.Arrays;

// import static ru.academits.findyurov.vector.Vector.getScalarProduct;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (rowsQuantity < 0 || rowsQuantity == 0) {
            throw new IllegalArgumentException("Incorrect rows quantity." +
                    "The number must be greater than 0. Your value = " + rowsQuantity);
        }

        if (columnsQuantity < 0 || columnsQuantity == 0) {
            throw new IllegalArgumentException("Incorrect columns quantity." +
                    "The number must be greater than 0. Your value = " + rowsQuantity);
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
        if (array.length == 0) {
            throw new IllegalArgumentException("It is not possible to create a matrix with a zero size.");
        }

        rows = new Vector[array.length];

        int columnsQuantity = 0;

        for (double[] row : array) {
            if (row.length == 0) {
                throw new IllegalArgumentException("Rows quantity must be greater than 0.");
            }
            if (row.length > columnsQuantity) {
                columnsQuantity = row.length;
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

    public int getRowsQuantity() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
        //return rows[getRowsQuantity() - 1].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Illegal index of row. Your index = " + index + ". Minimal index = 0. "
                    + " Maximal index = " + (rows.length - 1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Illegal index of row. Your index = "
                    + index + ". Minimal index = 0. "
                    + " Maximal index = " + (rows.length - 1));
        }

        if (vector.getSize() != rows.length) {
            throw new IllegalArgumentException("The sizes are not equal");
        }

        int size = vector.getSize();

        for (int i = 0; i < size; ++i) {
            rows[index].setCoordinate(i, vector.getCoordinate(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IllegalArgumentException("Illegal index of row. Your index = " + index
                    + ". Minimal index = 0. " + " Maximal index = " + (getColumnsCount() - 1));
        }

        int rowsQuantity = rows.length;
        Vector vector = new Vector(rowsQuantity);

        for (int i = 0; i < rowsQuantity; ++i) {
            vector.setCoordinate(i, rows[i].getCoordinate(index));
        }

        return vector;
    }

    public void transpose() {
        int columnsQuantity = getColumnsCount();
        System.out.println(rows.length);
        System.out.println(rows[0].getSize());

        System.out.println("Matrix");
        for (Vector row : rows) {
            System.out.println(Arrays.toString(new Vector[]{row}));
        }

        System.out.println();

        /*
        System.out.println("Columns");
        for (int i = 0; i <= rows.length; i++) {
            System.out.println(getColumn(i));
        }*/

        System.out.println();
        //rows[3] = getColumn(4);

        Matrix mat2 = new Matrix(rows);

        System.out.println("Mat2");
        for (Vector row : mat2.rows) {
            System.out.println(Arrays.toString(new Vector[]{row}));
        }

        System.out.println();

        for (int i = 0; i < rows.length; i++) {
            mat2.rows[i] = getColumn(i);
        }

        System.out.println("Trans matrix");
        for (Vector row : mat2.rows) {
            System.out.println(Arrays.toString(new Vector[]{row}));
        }
        System.out.println();

        /*
        Vector[] rows2;
        Matrix matrix = new Matrix(getColumnsCount(), rows.length);
        for (int i = 0; i < matrix.rows.length; i++) {
            rows2 = new Vector[]{getColumn(i)};

            for (int j = 0; j < matrix.rows[i].getSize(); j++) {
                rows[i].setCoordinate(j, rows2[i].getCoordinate(j));

                /*
                System.out.println("rowss");
                System.out.println(Arrays.toString(new Vector[]{rows[i]}));

                System.out.println("rows2");
                System.out.println(Arrays.toString(rows2));

            }
        }*/
/*
        String[][] mat = new String[getColumnsCount()][rows.length];

        System.out.println(mat.length);
        System.out.println(mat[0].length);

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = String.valueOf(rows[i].getCoordinate(j));
                mat[j][i] = String.valueOf(rows[i].getCoordinate(i));
            }
        }


/*
 Matrix composition = new Matrix(rowCount1, rowCount2);

        for (int i = 0; i < rowCount1; ++i) {
            for (int j = 0; j < rowCount2; ++j) {
                composition.rows[i].setCoordinate(j, getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[0].length; j++) {
                str[i][j] = String.valueOf(rows[j].getCoordinate(i));
                str[j][i] = String.valueOf(rows[i].getCoordinate(j));
                /*
                rows[i].setCoordinate(j, rows[i].getCoordinate(i));
                rows[j].setCoordinate(i, temp);
                 */
        // }
        // }

        /*
        for (int i = 0; i < getColumnsCount(); i++) {
            for (int j = 0; i < rows.length; j++) {
                double temp = rows[j].getCoordinate(i);
                rows[j].setCoordinate(i, rows[i].getCoordinate(j));
                rows[i].setCoordinate(j, temp);
            }
        }

/*
        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < getColumnsCount(); ++j) {
                double temp = rows[i].getCoordinate(j);
                rows[i].setCoordinate(j, rows[j].getCoordinate(i));
                rows[j].setCoordinate(i, temp);
            }
        }
 */
    }

    public void multiplyByScalar(double scalar) {
        for (Vector string : rows) {
            string.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsCount();

        /*
        if (rowsQuantity != columnsQuantity) {
            throw new UnsupportedOperationException("Determinant can be computed from the elements of a square matrix only. quantityRows = " + rowsQuantity + ", quantityColumns = " + columnsQuantity);
        }
         */

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
        int columnsQuantity = getColumnsCount();

        if (rowsQuantity != vector.getSize()) {
            throw new IllegalArgumentException("The rows quantity does not match the size. rowsQuantity = " + rowsQuantity);
        }

        if (columnsQuantity != vector.getSize()) {
            throw new IllegalArgumentException("The number of columns does not match the size. columnsQuantity = " + columnsQuantity);
        }

        Vector multiplyResult = new Vector(rowsQuantity);

        for (int i = 0; i < rowsQuantity; ++i) {
            double sum = 0;

            for (int j = 0; j < columnsQuantity; ++j) {
                //multiplyResult.multiplyByScalar(vector.getCoordinate(j));
                sum += rows[i].getCoordinate(j) * vector.getCoordinate(j);
            }

            multiplyResult.setCoordinate(i, sum);
        }

        return multiplyResult;
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. rows.length = " + rows.length);
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].setCoordinate(i, Vector.getSum(rows[i], matrix.rows[i]).getCoordinate(i));
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. rows.length = " + rows.length);
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].setCoordinate(i, Vector.getDifference(rows[i], matrix.rows[i]).getCoordinate(i));
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsQuantity() = " + matrix1.rows.length +
                    "matrix2.getRowsQuantity()" + matrix2.rows.length +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix1.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }


    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsQuantity() = " + matrix1.rows.length +
                    "matrix2.getRowsQuantity()" + matrix2.rows.length +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix1.getColumnsCount());
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowsCount1 = matrix1.rows.length;
        int rowsCount2 = matrix2.rows.length;
        int columnsCount1 = matrix1.getColumnsCount();
        int columnsCount2 = matrix2.getColumnsCount();

        if (rowsCount1 != rowsCount2 || columnsCount1 != columnsCount2) {
            throw new IllegalArgumentException("Matrix size error. matrix1.getRowsQuantity() = " + matrix1.rows.length +
                    "matrix2.getRowsQuantity() = " + matrix2.rows.length +
                    "matrix1.getColumnsCount()" + matrix1.getColumnsCount() +
                    "matrix2.getColumnsCount()" + matrix2.getColumnsCount());
        }

        Matrix composition = new Matrix(rowsCount1, columnsCount1);

        for (int i = 0; i < rowsCount1; ++i) {
            for (int j = 0; j < rowsCount2; ++j) {
                composition.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return composition;
    }
}
