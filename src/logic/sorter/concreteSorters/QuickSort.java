package logic.sorter.concreteSorters;

import graphics.action.AbstractAction;
import graphics.sorterGraphics.figures.Rectangle;
import logic.sorter.AbstractSorter;
import util.Util;

import java.awt.*;

public class QuickSort <K extends Comparable<K>> extends AbstractSorter<K> {

        public void swap(int i, int p){

            AbstractAction ac = P.swap(i, p);
            while(ac.isRunning()){
                System.console();
            }

            K n = v.elementAt(i);
            v.set(i, v.elementAt(p));
            v.set(p, n);
        }

        public void quickSort(int p, int q){
            if(p < q){
                int r = Partition(p, q);

                Rectangle rect1 = P.highlightArea(p, r-1);
                Rectangle rect2 = P.highlightArea(r+1, q);

                quickSort(p, r-1);

                P.removeRect(rect1);

                quickSort(r+1, q);

                P.removeRect(rect2);
            }
        }

        public int Partition(int p, int q){
            int i = p-1;
            for(int j = p; j <= q; j++){

                P.color(q, Color.GREEN);
                Util.waitAction(1000);
                P.addPin(j);

                if(v.elementAt(j).compareTo(v.elementAt(q)) <= 0){
                    i += 1;
                    swap(i, j);
                }
            }

            P.removeAllPins();

            return i;
        }

    @Override
    public void doSort() {
        quickSort(0, v.size()-1);
    }
}
