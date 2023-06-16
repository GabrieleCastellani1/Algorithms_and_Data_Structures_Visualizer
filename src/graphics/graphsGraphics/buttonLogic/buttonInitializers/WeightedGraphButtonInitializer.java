package graphics.graphsGraphics.buttonLogic.buttonInitializers;

import logic.graphs.Node;
import logic.graphs.WeightedGraph;
import util.Util;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeightedGraphButtonInitializer implements ButtonInitializer {
    protected final List<Component> allComponents;
    public <K> WeightedGraphButtonInitializer(WeightedGraph<K> graph){

        JButton KruscalButton = Util.createButton(600, 0, 200, 50, "Kruscal");

        JButton PrimButton = Util.createButton(600, 50, 200, 50, "Prim");

        JTextField PrimNodeText = Util.createTextField(550, 50, 50,50);

        JButton DijkstraButton = Util.createButton(600, 100, 200, 50, "Dijkstra");

        JTextField DijkstraNodeText = Util.createTextField(550, 100, 50,50);

        KruscalButton.addActionListener(e -> graph.Kruscal());

        PrimButton.addActionListener(e -> {
            Optional<Node<K>> optionalNode = graph.findNode((K) PrimNodeText.getText());
            optionalNode.ifPresent(graph::Prim);
            PrimNodeText.setText("");
        });

        DijkstraButton.addActionListener(e -> {
            Optional<Node<K>> optionalNode = graph.findNode((K) DijkstraNodeText.getText());
            optionalNode.ifPresent(graph::Dijkstra);
            DijkstraNodeText.setText("");
        });

        ArrayList<Component> PrimComponents = new ArrayList<>();
        ArrayList<Component> DijkstraComponents = new ArrayList<>();
        PrimComponents.add(PrimButton);
        PrimComponents.add(PrimNodeText);
        DijkstraComponents.add(DijkstraButton);
        DijkstraComponents.add(DijkstraNodeText);

        allComponents = new ArrayList<>();
        allComponents.addAll(PrimComponents);
        allComponents.addAll(DijkstraComponents);
        allComponents.add(KruscalButton);
    }

    @Override
    public List<Component> getAllComponents() {
        return allComponents;
    }
}
