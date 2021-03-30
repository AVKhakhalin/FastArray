package FastArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindow
{
    int sizeWidth;
    int sizeHeight;
    int SIZE;
    JTextField textField_NumberThreads;

    DialogWindow (int _sizeWidth, int _sizeHeight, int _SIZE)
    {
        this.sizeWidth = _sizeWidth;
        this.sizeHeight = _sizeHeight;
        this.SIZE = _SIZE;
    }

    public JPanel createContentPane ()
    {
        // Создаём панель
        JPanel totalGUI = new JPanel();
        totalGUI.setBackground(new Color(200, 203, 255)); // смена фона окна
        totalGUI.setLayout(null);

        // Добавляем текст в окно
        JLabel blueLabel = new JLabel("<html><font face=\"MyFont, Verdana, Arial\", size=\"5\"><br>" +
                "Домашнее задание №12 включало в себя следующее:<br><br>" +
                "Необходимо написать два метода, которые делают следующее:<br>" +
                "1) создают одномерный длинный массив, например:<br>" +
                "static final int SIZE = 10 000 000;<br>" +
                "static final int HALF = size / 2;<br>" +
                "float [] arr = new float [size];<br>" +
                "2) заполняют этот массив единицами;<br>" +
                "3) засекают время выполнения: long a = System.currentTimeMillis();<br>" +
                "4) проходят по всему массиву и для каждой ячейки считают новое значение по формуле:<br>" +
                "arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));<br>" +
                "5) проверяется время окончания метода System.currentTimeMillis();<br>" +
                "6) в консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).<br><br>" +
                "Отличие первого метода от второго:<br>" +
                "1. Первый просто бежит по массиву и вычисляет значения.<br>" +
                "2. Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.<br>" +
                "3. По замерам времени:<br>" +
                "Для первого метода надо считать время только на цикл расчета:<br>" +
                "for (int i = 0; i < size ; i++) {<br>" +
                "arr[i] = (float )(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));}<br>" +
                "Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.</html>");
        blueLabel.setLocation(30, 0);
        blueLabel.setSize(sizeWidth - 70, sizeHeight - 100);
        blueLabel.setVerticalAlignment(1);
        blueLabel.setHorizontalAlignment(0);
        blueLabel.setForeground(Color.BLACK);
        totalGUI.add(blueLabel);

        // Создание кнопки для задания №1
        JButton task_1_button = new JButton("Проверить задание");
        task_1_button.setToolTipText("<html>Нажмите для проверки задания №1</html>");
        task_1_button.setLocation(700, 140);
        task_1_button.setSize(150,40);
        totalGUI.add(task_1_button);
        ActionListener actionListener1 = new TestActionListener(1);
        task_1_button.addActionListener(actionListener1);

        // Создание текстового поля ввода количества потоков для выполнения задания №1
        textField_NumberThreads = new JTextField(10);
        textField_NumberThreads.setToolTipText("<html>Укажите количество потоков для выполнения задания №1<br>и нажмите кнопку \"Проверить задание\"</html>");
        textField_NumberThreads.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        textField_NumberThreads.setBounds(810, 100, 40, 30);
        textField_NumberThreads.setText("2");
        textField_NumberThreads.setForeground(Color.RED);
        totalGUI.add(textField_NumberThreads);

        JLabel throatLabel = new JLabel("<html><font face=\"MyFont, Verdana, Arial\", size=\"5\">Количество<br>потоков</html>");
        throatLabel.setBounds(700, 75, 120, 80);
        throatLabel.setForeground(Color.RED);
        totalGUI.add(throatLabel);

        // Создание кнопки для выхода из программы
        JButton task_EXIT_button = new JButton("Выйти из программы");
        task_EXIT_button.setToolTipText("<html>Нажмите для выхода из программы</html>");
        task_EXIT_button.setLocation((int) (sizeWidth / 2 - 80), sizeHeight - 98);
        task_EXIT_button.setSize(160,40 );
        totalGUI.add(task_EXIT_button);
        ActionListener actionListener_EXIT = new TestActionListener(0);
        task_EXIT_button.addActionListener(actionListener_EXIT);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // Обработка событий при нажатии на левую кнопку мыши
    public class TestActionListener implements ActionListener
    {
        int taskNumber = 0;

        TestActionListener (int _taskNumber)
        {
            this.taskNumber = _taskNumber;
        }

        public void actionPerformed(ActionEvent e)
        {
            switch (taskNumber)
            {
                case 1:
                    try
                    {
//                    System.out.println("Количество доступных процессоров " + Runtime.getRuntime().availableProcessors());
                        SolveTask1 solveTask1_ = new SolveTask1(Integer.parseInt(textField_NumberThreads.getText()), SIZE);
                        long timeMethod1 = solveTask1_.method1();
                        long timeMethod2 = solveTask1_.method2();
                        System.out.println("Работа над массивом первым методом заняла: " + timeMethod1 + " мс.");
                        System.out.println("Работа над массивом вторым методом заняла: " + timeMethod2 + " мс.");
                        JOptionPane.showMessageDialog(new JFrame(), "<html><font face=\"MyFont, Verdana, Arial\", size=\"4\">Работа над массивом первым методом заняла: " + timeMethod1 + " мс.<br>Работа над массивом вторым методом заняла: " + timeMethod2 + " мс.</html>", "ИНФОРМАЦИЯ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (NumberFormatException x)
                    {
                        System.out.println("Количество процессов нужно указывать числом. Пожалуйста, введите число (>0) и нажмите на кнопку \"Проверить задание\" ещё раз.");
                        JOptionPane.showMessageDialog(new JFrame(), "<html><font face=\"MyFont, Verdana, Arial\", size=\"4\">Количество процессов нужно указывать числом.<br>Пожалуйста, введите число (>0) и нажмите<br>на кнопку \"Проверить задание\" ещё раз.</html>", "ОШИБКА", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }
}
