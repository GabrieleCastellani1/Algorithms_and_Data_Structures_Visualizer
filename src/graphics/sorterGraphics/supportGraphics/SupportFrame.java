package graphics.sorterGraphics.supportGraphics;

import util.Util;

import javax.swing.*;

public class SupportFrame<K extends Comparable<K>> extends JFrame {
    private final SupportPanel<K> supportPanel;

    public SupportFrame() {
        super();
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        Util.setLocationToTopRight(this);
        this.supportPanel = new SupportPanel<>(200, 300, 70);
        this.add(supportPanel);
    }

    public void addSquare(K key){
        supportPanel.addSquare(key);
    }

}
