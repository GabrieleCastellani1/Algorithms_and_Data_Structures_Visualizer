package logic.sorter;

import graphics.sorterGraphics.VectorPanel;

import java.util.Vector;

public abstract class AbstractSorter<K extends Comparable<K>>{
    protected VectorPanel<K> P;
    protected Vector<K> v;

    public void setPanel(VectorPanel<K> p) {
        P = p;
    }

    public abstract void doSort();
}
