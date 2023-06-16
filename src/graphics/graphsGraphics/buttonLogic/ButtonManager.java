package graphics.graphsGraphics.buttonLogic;

import logic.graphs.AbstractGraph;
import graphics.graphsGraphics.CustomActionListener;
import graphics.graphsGraphics.buttonLogic.buttonInitializers.ButtonInitializer;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class ButtonManager<K> {
    private final List<AbstractGraph<K>> graphs;
    private final List<ButtonInitializer> initializers;
    private final ButtonInitializerFactory buttonInitializerFactory;

    public ButtonManager(List<AbstractGraph<K>> graphs) {
        this.buttonInitializerFactory = new ButtonInitializerFactory();
        this.graphs = graphs;
        this.initializers = new ArrayList<>();
        generateInitializers();
    }

    public void generateInitializers(){
        graphs.forEach(
                g -> initializers.add(buttonInitializerFactory.createButtonInitializer(g))
        );
    }

    public Component initializeShowButton(List<Component> allComponents){
        JButton showButtonsButton = Util.createButton(600, 0, 200, 50, "Mostra bottoni");
        allComponents.add(showButtonsButton);

        showButtonsButton.addActionListener(e -> {
            if(allComponents.stream().anyMatch(c -> !c.isVisible())){
                allComponents.forEach(b -> b.setVisible(true));
            }else{
                allComponents.forEach(b -> b.setVisible(false));
                allComponents.get(allComponents.indexOf(showButtonsButton)).setVisible(true);
            }
        });

        return showButtonsButton;
    }

    public List<Component> combineButtons(){
        List<Component> components = new ArrayList<>();
        append(components, buttonInitializerFactory.createIOInitializer(graphs).getAllComponents());
        append(components, buttonInitializerFactory.createBasicGraphButtonInitializer(graphs.get(0)).getAllComponents());
        initializers.forEach(i -> append(components, i.getAllComponents()));

        Map<JButton, ActionListener[]> buttonMap = new HashMap<>();
        components.forEach(component -> {
            if (component instanceof JButton){
                buttonMap.put((JButton) component, ((JButton) component).getActionListeners());
            }
        });

        buttonMap.keySet().forEach(button -> {
            List<ActionListener> actionListeners = new ArrayList<>();
            List.of(buttonMap.get(button))
                    .forEach(actionListener -> actionListeners.add(
                            new CustomActionListener(actionListener, components))
                    );

            List.of(buttonMap.get(button)).forEach(button::removeActionListener);

            buttonMap.put(button, actionListeners.toArray(new ActionListener[]{}));
        });
        buttonMap.keySet().forEach(button -> Arrays.stream(buttonMap.get(button))
                .sequential()
                .forEach(button::addActionListener));
        components.add(initializeShowButton(components));
        return components;
    }

    public void append(List<Component> components, List<Component> append) {
        int maxY = 0;
        for (Component c : components) {
            if (c.getY() > maxY) {
                maxY = c.getY();
            }
        }
        for (Component c : append) {
            c.setBounds(c.getX(), c.getY() + maxY + 50, c.getWidth(), c.getHeight());
        }
        components.addAll(append);
    }
}
