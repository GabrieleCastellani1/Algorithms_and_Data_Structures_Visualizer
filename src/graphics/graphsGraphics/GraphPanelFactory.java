package graphics.graphsGraphics;

import graphics.graphsGraphics.buttonLogic.ButtonManager;
import logic.graphs.AbstractGraph;

import java.awt.*;
import java.util.List;
public class GraphPanelFactory {
    public <K> AbstractGraphPanel<K> createGraphPanel(List<AbstractGraph<K>> graphs){
        AbstractGraphPanel<K> panel = new AbstractGraphPanel<>(graphs);
        List<Component> components = initializeButtons(graphs);
        panel.setLayout(null);
        components.forEach(c -> c.setVisible(true));
        components.forEach(c -> c.setEnabled(true));
        components.forEach(panel::add);
        return panel;
    }

    private <K> List<Component> initializeButtons(List<AbstractGraph<K>> graphs){
        ButtonManager<K> buttonManager = new ButtonManager<>(graphs);
        return buttonManager.combineButtons();
    }
}
