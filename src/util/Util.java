package util;

import java.awt.*;

import logic.binaryTree.Node;

import javax.swing.*;

public class Util {

    public static int OVALDIAMETER = 30;
    public static int OVALRAY = OVALDIAMETER / 2;
    public static int XSTART = 390;
    public static int YSTART = 100;
    public static int FRAMEWIDTH = 800;
    public static int FRAMEHEIGHT = 600;
    public static int BUTTONWIDTH = 220;
    public static int BUTTONHEIGHT = 50;
    public static final double cSpring = 24;
    public static final double l = 130;
    public static final double cRep = 40000;
    public static final double cGrav = 150;
    public static final double breakFactor = 0.88;

    public static <K extends Comparable<K>> void drawFill(Node<K> t, Graphics2D g2d){
        g2d.setColor(t.getColor());
        int width = t.getCoordinate()[0];
        int height = t.getCoordinate()[1];
        g2d.fillOval(width, height, Util.OVALDIAMETER, Util.OVALDIAMETER);
        Font f1 = new Font("Arial",Font.BOLD,13);
        g2d.setFont(f1);
        g2d.setColor(Color.BLACK);
        drawS(t, g2d);
    }

    public static <K extends Comparable<K>> void drawO(Node<K> t, Graphics2D g2d) {
        if(!(t.getCoordinate() == null)){
        int w = t.getCoordinate()[0];
        int h = t.getCoordinate()[1];
        g2d.drawOval(w, h, OVALDIAMETER, OVALDIAMETER);
        }
    }

    public static <K extends Comparable<K>> void drawS(Node<K> t, Graphics2D g2d) {
        if(!(t.getCoordinate() == null)){
        String s = (t.getKey() == null)? "null" : t.getKey().toString();// da sistemare con un'eccezione apposita
        int w = t.getCoordinate()[0] + (OVALRAY / 2) + 5;
        int h = t.getCoordinate()[1] + OVALRAY + 5;
        int length = s.length();
        int deviation = (length == 1) ? 0 : 2 * length;
        g2d.drawString(s, w - deviation, h);
        }
    }

    public static void drawL(Node<?> p, Node<?> s, Graphics2D g2d) {
        if(p.getCoordinate() != null && s.getCoordinate() != null){
            int x1 = p.getCoordinate()[0] + OVALRAY;
            int y1 = p.getCoordinate()[1] + OVALDIAMETER;
            int x2 = s.getCoordinate()[0] + OVALRAY;
            int y2 = s.getCoordinate()[1];
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public static void setLocationToTopRight(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

        int x = bounds.x + bounds.width - insets.right - frame.getWidth();
        int y = bounds.y + insets.top;
        frame.setLocation(x, y);
    }

    public static void waitAction(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static JButton createButton(int x, int y, String text){
        JButton Button = new JButton();
        Button.setBounds(x, y, BUTTONWIDTH, BUTTONHEIGHT);
        Button.setText(text);
        return Button;
    }

    public static JButton createButton(int x, int y, int width, int height, String text){
        JButton Button = new JButton();
        Button.setBounds(x, y, width, height);
        Button.setText(text);
        return Button;
    }

    public static JTextField createTextField(int x, int y){
        JTextField text = new JTextField();
        text.setBounds(x, y, BUTTONWIDTH, BUTTONHEIGHT);
        return text;
    }

    public static JTextField createTextField(int x, int y, int width, int height){
        JTextField text = new JTextField();
        text.setBounds(x, y, width, height);
        return text;
    }
}
