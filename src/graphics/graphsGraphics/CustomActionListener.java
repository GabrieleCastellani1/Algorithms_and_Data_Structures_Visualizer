package graphics.graphsGraphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomActionListener implements ActionListener {

    ActionListener actionListener;
    List<Component> components;

    public CustomActionListener(ActionListener actionListener, List<Component> components) {
        this.actionListener = actionListener;
        this.components = components;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread t = new Thread(() -> {
            components.forEach(c -> c.setVisible(false));
            actionListener.actionPerformed(e);
            components.forEach(c -> c.setVisible(true));
        });
        t.start();
    }
}
