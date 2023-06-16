package graphics.graphsGraphics.buttonLogic.buttonInitializers;

import logic.graphs.OrientedGraph;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrientedGraphButtonInitializer implements ButtonInitializer {

    protected final List<Component> allComponents;

    public OrientedGraphButtonInitializer(OrientedGraph<?> graph){

        JButton KosarajuButton = Util.createButton(600, 0, 200, 50, "Kosaraju");

        KosarajuButton.addActionListener(e -> graph.Kosaraju());

        allComponents = List.of(KosarajuButton);
    }

    @Override
    public List<Component> getAllComponents() {
        return allComponents;
    }
}
