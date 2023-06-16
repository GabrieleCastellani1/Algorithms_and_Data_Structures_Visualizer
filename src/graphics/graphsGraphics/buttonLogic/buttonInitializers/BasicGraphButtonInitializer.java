package graphics.graphsGraphics.buttonLogic.buttonInitializers;

import logic.graphs.AbstractGraph;
import logic.graphs.Node;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class BasicGraphButtonInitializer implements ButtonInitializer {
    protected final List<Component> allComponents;

    public <K> BasicGraphButtonInitializer(AbstractGraph<K> graph) {

        JButton BFSButton = Util.createButton(600, 0, 200, 50, "BFS");

        JTextField BFSNodeText = Util.createTextField(550, 0, 50, 50);

        JButton DFSButton = Util.createButton(600, 50, 200, 50, "DFS");

        BFSButton.addActionListener(e -> {
            Optional<Node<K>> optionalNode = graph.findNode((K) BFSNodeText.getText());
            optionalNode.ifPresent(graph::BFS);
            BFSNodeText.setText("");
        });


        DFSButton.addActionListener(e -> graph.DFS());

        allComponents = List.of(
                BFSButton,
                BFSNodeText,
                DFSButton
        );
    }

    public List<Component> getAllComponents() {
        return allComponents;
    }
}
