package graphics.binaryTreeGraphics;

import util.Util;

import javax.swing.*;
import java.awt.*;

public class PanelProva extends JPanel {
    public PanelProva(){
        JButton button1 = Util.createButton(0, 0, "bottone");
        JTextField textField1 = Util.createTextField(100, 100);
        JButton button2 = Util.createButton(100, 100, "bottone");
        JTextField textField2 = Util.createTextField(100, 100);
        this.add(button1);
        this.add(textField1);
        this.add(button2);
        this.add(textField2);
        this.setLayout(new GridLayout(3, 4, 5, 10));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setVisible(true);
    }
}
