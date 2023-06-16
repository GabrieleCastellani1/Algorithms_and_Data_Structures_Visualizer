package logic.sorter.concreteSorters;

import graphics.action.AbstractAction;
import logic.sorter.AbstractSorter;
import util.Util;

import java.awt.*;

public class SelectionSort <K extends Comparable<K>> extends AbstractSorter<K> {

    public void doSort (){
            K min = v.elementAt(0);
            int count = 0;
            int minpos = 0;
            while(count < v.size()-1) {
                for (int i = count; i < v.size(); i++) {

                    P.removeAllRect();
                    P.highlightElement(count);
                    Util.waitAction(1000);

                    if (v.elementAt(i).compareTo(min) < 0) {
                        min = v.elementAt(i);
                        minpos = i;
                    }

                    P.addPin(i);

                }

                P.removeAllPins();
                P.color(minpos, Color.GREEN);
                AbstractAction ac = P.swap(minpos, count);
                while(ac.isRunning()){
                    System.console();
                }

                v.set(minpos, v.elementAt(count));
                v.set(count, min);
                count += 1;
                min = v.elementAt(count);
                minpos = count;
            }
        }
}
