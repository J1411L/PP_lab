/* Лускина Юлия 

9.	Дана строка, слова в которой состоят из букв латинского алфавита и десятичных цифр. 
Остальные символы считаются разделителями между словами. Получить новую строку, выполняя 
в заданной строке замены по следующему правилу. Все слова, состоящие только из букв, 
записываются строчными буквами. Слова в исходной строке разделяются некоторым множеством 
разделителей. Слова в новой строке должны разделяться ровно одним пробелом. 

Введите строку:
Hello, World! 123, Test123; another_test!
Результат: hello world 123 Test123 another test

Инициализация результата:
Создается объект StringBuffer для накопления результата.
Разделение строки:
Входная строка разбивается на слова с использованием регулярного выражения 
[^a-zA-Z0-9]+, которое определяет разделители как все символы, не являющиеся латинскими буквами или цифрами.
Обработка каждого слова:
Программа перебирает все слова из массива words:
Если слово состоит только из букв (проверяется с помощью регулярного выражения [a-zA-Z]+):
Слово преобразуется в строчные буквы (toLowerCase()) и добавляется к результату с пробелом.
Если слово состоит из букв и цифр (проверяется с помощью регулярного выражения [a-zA-Z0-9]+):
Слово добавляется к результату без изменений и с пробелом.
Удаление лишнего пробела:
Если в результате есть хотя бы один символ, последний пробел удаляется с помощью метода deleteCharAt().

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Введите строку:");

        String input = "";
        try {
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
            return;
        }

        StringBuffer result = new StringBuffer();
        String[] words = input.split("[^a-zA-Z0-9]+"); // Разделяем по разделителям

        for (String word : words) {
            if (word.matches("[a-zA-Z]+")) { // Если слово состоит только из букв
                result.append(word.toLowerCase()).append(" "); // Добавляем в строчных буквах
            } else if (word.matches("[a-zA-Z0-9]+")) { // Если слово состоит из букв и цифр
                result.append(word).append(" "); // Добавляем без изменений
            }
        }

        // Удаление последнего пробела, если он есть
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }

        System.out.println("Результат: " + result.toString());
    }
}