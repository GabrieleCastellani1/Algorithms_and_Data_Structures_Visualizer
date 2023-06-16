package graphics.graphsGraphics;

import logic.graphs.AbstractGraph;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphFrame extends JFrame {

    public <K> GraphFrame(List<AbstractGraph<K>> graphs){
        super();
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        GraphPanelFactory factory = new GraphPanelFactory();
        AbstractGraphPanel<K> Panel = factory.createGraphPanel(graphs);
        Panel.setBackground(Color.WHITE);
        Panel.setVisible(true);
        Panel.repaint();
        this.add(Panel);
    }
}
