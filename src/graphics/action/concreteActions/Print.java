package graphics.action.concreteActions;

import java.awt.*;

import graphics.action.AbstractAction;
import logic.binaryTree.Node;
import util.Util;

public class Print extends AbstractAction {

    private final Node<?> Tree;

    public void doAction(Graphics2D g2d) {
        printTree(Tree, g2d);
    }

    public Print(Node<?> Tree) {
        this.Tree = Tree;
    }

    public void printTree(Node<?> Tree, Graphics2D g2d) {
        if (Tree != null) {

            Util.drawFill(Tree, g2d);
            Util.drawO(Tree, g2d);

            if (!(Tree.getLeft() == null)) {
                printTree(Tree.getLeft(), g2d);
                Util.drawL(Tree, Tree.getLeft(), g2d);
            }

            if (!(Tree.getRight() == null)) {
                printTree(Tree.getRight(), g2d);
                Util.drawL(Tree, Tree.getRight(), g2d);
            }
        }
    }
}
