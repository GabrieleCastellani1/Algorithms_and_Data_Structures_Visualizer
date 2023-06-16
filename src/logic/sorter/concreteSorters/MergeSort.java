package logic.sorter.concreteSorters;

import graphics.sorterGraphics.VectorPanel;

import graphics.sorterGraphics.figures.Rectangle;
import logic.sorter.AbstractSorter;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MergeSort <K extends Comparable<K>> extends AbstractSorter<K> {
        public void Merge(int p, int r, int q){
            int i = p;
            int j = r+1;

            Rectangle rect1 = P.highlightArea(p, r);
            Util.waitAction(1000);
            Rectangle rect2 = P.highlightArea(r+1, q);
            Util.waitAction(1000);

            Vector<K> B = new Vector<>();
            VectorPanel<K> supportPanel = new VectorPanel<>(B, null,200, 300, 70);
            JFrame F = new JFrame();
            F.setResizable(true);
            F.setVisible(true);
            F.setSize(500, 600);
            supportPanel.setBackground(Color.white);
            Util.setLocationToTopRight(F);
            supportPanel.removeButtons();
            F.add(supportPanel);

            int k = 0;
            while(i <= r && j <= q){
                if(v.elementAt(i).compareTo(v.elementAt(j)) <= 0){
                    B.add(k, v.elementAt(i));

                    supportPanel.initializeSquares(B);
                    Util.waitAction(1000);

                    i++;
                }else{
                    B.add(k, v.elementAt(j));

                    supportPanel.initializeSquares(B);
                    Util.waitAction(1000);

                    j++;
                }
                k++;
            }
            if( i > r ){
                for(int m = j ; m <= q; m++ ){
                    B.add(k, v.elementAt(m));

                    supportPanel.initializeSquares(B);
                    Util.waitAction(1000);

                    k++;
                }
            }else{
                for(int m = i; m <= r; m++){
                    B.add(k, v.elementAt(m));

                    supportPanel.initializeSquares(B);
                    Util.waitAction(1000);

                    k++;
                }
            }
            for(int w = p; w <= q; w++){
                v.set(w, B.elementAt(w-p));

                P.initializeSquares(v);
                Util.waitAction(1000);
            }
            F.dispose();
            P.removeRect(rect1);
            P.removeRect(rect2);
        }
        public void MergeSortSupport(int p, int q){
            if(p < q){
                int r = (p+q)/2;

                Rectangle rect1 = P.highlightArea(p, r);
                Util.waitAction(1000);

                Rectangle rect2 = P.highlightArea(r+1, q);
                Util.waitAction(1000);

                P.removeRect(rect1);
                P.removeRect(rect2);

                MergeSortSupport(p,r);
                MergeSortSupport(r+1,q);
                Merge(p,r,q);
            }
        }

    @Override
    public void doSort() {
        MergeSortSupport(0, v.size()-1);
        P.initializeSquares(v);
    }
}
