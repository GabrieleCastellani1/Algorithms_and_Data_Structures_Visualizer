package graphics.sorterGraphics;

import logic.sorter.AbstractSorter;
import util.Util;

import javax.swing.*;
import java.awt.*;

public class VectorFrame extends JFrame{

    public <K extends Comparable<K>> VectorFrame(AbstractSorter<K> ord){
        super();
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        VectorPanel<K> panel = new VectorPanel<>(ord.getActionManager());

        VectorButtonPanel<K> vectorButtonPanel = new VectorButtonPanel<>(ord);

        JSplitPane container = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, vectorButtonPanel);
        container.setTopComponent(panel);
        container.setBottomComponent(vectorButtonPanel);
        container.setDividerLocation(400);
        container.setPreferredSize(new Dimension(800,600));
        container.setVisible(true);

        this.add(container);
    }
}
