package logic.sorter;

import graphics.sorterGraphics.ActionManager;
import graphics.sorterGraphics.VectorViewManager;
import logic.sorter.concreteSorters.*;

import java.util.Vector;

public class SorterFactory <K extends Comparable<K>>{
    private final VectorViewManager<K> vectorViewManager;
    private final ActionManager<K> actionManager;

    public SorterFactory(){
        this.vectorViewManager = new VectorViewManager<>(200,300,70);
        this.actionManager = new ActionManager<>(vectorViewManager);
    }

    public AbstractSorter<K> createInsertionSort(Vector<K> v){
        return new InsertionSort<>(v, actionManager);
    }

    public AbstractSorter<K> createSelectionSort(Vector<K> v){
        return new SelectionSort<>(v, actionManager);
    }

    public AbstractSorter<K> createQuickSort(Vector<K> v){
        return new QuickSort<>(v, actionManager);
    }

    public AbstractSorter<K> createMergeSort(Vector<K> v){
        return new MergeSort<>(v, actionManager);
    }

    public AbstractSorter<K> createOptQuickSort(Vector<K> v){
        return new OptQuickSort<>(v, actionManager);
    }
}
