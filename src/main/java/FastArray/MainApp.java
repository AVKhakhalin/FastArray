package FastArray;

import javax.swing.*;
import java.awt.*;

public class MainApp
{
    public static final int SIZE_WIDTH = 900;
    public static final int SIZE_HEIGHT = 720;

    public static final int SIZE = 10000000;

    public static void main(String[] args)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerLocationX = (int) ((screenSize.width - SIZE_WIDTH) / 2);
        int centerLocationY = (int) ((screenSize.height - SIZE_HEIGHT) / 2);

        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Домашнее задание №12 студента GeekBrains Хахалина Андрея Владимировича");

        DialogWindow dialogWindow = new DialogWindow(SIZE_WIDTH, SIZE_HEIGHT, SIZE);
        frame.setContentPane(dialogWindow.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(centerLocationX, centerLocationY);
        frame.setSize(SIZE_WIDTH, SIZE_HEIGHT);
        frame.setVisible(true);
    }
}
