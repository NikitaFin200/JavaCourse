package ru.academits.findyurov.matrix;

import ru.academits.findyurov.vector.Vector;

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
        int rowsQuantity = getRowsQuantity();

        /*
        System.out.println("Matrix");
        for (Vector row : rows) {
            System.out.println(Arrays.toString(new Vector[]{row}));
        }
        */ //вывод основной матрицы


        Vector[] vec;
        vec = new Vector[columnsQuantity];

        for (int i = 0; i < getRowsQuantity(); ++i) {
            vec[i] = new Vector(getRowsQuantity());
        }

        for (int i = 0; i < getColumnsCount(); i++) {
            vec[i] = getColumn(i);
        }

        /*
        System.out.println("Matrix2");

        for (Vector row : vec) {
            System.out.println(Arrays.toString(new Vector[]{row}));
        }*/ //вывод матрицы

        rows = new Vector[vec.length];//меняем размер основной матрицы

        for (int i = 0; i < vec.length; ++i) {
            rows[i] = new Vector(vec[0].getSize()); //меняем размеры векторов
        }

        for (int i = 0; i <= getColumnsCount(); i++) { //меняем основной массив векторов
            rows[i] = vec[i];
        }

        /*System.out.println("rows");

        for (Vector row : rows) {
            System.out.println(Arrays.toString(new Vector[]{row}));
        }

        System.out.println();
         */ //обычная печать матрицы
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsCount();

        /*
        if (rowsQuantity != columnsQuantity) {
            throw new UnsupportedOperationException("Determinant can be computed from the elements of a square matrix only. rowsQuantity = "
                    + rowsQuantity + ", quantityColumns = " + columnsQuantity);
        }*/

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
        int maximalIndex = rows.length - 1;

        for (int i = 0; i < maximalIndex; i++) {
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[maximalIndex]).append("}");
        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        int rowsQuantity = rows.length;
        int columnsQuantity = getColumnsCount();

        if (rowsQuantity != vector.getSize() && columnsQuantity != vector.getSize()) {
            throw new IllegalArgumentException("The rows quantity does not match the size. rowsQuantity = "
                    + rowsQuantity + " columnsQuantity = " + columnsQuantity +
                    " vector size = " + vector.getSize());
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

    public void checkMatrix(Vector[] matrix1, Matrix matrix2) {
        if (rows.length != matrix2.rows.length || getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Now the dimensions of the matrix are not equal. Height matrix1 = "
                    + rows.length + ", width matrix1 = " + getColumnsCount()
                    + "Height matrix2 = " + matrix2.rows.length
                    + ", width matrix2" + matrix2.getColumnsCount());
        }
    }

    public void checkMatrix(Matrix matrix2) {
        if (rows.length != matrix2.rows.length || getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Now the dimensions of the matrix are not equal. Height matrix1 = "
                    + rows.length + ", width matrix1 = " + getColumnsCount()
                    + ". Height matrix2 = " + matrix2.rows.length
                    + ", width matrix2 = " + matrix2.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatrix(rows, matrix);

        for (int i = 0; i < rows.length; ++i) {
            //Vector.getSum(rows[i], matrix.rows[i]);
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrix(rows, matrix);

        for (int i = 0; i < rows.length; ++i) {
            //Vector.getDifference(rows[i], matrix.rows[i]);
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatrix(matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }


    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatrix(matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int rowsQuantity1 = matrix1.rows.length;
        int rowsQuantity2 = matrix2.rows.length;
        int columnsQuantity1 = matrix1.getColumnsCount();
        int columnsQuantity2 = matrix2.getColumnsCount();

        if (rowsQuantity1 != rowsQuantity2 && columnsQuantity1 != columnsQuantity2) {
            throw new IllegalArgumentException("The rows quantity does not match the size. rowsQuantity1 = "
                    + rowsQuantity1 + ", columnsQuantity1 = " + columnsQuantity1 +
                    ", rowsQuantity2" + rowsQuantity2 + ", columnsQuantity2" + columnsQuantity2);
        }

        Matrix product = new Matrix(rowsQuantity1, columnsQuantity1);

        for (int i = 0; i < rowsQuantity1; ++i) {
            for (int j = 0; j < rowsQuantity2; ++j) {
                product.rows[i].setCoordinate(j, Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return product;
    }
}
