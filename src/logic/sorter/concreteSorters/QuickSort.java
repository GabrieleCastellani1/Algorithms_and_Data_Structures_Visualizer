package logic.sorter.concreteSorters;

import graphics.sorterGraphics.ActionManager;
import graphics.sorterGraphics.figures.Rectangle;
import logic.sorter.AbstractSorter;
import util.Util;

import java.awt.*;
import java.util.Vector;

public class QuickSort <K extends Comparable<K>> extends AbstractSorter<K> {

    public QuickSort(Vector<K> v, ActionManager<K> actionManager) {
        super(v, actionManager);
    }

    public void swap(int i, int p){

            actionManager.swap(i, p);

            K n = v.elementAt(i);
            v.set(i, v.elementAt(p));
            v.set(p, n);
        }

        public void quickSort(int p, int q){
            if(p < q){
                int r = Partition(p, q);

                Rectangle rect1 = actionManager.highlightArea(p, r-1);
                Rectangle rect2 = actionManager.highlightArea(r+1, q);

                quickSort(p, r-1);

                actionManager.removeRect(rect1);

                quickSort(r+1, q);

                actionManager.removeRect(rect2);
            }
        }

        public int Partition(int p, int q){
            int i = p-1;
            for(int j = p; j <= q; j++){

                actionManager.color(q, Color.GREEN);
                Util.waitAction(1000);
                actionManager.addPin(j);

                if(v.elementAt(j).compareTo(v.elementAt(q)) <= 0){
                    i += 1;
                    swap(i, j);
                }
            }

            actionManager.removeAllPins();

            return i;
        }

    @Override
    public void doSort() {
        quickSort(0, v.size()-1);
    }
}
