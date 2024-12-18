/* Лускина Юлия

Задача 9 Проверить является ли заданная строка шестизначным числом, 
записанным в десятичной системе счисления без нулей в старших разрядах. 

Пример правильных выражений: 
123456
234567 

Пример неправильных выражений: 
1234567
12345 

Считывание пользовательского ввода:
Читаем строку, введенную пользователем, с помощью метода nextLine().
Проверка строки:
Вызываем метод isValidSixDigitNumber, передавая ему введенную строку.
Метод возвращает true, если строка соответствует критериям, и false в противном случае.
Вывод результата проверки:
Если метод возвращает true:
Выводим сообщение, что строка является шестизначным числом.
Добавляем сообщение о том, что нет нулей в старших разрядах.
Если метод возвращает false:
Выводим сообщение, что строка не является шестизначным числом.
Закрытие объекта Scanner:
Закрываем Scanner, чтобы освободить ресурсы.
Определение метода isValidSixDigitNumber:
Этот метод принимает строку как параметр.
Использует регулярное выражение ^[1-9][0-9]{5}$ для проверки:
^ - начало строки.
[1-9] - первая цифра должна быть от 1 до 9 (без нуля).
[0-9]{5} - следующие 5 цифр могут быть любыми цифрами от 0 до 9.
$ - конец строки.
Метод возвращает true, если строка соответствует шаблону, иначе false.

*/


import java.util.Scanner;

public class SixDigitNumberChecker {
    public static void main(String[] args) {
        // Создаем объект Scanner для считывания пользовательского ввода
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку для проверки:");

        // Читаем строку, введенную пользователем
        String input = scanner.nextLine();

        // Проверяем, соответствует ли строка критериям шестизначного числа
        if (isValidSixDigitNumber(input)) {
            System.out.println(input + " - это шестизначное число.");
            System.out.println("Нет нулей в старших разрядах.");
        } else {
            System.out.println(input + " - это не шестизначное число.");
        }

        // Закрываем Scanner
        scanner.close();
    }

    /**
     * Метод для проверки, является ли строка шестизначным числом
     * без нулей в старших разрядах.
     * 
     * @param str строка для проверки
     * @return true, если строка соответствует условиям, иначе false
     */
    private static boolean isValidSixDigitNumber(String str) {
        // Проверяем, соответствует ли строка регулярному выражению
        return str.matches("^[1-9][0-9]{5}$");
    }
}