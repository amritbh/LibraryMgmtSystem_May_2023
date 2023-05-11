package utils;



import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// use: TextField test= new MyInput().getInput
public class MyInput extends JTextField {

    private JTextField textField;
    private JLabel label;

    public MyInput() {

        textField = new JTextField(10);
        Font fo = new Font("Serif", Font.TRUETYPE_FONT, 18);
        textField.setFont(fo);
        textField.setCaretColor(new Color(0xA4BC92));
        textField.setBorder(new LineBorder(new Color(0xA4BC92),2));
    }

    public MyInput(Color color) {

        textField = new JTextField(10);
        Font fo = new Font("Serif", Font.CENTER_BASELINE, 12);
        textField.setFont(fo);
        textField.setCaretColor(new Color(0xA4BC92));
        textField.setBorder(new LineBorder(color,2));
    }

    public JTextField getInput() {
        return textField;
    }

}

