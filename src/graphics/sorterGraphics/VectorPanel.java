package graphics.sorterGraphics;

import graphics.action.AbstractAction;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class VectorPanel <K extends Comparable<K>> extends JPanel {
    private final ActionManager<K> actionManager;

    public VectorPanel(ActionManager<K> actionManager){
        this.actionManager = actionManager;
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
        }catch(ConcurrentModificationException ignored){

        }finally {
            repaint();
        }
    }
}
