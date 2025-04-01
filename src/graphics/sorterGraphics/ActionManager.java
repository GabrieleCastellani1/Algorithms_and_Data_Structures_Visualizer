package graphics.sorterGraphics;

import graphics.action.AbstractAction;
import graphics.action.ActionFactory;
import graphics.sorterGraphics.figures.Rectangle;
import graphics.sorterGraphics.figures.*;

import java.awt.*;
import java.util.Vector;

public class ActionManager <K extends Comparable<K>> {
    private final VectorViewManager<K> vectorViewManager;
    private final Vector<Pin> pins;
    private final Vector<Rectangle> rectangles;
    private final Vector<Line> lines;
    private final Vector<AbstractAction> actions;

    public ActionManager(VectorViewManager<K> vectorViewManager) {
        this.vectorViewManager = vectorViewManager;
        this.actions = new Vector<>();
        this.rectangles = new Vector<>();
        this.pins = new Vector<>();
        this.lines = new Vector<>();

    }

    public void swap(int i, int j){
        ActionFactory factory = new ActionFactory();
        AbstractAction ac = factory.createSwap(vectorViewManager.get(i), vectorViewManager.get(j));
        vectorViewManager.swap(i, j);
        actions.add(ac);
        waitAction(ac);
    }

    public Rectangle highlightElement(int i){
        try{
            Square<K> key = vectorViewManager.get(i);
            graphics.sorterGraphics.figures.Rectangle rect = new graphics.sorterGraphics.figures.Rectangle(
                    key.x - key.sideLength/5,
                    key.y - key.sideLength/5,
                    key.sideLength + key.sideLength/3,
                    key.sideLength + key.sideLength/3
            );
            rectangles.add(rect);
            return rect;
        }catch(ArrayIndexOutOfBoundsException ignored){
            return null;
        }
    }

    public void setSquare(int index, K key){
        vectorViewManager.setSquare(index, key);
    }

    public Rectangle highlightArea(int i, int j){
        if(i == j) {
            return highlightElement(i);
        }else{
            try{
                Square<K> key = vectorViewManager.get(i);
                graphics.sorterGraphics.figures.Rectangle rect = new graphics.sorterGraphics.figures.Rectangle(
                        key.x - key.sideLength/5,
                        key.y - key.sideLength/5,
                        (key.sideLength * (j - i + 1))+ key.sideLength/3,
                        key.sideLength + key.sideLength/3
                );
                rectangles.add(rect);
                return rect;
            }catch(ArrayIndexOutOfBoundsException ignored){
                return null;
            }
        }
    }

    public void addLine(int i){
        Square<K> square = vectorViewManager.get(i);
        int x = square.x + square.sideLength;
        int y = square.y;
        Line line = new Line(x, y);
        lines.add(line);
    }

    public void removeAllLines(){
        lines.removeAll(lines);
    }

    public void removeRect(Rectangle rect){
        rectangles.remove(rect);
    }

    public void removeAllRect(){
        rectangles.removeAll(rectangles);
    }

    public void addPin(int i){
        int x = vectorViewManager.get(i).x + 30;
        int y = vectorViewManager.get(i).y;

        Pin pin = new Pin(x, y);
        pins.add(pin);
    }

    public void removeAllPins(){
        pins.removeAll(pins);
    }

    public void color(int i, Color color){
        vectorViewManager.get(i).color = color;
    }

    public Vector<Figure> getAllFigures(){
        Vector<Figure> figures = new Vector<>();
        figures.addAll(vectorViewManager.getAll());
        figures.addAll(rectangles);
        figures.addAll(pins);
        figures.addAll(lines);
        return figures;
    }

    public Vector<AbstractAction> getAllActions(){
        return actions;
    }

    public synchronized void waitAction(AbstractAction action){
        while(this.actions.contains(action)){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void restoreAction(AbstractAction action) {
        this.actions.remove(action);
        notify();
    }

    public void addSquare(K key) {
        this.vectorViewManager.addSquare(key);
    }

    public void removeSquare(K key) {
        this.vectorViewManager.removeSquare(key);
    }


}
