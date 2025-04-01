package graphics.binaryTreeGraphics;

import enums.Direction;
import graphics.action.AbstractAction;
import graphics.action.ActionFactory;
import logic.binaryTree.AbstractTree;
import logic.binaryTree.Node;

import java.awt.*;
import java.util.Vector;

public class ActionManager {
    private volatile AbstractAction action;
    private final ActionFactory factory;
    private Dimension size;

    public ActionManager() {
        this.factory = new ActionFactory();
        this.action = null;
        this.size = null;
    }

    public void setSize(Dimension size){
        this.size = size;
    }

    public <K extends Comparable<K>> void insert(K k, AbstractTree<K> tree, Vector<Direction> directions) {
        waitAction();
        this.action = factory.createInsertion(directions, tree.getRoot(), new Node<>(k));
        waitAction();
    }

    public <K extends Comparable<K>> void rotate(Direction dir, Node<K> pivot) {
        waitAction();
        this.action = factory.createRotation(dir, pivot);
        waitAction();
    }

    public synchronized void waitAction(){
        while(action != null){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setAllCoordinates(Node<?> node){
        setAllCoordinates(node,size.width /2, size.height/10, size.width/4, size.height/6);
    }

    private void setAllCoordinates(Node<?> node, int x, int y, int xDist, int yDist) {
        if (node != null) {
            node.setCoordinate(new int[]{x, y});
            setAllCoordinates(node.getLeft(), x - xDist, y + yDist, xDist / 2, yDist);
            setAllCoordinates(node.getRight(), x + xDist, y + yDist, xDist / 2, yDist);
        }
    }

    public AbstractAction getAction() {
        return action;
    }

    public synchronized void restoreAction() {
        this.action = null;
        notify();
    }
}
