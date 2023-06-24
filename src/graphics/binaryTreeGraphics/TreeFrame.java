package graphics.binaryTreeGraphics;
import javax.swing.*;

import logic.binaryTree.AbstractTree;
import util.Util;

import java.awt.*;

public class TreeFrame extends JFrame{

    public <K extends Comparable<K>> TreeFrame(AbstractTree<K> tree){
        super();
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        ActionManager actionManager = new ActionManager();
        TreePanel<K> p = new TreePanel<>(tree, actionManager);
        tree.setActionManager(actionManager);

        TreeButtonPanel<K> treeButtonPanel = new TreeButtonPanel<>(tree, actionManager);

        JSplitPane container = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p, treeButtonPanel);
        container.setTopComponent(p);
        container.setBottomComponent(treeButtonPanel);
        container.setDividerLocation(400);
        container.setPreferredSize(new Dimension(800,600));
        container.setVisible(true);

        this.add(container);
    }
}
