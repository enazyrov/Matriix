package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        Matrix matrix1 = new Matrix(3,3);
        matrix1.fill(2);

        matrix1.ReturnElement(1,2);
        matrix1.ReturnElement(3,3);

        System.out.print("Определитель равен: " + matrix1.det(matrix1.matrix));


    }
}