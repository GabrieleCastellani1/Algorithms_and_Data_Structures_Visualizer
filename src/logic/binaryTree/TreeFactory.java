package logic.binaryTree;

import java.awt.Color;

public class TreeFactory {

    public AbstractTree<?> createBST() {
        BST<?> tree = new BST<>();
        tree.root = null;
        return tree;
    }

    public <K extends Comparable<K>> AbstractTree<K> createRBT() {
        RBT<K> tree = new RBT<>();

        Node<K> node = new Node<>(null);
        node.parent = new Node<>(null);
        node.parent.color = Color.LIGHT_GRAY;
        node.left = new Node<>(null);
        node.left.color = Color.LIGHT_GRAY;
        node.right = new Node<>(null);
        node.right.color = Color.LIGHT_GRAY;
        node.color = Color.LIGHT_GRAY;

        tree.root = node;
        return tree;
    }

}
