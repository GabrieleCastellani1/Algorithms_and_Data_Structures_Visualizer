package graphics.action.concreteActions;

import graphics.action.AbstractAction;
import graphics.sorterGraphics.figures.Square;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Swap extends AbstractAction implements ActionListener {

    private final Square<?> key1;
    private final int initXPosition1;
    private final int initYPosition1;
    private final Square<?> key2;
    private final int initXPosition2;

    private final Timer t = new Timer(70, this);

    public Swap(Square<?> key1, Square<?> key2) {
        this.key1 = key1;
        this.initXPosition1 = key1.x;
        this.initYPosition1 = key1.y;
        this.key2 = key2;
        this.initXPosition2 = key2.x;
        setActionRunning();
    }

    @Override
    public void doAction(Graphics2D g2d) {
        if (firstStart) {
            t.start();
            firstStart = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (initXPosition1 < initXPosition2) {
            if (key1.y < (initYPosition1 + key1.sideLength) && key1.x < initXPosition2) {
                key1.y += 10;
                key2.y -= 10;
            } else if (key1.x < initXPosition2) {
                key1.x += 10;
                key2.x -= 10;
            } else if (key1.y > initYPosition1) {
                key1.y -= 10;
                key2.y += 10;
            } else {
                t.stop();
                setActionOver();
            }
        } else {
            if (key1.y < (initYPosition1 + key1.sideLength) && key1.x > initXPosition2) {
                key1.y += 10;
                key2.y -= 10;
            } else if (key1.x > initXPosition2) {
                key1.x -= 10;
                key2.x += 10;
            } else if (key1.y > initYPosition1) {
                key1.y -= 10;
                key2.y += 10;
            } else {
                t.stop();
                setActionOver();
            }
        }
    }
}
