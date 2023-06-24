package graphics.binaryTreeGraphics;

import graphics.action.AbstractAction;
import logic.binaryTree.AbstractTree;

import javax.swing.*;
import java.awt.*;

public class TreePanel<K extends Comparable<K>> extends JPanel {
    private final AbstractTree<K> tree;
    private final ActionManager actionManager;

    public TreePanel(AbstractTree<K> tree, ActionManager actionManager) {

        this.tree = tree;

        this.actionManager = actionManager;

        this.setBackground(Color.white);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);

        tree.printTree(tree.getRoot(), g2d);

        AbstractAction action = actionManager.getAction();

        if(action != null && action.isRunning()){
            action.doAction(g2d);
        }else{
            actionManager.restoreAction();
        }

        repaint();
    }
}
