package org.example;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CompanyDataProcessorTest {
    private CompanyDataProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new CompanyDataProcessor();
    }

    @Test
    void testLoadData() throws IOException {
        // Подготовка: создаем тестовый CSV файл
        String testCsv = "Test Company;TC;Technology;Development;2020-01-01;100\n" +
                "Another Company;AC;Finance;Consulting;2018-05-15;50\n";
        File tempFile = File.createTempFile("test", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(testCsv);
        }

        // Изменяем путь к файлу для тестирования
        processor.CSV_FILE = tempFile.getAbsolutePath();
        processor.loadData();

        // Проверка, что данные загружены
        assertEquals(2, processor.getCompanies().size());
        assertEquals("Test Company", processor.getCompanies().get(0).getName());
    }

    @AfterEach
    void tearDown() {
        // Удаление временных файлов или очистка ресурсов, если это необходимо
    }
}