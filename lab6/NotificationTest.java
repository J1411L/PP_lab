/* Лускина Юлия 5 группа

Предположим, у нас есть система, которая отправляет уведомления пользователям.
Уведомления могут быть отправлены по электронной почте (Email) или через SMS.
У нас есть класс EmailSender, который отправляет электронные письма, и класс
SMSSender, который отправляет SMS-сообщения. Однако в системе есть интерфейс
INotificationSender, который требует методов с другими именами. Мы будем использовать
паттерн "Адаптер", чтобы адаптировать существующие классы к требованиям интерфейса

- EmailSender: класс для отправки электронных писем.
- SMSSender: класс для отправки SMS.
- INotificationSender: интерфейс для отправки уведомлений.
- EmailAdapter: адаптер для EmailSender.
- SMSAdapter: адаптер для SMSSender.
- NotificationService: класс для обработки уведомлений.
*/

// Класс для отправки электронных писем
class EmailSender {
    void sendEmail(String email, String message) {
        System.out.println("Отправлено электронное письмо на " + email + ": " + message);
    }
}

// Класс для отправки SMS
class SMSSender {
    void sendSMS(String phoneNumber, String message) {
        System.out.println("Отправлено SMS на " + phoneNumber + ": " + message);
    }
}

// Интерфейс для отправки уведомлений
interface INotificationSender {
    void sendNotification(String recipient, String message);
}

// Адаптер для EmailSender
class EmailAdapter implements INotificationSender {
    private EmailSender emailSender;

    EmailAdapter(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendNotification(String recipient, String message) {
        emailSender.sendEmail(recipient, message);
    }
}

// Адаптер для SMSSender
class SMSAdapter implements INotificationSender {
    private SMSSender smsSender;

    SMSAdapter(SMSSender smsSender) {
        this.smsSender = smsSender;
    }

    @Override
    public void sendNotification(String recipient, String message) {
        smsSender.sendSMS(recipient, message);
    }
}

// Класс для обработки уведомлений
class NotificationService {
    private INotificationSender sender;

    NotificationService(INotificationSender sender) {
        this.sender = sender;
    }

    void notifyUser(String recipient, String message) {
        sender.sendNotification(recipient, message);
    }
}

// Тестирование адаптеров и службы уведомлений
public class NotificationTest {
    public static void main(String[] args) {
        // Создаем экземпляры отправителей
        EmailSender emailSender = new EmailSender();
        SMSSender smsSender = new SMSSender();

        // Создаем адаптеры
        INotificationSender emailAdapter = new EmailAdapter(emailSender);
        INotificationSender smsAdapter = new SMSAdapter(smsSender);

        // Создаем службы уведомлений
        NotificationService emailService = new NotificationService(emailAdapter);
        NotificationService smsService = new NotificationService(smsAdapter);

        // Отправляем уведомления
        emailService.notifyUser("user@example.com", "Привет! Это тестовое сообщение по электронной почте.");
        smsService.notifyUser("123-456-7890", "Привет! Это тестовое SMS сообщение.");
    }
}
