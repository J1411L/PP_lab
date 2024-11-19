/* Лускина Юлия

Регулярные выражения
Разработать консольное приложение на Java.
Постановка задачи
Для выполнения заданий использовать регулярные выражения.
Каждое задание реализовать в отдельном методе.
Строку получать из текстового файла input.txt.
Результат работы методов записывать в выходной текстовый файл output.txt.

    1. Из заданной строки исключить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.

    2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.

    3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.

Обработка строки:
Вызвать метод removeCharactersInBrackets(content):
Удалить символы, находящиеся внутри круглых скобок, включая сами скобки.
Сохранить результат в result1.
Вызвать метод removeExcessDigits(content):
Удалить из каждой группы идущих подряд цифр все цифры, начиная с третьей.
Сохранить результат в result2.
Вызвать метод removeLeadingZeros(content):
Удалить из каждой группы идущих подряд цифр все незначащие нули.
Сохранить результат в result3.

Метод removeCharactersInBrackets:
Использует регулярное выражение для удаления всех символов, находящихся внутри круглых скобок, включая сами скобки.
Метод removeExcessDigits:
Использует регулярное выражение для поиска групп цифр и удаления всех цифр, начиная с третьей.
Метод removeLeadingZeros:
Использует регулярное выражение для удаления незначащих нулей из групп цифр.


 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegexProcessor {

    public static void main(String[] args) {
        String inputFilePath = "input.txt";   // Путь к входному файлу
        String outputFilePath = "output.txt"; // Путь к выходному файлу

        try {
            // Чтение строки из файла
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // Обработка строк
            String result1 = removeCharactersInBrackets(content);
            String result2 = removeExcessDigits(content);
            String result3 = removeLeadingZeros(content);

            // Запись результатов в выходной файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write("1. Результат удаления символов в круглых скобках:\n" + result1 + "\n\n");
                writer.write("2. Результат удаления лишних цифр:\n" + result2 + "\n\n");
                writer.write("3. Результат удаления незначащих нулей:\n" + result3 + "\n");
            }

            System.out.println("Обработка завершена. Результаты записаны в " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1. Из строки исключить символы, расположенные внутри круглых скобок
    private static String removeCharactersInBrackets(String input) {
        return input.replaceAll("\\([^()]*\\)", "");
    }

    // 2. Удалить из группы идущих подряд цифр все цифры, начиная с третьей
    private static String removeExcessDigits(String input) {
        return input.replaceAll("(\\d{2})(\\d{1,})", "$1"); // Удаляем лишние цифры
    }

    // 3. Удалить из каждой группы идущих подряд цифр все незначащие нули
    private static String removeLeadingZeros(String input) {
        return input.replaceAll("\\b0+(\\d)", "$1");
    }
}