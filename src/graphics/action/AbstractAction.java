package graphics.action;

import java.awt.*;

public abstract class AbstractAction {

    private boolean inAction = true;

    protected boolean firstStart = true;

    public abstract void doAction(Graphics2D g2d);

    public void setActionRunning() {
        this.inAction = true;
    }

    public void setActionOver() {
        this.inAction = false;
    }

    public boolean isRunning() {
        return inAction;
    }
}
 