package graphics.action;

import enums.Direction;
import graphics.action.concreteActions.Insertion;
import graphics.action.concreteActions.Rotation;
import graphics.action.concreteActions.Swap;
import graphics.sorterGraphics.figures.Square;
import logic.binaryTree.Node;

import java.util.Vector;

public class ActionFactory {

    public AbstractAction createInsertion(Vector<Direction> dir, Node<?> tree, Node<?> node) {
        return new Insertion(dir, tree, node);
    }

    public AbstractAction createRotation(Direction dir, Node<?> pivot) {
        return new Rotation(dir, pivot);
    }

    public AbstractAction createSwap(Square<?> key1, Square<?> key2) {
        return new Swap(key1, key2);
    }
}
