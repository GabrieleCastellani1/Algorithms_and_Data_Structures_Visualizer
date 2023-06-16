package graphics.sorterGraphics;

import graphics.action.AbstractAction;
import graphics.action.ActionFactory;
import graphics.sorterGraphics.figures.Line;
import graphics.sorterGraphics.figures.Pin;
import graphics.sorterGraphics.figures.Rectangle;
import graphics.sorterGraphics.figures.Square;
import logic.sorter.AbstractSorter;
import util.Util;

import javax.swing.*;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Vector;

public class VectorPanel <K extends Comparable<K>> extends JPanel {

    private final int xStart;
    private final int yStart;
    private final int sideLength;
    Vector<K> v;
    Vector<Square<K>> squares = new Vector<>();
    Vector<AbstractAction> actions = new Vector<>();
    Vector<Pin> pins = new Vector<>();
    Vector<Rectangle> rectangles = new Vector<>();
    Vector<Line> lines = new Vector<>();
    AbstractSorter<K> ord;
    private final JButton doSortButton;
    private final JButton insertButton;
    private final JButton deleteButton;
    private final JTextField insertText;
    private final JTextField deleteText;

    public VectorPanel(Vector<K> v, AbstractSorter<K> ord, int xStart, int yStart, int sideLength){
        this.v = v;
        this.xStart = xStart;
        this.yStart = yStart;
        this.sideLength = sideLength;
        this.ord = ord;
        initializeSquares(v);


        insertButton = Util.createButton(500, 400, "Inserisci");

        insertText = Util.createTextField(280, 400);

        deleteButton = Util.createButton(500,450, "Elimina");

        deleteText = Util.createTextField(280, 450);

        doSortButton = Util.createButton(500, 350, "Ordina");

        insertButton.addActionListener(e -> {
            K Jtext = setText(insertText.getText());
            if(v.size() > 0){
                if(!v.get(0).getClass().equals(Jtext.getClass())){
                    insertText.setText("");
                }else{
                    insertText.setText("");
                    v.add(Jtext);
                    initializeSquares(v);
                }
            }else{
                insertText.setText("");
                v.add(Jtext);
                initializeSquares(v);
            }
        });

        deleteButton.addActionListener(e -> {
            K Jtext = setText(deleteText.getText());
            v.remove(Jtext);
            initializeSquares(v);
            deleteText.setText("");
        });

        doSortButton.addActionListener(e -> {
            Thread t = new Thread(() -> {
                insertButton.setEnabled(false);
                deleteButton.setEnabled(false);
                doSortButton.setEnabled(false);
                ord.doSort();
                insertButton.setEnabled(true);
                deleteButton.setEnabled(true);
                doSortButton.setEnabled(true);
            });
            t.start();
        });

        this.setLayout(null);
        this.add(insertButton);
        this.add(insertText);
        this.add(deleteButton);
        this.add(deleteText);
        this.add(doSortButton);
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

    public void removeButtons(){
        remove(doSortButton);
        remove(insertButton);
        remove(deleteButton);
        remove(insertText);
        remove(deleteText);
    }

    public void initializeSquares(Vector<K> v){
        squares.removeAll(squares);
        int xStartInitialize = xStart;
        for (K k : v) {
            Square<K> square = new Square<>(k, xStartInitialize, yStart, sideLength);
            this.squares.add(square);
            xStartInitialize += sideLength;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Iterator<AbstractAction> it = actions.iterator();
        while(it.hasNext()){
            AbstractAction action = it.next();
            if(action.isRunning()){
                action.doAction(g2d);
            }else{
                it.remove();
            }
        }

        try {
            for (Square<K> square : squares) {
                square.draw(g2d);
            }

            for (Rectangle rectangle : rectangles) {
                rectangle.draw(g2d);
            }

            for (Pin pin : pins) {
                pin.draw(g2d);
            }

            for (Line line : lines) {
                line.draw(g2d);
            }
        }catch(ConcurrentModificationException ignored){

        }finally {
            repaint();
        }
    }

    public AbstractAction swap(int i, int j){
        ActionFactory factory = new ActionFactory();
        AbstractAction ac = factory.createSwap(squares.elementAt(i), squares.elementAt(j));
        Square<K> key = squares.elementAt(i);
        squares.set(i, squares.elementAt(j));
        squares.set(j, key);
        actions.add(ac);
        return ac;
    }

    public Rectangle highlightElement(int i){
        try{
            Square<K> key = squares.elementAt(i);
            graphics.sorterGraphics.figures.Rectangle rect = new graphics.sorterGraphics.figures.Rectangle(
                    key.x - key.sideLength/5,
                    key.y - key.sideLength/5,
                    key.sideLength + key.sideLength/3,
                    key.sideLength + key.sideLength/3
            );
            rectangles.add(rect);
            return rect;
        }catch(ArrayIndexOutOfBoundsException exc){
            return null;
        }
    }

    public Rectangle highlightArea(int i, int j){
            if(i == j) {
                return highlightElement(i);
            }else{
                try{
                    Square<K> key = squares.elementAt(i);
                    graphics.sorterGraphics.figures.Rectangle rect = new graphics.sorterGraphics.figures.Rectangle(
                            key.x - key.sideLength/5,
                            key.y - key.sideLength/5,
                            (key.sideLength * (j - i + 1))+ key.sideLength/3,
                            key.sideLength + key.sideLength/3
                    );
                    rectangles.add(rect);
                    return rect;
                }catch(ArrayIndexOutOfBoundsException exc){
                    return null;
                }
            }
    }

    public void addLine(int i){
        Square<K> square = squares.get(i);
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
        int x = squares.elementAt(i).x + 30;
        int y = squares.elementAt(i).y;

        Pin pin = new Pin(x, y);
        pins.add(pin);
    }

    public void removeAllPins(){
        pins.removeAll(pins);
    }

    public void color(int i, Color color){
        squares.elementAt(i).color = color;
    }
}
