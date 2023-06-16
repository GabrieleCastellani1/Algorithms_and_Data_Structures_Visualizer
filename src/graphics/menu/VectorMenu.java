package graphics.menu;

import enums.SorterType;
import graphics.sorterGraphics.VectorFrame;
import graphics.sorterGraphics.VectorPanel;
import logic.sorter.AbstractSorter;
import logic.sorter.SorterFactory;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorMenu extends JFrame{

    public VectorMenu() {
        super();
        initializeMenu();

        List<String> sorts = new ArrayList<>();
        sorts.add("SelectionSort");
        sorts.add("InsertionSort");
        sorts.add("MergeSort");
        sorts.add("QuickSort");
        sorts.add("OptimizedQuickSort");
        int yStart = 300;

        for (String sort : sorts) {
            JButton button = Util.createButton(300, yStart, sort);
            button.addActionListener(e -> createSort(SorterType.valueOf(sort.toUpperCase())));
            this.add(button);
            yStart -= 50;
        }
    }

    private void initializeMenu(){
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
    }

    private <K extends Comparable<K>> void createSort(SorterType sortType){
        Vector<K> v = new Vector<>(10);
        AbstractSorter<K> ord = chooseSorter(sortType, v);
        VectorPanel<K> P = new VectorPanel<>(v, ord,100, 100, 80);
        assert ord != null;
        ord.setPanel(P);
        JFrame F = new VectorFrame(P);
        Util.setLocationToTopRight(F);
    }

    private <K extends Comparable<K>> AbstractSorter<K> chooseSorter(SorterType sorterType, Vector<K> v){
        SorterFactory factory = new SorterFactory();
        switch (sorterType) {
            case SELECTIONSORT: {
                return factory.createSelectionSort(v);
            }
            case INSERTIONSORT: {
                return factory.createInsertionSort(v);
            }
            case MERGESORT: {
                return factory.createMergeSort(v);
            }
            case QUICKSORT: {
                return factory.createQuickSort(v);
            }
            case OPTQUICKSORT: {
                return factory.createOptQuickSort(v);
            }
            default : {
                return null;
            }
        }
    }
}
