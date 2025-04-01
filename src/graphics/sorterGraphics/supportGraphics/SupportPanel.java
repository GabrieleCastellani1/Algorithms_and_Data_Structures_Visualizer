package graphics.sorterGraphics.supportGraphics;

import graphics.sorterGraphics.VectorViewManager;

import javax.swing.*;
import java.awt.*;

public class SupportPanel<K extends Comparable<K>> extends JPanel {
    private final VectorViewManager<K> vectorViewManger;

    public SupportPanel(int xStart, int currentY, int sideLength) {
        this.setVisible(true);
        this.setSize(500, 600);
        this.setBackground(Color.white);

        this.vectorViewManger = new VectorViewManager<>(xStart, currentY, sideLength);
    }

    public void addSquare(K key){
        this.vectorViewManger.addSquare(key);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        this.vectorViewManger.getAll().forEach(s -> s.draw(g2d));
        repaint();
    }
}
