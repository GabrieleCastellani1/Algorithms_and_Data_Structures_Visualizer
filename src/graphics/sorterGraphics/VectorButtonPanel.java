package graphics.sorterGraphics;

import logic.sorter.AbstractSorter;

import javax.swing.*;
import java.awt.*;

public class VectorButtonPanel <K extends Comparable<K>> extends JPanel{

    public VectorButtonPanel(AbstractSorter<K> ord){

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

        JButton doSortButton = new JButton();
        doSortButton.setText("Ordina");
        doSortButton.setMaximumSize(new Dimension(200, 50));

        insertButton.addActionListener(e -> {
        K Jtext = setText(insertText.getText());
            insertText.setText("");
            ord.add(Jtext);
            ord.getActionManager().addSquare(Jtext);
    });

        deleteButton.addActionListener(e -> {
        K Jtext = setText(deleteText.getText());
        ord.remove(Jtext);
        ord.getActionManager().removeSquare(Jtext);
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

        this.add(doSortButton);

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
