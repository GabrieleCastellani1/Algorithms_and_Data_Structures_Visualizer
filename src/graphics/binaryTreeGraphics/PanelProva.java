package graphics.binaryTreeGraphics;

import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelProva extends JPanel {
    public PanelProva(){

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JButton insertButton = new JButton();
        insertButton.setMaximumSize(new Dimension(200,50));
        insertButton.addActionListener(e -> {

        });

        JTextField insertText = new JTextField();
        insertText.setPreferredSize(new Dimension(50,50));
        insertText.setMaximumSize(new Dimension(50,50));

        JButton deleteButton = new JButton();
        deleteButton.setMaximumSize(new Dimension(200,50));

        JTextField deleteText = new JTextField();
        deleteText.setMaximumSize(new Dimension(50,50));
        deleteText.setPreferredSize(new Dimension(50,50));

        this.add(new Box.Filler(
                        new Dimension(5, 50),
                        new Dimension(5, 50),
                        new Dimension(50, 50)
                )
        );
        this.add(insertButton);
        this.add(insertText);
        this.add(new Box.Filler(
                new Dimension(5, 50),
                new Dimension(5, 50),
                new Dimension(800, 50))
        );
        this.add(deleteButton);
        this.add(deleteText);
        this.add(new Box.Filler(
                        new Dimension(5, 50),
                        new Dimension(5, 50),
                        new Dimension(50, 50)
                )
        );
        this.setVisible(true);
    }
}
