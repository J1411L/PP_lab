/* Лускина Юлия 5 группа


Модифицировать условие задачи учитывая свои пожелания
Возможно  авторское условие задачи
Использовать контейнеры: 
 Vector, ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap.)

1) Задача "контакты"
а) разработать класс Контакт, определяющий запись в электронной книге мобильного
телефона и содержащий по меньшей мере следующие поля:
- *Наименование (имя человека или организации)
- *Номер телефона мобильного
- Номер телефона рабочего
- Номер телефона (домашнего)
- Адрес электронной почты
- Адрес веб-страницы
- Адрес

Обязательными является поля помеченные *, остальные поля могут быть пустыми

б) класс Контакт должен реализовать:
-интерфейс Comparable и Comparator с возможностью выбора одного из полей для сравнения
-интерфейс Iterator - индексатор по всем полям объекта Контакт
-метод для сохранения значений всех полей в строке текста (переопределить toString())
-конструктор или метод для инициализации объекта из строки текста

в) Для тестирования класса Контакт, создать консольное приложение позволяющее
создать небольшой массив контактов и напечатать отсортированными по 
выбранному полю.



*/

/*
ТЕСТЫ:

Выберите контейнер (ArrayList, TreeSet, TreeMap): 
TreeSet
Выберите поле для сортировки (name, mobilePhone, email): 
name

Список контактов (TreeSet):
Contact{name='Алексей', mobilePhone='890-123-4567', workPhone='123-456-7890', homePhone='null', email='alexey@example.com', website='alexey.com', address='ул. Ленина, д. 1'}
Contact{name='Анна', mobilePhone='890-678-9012', workPhone='678-901-2345', homePhone='null', email='anna@example.com', website='anna.com', address='ул. С�довая, д. 6'}
Contact{name='Дмитрий', mobilePhone='890-345-6789', workPhone='345-678-9012', homePhone='456-789-0123', email='null', website='null', address='ул. Чехова, д. 3'}
Contact{name='Елена', mobilePhone='890-456-7890', workPhone='456-789-0123', homePhone='null', email='elena@example.com', website='elena.com', address='ул. Горького, д. 4'}
Contact{name='Иван', mobilePhone='890-789-0123', workPhone='789-012-3456', homePhone='890-123-4567', email='null', website='null', address='ул. Невский, д. 7'}
Contact{name='Мария', mobilePhone='890-234-5678', workPhone='234-567-8901', homePhone='null', email='maria@example.com', website='maria.com', address='ул. Пушкина, д. 2'}
Contact{name='Сергей', mobilePhone='890-567-8901', workPhone='567-890-1234', homePhone='null', email='sergey@example.com', website='sergey.com', address='ул. Тверская, д. 5'}
------------------------
Выберите контейнер (ArrayList, TreeSet, TreeMap): 
ArrayList
Выберите поле для сортировки (name, mobilePhone, email): 
mobilePhone

Список контактов (ArrayList):
Contact{name='Алексей', mobilePhone='890-123-4567', workPhone='123-456-7890', homePhone='null', email='alexey@example.com', website='alexey.com', address='ул. Ленина, д. 1'}
Contact{name='Мария', mobilePhone='890-234-5678', workPhone='234-567-8901', homePhone='null', email='maria@example.com', website='maria.com', address='ул. Пушкина, д. 2'}
Contact{name='Дмитрий', mobilePhone='890-345-6789', workPhone='345-678-9012', homePhone='456-789-0123', email='null', website='null', address='ул. Чехова, д. 3'}
Contact{name='Елена', mobilePhone='890-456-7890', workPhone='456-789-0123', homePhone='null', email='elena@example.com', website='elena.com', address='ул. Горького, д. 4'}
Contact{name='Сергей', mobilePhone='890-567-8901', workPhone='567-890-1234', homePhone='null', email='sergey@example.com', website='sergey.com', address='ул. Тверская, д. 5'}
Contact{name='Анна', mobilePhone='890-678-9012', workPhone='678-901-2345', homePhone='null', email='anna@example.com', website='anna.com', address='ул. Садовая, д. 6'}
Contact{name='Иван', mobilePhone='890-789-0123', workPhone='789-012-3456', homePhone='890-123-4567', email='null', website='null', address='ул. Невский, д. 7'}


*/

import java.util.*;
import java.io.*;

// Основной класс Contact
class Contact implements Comparable<Contact>, Iterable<String> {
    // Обязательные поля
    private String name;
    private String mobilePhone;

    // Необязательные поля
    private String workPhone;
    private String homePhone;
    private String email;
    private String website;
    private String address;

    // Конструктор с обязательными полями
    public Contact(String name, String mobilePhone) {
        this.name = name;
        this.mobilePhone = mobilePhone;
    }

    // Дополнительный конструктор для всех полей
    public Contact(String name, String mobilePhone, String workPhone, String homePhone, 
                   String email, String website, String address) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.homePhone = homePhone;
        this.email = email;
        this.website = website;
        this.address = address;
    }

    // Методы доступа к полям (геттеры)
    public String getName() { return name; }
    public String getMobilePhone() { return mobilePhone; }
    public String getWorkPhone() { return workPhone; }
    public String getHomePhone() { return homePhone; }
    public String getEmail() { return email; }
    public String getWebsite() { return website; }
    public String getAddress() { return address; }

    // Метод для отображения объекта в виде строки
    @Override
    public String toString() {
        return "Contact{name='" + name + "', mobilePhone='" + mobilePhone + "', workPhone='" + workPhone +
                "', homePhone='" + homePhone + "', email='" + email + "', website='" + website + "', address='" + address + "'}";
    }

    // Реализация интерфейса Comparable (по умолчанию сортировка по имени)
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    // Реализация интерфейса Iterable (позволяет перебор полей)
    @Override
    public Iterator<String> iterator() {
        List<String> fields = Arrays.asList(name, mobilePhone, workPhone, homePhone, email, website, address);
        return fields.iterator();
    }

    // Статические компараторы для сортировки по разным полям
    public static Comparator<Contact> compareByName() {
        return Comparator.comparing(Contact::getName);
    }

    public static Comparator<Contact> compareByMobilePhone() {
        return Comparator.comparing(Contact::getMobilePhone);
    }

    public static Comparator<Contact> compareByEmail() {
        return Comparator.comparing(Contact::getEmail, Comparator.nullsLast(String::compareTo));
    }

    // Метод для создания контакта из строки
    public static Contact fromString(String line) {
        String[] parts = line.split(",", -1);  // Используем -1, чтобы включить пустые поля
        return new Contact(
                parts[0].trim(),
                parts[1].trim(),
                parts.length > 2 ? parts[2].trim() : null,
                parts.length > 3 ? parts[3].trim() : null,
                parts.length > 4 ? parts[4].trim() : null,
                parts.length > 5 ? parts[5].trim() : null,
                parts.length > 6 ? parts[6].trim() : null
        );
    }
}

// Консольное приложение для тестирования
public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();

        // Чтение контактов из файла
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                contacts.add(Contact.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }
        
        // Запрос выбора контейнера и поля для сортировки
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Выберите контейнер (ArrayList, TreeSet, TreeMap): ");
        String containerChoice = scanner.nextLine();
        
        System.out.println("Выберите поле для сортировки (name, mobilePhone, email): ");
        String sortField = scanner.nextLine();
        
        // Сортировка списка в зависимости от выбора пользователя
        switch (sortField) {
            case "name":
                contacts.sort(Contact.compareByName());
                break;
            case "mobilePhone":
                contacts.sort(Contact.compareByMobilePhone());
                break;
            case "email":
                contacts.sort(Contact.compareByEmail());
                break;
            default:
                System.out.println("Неверный выбор. Сортировка по имени по умолчанию.");
                contacts.sort(Contact.compareByName());
                break;
        }
        
        // Вывод отсортированного списка в выбранном контейнере
        switch (containerChoice) {
            case "ArrayList":
                System.out.println("\nСписок контактов (ArrayList):");
                contacts.forEach(System.out::println);
                break;
            case "TreeSet":
                TreeSet<Contact> contactTreeSet = new TreeSet<>(contacts);
                System.out.println("\nСписок контактов (TreeSet):");
                contactTreeSet.forEach(System.out::println);
                break;
            case "TreeMap":
                TreeMap<String, Contact> contactTreeMap = new TreeMap<>();
                for (Contact contact : contacts) {
                    contactTreeMap.put(contact.getName(), contact);
                }
                System.out.println("\nСписок контактов (TreeMap):");
                contactTreeMap.forEach((name, contact) -> System.out.println(name + ": " + contact));
                break;
            default:
                System.out.println("Неверный выбор контейнера.");
                break;
        }
    }
}
