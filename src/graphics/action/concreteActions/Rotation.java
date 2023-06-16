package graphics.action.concreteActions;

import javax.swing.Timer;

import graphics.action.AbstractAction;
import logic.binaryTree.Node;
import graphics.binaryTreeGraphics.TreePanel.Direction;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rotation extends AbstractAction implements ActionListener {

    private final Node<?> pivot;
    private final Node<?> leftSon;
    private final Node<?> RightSon;
    private final Timer t = new Timer(50, this);
    private final Direction dir;
    private int xDistanceGoingDownSon;
    private int yDistanceGoingDownSon;
    private int xDistanceBetweenPivotAndGoingUpSon;
    private int yDistanceBetweenPivotAndGoingUpSon;
    private int count;

    public Rotation(Direction dir,
                    Node<?> pivot) {
        setActionRunning();
        this.dir = dir;
        this.pivot = pivot;
        this.RightSon = pivot.getRight();
        this.leftSon = pivot.getLeft();
        this.count = 0;

        switch (dir) {
            case LEFT: {
                xDistanceGoingDownSon = (Math.abs(pivot.getCoordinate()[0] - leftSon.getCoordinate()[0]) / 2) / 50;
                yDistanceGoingDownSon = (Math.abs(pivot.getCoordinate()[1] - leftSon.getCoordinate()[1])) / 50;
                xDistanceBetweenPivotAndGoingUpSon = Math.abs(pivot.getCoordinate()[0] - RightSon.getCoordinate()[0]) / 50;
                yDistanceBetweenPivotAndGoingUpSon = Math.abs(pivot.getCoordinate()[1] - RightSon.getCoordinate()[1]) / 50;
                break;
            }
            case RIGHT: {
                xDistanceGoingDownSon = (Math.abs(pivot.getCoordinate()[0] - RightSon.getCoordinate()[0]) / 2) / 50;
                yDistanceGoingDownSon = (Math.abs(pivot.getCoordinate()[1] - RightSon.getCoordinate()[1])) / 50;
                xDistanceBetweenPivotAndGoingUpSon = Math.abs(pivot.getCoordinate()[0] - leftSon.getCoordinate()[0]) / 50;
                yDistanceBetweenPivotAndGoingUpSon = Math.abs(pivot.getCoordinate()[1] - leftSon.getCoordinate()[1]) / 50;
                break;
            }
        }

        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (count < 50) {
            switch (dir) {
                case LEFT: {
                    pushDownNode(pivot, xDistanceBetweenPivotAndGoingUpSon, yDistanceBetweenPivotAndGoingUpSon, dir);
                    pullUpNode(RightSon, xDistanceBetweenPivotAndGoingUpSon, yDistanceBetweenPivotAndGoingUpSon, dir);
                    pushDownTree(leftSon, xDistanceGoingDownSon, yDistanceGoingDownSon, dir);
                    pullUpTree(RightSon.getRight(), xDistanceGoingDownSon, yDistanceGoingDownSon);
                    pushDownTree(RightSon.getLeft(), xDistanceBetweenPivotAndGoingUpSon, 0, dir);
                    break;
                }
                case RIGHT: {
                    pushDownNode(pivot, xDistanceBetweenPivotAndGoingUpSon, yDistanceBetweenPivotAndGoingUpSon, dir);
                    pullUpNode(leftSon, xDistanceBetweenPivotAndGoingUpSon, yDistanceBetweenPivotAndGoingUpSon, dir);
                    pushDownTree(RightSon, xDistanceGoingDownSon, yDistanceGoingDownSon, dir);
                    pullUpTree(leftSon.getLeft(), xDistanceGoingDownSon, yDistanceGoingDownSon);
                    pushDownTree(leftSon.getRight(), xDistanceBetweenPivotAndGoingUpSon, 0, dir);
                    break;
                }
            }
            count++;
        } else {
            count = 0;
            setActionOver();
            t.stop();
        }
    }

    public void doAction(Graphics2D g2d) {
        if (firstStart) {
            t.start();
            firstStart = false;
        }
    }

    private void pushDownNode(Node<?> node, int xDistance, int yDistance, Direction dir) {
        switch (dir) {
            case LEFT: {
                node.getCoordinate()[0] -= xDistance;
                node.getCoordinate()[1] += yDistance;
                break;
            }
            case RIGHT: {
                node.getCoordinate()[0] += xDistance;
                node.getCoordinate()[1] += yDistance;
                break;
            }
        }
    }

    private void pullUpNode(Node<?> node, int xDistance, int yDistance, Direction dir) {
        switch (dir) {
            case LEFT: {
                node.getCoordinate()[0] -= xDistance;
                node.getCoordinate()[1] -= yDistance;
                break;
            }
            case RIGHT: {
                node.getCoordinate()[0] += xDistance;
                node.getCoordinate()[1] -= yDistance;
                break;
            }
        }
    }

    private void pushDownTree(Node<?> node, int xDistance, int yDistance, Direction dir) {
        if (node != null) {
            switch (dir) {
                case LEFT: {
                    node.getCoordinate()[0] -= xDistance;
                    node.getCoordinate()[1] += yDistance;
                    break;
                }
                case RIGHT: {
                    node.getCoordinate()[0] += xDistance;
                    node.getCoordinate()[1] += yDistance;
                    break;
                }
            }
            pushDownTree(node.getLeft(), xDistance, yDistance, dir);
            pushDownTree(node.getRight(), xDistance, yDistance, dir);
        }
    }

    private void pullUpTree(Node<?> node, int xDistance, int yDistance) {
        if (node != null) {
            switch (dir) {
                case LEFT: {
                    node.getCoordinate()[0] -= xDistance;
                    node.getCoordinate()[1] -= yDistance;
                    break;
                }
                case RIGHT: {
                    node.getCoordinate()[0] += xDistance;
                    node.getCoordinate()[1] -= yDistance;
                    break;
                }
            }
            pullUpTree(node.getLeft(), xDistance, yDistance);
            pullUpTree(node.getRight(), xDistance, yDistance);
        }
    }
}
