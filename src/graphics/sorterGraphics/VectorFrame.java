package graphics.sorterGraphics;

import util.Util;

import javax.swing.*;

public class VectorFrame extends JFrame{

    public <K extends Comparable<K>> VectorFrame(VectorPanel<K> P){
        super();
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.add(P);
    }
}
