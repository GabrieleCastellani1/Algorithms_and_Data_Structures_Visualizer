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

        PanelProva prova = new PanelProva();
        prova.setBackground(Color.blue);
        JSplitPane container = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p, prova);
        container.setTopComponent(p);
        container.setBottomComponent(prova);
        container.setDividerLocation(400);
        container.setPreferredSize(new Dimension(800,600));
        container.setVisible(true);

        p.setBackground(Color.white);
        p.repaint();
        this.add(container);
    }
}
