package utils;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPasswordField;

public class PlaceholderPasswordField extends JPasswordField {
    private String placeholder;

    public PlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the placeholder text if the password field is empty
        if (getPassword().length == 0) {
            g.setColor(Color.black);
            g.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
        }
    }
}