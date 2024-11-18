import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите значение x: ");
        BigDecimal x = scanner.nextBigDecimal();

        System.out.print("Введите натуральное число k: ");
        int k = scanner.nextInt();

        BigDecimal epsilon = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k), MathContext.DECIMAL128);

        // Проверка на случай, если x равно 0
        if (x.compareTo(BigDecimal.ZERO) == 0) {
            System.out.printf("Приближенное значение sin(x)/x: %.3f%n", BigDecimal.ONE.setScale(3));
            System.out.printf("Стандартное значение sin(x)/x: %.3f%n", BigDecimal.ONE.setScale(3));
            scanner.close();
            return; // Завершаем программу
        }

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal term = x; // Первое слагаемое равно x
        int n = 0; // Счетчик для количества слагаемых
        int maxIterations = 1000;

        while (term.abs().compareTo(epsilon) >= 0 && n < maxIterations) {
            sum = sum.add(term);
            n++;

            // Вычисляем следующее слагаемое ряда Тейлора
            term = term.multiply(BigDecimal.valueOf(-1)) // Меняем знак
                    .multiply(x).multiply(x) // Умножаем на x^2
                    .divide(BigDecimal.valueOf((2 * n) * (2 * n + 1)), MathContext.DECIMAL128); // Делим на (2n)(2n + 1)
        }

        // Нормализуем сумму
        sum = sum.divide(x);

        // Вычисляем стандартное значение sin(x)/x
        BigDecimal standardValue = BigDecimal.valueOf(Math.sin(x.doubleValue())).divide(x, MathContext.DECIMAL128);

        System.out.printf("Приближенное значение sin(x)/x: %.3f%n", sum.setScale(3, RoundingMode.HALF_UP));
        System.out.printf("Стандартное значение sin(x)/x: %.3f%n", standardValue.setScale(3, RoundingMode.HALF_UP));

        scanner.close();
    }
}