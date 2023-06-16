package graphics.binaryTreeGraphics;
import javax.swing.*;

import logic.binaryTree.AbstractTree;
import util.Util;

import java.awt.*;

public class TreeFrame extends JFrame{

    public <K extends Comparable<K>> TreeFrame(AbstractTree<K> T){
        super();
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        TreePanel<K> p = new TreePanel<>(T);
        T.setPanel(p);
        /*
        p.setSize(400, 600);
        JPanel container = new JPanel();
        container.setLayout(new GridLayout());
        PanelProva prova = new PanelProva();
        prova.setBackground(Color.blue);
        prova.setSize(400, 600);
        container.add(p);
        container.add(prova);
        container.setVisible(true);
        */
        p.setBackground(Color.white);
        p.repaint();
        //this.add(container);
        this.add(p);
    }
}
