package com.company;
import java.util.Scanner;

public class Matrix {
    public int rows;                        //строки
    public int columns;                     //столбцы
    public int [][] matrix;                 //сама матрица

    // Конструкторы
   public Matrix(int rows, int columns) throws Exception {

        if (rows <= 0 || columns <= 0) {
        throw new Exception("Заданы неверные размеры матрицы");
        }
        this.rows    = rows;
        this.columns = columns;
        this.matrix  = new int[rows][columns];
    }

    public Matrix(int size) throws Exception {
        if(size <= 0) {
            throw new Exception("Заданы неверные размеры матрицы");
        }
        this.rows = this.columns = size;
        this.matrix = new int[size][size];
    }

    public Matrix(int[][] matrix) {
        this.rows    = matrix.length;
        this.columns = matrix[0].length;
        this.matrix  = matrix;
    }

    //Методы
    public boolean checkSquare() {
        return rows == columns;
    }

    public void fill(int number) {

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = number;
            }
        }
    }

    private boolean check(Matrix matrix1, Matrix matrix2, boolean forMultiply) {
        if(forMultiply) {
            return ((matrix1.rows == matrix2.rows) && (matrix1.columns == matrix2.columns)) ||
                    (matrix1.columns == matrix2.rows);
        }
        else {
            return (matrix1.rows == matrix2.rows) && (matrix1.columns == matrix2.columns);
        }
    }

   public final void print() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void Initialize () {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
    }

    public void ReturnElement (int ElemRow, int ElemColumn) throws Exception {
        int n;

        Scanner in = new Scanner(System.in);

        if (ElemRow <= 0 || ElemColumn <= 0) {
            throw new Exception("Заданы неверные индексы элемента");
        }

        if (ElemRow > matrix.length || ElemColumn > matrix[0].length) {
            throw new Exception("Введен индекс больше размеров матрицы");
        }

        System.out.println("Значение элемента: " + matrix[ElemRow-1][ElemColumn-1]);
        System.out.print("Хотите ли вы изменить значение элемента? Нажмите 1 если хотите ");
        n = in.nextByte();
        switch (n) {
            case 1:
                matrix[ElemRow-1][ElemColumn-1] = in.nextInt();
                break;
            default: break;
        }
    }

    public void ReturnSize () {
        System.out.println("Матрица имеет " + rows + " строк и "+ columns + " столбцов.");
    }

    public  void sum(Matrix matrix1, Matrix matrix2) throws Exception {

        if(!check(matrix1, matrix2, false)) {
            throw new Exception("Размеры матриц не совпадают");
        }
        else {
            int size;

            if(!matrix1.checkSquare()) {
                size = matrix1.rows > matrix1.columns ? matrix1.columns : matrix1.rows;
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
            System.out.println("Сумма этих матриц: ");
            matrix.print();
        }
    }

    public void diff(Matrix matrix1, Matrix matrix2) throws Exception {

        if(!check(matrix1, matrix2, false)) {
            throw new Exception("Размеры матриц не совпадают");
        }
        else {
            int size;

            if(!matrix1.checkSquare()) {
                size = matrix1.rows > matrix1.columns ? matrix1.columns : matrix1.rows;
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
            System.out.println("Разность этих матриц: ");
            matrix.print();
        }
    }

    public void multiply(Matrix matrix1, Matrix matrix2) throws Exception {

        if(!check(matrix1, matrix2, true)) {
            throw new Exception("Размеры матриц не совпадают");
        }
        else {

            int size;
            int n;

            if(!matrix1.checkSquare()) {
                size = matrix1.rows > matrix1.columns ? matrix1.columns : matrix1.rows;
                n = matrix1.rows > matrix1.columns ? matrix1.rows : matrix1.columns;
            }
            else {
                size = matrix1.rows;
                n = matrix1.rows;
            }

            Matrix matrix = new Matrix(size);
            matrix.fill(0);

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    for(int k = 0; k < n; k++) {
                        matrix.matrix[i][j] += matrix1.matrix[i][k]*matrix2.matrix[k][j];
                    }
                }
            }
            System.out.println("Произведение этих матриц: ");
            matrix.print();
        }
    }

    public void multiplyScalyar (Matrix matrix, int scalyar) {
        for(int i = 0; i < matrix.rows; i++) {
            for(int j = 0; j < matrix.columns; j++) {
                matrix.matrix[i][j] = matrix.matrix[i][j] * scalyar;
            }
        }
        matrix.print();
    }

    public int det(int matrix[][]){
        int size = matrix.length;
        if(size == 1) return matrix[0][0];

        int ans = 0;

        int B[][] = new int[size-1][size-1];
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
            ans += l * matrix[0][i] * det(B);
            l *= (-1);
        }
        return ans;
    }

    public boolean Equal (Matrix matrix1, Matrix matrix2) throws Exception {
        boolean Aux = true;

        if(!check(matrix1, matrix2, true)) {
            throw new Exception("Размеры матриц не совпадают");
        }
        else {

            for(int i = 0; i < matrix1.rows; i++) {
                for(int j = 0; j < matrix1.columns; j++) {
                    if (matrix1.matrix[i][j] == matrix2.matrix[i][j]) {
                        continue;
                    }
                    else {
                        Aux = false;
                        break;
                    }
                }
            }
            return Aux;
        }
    }
}






