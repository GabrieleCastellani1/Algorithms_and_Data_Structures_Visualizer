package graphics.menu;

import enums.TreeType;
import logic.binaryTree.AbstractTree;
import logic.binaryTree.TreeFactory;
import graphics.binaryTreeGraphics.TreeFrame;
import util.Util;

import javax.swing.*;
import java.awt.*;

public class TreeMenu<K extends Comparable<K>> extends JFrame{
    public TreeMenu(){
        super();
        initializeMenu();

        JButton bstButton = Util.createButton(300,100,"BST");

        bstButton.addActionListener(e -> createTree(TreeType.BST));

        JButton rbtButton = Util.createButton(300,150, "RBT");

        rbtButton.addActionListener(e -> createTree(TreeType.RBT));

        this.add(bstButton);
        this.add(rbtButton);
    }

    private void initializeMenu(){
        this.setSize(Util.FRAMEWIDTH, Util.FRAMEHEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
    }

    private void createTree(TreeType treeType){
        AbstractTree<?> tree = chooseTree(treeType);
        JFrame F = new TreeFrame(tree);
        Util.setLocationToTopRight(F);
    }

    private AbstractTree<?> chooseTree(TreeType treeType){
        TreeFactory factory = new TreeFactory();
        switch (treeType) {
            case BST : {
                return factory.createBST();
            }
            case RBT : {
                return factory.createRBT();
            }
            default : {
                return null;
            }
        }
    }
}
