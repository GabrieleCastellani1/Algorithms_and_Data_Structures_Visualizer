package logic.sorter.concreteSorters;

import graphics.sorterGraphics.ActionManager;
import logic.sorter.AbstractSorter;
import util.Util;

import java.awt.*;
import java.util.Vector;

public class SelectionSort <K extends Comparable<K>> extends AbstractSorter<K> {

    public SelectionSort(Vector<K> v, ActionManager<K> actionManager) {
        super(v, actionManager);
    }

    public void doSort (){
            K min = v.elementAt(0);
            int count = 0;
            int minpos = 0;
            while(count < v.size()-1) {
                for (int i = count; i < v.size(); i++) {

                    actionManager.removeAllRect();
                    actionManager.highlightElement(count);
                    Util.waitAction(1000);

                    if (v.elementAt(i).compareTo(min) < 0) {
                        min = v.elementAt(i);
                        minpos = i;
                    }

                    actionManager.addPin(i);

                }

                actionManager.removeAllPins();
                actionManager.color(minpos, Color.GREEN);
                actionManager.swap(minpos, count);

                v.set(minpos, v.elementAt(count));
                v.set(count, min);
                count += 1;
                min = v.elementAt(count);
                minpos = count;
            }
        }
}
