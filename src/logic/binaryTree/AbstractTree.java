package logic.binaryTree;

import java.awt.*;
import java.util.ArrayList;

import graphics.binaryTreeGraphics.ActionManager;
import graphics.binaryTreeGraphics.TreePanel;
import util.Util;


public abstract class AbstractTree<K extends Comparable<K>> {
    protected Node<K> root;

    protected ActionManager actionManager;

    public Node<K> getRoot() {
        return root;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public ArrayList<K> PreOrder() {
        int n = calculateHeight(root);
        ArrayList<K> array = new ArrayList<>(n);
        PreOrderAux(root, array);
        //System.out.println();
        return array;
    }

    private void PreOrderAux(Node<K> x, ArrayList<K> array) {
        if (x != null) {
            //System.out.print(x.key);
            array.add(x.key);
            PreOrderAux(x.left, array);
            PreOrderAux(x.right, array);
        }
    }

    public ArrayList<K> InOrder() {
        int n = calculateHeight(root);
        ArrayList<K> array = new ArrayList<>(n);
        InOrderAux(root, array);
        //System.out.println();
        return array;
    }

    private void InOrderAux(Node<K> x, ArrayList<K> array) {
        if (x != null) {
            InOrderAux(x.left, array);
            //System.out.print(x.key);  
            array.add(x.key);
            InOrderAux(x.right, array);
        }
    }

    public ArrayList<K> PostOrder() {
        int n = calculateHeight(root);
        ArrayList<K> array = new ArrayList<>(n);
        PostOrderAux(root, array);
        //System.out.println();
        return array;
    }

    private void PostOrderAux(Node<K> x, ArrayList<K> array) {
        if (x != null) {
            PostOrderAux(x.left, array);
            PostOrderAux(x.right, array);
            //System.out.print(x.key);
            array.add(x.key);
        }
    }

    private int calculateHeight(Node<K> x) {
        if (x == null) {
            return 0;
        } else {
            return calculateHeight(x.left) + calculateHeight(x.right) + 1;
        }
    }

    //x = 390, y = 100, node = root, xDist = 200, yDist = 50
    public void setAllCoordinates(Node<K> node, int x, int y, int xDist, int yDist) {
        if (node != null) {
            node.coordinate = new int[]{x, y};
            setAllCoordinates(node.left, x - xDist, y + yDist, xDist / 2, yDist);
            setAllCoordinates(node.right, x + xDist, y + yDist, xDist / 2, yDist);
        }
    }

    public void TreeReconstruct(ArrayList<K> P, ArrayList<K> I) {
        int xDist = 200;
        int yDist = 50;
        root = new Node<>(null);
        root.coordinate = new int[]{390, 100};
        TreeReconstructAux(P, I, 0, P.size(), 0, I.size(), root, xDist, yDist);
    }

    private void TreeReconstructAux(ArrayList<K> P, ArrayList<K> I, int a, int b, int c, int d, Node<K> x, int xDist, int yDist) {//ricostruisce qualasiasi albero dalle sue visite PreOrder e InOrder in O(n^2)
        x.key = P.get(a);
        if (a < b) {
            int j = TreeSearch(I, c, d, P.get(a));
            if (j > c) {
                Node<K> y = new Node<>(null);
                y.parent = x;
                x.left = y;
                y.coordinate = (new int[]{x.coordinate[0] - xDist, x.coordinate[1] + yDist});
                TreeReconstructAux(P, I, a + 1, a + j - c, c, j - 1, y, xDist / 2, yDist);
            }
            if (j < d) {
                Node<K> y = new Node<>(null);
                y.parent = x;
                x.right = y;
                y.coordinate = (new int[]{x.coordinate[0] + xDist, x.coordinate[1] + yDist});
                TreeReconstructAux(P, I, a + j - c + 1, b, j + 1, d, y, xDist / 2, yDist);
            }
        }
    }

    private int TreeSearch(ArrayList<K> I, int c, int d, K a) {//cerca l'indice di c in I tra gli indici d e a
        int i = 0;
        while (c <= d) {
            if (I.get(c).equals(a)) {
                i = c;
                break;
            } else {
                c++;
            }
        }
        return i;
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

    public abstract void insert(Node<K> node);

    public abstract void delete(K key);

    public abstract Node<K> min(Node<K> node);

    public abstract Node<K> max(Node<K> node);

    public abstract Node<K> search(K key);

    public abstract Node<K> createTreeNode(K key);
}

