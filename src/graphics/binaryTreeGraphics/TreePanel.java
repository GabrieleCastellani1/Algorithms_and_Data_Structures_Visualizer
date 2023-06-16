package graphics.binaryTreeGraphics;

import graphics.action.AbstractAction;
import graphics.action.ActionFactory;
import logic.binaryTree.AbstractTree;
import logic.binaryTree.Node;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TreePanel<K extends Comparable<K>> extends JPanel {
    private final AbstractTree<K> Tree;
    private Graphics2D g2d;
    private volatile AbstractAction action = null;
    private final JButton insertButton;
    private final JButton deleteButton;
    private final ActionFactory factory;

    public AbstractAction getAction() {
        return action;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public enum Direction {
        LEFT,
        RIGHT
    }

    public TreePanel(AbstractTree<K> tree) {

        Tree = tree;

        insertButton = Util.createButton(500, 400, "Inserisci");
        
        JTextField insertText = Util.createTextField(280,400);

        deleteButton = Util.createButton(500, 450, "Elimina");

        JTextField deleteText = Util.createTextField(280, 450);

        insertButton.addActionListener(e -> {
            K Jtext = setText(insertText.getText());
            insertText.setText("");
            Node<K> node = tree.createTreeNode(Jtext);
            Thread t = new Thread(() -> {
                insertButton.setEnabled(false);
                deleteButton.setEnabled(false);
                Tree.insert(node);
                while(action != null && action.isRunning()){
                    Thread.onSpinWait();
                }
                insertButton.setEnabled(true);
                deleteButton.setEnabled(true);
            });
            t.start();
        });

        deleteButton.addActionListener(e -> {
            K Jtext = setText(deleteText.getText());
            deleteText.setText("");
            Thread t = new Thread(() -> {
                insertButton.setEnabled(false);
                deleteButton.setEnabled(false);
                Tree.delete(Jtext);
                while(action != null && action.isRunning()){
                    Thread.onSpinWait();
                }
                insertButton.setEnabled(true);
                deleteButton.setEnabled(true);
            });
            t.start();
        });
        
        this.setLayout(null);
        this.add(insertButton);
        this.add(insertText);
        this.add(deleteButton);
        this.add(deleteText);

        factory = new ActionFactory();
    }

    public void waitAction(){
        while(action != null){
            Thread.onSpinWait();
        }
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

    public void insert(K k, Vector<Direction> directions) {
        waitAction();
        this.action = factory.createInsertion(directions, Tree.getRoot(), new Node<>(k));
    }

    public void rotate(Direction dir, Node<K> pivot) {
        waitAction();
        this.action = factory.createRotation(dir, pivot);
        waitAction();
    }

    @Override
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        AbstractAction print = factory.createPrint(Tree.getRoot());
        print.doAction(g2d);

        if(action != null && action.isRunning()){
            action.doAction(g2d);
        }else{
            action = null;
        }

        repaint();
    }
}
