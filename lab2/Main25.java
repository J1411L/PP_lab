/* Лускина Юлия
25.	Определить, становится ли симметричной (относительно главной диагонали) заданная матрица 
после замены на число 0 каждого локального максимума. Элемент матрицы называется локальным максимумом, 
если он строго больше всех имеющихся у него соседей.


алгоритм:
Замена локальных максимумов на 0
Пройти по всем элементам матрицы.
Для каждого элемента вызвать функцию `isLocalMaximum`, чтобы проверить, является ли он локальным максимумом.
Если элемент является локальным максимумом, заменить его на 0.

Проверка симметричности матрицы
Вызвать функцию `checkSymmetry` для проверки, является ли матрица симметричной относительно главной диагонали.
Если матрица не квадратная, она не симметрична.
Пройти по элементам матрицы и сравнить `matrix[i][j]` с `matrix[j][i]`.
Если найдены элементы, которые не равны, вернуть `false`.


Функция `isLocalMaximum
Проверяет соседей для данного элемента, чтобы определить, является ли он локальным максимумом.
Пройти по всем соседям (всего 8 возможных) и сравнить их значения с текущим элементом.
Если найден хотя бы один сосед, который не меньше, вернуть `false`; в противном случае вернуть `true`.

*/

import java.util.Scanner;

public class Main25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размеров матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        // Ввод элементов матрицы с клавиатуры
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Вывод исходной матрицы
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        // Замена локальных максимумов на 0
        replaceLocalMaxima(matrix);

        // Вывод измененной матрицы
        System.out.println("Матрица после замены локальных максимумов на 0:");
        printMatrix(matrix);

        // Проверка симметричности
        boolean isSymmetric = checkSymmetry(matrix);
        System.out.println("Матрица симметрична относительно главной диагонали: " + isSymmetric);

        scanner.close();
    }

    // Метод для вывода матрицы
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    // Метод для замены локальных максимумов на 0
    private static void replaceLocalMaxima(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLocalMaximum(matrix, i, j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Метод для проверки, является ли элемент локальным максимумом
    private static boolean isLocalMaximum(int[][] matrix, int i, int j) {
        int value = matrix[i][j];
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Проверяем соседей
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                // Пропускаем сам элемент и проверяем границы
                if (x >= 0 && x < rows && y >= 0 && y < cols && (x != i || y != j)) {
                    if (matrix[x][y] >= value) {
                        return false; // Если нашел соседей, которые не меньше, не локальный максимум
                    }
                }
            }
        }
        return true; // Локальный максимум
    }

    // Метод для проверки симметричности матрицы
    private static boolean checkSymmetry(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Проверяем только квадратные матрицы
        if (rows != cols) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false; // Если элементы не равны, матрица не симметрична
                }
            }
        }
        return true; // Матрица симметрична
    }
}