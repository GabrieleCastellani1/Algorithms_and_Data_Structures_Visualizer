package graphics.menu;

import util.Util;

import javax.swing.*;
import java.awt.*;

public class TotalMenu extends JFrame {

    public TotalMenu(){
        super();
        initializeMenu();

        JButton treeButton = Util.createButton(300,200, "Alberi");

        treeButton.addActionListener(e -> new TreeMenu<>());

        JButton ordButton = Util.createButton(300, 250, "Ordinamento");

        ordButton.addActionListener(e -> new VectorMenu());

        JButton graphButton = Util.createButton(300, 300, "Grafi");

        graphButton.addActionListener(e -> new GraphMenu());

        this.add(treeButton);
        this.add(ordButton);
        this.add(graphButton);
    }

    private void initializeMenu(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
    }
}
