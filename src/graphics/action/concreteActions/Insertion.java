package graphics.action.concreteActions;

import enums.Direction;
import graphics.action.AbstractAction;
import logic.binaryTree.Node;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Insertion extends AbstractAction implements ActionListener {

    private final Vector<Direction> dir;
    private Node<?> tree;
    private final Timer t = new Timer(40, this);
    private Direction d;
    private int xDistance;
    private int yDistance;
    private final Node<?> insertionNode;

    public Insertion(Vector<Direction> dir,
                     Node<?> tree,
                     Node<?> node) {
        setActionRunning();
        this.dir = dir;
        this.tree = tree;
        this.insertionNode = node;
    }

    private void insert() {
        if (!dir.isEmpty()) {
            d = dir.remove(0);
            calculateDistance();
            t.start();
        } else {
            setActionOver();
        }
    }

    private void calculateDistance() {
        switch (d) {
            case LEFT:
                if (!(tree.getLeft() == null)) {
                    xDistance = Math.abs((tree.getCoordinate()[0] - tree.getLeft().getCoordinate()[0]) / 50);
                    yDistance = Math.abs((tree.getCoordinate()[1] - tree.getLeft().getCoordinate()[1]) / 50);
                    tree = tree.getLeft();
                }
                break;
            case RIGHT:
                if (!(tree.getRight() == null)) {
                    xDistance = Math.abs((tree.getCoordinate()[0] - tree.getRight().getCoordinate()[0]) / 50);
                    yDistance = Math.abs((tree.getCoordinate()[1] - tree.getRight().getCoordinate()[1]) / 50);
                    tree = tree.getRight();
                }
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {

        int[] coordinate = new int[2];
        switch (d) {
            case LEFT:

                if (tree.getCoordinate()[0] < insertionNode.getCoordinate()[0] &&
                        tree.getCoordinate()[1] > insertionNode.getCoordinate()[1]) {

                    coordinate[0] = insertionNode.getCoordinate()[0] - xDistance;
                    coordinate[1] = insertionNode.getCoordinate()[1] + yDistance;
                    insertionNode.setCoordinate(coordinate);

                } else {

                    t.stop();
                    insert();

                }
                break;

            case RIGHT:

                if (tree.getCoordinate()[0] > insertionNode.getCoordinate()[0] &&
                        tree.getCoordinate()[1] > insertionNode.getCoordinate()[1]) {

                    coordinate[0] = insertionNode.getCoordinate()[0] + xDistance;
                    coordinate[1] = insertionNode.getCoordinate()[1] + yDistance;
                    insertionNode.setCoordinate(coordinate);

                } else {

                    t.stop();
                    insert();

                }
                break;
        }
    }

    @Override
    public void doAction(Graphics2D g2d) {
        if (firstStart) {
            int[] coordinate = new int[]{Util.XSTART, Util.YSTART};
            insertionNode.setCoordinate(coordinate);
            firstStart = false;
            insert();
        } else {
            Util.drawO(insertionNode, g2d);
            Util.drawS(insertionNode, g2d);
        }
    }
}
