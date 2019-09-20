package GUI.cangku;

import javax.swing.*;
import java.awt.*;

public class Appwindow extends JFrame {
    JTextField text = new JTextField("second field");
    public Appwindow() {
        this.setLayout(new BorderLayout());
        this.add(text);
        this.setSize(500, 400);
        this.setLocation(500,200);
        this.setVisible(false);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }

    public void changeTextValue(String newValue) {
        text.setText(newValue);
    }
}
