package logic.binaryTree;

import java.util.Objects;
import java.util.Vector;

import enums.Direction;

import java.awt.Color;

public class RBT<K extends Comparable<K>> extends AbstractTree<K> {

    public boolean checkHeight(Node<K> x) {
        return checkHeightAux(x) != 0;
    }

    private int checkHeightAux(Node<K> x) {//scende ricorsivamente nei rami dalla radice alle foglie contando i nodi neri e settando bh a 0 se non corrispondono
        if (x == null) {
            return 0;
        } else if (x.color.equals(Color.LIGHT_GRAY)) {
            int bh1 = checkHeightAux(x.left);
            int bh2 = checkHeightAux(x.right);
            if (bh1 != bh2) {//se le altezze nere non corrsipondono tra due percorsi ritorno 0, setto l'altezza della chiamata precedente a 0 e così via
                return 0;
            } else {
                return bh1 + 1;
            }
        } else {
            int bh1 = checkHeightAux(x.left);
            int bh2 = checkHeightAux(x.right);
            if (bh1 != bh2) {//se le altezze nere non corrsipondono tra due percorsi ritorno 0, setto l'altezza della chiamata precedente a 0 e così via
                return 0;
            } else {
                return bh1;
            }
        }
    }

    public Node<K> RBTSearch(Node<K> T, K k) {//ritorna null se l'intero cercato non c'è in T altrimenti ritorna il nodo che contiene k, cercato ricorsivamente
        if (T.key == null || T.key.equals(k)) {
            return T;
        } else {
            if (T.key.compareTo(k) > 0) {
                return RBTSearch(T.left, k);
            } else {
                return RBTSearch(T.right, k);
            }
        }
    }

    public void LeftRotate(Node<K> y, Node<K> x) {//ruota x figlio destro di y a sinistra

        actionManager.rotate(Direction.LEFT, y);

        y.right = x.left;
        y.right.parent = y;
        x.left = y;
        x.parent = y.parent;
        if (y.parent.key != null) {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        y.parent = x;
    }

    public void RightRotate(Node<K> y, Node<K> x) {//ruota x figlio sinistro di y a destra

        actionManager.rotate(Direction.RIGHT, y);

        y.left = x.right;
        y.left.parent = y;
        x.right = y;
        x.parent = y.parent;
        if (y.parent.key != null) {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        y.parent = x;
    }

    @Override
    public void insert(Node<K> z) {// Procedura di inserimento che richiama RBTFixUp, identica a quella dei BST
        Node<K> y = createTreeNode(null);
        Node<K> x = root;

        Vector<Direction> dir = new Vector<>();
        while (x.key != null) {
            y = x;
            if (x.key.compareTo(z.key) > 0) {
                dir.add(Direction.LEFT);
                x = x.left;
            } else {
                dir.add(Direction.RIGHT);
                x = x.right;
            }
        }
        z.parent = y;
        if (y.key == null) {
            root = z;
        } else {
            if (y.key.compareTo(z.key) > 0) {
                y.left = z;
            } else {
                y.right = z;
            }
        }

        z.color = Color.RED;

        this.setAllCoordinates(this.root, 390, 100, 200, 50);

        actionManager.insert(z.key, this, dir);

        if (!z.equals(root)) {
            insertFixUp(z);
        } else {
            z.color = Color.LIGHT_GRAY;
        }

        while (root.parent != null && root.parent.key != null) {
            root = root.parent;
        }

        this.setAllCoordinates(this.root, 390, 100, 200, 50);
    }

    public void insertFixUp(Node<K> x) {
        Node<K> y = x.parent;
        Node<K> w = y.parent;
        while (y.color.equals(Color.RED)) {
            if (y == w.left) {
                Node<K> z = w.right;
                if (z.color.equals(Color.LIGHT_GRAY) && y.left == x) {
                    RightRotate(w, y);
                    y.color = Color.LIGHT_GRAY;
                    w.color = Color.RED;
                } else if (z.color.equals(Color.LIGHT_GRAY) && y.right == x) {
                    LeftRotate(y, x);
                    x = w.left.left;
                    y = w.left;
                } else if (z.color.equals(Color.RED)) {
                    y.color = Color.LIGHT_GRAY;
                    z.color = Color.LIGHT_GRAY;
                    if (w.parent.key == null) {
                        w.color = Color.LIGHT_GRAY;
                    } else {
                        w.color = Color.RED;
                        x = w;
                        y = x.parent;
                        w = y.parent;
                    }
                }
            } else {
                Node<K> z = w.left;
                if (z.color.equals(Color.LIGHT_GRAY) && y.right == x) {
                    LeftRotate(w, y);
                    y.color = Color.LIGHT_GRAY;
                    w.color = Color.RED;
                } else if (z.color.equals(Color.LIGHT_GRAY) && y.left == x) {
                    RightRotate(y, x);
                    x = w.right.right;
                    y = w.right;
                } else if (z.color.equals(Color.RED)) {
                    y.color = Color.LIGHT_GRAY;
                    z.color = Color.LIGHT_GRAY;
                    if (w.parent.key == null) {
                        w.color = Color.LIGHT_GRAY;
                    } else {
                        w.color = Color.RED;
                        x = w;
                        y = x.parent;
                        w = y.parent;
                    }
                }
            }
        }
    }

    @Override
    public void delete(K key) {
        Node<K> z = search(key);
        Node<K> x = root;
        if (z.left.key == null || z.right.key == null) {
            x = z;
        } else {
            x = predecessor(z);
        }
        Node<K> y = x.parent;
        Node<K> v;
        if (x.left.key != null) {
            v = x.left;
        } else {
            v = x.right;
        }
        if (y.key == null) {
            root = v;
        } else {
            if (x == y.left) {
                y.left = v;
            } else {
                y.right = v;
            }
        }
        if (v != null) {
            v.parent = y;
        }
        if (z != x) {
            z.key = x.key;
        }
        if (x.color.equals(Color.LIGHT_GRAY)) {
            if (Objects.requireNonNull(v).color.equals(Color.LIGHT_GRAY)) {
                deleteFixUp(v);
            } else {
                v.color = Color.LIGHT_GRAY;
            }
        }
        while (Objects.requireNonNull(root).key != null && root.parent.key != null) {
            root = root.parent;
        }
        this.setAllCoordinates(this.root, 390, 100, 200, 50);
    }

    public void deleteFixUp(Node<K> z) {
        while (z.parent.key != null && z.color.equals(Color.LIGHT_GRAY)) {
            Node<K> w = z.parent;
            if (z == z.parent.left) {
                Node<K> y = z.parent.right;
                if (y.right.color.equals(Color.RED)) {
                    Node<K> x = y.right;
                    x.color = Color.LIGHT_GRAY;
                    y.color = w.color;
                    w.color = Color.LIGHT_GRAY;
                    LeftRotate(w, y);
                    break;
                } else if (y.left.color.equals(Color.RED)) {
                    Node<K> x = y.left;
                    x.color = Color.LIGHT_GRAY;
                    y.color = Color.RED;
                    RightRotate(y, x);
                } else if (y.right.color.equals(Color.LIGHT_GRAY)
                        && y.left.color.equals(Color.LIGHT_GRAY)
                        && y.color.equals(Color.LIGHT_GRAY)) {
                    y.color = Color.RED;
                    if (w.color.equals(Color.RED)) {
                        w.color = Color.LIGHT_GRAY;
                        break;
                    } else {
                        w.color = Color.LIGHT_GRAY;
                        z = w;
                    }
                } else if (y.color.equals(Color.RED)
                        && y.right.color.equals(Color.LIGHT_GRAY)
                        && y.left.color.equals(Color.LIGHT_GRAY)) {
                    y.color = Color.LIGHT_GRAY;
                    w.color = Color.RED;
                    LeftRotate(w, y);
                }
            } else {
                Node<K> y = z.parent.left;
                if (y.left.color.equals(Color.RED)) {
                    Node<K> x = y.left;
                    x.color = Color.LIGHT_GRAY;
                    y.color = w.color;
                    w.color = Color.LIGHT_GRAY;
                    RightRotate(w, y);
                    break;
                } else if (y.right.color.equals(Color.RED)) {
                    Node<K> x = y.right;
                    x.color = Color.LIGHT_GRAY;
                    y.color = Color.RED;
                    LeftRotate(y, x);
                } else if (y.color.equals(Color.LIGHT_GRAY)
                        && y.left.color.equals(Color.LIGHT_GRAY)
                        && y.right.color.equals(Color.LIGHT_GRAY)) {
                    y.color = Color.RED;
                    if (w.color.equals(Color.RED)) {
                        w.color = Color.LIGHT_GRAY;
                        break;
                    } else {
                        w.color = Color.LIGHT_GRAY;
                        z = w;
                    }
                } else if (y.color.equals(Color.RED)
                        && y.left.color.equals(Color.LIGHT_GRAY)
                        && y.right.color.equals(Color.LIGHT_GRAY)) {
                    y.color = Color.LIGHT_GRAY;
                    w.color = Color.RED;
                    RightRotate(w, y);
                }
            }
        }
    }

    public Node<K> successor(Node<K> x) {//trova il successore del nodo x nell'albero
        Node<K> y;
        if (x.right.key != null) {
            return min(x.right);
        } else {
            y = x.parent;
            while (y.key == null && x == y.right) {
                x = y;
                y = y.parent;
            }
            return y;
        }
    }

    public Node<K> min(Node<K> x) {//ritorna il minimo nodo dell'albero x
        if (x.left.key == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public Node<K> predecessor(Node<K> x) {//trova il predecessore del nodo x nell'albero
        Node<K> y;
        if (x.left.key != null) {
            return max(x.left);
        } else {
            y = x.parent;
            while (y.parent.key == null && x == y.left) {
                x = y;
                y = y.parent;
            }
            return y;
        }
    }

    public Node<K> max(Node<K> x) {//ritorna il massimo nodo dell'albero x
        if (x.right.key == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    @Override
    public Node<K> search(K key) {
        return searchAux(root, key);
    }

    private Node<K> searchAux(Node<K> x, K k) {//ricerca un nodo con chiave k in x
        if (x.key == null || x.key.equals(k)) {
            return x;
        } else {
            if (k.compareTo(x.key) < 0) {
                return searchAux(x.left, k);
            } else {
                return searchAux(x.right, k);
            }
        }
    }

    @Override
    public Node<K> createTreeNode(K key) {
        Node<K> node = new Node<>(key);
        node.color = Color.LIGHT_GRAY;
        node.parent = new Node<>(null);
        node.parent.color = Color.LIGHT_GRAY;
        node.left = new Node<>(null);
        node.left.color = Color.LIGHT_GRAY;
        node.right = new Node<>(null);
        node.right.color = Color.LIGHT_GRAY;
        return node;
    }
}

