/* Лускина Юлия 

разработать Java-программу, которая будет обрабатывать файл с данными о пользователях и их учетных записях, 
используя регулярные выражения для извлечения информации

 Используя регулярное выражение, программа должна извлекать следующие данные из строки:
IP-адрес
Порт
Имя пользователя
Пароль
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCredentialsWithRegex {

    public static void main(String[] args) {
        String inputFilePath = "input.txt";   // Путь к входному файлу
        String outputFilePath = "output.txt"; // Путь к выходному файлу

        // Регулярное выражение для разбора строки
        String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+);([^;]+);([^\\s]+)";
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String ip = matcher.group(1);
                    String port = matcher.group(2);
                    String username = matcher.group(3);
                    String password = matcher.group(4);

                    writer.write("IP: " + ip);
                    writer.newLine();
                    writer.write("Port: " + port);
                    writer.newLine();
                    writer.write("Username: " + username);
                    writer.newLine();
                    writer.write("Password: " + password);
                    writer.newLine();
                    writer.write("-------------------------");
                    writer.newLine();
                }
            }
            System.out.println("Данные успешно обработаны и записаны в " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}