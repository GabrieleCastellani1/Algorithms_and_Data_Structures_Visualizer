package logic.binaryTree;

import enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class BST<K extends Comparable<K>> extends AbstractTree<K> {

    //METODI PROTOCOLLO VISTI IN CLASSE

    @Override
    public Node<K> max(Node<K> x) {//ritorna il massimo dell'albero x
        if (x.right == null) {
            return root;
        } else {
            return max(x.right);
        }
    }

    @Override
    public Node<K> min(Node<K> x) {//ritorna il minimo dell'albero x
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    @Override
    public Node<K> search(K key) {
        return searchAux(root, key);
    }

    public Node<K> searchAux(Node<K> x, K k) {//ricerca un nodo con chiave k in x
        if (x == null || x.key.equals(k)) {
            return x;
        } else {
            if (k.compareTo(x.key) < 0) {
                return searchAux(x.left, k);
            } else {
                return searchAux(x.right, k);
            }
        }
    }

    private Node<K> predecessor(Node<K> x){//ritorna il predecessore di x
        Node<K> y = null;
        if(x.left != null){
            return max(x.left);
        }else{
            y = x.parent;
            while(y != null && x == y.left){
                x = y;
                y = y.parent;
            }
            return y;
        }
    }

    private Node<K> successor(Node<K> x) {//ritorna il successore di x
        Node<K> y;
        if (x.right != null) {
            return min(x.right);
        } else {
            y = x.parent;
            while (y != null && x == y.right) {
                x = y;
                y = y.parent;
            }
            return y;
        }
    }

    @Override
    public void insert(Node<K> z) {//inserisce il nodo z in T, nel quaderno
        Vector<Direction> dir = new Vector<>();
        Node<K> y = null;
        Node<K> x = root;
        while (x != null) {
            y = x;
            if (x.key.compareTo(z.key) > 0) {
                x = x.left;
                dir.add(Direction.LEFT);
            } else {
                dir.add(Direction.RIGHT);
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            root = z;

        } else {
            if (y.key.compareTo(z.key) > 0) {
                y.left = z;
            } else {
                y.right = z;
            }
        }
        actionManager.setAllCoordinates(this.root);
        actionManager.insert(z.key, this, dir);
    }

    @Override
    public void delete(K key) {
        Node<K> x;
        Node<K> z = search(key);
        if (z.left == null || z.right == null) {
            x = z;
        } else {
            x = successor(z);
        }
        Node<K> y = x.parent;
        Node<K> v;
        if (x.left != null) {
            v = x.left;
        } else {
            v = x.right;
        }
        if (y == null) {
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
        actionManager.setAllCoordinates(this.root);
    }

    public ArrayList<K> Sort(K[] A) {//ordina un array creando un bst e facendo poi una InOrder di questo
        root = new Node<>(null);
        for (K k : A) {
            Node<K> x = new Node<>(k);
            insert(x);
        }
        return this.InOrder();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //METODI AGGIUNTIVI SU ALBERI GENERICI E BST DA ESERCIZI

    public Node<K> BuildBst(ArrayList<K> A, int p, int q) {//costruisce un bst bilanciato da un array già ordinato in tempo (theta)n
        if (p <= q) {
            int r = (p + q) / 2;
            Node<K> T = new Node<>(A.get(r));
            T.left = BuildBst(A, p, r - 1);
            T.right = BuildBst(A, r + 1, q);
            return fixParentsInSubTrees(T);
        } else {
            return null;
        }
    }

    public Node<K> restoreBst(ArrayList<K> PreOrder, int p, int q) {//ricostruisce un bst dalla sua sola visita in PreOrder memorizzata in un array in O(n^2)
        if (p <= q) {
            Node<K> T = new Node<>(PreOrder.get(p));
            int bound = searchBound(PreOrder.get(p), PreOrder, p + 1, q);
            T.left = restoreBst(PreOrder, p + 1, bound - 1);
            T.right = restoreBst(PreOrder, bound, q);
            return fixParentsInSubTrees(T);
        } else {
            return null;
        }
    }

    private int searchBound(K value, ArrayList<K> PreOrder, int p, int q) {//procedura ausiliaria per RestoreBst
        if (p < q) {
            int r = (p + q) / 2;
            if ((PreOrder.get(r).compareTo(value)) < 0) {
                return searchBound(value, PreOrder, r + 1, q);
            } else {
                return searchBound(value, PreOrder, p, r - 1);
            }
        } else {
            return p;
        }
    }

    private Node<K> fixParentsInSubTrees(Node<K> T) {//sistema coerentemente i parent dei nodi del bst T
        if (T != null) {
            if (T.left != null) {
                T.left.parent = T;
            }
            if (T.right != null) {
                T.right.parent = T;
            }
            fixParentsInSubTrees(T.left);
            fixParentsInSubTrees(T.right);
        }
        return T;
    } 
  
    private Node<K> MergeBst(ArrayList<K> InOrder1, ArrayList<K> InOrder2){//da due bst crea un nuovo bst più grande con le chiavi di entrambi in tempo (theta)n
        ArrayList<K> A = new ArrayList<>(InOrder1.size() + InOrder2.size());
        for(int i = 0; i < InOrder1.size(); i++){
            A.set(i, InOrder1.get(i));
        }
        for(int i = 0; i < InOrder2.size(); i++){
            A.set(InOrder1.size() + i, InOrder2.get(i)); 
        }
        Merge(A, 0, InOrder1.size()-1, A.size()-1);
        for (K k : A) {
            System.out.print(k);
        }
        return BuildBst(A, 0, A.size()-1);
    }
  
    private void Merge(ArrayList<K> A, int p, int r, int q){//unisce due vettori ordinati in un nuovo vettore più grande, ordinato
        int i = p;
        int j = r+1;
        int k = 0;
        ArrayList<K> B = new ArrayList<>(q-p+1);
        while(i <= r && j <= q){
            if (A.get(i).compareTo(A.get(j)) <= 0) {
                B.set(k, A.get(i));
                i++;
            }else{
                B.set(k, A.get(j));
                j++;
            }
            k++;
        }
        if( i > r ){
            for(int m = j ; m <= q; m++ ){
                B.set(k, A.get(m));
                k++;
            }
            }else{
                for(int m = i; m <= r; m++){
                    B.set(k, A.get(m));
                    k++;
                }
            }
        for(int w = p; w <= q; w++){
            A.set(w, B.get(w-p));
        }
    }

    @Override
    public Node<K> createTreeNode(K key) {
        Node<K> node = new Node<>(key);
        node.color = Color.WHITE;
        node.left = null;
        node.right = null;
        node.parent = null;
        return node;
    }
}
