package logic.sorter.concreteSorters;

import graphics.sorterGraphics.ActionManager;
import logic.sorter.AbstractSorter;

import java.util.Vector;

public class InsertionSort <K extends Comparable<K>> extends AbstractSorter<K> {
    public InsertionSort(Vector<K> v, ActionManager<K> actionManager) {
        super(v, actionManager);
    }

    public void doSort(){
            for(int j = 1; j < v.size(); j++){
                int i = j-1;
                K key = v.elementAt(j);

                actionManager.removeAllRect();
                actionManager.highlightElement(j);

                while(i >= 0 && v.elementAt(i).compareTo(key) > 0) {

                    actionManager.addPin(i);

                    v.set(i + 1, v.elementAt(i));
                    v.set(i, key);
                    actionManager.swap(i, i+1);

                    i = i - 1;
                }

                actionManager.removeAllPins();

            }
        }
}
