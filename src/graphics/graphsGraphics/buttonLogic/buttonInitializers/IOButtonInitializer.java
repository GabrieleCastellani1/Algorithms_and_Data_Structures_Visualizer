package graphics.graphsGraphics.buttonLogic.buttonInitializers;

import logic.graphs.AbstractGraph;
import logic.graphs.Node;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IOButtonInitializer<K> implements ButtonInitializer {
    private final List<AbstractGraph<K>> graphs;

    public IOButtonInitializer(List<AbstractGraph<K>> graphs) {
        this.graphs = graphs;
    }

    @Override
    public List<Component> getAllComponents() {
        return initializeIOButtons(graphs);
    }

    public List<Component> initializeAddNodeButton(List<AbstractGraph<K>> graphs){
        JButton insertNodeButton = Util.createButton(600, 0, 200, 50, "Inserisci nodo");

        JTextField insertNodeText = Util.createTextField(550,0, 50, 50);

        insertNodeButton.addActionListener(e -> {
                graphs.get(0).addNode((K) insertNodeText.getText());
                insertNodeText.setText("");
        });

        return List.of(insertNodeButton, insertNodeText);
    }

    public List<Component> initializeAddEdgeButton(List<AbstractGraph<K>> graphs){

        JButton addEdgeButton = Util.createButton(600, 50, 200, 50, "Inserisci arco");

        JTextField insertFirstNodeText = Util.createTextField(450, 50, 50, 50);

        JTextField insertSecondNodeText = Util.createTextField(500, 50, 50, 50);

        JTextField insertWeightText = Util.createTextField(550, 50, 50, 50);

        addEdgeButton.addActionListener(e -> {
            graphs.forEach(g -> g.addEdge(
                            (K) insertFirstNodeText.getText(),
                            (K) insertSecondNodeText.getText(),
                            Integer.parseInt(insertWeightText.getText())
                    )
            );
                insertFirstNodeText.setText("");
                insertSecondNodeText.setText("");
                insertWeightText.setText("");
        });

        return List.of(addEdgeButton, insertFirstNodeText, insertSecondNodeText, insertWeightText);
    }

    public List<Component> initializeDeleteNodeButton(List<AbstractGraph<K>> graphs){
        JButton deleteNodeButton = Util.createButton(600, 100,200,50, "Elimina nodo");

        JTextField deleteNodeText = Util.createTextField(550, 100, 50, 50);

        deleteNodeButton.addActionListener(e -> {
                Optional<Node<K>> node = graphs.get(0).findNode((K) deleteNodeText.getText());
                node.ifPresent(kNode -> graphs.forEach(g -> g.deleteNode(kNode)));
                deleteNodeText.setText("");
        });

        return List.of(deleteNodeButton, deleteNodeText);
    }

    public List<Component> initializeDeleteEdgeButton(List<AbstractGraph<K>> graphs){
        JButton deleteEdgeButton = Util.createButton(600, 150,200,50, "Elimina arco");

        JTextField deleteFirstNodeText = Util.createTextField(500, 150, 50, 50);

        JTextField deleteSecondNodeText = Util.createTextField(550, 150, 50, 50);

        deleteEdgeButton.addActionListener(e -> {
            Optional<Node<K>> node1 = graphs.get(0).findNode((K) deleteFirstNodeText.getText());
            Optional<Node<K>> node2 = graphs.get(0).findNode((K) deleteSecondNodeText.getText());
            if(node1.isPresent() && node2.isPresent()){
                graphs.forEach(g -> g.deleteEdge(node1.get(), node2.get()));
            }
            deleteFirstNodeText.setText("");
            deleteSecondNodeText.setText("");
        });

        return List.of(deleteEdgeButton, deleteFirstNodeText, deleteSecondNodeText);
    }

    public List<Component> initializeIOButtons(List<AbstractGraph<K>> graphs){
        List<Component> IOComponents = new ArrayList<>(initializeAddNodeButton(graphs));
        IOComponents.addAll(initializeAddEdgeButton(graphs));
        IOComponents.addAll(initializeDeleteNodeButton(graphs));
        IOComponents.addAll(initializeDeleteEdgeButton(graphs));
        return IOComponents;
    }
}
