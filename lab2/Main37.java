/* Лускина Юлия
37.	Характеристикой столбца целочисленной матрицы назовем сумму модулей его 
отрицательных нечетных элементов. Переставляя столбцы заданной матрицы, расположить 
их в соответствии с ростом характеристик

алгоритм:
Вычисление характеристик столбцов
Создать массив для хранения характеристик столбцов.
Для каждого столбца вызвать функцию `calculateColumnCharacteristic`, 
чтобы вычислить сумму модулей отрицательных нечетных элементов.

Сортировка индексов столбцов
Создать массив индексов столбцов.
Отсортировать индексы на основе значений характеристик с помощью `Arrays.sort` и `Comparator`.

Создать новую матрицу и заполнить ее, используя отсортированные индексы столбцов.

Функция `printMatrix`:
Выводит элементы матрицы на экран.

Функция `calculateColumnCharacteristic:
Вычисляет характеристику столбца, суммируя модули отрицательных нечетных элементов.
*/


import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ввод размеров матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        // Заполнение матрицы случайными числами от -10 до 10
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(21) - 10; // Случайные числа от -10 до 10
            }
        }

        // Вывод исходной матрицы
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        // Получаем массив характеристик столбцов
        int[] characteristics = new int[cols];
        for (int j = 0; j < cols; j++) {
            characteristics[j] = calculateColumnCharacteristic(matrix, j);
        }

        // Сортируем индексы столбцов по их характеристикам
        Integer[] indices = new Integer[cols];
        for (int j = 0; j < cols; j++) {
            indices[j] = j;
        }

        // Массив для хранения старых индексов в новом порядке
        Integer[] sortedIndices = new Integer[cols];
        Arrays.sort(indices, Comparator.comparingInt(i -> characteristics[i]));

        // Заполняем массив sortedIndices
        for (int j = 0; j < cols; j++) {
            sortedIndices[j] = indices[j];
        }

        // Создаем новую матрицу с отсортированными столбцами
        int[][] sortedMatrix = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                sortedMatrix[i][j] = matrix[i][indices[j]];
            }
        }

        // Вывод индексов старой матрицы в новом порядке
        System.out.println("Индексы столбцов в новом порядке:");
        for (int index : sortedIndices) {
            System.out.print(index + "\t");
        }
        System.out.println();

        // Вывод модулей характеристик
        System.out.println("Модули характеристик столбцов:");
        for (int j = 0; j < cols; j++) {
            System.out.print(Math.abs(characteristics[j]) + "\t");
        }
        System.out.println();

        // Вывод отсортированной матрицы
        System.out.println("Матрица после сортировки столбцов по характеристикам:");
        printMatrix(sortedMatrix);

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

    // Метод для вычисления характеристики столбца
    private static int calculateColumnCharacteristic(int[][] matrix, int col) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            int value = matrix[i][col];
            if (value < 0 && value % 2 != 0) { // Отрицательный и нечетный
                sum += Math.abs(value);
            }
        }
        return sum;
    }
}