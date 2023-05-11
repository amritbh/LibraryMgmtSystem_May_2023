package utils;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String name) {
        super(name);
        setPreferredSize(new Dimension(90, 30));
        setBackground(new Color(0xDDFFBB));
        setForeground(Color.BLACK);

        Border line = new LineBorder(new Color(0xA4BC92));
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        setBorder(compound);

        setUI(new StyledButtonUI());
    }

    public MyButton(String name,int width , int height , Color color) {
        super(name);
        setPreferredSize(new Dimension(width, height));
        setBackground(color);
        setForeground(Color.BLACK);

        Border line = new LineBorder(color);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        setBorder(compound);

        setUI(new StyledButtonUI());
    }
}