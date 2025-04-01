package graphics.binaryTreeGraphics;

import logic.binaryTree.AbstractTree;
import util.Util;

import javax.swing.*;
import java.awt.*;

public class TreeFrame extends JFrame{

    public <K extends Comparable<K>> TreeFrame(AbstractTree<K> tree){
        super();
        Dimension preferredDimension = new Dimension(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setPreferredSize(preferredDimension);
        this.setResizable(true);
        this.setVisible(true);

        ActionManager actionManager = new ActionManager();
        TreePanel<K> p = new TreePanel<>(tree, actionManager, preferredDimension);
        tree.setActionManager(actionManager);

        p.setLayout(new BorderLayout());
        p.setPreferredSize(preferredDimension);

        JScrollPane scrollPane = new JScrollPane(
                p,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        JViewport viewport = new JViewport(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                p.paintComponent(g);
                repaint();
            }
        };

        viewport.setView(p);
        scrollPane.setViewport(viewport);

        TreeButtonPanel<K> treeButtonPanel = new TreeButtonPanel<>(tree, actionManager);

        JSplitPane container = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        container.setTopComponent(scrollPane);
        container.setBottomComponent(treeButtonPanel);
        container.setDividerLocation(Util.FRAMEWIDTH/2);
        container.setPreferredSize(new Dimension(Util.FRAMEWIDTH,Util.FRAMEHEIGHT));
        container.setVisible(true);

        this.add(container);
        this.pack();
    }
}
