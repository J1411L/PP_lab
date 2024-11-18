/* Лускина Юлия

9.	Задан текстовый файл input.txt. Требуется определить строки этого файла, 
содержащие максимальную по длине подстроку, состоящую только из знаков препинания. 
Перечень символов, подходящих под определение “знаки препинания”, задает пользователь. 
Если таких строк несколько, найти первые 10. Результат вывести на консоль в форме,
удобной для чтения. 

Введите знаки препинания:
,.!:;?
Строка: "Hello, world! How are you?", длина подстроки знаков препинания: 2
Строка: "This is a test: do you see the punctuation?", длина подстроки знаков препинания: 1
Строка: "Amazing!!! What a beautiful day... isn't it?", длина подстроки знаков препинания: 3
Строка: "Just some text without punctuation", длина подстроки знаков препинания: 0
Строка: "Another line; with some: punctuation, here!", длина подстроки знаков препинания: 2


Обработка каждой строки:
Для каждой строки вызывается метод getMaxPunctuationLength, который определяет максимальную 
длину последовательности знаков препинания.
Программа выводит строку и длину подстроки знаков препинания в консоль.
Сравнение с текущей максимальной длиной:
Если текущая длина подстроки больше maxLength, то обновляется maxLength, и список linesWithMaxPunctuation очищается и заполняется текущей строкой.
Если длина подстроки равна maxLength, строка добавляется в список.
Вывод результатов:
После обработки всех строк программа выводит первые 10 строк из linesWithMaxPunctuation, если таковые имеются.
Метод getMaxPunctuationLength:
Этот метод принимает строку и строку знаков препинания как параметры.
Инициализируются переменные maxLength и currentLength для отслеживания текущей длины и максимальной длины.
В цикле программа перебирает символы строки:
Если символ является знаком препинания (проверяется с помощью indexOf), увеличивается currentLength.
Если символ не является знаком препинания, проверяется, не превышает ли currentLength maxLength. Если 
превышает, maxLength обновляется.
currentLength сбрасывается на 0.
В конце строки проверяется, нужно ли обновить maxLength с учетом оставшейся длины.
Метод возвращает maxLength.
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main9two {
    public static void main(String[] args) {
        // Подготовка для ввода знаков препинания
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Введите знаки препинания:");
        
        String punctuationMarks = "";
        try {
            punctuationMarks = br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
            return;
        }

        // Считывание строк из файла input.txt
        List<String> linesWithMaxPunctuation = new ArrayList<>();
        int maxLength = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                int currentMaxLength = getMaxPunctuationLength(line, punctuationMarks);
                System.out.println("Строка: \"" + line + "\", длина подстроки знаков препинания: " + currentMaxLength);
                
                // Сравнение с текущей максимальной длиной
                if (currentMaxLength > maxLength) {
                    maxLength = currentMaxLength;
                    linesWithMaxPunctuation.clear(); // Очистка списка, если найдена новая максимальная длина
                    linesWithMaxPunctuation.add(line);
                } else if (currentMaxLength == maxLength && maxLength > 0) {
                    linesWithMaxPunctuation.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        // Вывод первых 10 строк с максимальной длиной подстроки знаков препинания
        System.out.println("Строки с максимальной длиной подстроки знаков препинания:");
        for (int i = 0; i < Math.min(10, linesWithMaxPunctuation.size()); i++) {
            System.out.println(linesWithMaxPunctuation.get(i));
        }
    }

    private static int getMaxPunctuationLength(String line, String punctuationMarks) {
        int maxLength = 0;
        int currentLength = 0;

        for (char c : line.toCharArray()) {
            if (punctuationMarks.indexOf(c) != -1) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
                currentLength = 0; // Сброс длины, если встретили не знак препинания
            }
        }
        // Проверка в конце строки
        if (currentLength > maxLength) {
            maxLength = currentLength;
        }

        return maxLength;
    }
}