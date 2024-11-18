/* Лускина Юлия
9.	Найти строку заданной матрицы, в которой длина максимальной серии (последовательности 
одинаковых элементов) минимальна.

алгоритм:
Установить переменную minMaxSeriesLength в максимальное значение (Integer.MAX_VALUE).
Установить переменную resultRowIndex для хранения индекса строки с минимальной длиной максимальной серии.
Поиск строки с минимальной длиной максимальной серии:
Для каждой строки матрицы:
Вызвать функцию findMaxSeriesLength, чтобы определить максимальную длину серии одинаковых элементов в строке.
Если найденная длина меньше текущего значения minMaxSeriesLength, обновить его и сохранить индекс строки.
Определение функции findMaxSeriesLength:
В этой функции:
Инициализировать переменные для отслеживания максимальной длины серии и текущей длины серии.
Проходить по элементам строки, сравнивая каждый элемент с предыдущим.
Увеличивать текущую длину серии при совпадении и обновлять максимальную длину при несовпадении.
Вернуть максимальную длину серии.
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainNew9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ввод размеров матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        // Создание матрицы как списка векторов
        List<List<Integer>> matrix = new ArrayList<>();

        // Заполнение матрицы случайными числами от 1 до 3
        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(random.nextInt(3) + 1); // Случайные числа от 1 до 3
            }
            matrix.add(row);
        }

        // Вывод матрицы на экран
        System.out.println("Сгенерированная матрица:");
        for (List<Integer> row : matrix) {
            for (Integer value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }

        int minMaxSeriesLength = Integer.MAX_VALUE;
        int resultRowIndex = -1;

        // Поиск строки с минимальной длиной максимальной серии
        for (int i = 0; i < rows; i++) {
            int maxSeriesLength = findMaxSeriesLength(matrix.get(i));
            if (maxSeriesLength < minMaxSeriesLength) {
                minMaxSeriesLength = maxSeriesLength;
                resultRowIndex = i;
            }
        }

        // Вывод результата
        int resultRowIndex1 = resultRowIndex + 1;
        System.out.println("Строка с минимальной длиной максимальной серии: " + resultRowIndex1);
        System.out.print("Элементы: ");
        for (Integer value : matrix.get(resultRowIndex)) {
            System.out.print(value + " ");
        }

        scanner.close();
    }

    // Метод для нахождения максимальной длины серии в строке
    private static int findMaxSeriesLength(List<Integer> row) {
        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < row.size(); i++) {
            if (row.get(i).equals(row.get(i - 1))) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }
        maxLength = Math.max(maxLength, currentLength); // Проверка на последнюю серию

        return maxLength;
    }
}