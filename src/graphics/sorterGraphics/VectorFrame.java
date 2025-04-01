package graphics.sorterGraphics;

import logic.sorter.AbstractSorter;
import util.Util;

import javax.swing.*;
import java.awt.*;

public class VectorFrame extends JFrame{

    public <K extends Comparable<K>> VectorFrame(AbstractSorter<K> ord){
        super();
        this.setPreferredSize(new Dimension(Util.FRAMEWIDTH, Util.FRAMEHEIGHT));
        this.setResizable(true);
        this.setVisible(true);
        VectorPanel<K> panel = new VectorPanel<>(ord.getActionManager());

        VectorButtonPanel<K> vectorButtonPanel = new VectorButtonPanel<>(ord);
        Dimension preferredDimension = new Dimension(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(preferredDimension);

        JScrollPane scrollPane = new JScrollPane(
                panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        JViewport viewport = new JViewport(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                panel.paintComponent(g);
                repaint();
            }
        };

        viewport.setView(panel);
        scrollPane.setViewport(viewport);

        JSplitPane container = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        container.setTopComponent(scrollPane);
        container.setBottomComponent(vectorButtonPanel);
        container.setDividerLocation(Util.FRAMEWIDTH/2);
        container.setPreferredSize(preferredDimension);
        container.setVisible(true);

        this.add(container);
        this.pack();
    }
}
