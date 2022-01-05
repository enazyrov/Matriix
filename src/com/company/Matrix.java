package com.company;
import java.util.Arrays;

public class Matrix {
    private int rows;
    private int columns;
    private double[][] matrix;

    // Конструкторы
   public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
             throw new IllegalArgumentException("Заданы неверные размеры матрицы");
        }

        this.rows    = rows;
        this.columns = columns;
        this.matrix  = new double[rows][columns];
   }

   public Matrix(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException("Заданы неверные размеры матрицы");
        }

        this.rows = this.columns = size;
        this.matrix = new double[size][size];
   }

   //Методы
    private boolean checkSquare() {
        return rows == columns;
    }

    public void fill(int number) {

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = number;
            }
        }
    }

    public double ReturnElement (int row, int column) {
        if (rows < 0 || columns < 0 || row >= rows || column >= columns)
            throw new IllegalArgumentException("Заданы неверные индексы элемента");

        return matrix[row][column];
    }

    public void EditElement (int value, int row, int column) {
        if (row < 0 || column < 0 || row >= rows || column >= columns)
            throw new IllegalArgumentException("Заданы неверные индексы элемента");

        matrix[row][column] = value;
    }

    public int ReturnRows () {
        return rows;
    }

    public int ReturnColumns() {
       return columns;
    }

    public static Matrix sum(Matrix matrix1, Matrix matrix2) {

        if(!((matrix1.rows == matrix2.rows) && (matrix1.columns == matrix2.columns))) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }
        else {
            int size;

            if(!matrix1.checkSquare()) {
                size = Math.min(matrix1.rows, matrix1.columns);
            }
            else {
                size = matrix1.rows;
            }

            Matrix matrix = new Matrix(size);

            for(int i = 0; i < matrix1.rows; i++) {
                for(int j = 0; j < matrix1.columns; j++) {
                    matrix.matrix[i][j] = matrix1.matrix[i][j] + matrix2.matrix[i][j];
                }
            }
            return matrix;
        }
    }

    public static Matrix diff(Matrix matrix1, Matrix matrix2) {

        if(!((matrix1.rows == matrix2.rows) && (matrix1.columns == matrix2.columns))) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }
        else {
            int size;

            if(!matrix1.checkSquare()) {
                size = Math.min(matrix1.rows, matrix1.columns);
            }
            else {
                size = matrix1.rows;
            }

            Matrix matrix = new Matrix(size);

            for(int i = 0; i < matrix1.rows; i++) {
                for(int j = 0; j < matrix1.columns; j++) {
                    matrix.matrix[i][j] = matrix1.matrix[i][j] - matrix2.matrix[i][j];
                }
            }
            return matrix;
        }
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {

        if(!(matrix1.columns == matrix2.rows)) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }
        else {
            int size;
            if(!matrix1.checkSquare()) {
                size = Math.min(matrix1.rows, matrix1.columns);
            }
            else {
                size = matrix1.rows;
            }

            Matrix matrix = new Matrix(size);

            for(int i = 0; i < matrix1.matrix.length; i++) {
                for(int j = 0; j < matrix2.matrix[0].length; j++) {
                    for(int k = 0; k < matrix1.matrix[0].length; k++) {
                        matrix.matrix[i][j] += matrix1.matrix[i][k]*matrix2.matrix[k][j];
                    }
                }
            }
            return matrix;
        }
    }

    public static Matrix multiplyScalyar (Matrix matrix1, int scalyar) {
       int size =  matrix1.matrix.length;

       Matrix matrix = new Matrix(size);

       for(int i = 0; i < matrix1.rows; i++) {
           for(int j = 0; j < matrix1.columns; j++) {
                matrix.matrix[i][j] = matrix1.matrix[i][j] * scalyar;
           }
       }
       return matrix;
    }

    public double det() {
        if (rows != columns)
            throw new IllegalArgumentException("Матрица не квадратная");
        return calcDet(matrix);
    }


    public double calcDet(double matrix[][]){
        int size = matrix.length;
        if(size == 1) return matrix[0][0];

        int ans = 0;

        double B[][] = new double[size-1][size-1];
        int l = 1;
        for(int i = 0; i < size; ++i){

            int x = 0, y = 0;
            for(int j = 1; j < size; ++j){
                for(int k = 0; k < size; ++k){
                    if(i == k) continue;
                    B[x][y] = matrix[j][k];
                    ++y;
                    if(y == size - 1){
                        y = 0;
                        ++x;
                    }
                }
            }
            ans += l * matrix[0][i] * calcDet(B);
            l *= (-1);
        }
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                str.append(matrix[i][j] + " ");
            }
            str.append("\n");
        }

        str.append("\n");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Matrix that = (Matrix) obj;
        if (rows != that.rows || columns != that.columns) return false;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (matrix[i][j] != that.ReturnElement(i, j)) return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}













