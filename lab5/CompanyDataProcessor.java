/*
Лускина Юлия 5 группа

Выходные данные
1. Прочитать данные из входного файла.
2. Выбрать данные по запросу.
3. Записать полученные данные в два файла (в XML-формате и JSON).

Запросы
1. Найти компанию по краткому наименованию.
2. Выбрать компании по отрасли.
3. Выбрать компании по виду деятельности.
4. Выбрать компании по дате основания в определенном промежутке (с и по).
5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).

Необходимо вести статистику работы приложения в файле logfile.txt. Данные должны накапливаться.
Формат данных: дата и время запуска приложения; текст запроса.

logfile.txt
2024-11-10 22:08:49 - Запуск приложения
2024-11-10 22:08:56 - Запрос: Выбрать компании по отрасли 'услуги'
2024-11-10 22:12:32 - Запуск приложения
2024-11-10 22:12:50 - Запрос: Найти компанию по краткому наименованию 'доставка'
2024-11-10 22:18:36 - Запуск приложения
2024-11-10 22:19:23 - Запуск приложения
2024-11-10 22:19:29 - Запрос: Найти компанию по краткому наименованию 'доставка'

* */
package org.example;

import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.json.*;
import org.w3c.dom.*;

public class CompanyDataProcessor {
    public static String CSV_FILE = ".idea/input.txt"; // Убедитесь, что путь правильный
    private static final String XML_FILE = "output.xml";
    private static final String JSON_FILE = "output.json";
    private static final String LOG_FILE = "logfile.txt"; // Файл для логов

    private List<Company> companies = new ArrayList<>();

    public static void main(String[] args) {
        CompanyDataProcessor processor = new CompanyDataProcessor();
        processor.createLogFile(); // Создаем файл логов при запуске
        processor.log("Запуск приложения"); // Логирование запуска приложения
        processor.loadData();
        processor.displayCompanies(); // Вывод всех компаний после загрузки данных
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите запрос:");
            System.out.println("1. Найти компанию по краткому наименованию");
            System.out.println("2. Выбрать компании по отрасли");
            System.out.println("3. Выбрать компании по виду деятельности");
            System.out.println("4. Выбрать компании по дате основания в определенном промежутке");
            System.out.println("5. Выбрать компании по численности сотрудников в определенном промежутке");
            System.out.println("0. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер

            switch (choice) {
                case 1:
                    System.out.print("Введите краткое наименование: ");
                    String shortTitle = scanner.nextLine();
                    processor.findByShortTitle(shortTitle);
                    processor.log("Запрос: Найти компанию по краткому наименованию '" + shortTitle + "'");
                    break;
                case 2:
                    System.out.print("Введите отрасль: ");
                    String industry = scanner.nextLine();
                    processor.findByIndustry(industry);
                    processor.log("Запрос: Выбрать компании по отрасли '" + industry + "'");
                    break;
                case 3:
                    System.out.print("Введите вид деятельности: ");
                    String activity = scanner.nextLine();
                    processor.findByActivity(activity);
                    processor.log("Запрос: Выбрать компании по виду деятельности '" + activity + "'");
                    break;
                case 4:
                    System.out.print("Введите начальную дату (yyyy-MM-dd): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Введите конечную дату (yyyy-MM-dd): ");
                    String endDate = scanner.nextLine();
                    processor.findByFoundationDate(startDate, endDate);
                    processor.log("Запрос: Выбрать компании по дате основания с " + startDate + " по " + endDate);
                    break;
                case 5:
                    System.out.print("Введите минимальное число сотрудников: ");
                    int minEmployees = scanner.nextInt();
                    System.out.print("Введите максимальное число сотрудников: ");
                    int maxEmployees = scanner.nextInt();
                    processor.findByEmployeeCount(minEmployees, maxEmployees);
                    processor.log("Запрос: Выбрать компании по численности сотрудников от " + minEmployees + " до " + maxEmployees);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    processor.log("Выход из приложения");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private void createLogFile() {
        try {
            File logFile = new File(LOG_FILE);
            if (logFile.createNewFile()) {
                System.out.println("Файл логов создан: " + logFile.getName());
            } else {
                System.out.println("Файл логов уже существует.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла логов.");
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                companies.add(new Company(fields));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCompanies() {
        System.out.println("Список всех компаний:");
        for (Company company : companies) {
            System.out.println(company);
        }
    }

    private void findByShortTitle(String shortTitle) {
        String lowerShortTitle = shortTitle.toLowerCase();
        List<Company> foundCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getShortTitle().toLowerCase().equals(lowerShortTitle)) {
                System.out.println(company);
                foundCompanies.add(company);
            }
        }
        if (foundCompanies.isEmpty()) {
            System.out.println("Компания с кратким наименованием '" + shortTitle + "' не найдена.");
        } else {
            writeToXml(foundCompanies);
            writeToJson(foundCompanies);
        }
    }

    private void findByIndustry(String industry) {
        List<Company> foundCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getIndustry().equalsIgnoreCase(industry)) {
                System.out.println(company);
                foundCompanies.add(company);
            }
        }
        if (foundCompanies.isEmpty()) {
            System.out.println("Компании в отрасли '" + industry + "' не найдены.");
        } else {
            writeToXml(foundCompanies);
            writeToJson(foundCompanies);
        }
    }

    private void findByActivity(String activity) {
        List<Company> foundCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getActivity().equalsIgnoreCase(activity)) {
                System.out.println(company);
                foundCompanies.add(company);
            }
        }
        if (foundCompanies.isEmpty()) {
            System.out.println("Компании с видом деятельности '" + activity + "' не найдены.");
        } else {
            writeToXml(foundCompanies);
            writeToJson(foundCompanies);
        }
    }

    private void findByFoundationDate(String startDateStr, String endDateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);
            List<Company> foundCompanies = new ArrayList<>();
            for (Company company : companies) {
                if (company.getFoundationDate().after(startDate) && company.getFoundationDate().before(endDate)) {
                    System.out.println(company);
                    foundCompanies.add(company);
                }
            }
            if (foundCompanies.isEmpty()) {
                System.out.println("Компании, основанные в промежутке с " + startDateStr + " по " + endDateStr + ", не найдены.");
            } else {
                writeToXml(foundCompanies);
                writeToJson(foundCompanies);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void findByEmployeeCount(int min, int max) {
        List<Company> foundCompanies = new ArrayList<>();
        for (Company company : companies) {
            if (company.getEmployeeCount() >= min && company.getEmployeeCount() <= max) {
                System.out.println(company);
                foundCompanies.add(company);
            }
        }
        if (foundCompanies.isEmpty()) {
            System.out.println("Компании с численностью сотрудников от " + min + " до " + max + " не найдены.");
        } else {
            writeToXml(foundCompanies);
            writeToJson(foundCompanies);
        }
    }

    private void writeToXml(List<Company> foundCompanies) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("companies");
            doc.appendChild(rootElement);

            for (Company company : foundCompanies) {
                Element companyElement = doc.createElement("company");
                rootElement.appendChild(companyElement);
                companyElement.appendChild(createElementWithText(doc, "name", company.getName()));
                companyElement.appendChild(createElementWithText(doc, "shortTitle", company.getShortTitle()));
                companyElement.appendChild(createElementWithText(doc, "industry", company.getIndustry()));
                companyElement.appendChild(createElementWithText(doc, "activity", company.getActivity()));
                companyElement.appendChild(createElementWithText(doc, "foundationDate", new SimpleDateFormat("yyyy-MM-dd").format(company.getFoundationDate())));
                companyElement.appendChild(createElementWithText(doc, "employeeCount", String.valueOf(company.getEmployeeCount())));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE));
            transformer.transform(source, result);
            System.out.println("Данные сохранены в файл " + XML_FILE);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void writeToJson(List<Company> foundCompanies) {
        JSONArray jsonArray = new JSONArray();
        for (Company company : foundCompanies) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", company.getName());
            jsonObject.put("shortTitle", company.getShortTitle());
            jsonObject.put("industry", company.getIndustry());
            jsonObject.put("activity", company.getActivity());
            jsonObject.put("foundationDate", new SimpleDateFormat("yyyy-MM-dd").format(company.getFoundationDate()));
            jsonObject.put("employeeCount", company.getEmployeeCount());
            jsonArray.put(jsonObject);
        }

        try (FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(jsonArray.toString(4)); // Форматирование JSON
            file.flush();
            System.out.println("Данные сохранены в файл " + JSON_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            bw.write(timestamp + " - " + message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Element createElementWithText(Document doc, String name, String text) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(text));
        return element;
    }

    public List<Company> getCompanies() {
        return companies; // Возвращаем список компаний
    }

    class Company {
        private String name;
        private String shortTitle;
        private String industry;
        private String activity;
        private Date foundationDate;
        private int employeeCount;

        public Company(String[] fields) {
            this.name = fields[0];
            this.shortTitle = fields[1];
            this.industry = fields[2];
            this.activity = fields[3];
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                this.foundationDate = sdf.parse(fields[4]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.employeeCount = Integer.parseInt(fields[5]);
        }

        public String getName() {
            return name;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public String getIndustry() {
            return industry;
        }

        public String getActivity() {
            return activity;
        }

        public Date getFoundationDate() {
            return foundationDate;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", shortTitle='" + shortTitle + '\'' +
                    ", industry='" + industry + '\'' +
                    ", activity='" + activity + '\'' +
                    ", foundationDate=" + new SimpleDateFormat("yyyy-MM-dd").format(foundationDate) +
                    ", employeeCount=" + employeeCount +
                    '}';
        }
    }
}