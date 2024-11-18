import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainNew37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ввод размеров матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        // Создание матрицы как списка списков
        List<List<Integer>> matrix = new ArrayList<>();

        // Заполнение матрицы случайными числами от -10 до 10
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(random.nextInt(21) - 10); // Случайные числа от -10 до 10
            }
            matrix.add(row);
        }

        // Вывод исходной матрицы
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        // Получаем список характеристик столбцов
        int[] characteristics = new int[cols];
        for (int j = 0; j < cols; j++) {
            characteristics[j] = calculateColumnCharacteristic(matrix, j);
        }

        // Сортируем индексы столбцов по их характеристикам
        Integer[] indices = new Integer[cols];
        for (int j = 0; j < cols; j++) {
            indices[j] = j;
        }

        // Сортировка индексов на основе характеристик
        Arrays.sort(indices, Comparator.comparingInt(i -> characteristics[i]));

        // Создаем новую матрицу с отсортированными столбцами
        List<List<Integer>> sortedMatrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                newRow.add(matrix.get(i).get(indices[j]));
            }
            sortedMatrix.add(newRow);
        }

        // Вывод индексов старой матрицы в новом порядке
        System.out.println("Индексы столбцов в новом порядке:");
        for (int index : indices) {
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
    private static void printMatrix(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            for (Integer value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    // Метод для вычисления характеристики столбца
    private static int calculateColumnCharacteristic(List<List<Integer>> matrix, int col) {
        int sum = 0;
        for (int i = 0; i < matrix.size(); i++) {
            int value = matrix.get(i).get(col);
            if (value < 0 && value % 2 != 0) { // Отрицательный и нечетный
                sum += Math.abs(value);
            }
        }
        return sum;
    }
}