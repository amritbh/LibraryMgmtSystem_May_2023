package utils;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the placeholder text if the text field is empty
        if (getText().isEmpty()) {
            g.setColor(Color.black);
            g.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().bottom);
        }
    }
}