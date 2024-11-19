b /* Лускина Юлия 

Лабораторная работа 42*.  Поиск текста на HTML-странице
 
Разработать консольное приложение на Java.
 
Постановка задачи
 
Входные данные:
Входной файл input1.html содержит текст, написанный на языке HTML.
В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых 
скобках, каждому открывающему тегу ставится в соответствие закрывающий тег. Например, пара тегов<b></b>.
Между тегами находится текст, причем теги не разрывают текст. Например, при поиске слова hello 
комбинация h<b><i>el</i>l</b>o должна быть найдена.
Гарантируется,что страница HTML является корректной, т.е. все символы "<" и ">" используются только 
в тегах, все теги записаны корректно.
Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле, записанных 
через разделители (точка с запятой). Может быть несколько строк.
 
Примечание: Ваша программа должна игнорировать различие между строчными и прописными буквами и для 
тегов и для искомого контекста. 
 
Выходные данные:
1. В выходной файл output1.out вывести список всех тегов в порядке возрастания количества символов тега.
2. В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла, в которых был 
найден искомый контекст в первый раз или -1 , если не был найден.
3. В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.




 
Чтение HTML-файла:
Открыть файл input1.html.
Прочитать содержимое файла в строку.
Разделить содержимое на строки и сохранить в список.
Поиск тегов в HTML:
Создать пустое множество для хранения уникальных тегов.
Определить регулярное выражение для поиска открывающих и закрывающих тегов.
Для каждой строки из списка:
Применить регулярное выражение для поиска тегов.
Добавить найденные теги в множество.
Запись тегов в выходной файл:
Открыть файл output1.out.
Записать каждый тег из множества в файл, по одному на строку.
Чтение фрагментов текста:
Открыть файл input2.in.
Прочитать содержимое файла, разделяя строки по точке с запятой.
Сохранить все фрагменты в список.
Поиск фрагментов в HTML:
Создать два списка: один для номеров строк, где были найдены фрагменты, и другой для отсутствующих фрагментов.
Для каждого фрагмента:
Преобразовать фрагмент в нижний регистр для игнорирования регистра.
Создать регулярное выражение для поиска фрагмента в строках HTML.
Для каждой строки HTML:
Проверить, содержится ли фрагмент в строке с помощью регулярного выражения.
Если найден, сохранить номер строки и завершить поиск для этого фрагмента.
Если не найден, добавить -1 в список номеров строк и сохранить фрагмент в список отсутствующих.
*/


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTagFinder {

    public static void main(String[] args) {
        String inputHtmlFilePath = "input1.html";  // Путь к HTML файлу
        String inputFragmentsFilePath = "input2.in"; // Путь к файлу с фрагментами
        String outputTagsFilePath = "output1.out";   // Путь к выходному файлу с тегами
        String outputLineNumbersFilePath = "output2.out"; // Путь к выходному файлу с номерами строк
        String outputMissingFragmentsFilePath = "output3.out"; // Путь к выходному файлу с отсутствующими фрагментами

        try {
            // Чтение HTML файла
            String htmlContent = new String(Files.readAllBytes(Paths.get(inputHtmlFilePath)));
            List<String> lines = Arrays.asList(htmlContent.split("\\n"));

            // Нахождение тегов с помощью регулярных выражений
            Set<String> tags = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(String::compareToIgnoreCase));
            Pattern tagPattern = Pattern.compile("<(\\w+)(\\s*[^>]*)?>|</(\\w+)>");
            
            for (String line : lines) {
                Matcher matcher = tagPattern.matcher(line);
                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        tags.add(matcher.group(1));
                    }
                    if (matcher.group(3) != null) {
                        tags.add(matcher.group(3));
                    }
                }
            }

            // Запись тегов в output1.out
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputTagsFilePath))) {
                for (String tag : tags) {
                    writer.write(tag);
                    writer.newLine();
                }
            }

            // Чтение фрагментов текста
            List<String> fragments = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFragmentsFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fragments.addAll(Arrays.asList(line.split(";")));
                }
            }

            // Поиск фрагментов в HTML с помощью регулярных выражений
            List<Integer> foundLineNumbers = new ArrayList<>();
            List<String> missingFragments = new ArrayList<>();

            for (String fragment : fragments) {
                boolean found = false;
                String lowerFragment = fragment.trim().toLowerCase();
                
                // Регулярное выражение для поиска фрагмента
                String regex = Pattern.quote(lowerFragment);
                
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i).toLowerCase();
                    Matcher matcher = Pattern.compile(regex).matcher(line);
                    if (matcher.find()) {
                        foundLineNumbers.add(i);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    foundLineNumbers.add(-1);
                    missingFragments.add(lowerFragment);
                }
            }

            // Запись номеров строк в output2.out
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputLineNumbersFilePath))) {
                for (Integer lineNumber : foundLineNumbers) {
                    writer.write(String.valueOf(lineNumber));
                    writer.newLine();
                }
            }

            // Запись отсутствующих фрагментов в output3.out
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputMissingFragmentsFilePath))) {
                for (String missingFragment : missingFragments) {
                    writer.write(missingFragment);
                    writer.newLine();
                }
            }

            System.out.println("Обработка завершена. Результаты записаны в выходные файлы.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}