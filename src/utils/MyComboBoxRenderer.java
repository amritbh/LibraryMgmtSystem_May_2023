package utils;

import javax.swing.*;
import java.awt.*;


public class MyComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            label.setForeground(Color.BLACK);
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            if (isSelected) {
                label.setBackground(new Color(0x0072C6));
                label.setForeground(Color.WHITE);
            } else {
                label.setBackground(Color.WHITE);
            }
            return label;
        }
    }


