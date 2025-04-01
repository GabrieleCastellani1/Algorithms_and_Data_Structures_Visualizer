package graphics.sorterGraphics;

import graphics.action.AbstractAction;
import graphics.sorterGraphics.figures.Square;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class VectorPanel <K extends Comparable<K>> extends JPanel {
    private final ActionManager<K> actionManager;
    private Dimension size;

    public VectorPanel(ActionManager<K> actionManager){
        this.actionManager = actionManager;
        this.size = this.getSize();
    }

    private K setText(String s){
        try {
            return (K) Integer.valueOf(s);
        }catch(Exception ignored){}
        if(s.length() == 1){
            return (K) Character.valueOf(s.charAt(0));
        }else{
            return (K) s;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Iterator<AbstractAction> it = actionManager.getAllActions().iterator();
        this.getSize(size);
        while(it.hasNext()){
            AbstractAction action = it.next();
            if(action.isRunning()){
                action.doAction(g2d);
            }else{
                it.remove();
                actionManager.restoreAction(action);
            }
        }
        try {
            actionManager.getAllFigures().forEach(f -> f.draw(g2d));
            actionManager.getAllFigures().forEach(f -> {
                if(f instanceof Square<?>){
                    fixBorderCollisions((Square<?>) f);
                }
            });
        }catch(ConcurrentModificationException ignored){

        }finally {
            repaint();
        }
    }

    private void fixBorderCollisions(Square<?> square){
        if(square != null){
            if (square.x + Util.SIDELENGTH > size.width || square.x < 0){
                size = new Dimension(size.width + 1, size.height);
                this.setPreferredSize(size);

            }
            if (square.y + Util.OVALDIAMETER > size.height || square.y < 0){
                size = new Dimension(size.width, size.height + 1);
                this.setPreferredSize(size);
            }
        }
    }
}
