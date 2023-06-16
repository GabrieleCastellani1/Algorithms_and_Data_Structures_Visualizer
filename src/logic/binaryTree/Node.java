package logic.binaryTree;

import java.awt.Color;

public class Node<K extends Comparable<K>> {

    protected K key;
    protected Node<K> parent;
    protected Node<K> left;
    protected Node<K> right;
    protected int[] coordinate;

    protected Color color;

    public Node(K k) {
        key = k;
        parent = null;
        left = null;
        right = null;
        coordinate = new int[2];
    }

    public K getKey() {
        return key;
    }

    public Node<K> getLeft() {
        return left;
    }

    public Node<K> getRight() {
        return right;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public Color getColor() {
        return color;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
