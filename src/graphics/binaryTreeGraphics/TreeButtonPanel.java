package graphics.binaryTreeGraphics;

import logic.binaryTree.AbstractTree;
import logic.binaryTree.Node;

import javax.swing.*;
import java.awt.*;

public class TreeButtonPanel<K extends Comparable<K>> extends JPanel {
    private final AbstractTree<K> tree;
    private final ActionManager actionManager;
    private Thread buttonThread;

    public TreeButtonPanel(AbstractTree<K> tree, ActionManager actionManager) {
        this.tree = tree;

        this.actionManager = actionManager;

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBackground(Color.BLUE);

        JButton insertButton = new JButton();
        insertButton.setText("Inserisci");
        insertButton.setMaximumSize(new Dimension(200, 50));

        JTextField insertText = new JTextField();
        insertText.setPreferredSize(new Dimension(50, 50));
        insertText.setMaximumSize(new Dimension(50, 50));

        JButton deleteButton = new JButton();
        deleteButton.setText("Elimina");
        deleteButton.setMaximumSize(new Dimension(200, 50));

        JTextField deleteText = new JTextField();
        deleteText.setMaximumSize(new Dimension(50, 50));
        deleteText.setPreferredSize(new Dimension(50, 50));

        insertButton.addActionListener(e -> {
            K Jtext = setText(insertText.getText());
            insertText.setText("");
            Node<K> node = this.tree.createTreeNode(Jtext);
            buttonThread = new Thread(() -> {
                insertButton.setEnabled(false);
                deleteButton.setEnabled(false);
                this.tree.insert(node);
                insertButton.setEnabled(true);
                deleteButton.setEnabled(true);
            });
            buttonThread.start();
        });

        deleteButton.addActionListener(e -> {
            K Jtext = setText(deleteText.getText());
            deleteText.setText("");
            buttonThread = new Thread(() -> {
                insertButton.setEnabled(false);
                deleteButton.setEnabled(false);
                this.tree.delete(Jtext);
                insertButton.setEnabled(true);
                deleteButton.setEnabled(true);
            });
            buttonThread.start();
        });

        this.add(new Box.Filler(
                        new Dimension(5, 50),
                        new Dimension(5, 50),
                        new Dimension(50, 50)
                )
        );
        this.add(insertButton);
        this.add(insertText);
        this.add(new Box.Filler(
                new Dimension(5, 50),
                new Dimension(5, 50),
                new Dimension(800, 50))
        );
        this.add(deleteButton);
        this.add(deleteText);
        this.add(new Box.Filler(
                        new Dimension(5, 50),
                        new Dimension(5, 50),
                        new Dimension(50, 50)
                )
        );
        this.setVisible(true);
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
}
