package logic.sorter.concreteSorters;

import graphics.sorterGraphics.ActionManager;
import graphics.sorterGraphics.VectorPanel;

import graphics.sorterGraphics.figures.Rectangle;
import graphics.sorterGraphics.supportGraphics.SupportFrame;
import logic.sorter.AbstractSorter;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MergeSort <K extends Comparable<K>> extends AbstractSorter<K> {
    public MergeSort(Vector<K> v, ActionManager<K> actionManager) {
        super(v, actionManager);
    }

    public void Merge(int p, int r, int q){
            int i = p;
            int j = r+1;

            Rectangle rect1 = actionManager.highlightArea(p, r);
            Util.waitAction(1000);
            Rectangle rect2 = actionManager.highlightArea(r+1, q);
            Util.waitAction(1000);

            Vector<K> B = new Vector<>();

            SupportFrame<K> supportFrame = new SupportFrame<>();

            int k = 0;
            while(i <= r && j <= q){
                if(v.elementAt(i).compareTo(v.elementAt(j)) <= 0){
                    B.add(k, v.elementAt(i));

                    supportFrame.addSquare(v.elementAt(i));
                    Util.waitAction(1000);

                    i++;
                }else{
                    B.add(k, v.elementAt(j));

                    supportFrame.addSquare(v.elementAt(j));
                    Util.waitAction(1000);

                    j++;
                }
                k++;
            }
            if( i > r ){
                for(int m = j ; m <= q; m++ ){
                    B.add(k, v.elementAt(m));

                    supportFrame.addSquare(v.elementAt(m));
                    Util.waitAction(1000);

                    k++;
                }
            }else{
                for(int m = i; m <= r; m++){
                    B.add(k, v.elementAt(m));

                    supportFrame.addSquare(v.elementAt(m));
                    Util.waitAction(1000);

                    k++;
                }
            }
            for(int w = p; w <= q; w++){
                v.set(w, B.elementAt(w-p));
                actionManager.setSquare(w, v.get(w));

                Util.waitAction(1000);
            }
            supportFrame.dispose();
            actionManager.removeRect(rect1);
            actionManager.removeRect(rect2);
        }
        public void MergeSortSupport(int p, int q){
            if(p < q){
                int r = (p+q)/2;

                Rectangle rect1 = actionManager.highlightArea(p, r);
                Util.waitAction(1000);

                Rectangle rect2 = actionManager.highlightArea(r+1, q);
                Util.waitAction(1000);

                actionManager.removeRect(rect1);
                actionManager.removeRect(rect2);

                MergeSortSupport(p,r);
                MergeSortSupport(r+1,q);
                Merge(p,r,q);
            }
        }

    @Override
    public void doSort() {
        MergeSortSupport(0, v.size()-1);
    }
}
