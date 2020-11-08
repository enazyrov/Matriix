package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2);
        Matrix matrix2 = new Matrix(2);

        System.out.println(matrix1.ReturnColumns());
        System.out.println(matrix2.ReturnRows());

        matrix1.EditElement(2, 0, 0);
        matrix1.EditElement(3, 0, 1);
        matrix1.EditElement(-3, 1, 0);
        matrix1.EditElement(6, 1, 1);

        matrix2.EditElement(1, 0, 0);
        matrix2.EditElement(-2, 0, 1);
        matrix2.EditElement(4, 1, 0);
        matrix2.EditElement(0, 1, 1);

        System.out.println("Cумма: \n" + matrix1.sum(matrix1, matrix2));
        System.out.println("Разность: \n" + matrix1.diff(matrix1, matrix2));
        System.out.println("Умножение: \n" + matrix1.multiply(matrix1, matrix2));
        System.out.println("Умножение первой матрицы на скаляр: \n" + matrix1.multiplyScalyar(matrix1, 2));
        System.out.println("Определитель первой матрицы: \n" + matrix1.det());
        System.out.println("Сравнение матриц: \n" + matrix1.equals(matrix2));
    }
}
