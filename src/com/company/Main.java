package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3,2);
        Matrix matrix2 = new Matrix(3);

        System.out.println(matrix1.ReturnColumns());

        System.out.print(matrix1.equals(matrix2));
    }
}
