package ru.academits.findyurov.matrix.main;

import ru.academits.findyurov.matrix.Matrix;
import ru.academits.findyurov.vector.Vector;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(4, 4);
        System.out.println(matrix1.toString());
        System.out.println();

        double[][] matrix = {
                {1, 2, 7, 21},
                {5, 5, 9, 11},
                {3, 8, 11, 4},
                {2, 3, 1, 12}
        };


        Matrix matrix2 = new Matrix(matrix);
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("determinant = " + matrix2.getDeterminant());
        System.out.println("Columns quantity = " + matrix2.getColumnsCount());
        System.out.println("Rows quantity = " + matrix2.getRowsCount());
        System.out.println();

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println("Copy Constructor(academ.findyurov.matrix.Matrix 3): ");
        System.out.println(matrix3.toString());
        System.out.println();

        int i = 0;
        System.out.println("Row number " + (i + 1) + ":");
        System.out.println(matrix2.getRow(i).toString());
        System.out.println();

        double[] value = {20, 2, 5, 6};
        System.out.println("let's change it to " + Arrays.toString(value));
        matrix2.setRow(i, new Vector(value));
        System.out.println(matrix2.getRow(0).toString());
        System.out.println();

        i = 1;
        System.out.println("Column number " + (i + 1) + ":");
        System.out.println(matrix2.getColumn(i).toString());
        System.out.println();

        System.out.println("got the matrix ");
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Transform the matrix: ");
        matrix2.transpose();
        System.out.println(matrix2.toString());
        System.out.println();

        double alpha = 6;
        System.out.println("Multiply the matrix by a scalar: " + alpha);
        matrix2.multiplyOnScalar(alpha);
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Multiplying a matrix by a vector ");
        Vector vector = new Vector(new double[]{0, 0, 0, 1});
        System.out.println(vector.toString() + ":");
        Vector mulOnVec = matrix2.mul(vector);
        System.out.println(mulOnVec.toString());
        System.out.println();

        System.out.println("A matrix created from an array of vectors:");
        Vector[] vectors = new Vector[]{new Vector(new double[]{1, 4, 2}), new Vector(new double[]{3, 6, 7})};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println(matrix4.toString());

        System.out.println("Let's add the matrix 2 and 3 statically:");
        Matrix matrix5 = Matrix.sum(matrix2, matrix3);
        System.out.println(matrix5.toString());
        System.out.println();

        System.out.println("Subtract matrix 3 from matrix 2 (statically):");
        Matrix matrix6 = Matrix.sub(matrix2, matrix3);
        System.out.println(matrix6.toString());
        System.out.println();

        System.out.println("Let's add matrix 3 to matrix 2:");
        matrix2.sum(matrix3);
        System.out.println("Now matrix 2 has the form: ");
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Now we subtract matrix 3 from the new matrix 2:");
        matrix2.sub(matrix3);
        System.out.println("Now matrix 2 has the form: ");
        System.out.println(matrix2.toString());
        System.out.println();

        System.out.println("Multiply the matrix (unit) by the matrix 3:");
        matrix1 = new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });

        Matrix matrix7 = Matrix.mul(matrix1, matrix3);
        System.out.println(matrix7.toString());
    }
}
