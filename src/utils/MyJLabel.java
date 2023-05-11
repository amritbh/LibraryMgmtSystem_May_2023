package utils;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {
    public MyJLabel(String text) {
        super(text);
        setFont(new Font("Serif", Font.PLAIN, 12));
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}