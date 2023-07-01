package graphics.binaryTreeGraphics;

import graphics.action.AbstractAction;
import logic.binaryTree.AbstractTree;
import logic.binaryTree.Node;
import util.Util;

import javax.swing.*;
import java.awt.*;

public class TreePanel<K extends Comparable<K>> extends JPanel {
    private final AbstractTree<K> tree;
    private final ActionManager actionManager;
    private Dimension size;

    public TreePanel(AbstractTree<K> tree, ActionManager actionManager, Dimension size) {

        this.tree = tree;

        this.actionManager = actionManager;

        this.setBackground(Color.white);

        this.size = size;
        actionManager.setSize(size);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        actionManager.setSize(this.getSize());

        tree.printTree(tree.getRoot(), g2d);

        AbstractAction action = actionManager.getAction();

        if(action != null && action.isRunning()){
            action.doAction(g2d);
        }else{
            actionManager.restoreAction();
            actionManager.setAllCoordinates(tree.getRoot());
            fixBorderCollisions(tree.getRoot());
        }

        repaint();
    }

    private void fixBorderCollisions(Node<?> node){
        if(node != null){
            if (node.getCoordinate()[0] + Util.OVALDIAMETER > size.width || node.getCoordinate()[0] < 0){
                size = new Dimension(size.width + 1, size.height);
                this.setPreferredSize(size);

            }
            if (node.getCoordinate()[1] + Util.OVALDIAMETER > size.height || node.getCoordinate()[1] < 0){
                size = new Dimension(size.width, size.height + 1);
                this.setPreferredSize(size);
            }
            fixBorderCollisions(node.getLeft());
            fixBorderCollisions(node.getRight());
        }
    }
}
