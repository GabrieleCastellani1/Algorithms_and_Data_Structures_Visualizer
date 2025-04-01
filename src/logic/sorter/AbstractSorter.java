package logic.sorter;

import graphics.sorterGraphics.ActionManager;

import java.util.Vector;

public abstract class AbstractSorter<K extends Comparable<K>>{
    protected Vector<K> v;
    protected ActionManager<K> actionManager;

    public AbstractSorter(Vector<K> v, ActionManager<K> actionManager) {
        this.v = v;
        this.actionManager = actionManager;
    }

    public abstract void doSort();
    public void add(K key){
        this.v.add(key);
    }
    public void remove(K key){
        this.v.remove(key);
    }

    public ActionManager<K> getActionManager() {
        return actionManager;
    }
}
