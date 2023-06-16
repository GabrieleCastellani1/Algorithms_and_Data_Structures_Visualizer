package logic.sorter;

import logic.sorter.concreteSorters.*;

import java.util.Vector;

public class SorterFactory {
    public SorterFactory(){}

    public <K extends Comparable<K>> AbstractSorter<K> createInsertionSort(Vector<K> v){
        AbstractSorter<K> ins = new InsertionSort<>();
        ins.v = v;
        return ins;
    }

    public <K extends Comparable<K>> AbstractSorter<K> createSelectionSort(Vector<K> v){
        AbstractSorter<K> sel = new SelectionSort<>();
        sel.v = v;
        return sel;
    }

    public <K extends Comparable<K>> AbstractSorter<K> createQuickSort(Vector<K> v){
        AbstractSorter<K> quick = new QuickSort<>();
        quick.v = v;
        return quick;
    }

    public <K extends Comparable<K>> AbstractSorter<K> createMergeSort(Vector<K> v){
        AbstractSorter<K> mergeSort = new MergeSort<>();
        mergeSort.v = v;
        return mergeSort;
    }

    public <K extends Comparable<K>> AbstractSorter<K> createOptQuickSort(Vector<K> v){
        AbstractSorter<K> quickSort = new OptQuickSort<>();
        quickSort.v = v;
        return quickSort;
    }
}
