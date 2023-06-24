package graphics.binaryTreeGraphics;

import enums.Direction;
import graphics.action.AbstractAction;
import graphics.action.ActionFactory;
import logic.binaryTree.AbstractTree;
import logic.binaryTree.Node;

import java.util.Vector;

public class ActionManager {
    private volatile AbstractAction action;
    private final ActionFactory factory;

    public ActionManager() {
        this.factory = new ActionFactory();
        this.action = null;
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

    public AbstractAction getAction() {
        return action;
    }

    public synchronized void restoreAction() {
        this.action = null;
        notify();
    }
}
