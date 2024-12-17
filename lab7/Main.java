import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class SortThread extends Thread {
    private Integer[] array;
    private boolean ascending;

    public SortThread(Integer[] array, boolean ascending) {
        this.array = array;
        this.ascending = ascending;
    }

    @Override
    public void run() {
        if (ascending) {
            Arrays.sort(array);
        } else {
            Arrays.sort(array, Comparator.reverseOrder());
        }
    }

    public Integer[] getSortedArray() {
        return array;
    }
}

public class Main extends JFrame {
    private JTextField sizeField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton sortButton;
    private JRadioButton ascendingButton;
    private JRadioButton descendingButton;
    private Integer[] array;

    public Main() {
        setTitle("Сортировка массива");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        sizeField = new JTextField(10);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        generateButton = new JButton("Сгенерировать");
        sortButton = new JButton("Сортировать");

        ascendingButton = new JRadioButton("Возрастание", true);
        descendingButton = new JRadioButton("Убывание");
        ButtonGroup group = new ButtonGroup();
        group.add(ascendingButton);
        group.add(descendingButton);

        add(new JLabel("Введите размер массива:"));
        add(sizeField);
        add(generateButton);
        add(new JLabel("Выберите порядок сортировки:"));
        add(ascendingButton);
        add(descendingButton);
        add(sortButton);
        add(new JScrollPane(outputArea));

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int size;
                try {
                    size = Integer.parseInt(sizeField.getText());
                    array = new Integer[size];
                    Random random = new Random();

                    for (int i = 0; i < size; i++) {
                        array[i] = random.nextInt(100); // Случайные числа от 0 до 99
                    }

                    outputArea.setText("Сгенерированный массив: " + Arrays.toString(array) + "\n");

                } catch (NumberFormatException ex) {
                    outputArea.setText("Ошибка: введите корректный размер массива.");
                }
            }
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (array == null) {
                    outputArea.setText("Ошибка: сначала сгенерируйте массив.");
                    return;
                }

                boolean ascending = ascendingButton.isSelected();
                SortThread sortThread = new SortThread(array, ascending);
                sortThread.start();

                try {
                    sortThread.join();
                } catch (InterruptedException ex) {
                    outputArea.append("Поток был прерван.\n");
                }

                outputArea.append("Отсортированный массив: " + Arrays.toString(sortThread.getSortedArray()));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}