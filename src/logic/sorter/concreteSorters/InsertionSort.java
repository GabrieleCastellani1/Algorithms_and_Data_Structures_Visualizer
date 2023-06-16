package logic.sorter.concreteSorters;

import graphics.action.AbstractAction;
import logic.sorter.AbstractSorter;

public class InsertionSort <K extends Comparable<K>> extends AbstractSorter<K> {
        public void doSort(){
            for(int j = 1; j < v.size(); j++){
                int i = j-1;
                K key = v.elementAt(j);

                P.removeAllRect();
                P.highlightElement(j);

                while(i >= 0 && v.elementAt(i).compareTo(key) > 0) {

                    P.addPin(i);

                    v.set(i + 1, v.elementAt(i));
                    v.set(i, key);

                    AbstractAction ac = P.swap(i, i+1);
                    while(ac.isRunning()){
                        System.console();
                    }

                    i = i - 1;
                }

                P.removeAllPins();

            }
        }
}
