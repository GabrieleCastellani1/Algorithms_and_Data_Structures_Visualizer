package graphics.sorterGraphics.figures;

import java.awt.*;

public class Line implements Figure{

    int x;
    int y;

    public Line(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g2d){
        g2d.drawLine(x, y-20, x, y + 100);
    }
}
