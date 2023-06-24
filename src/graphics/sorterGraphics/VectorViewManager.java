package graphics.sorterGraphics;

import graphics.sorterGraphics.figures.Square;

import java.util.Vector;

public class VectorViewManager<K extends Comparable<K>> {
    Vector<Square<K>> squares = new Vector<>();
    private final int xStart;
    private int currentX;
    private final int currentY;
    private final int sideLength;

    public VectorViewManager(int xStart, int yStart, int sideLength) {
        this.xStart = xStart;
        this.currentY = yStart;
        this.sideLength = sideLength;
        currentX = xStart;
    }

    public void addSquare(K key) {
        this.squares.add(new Square<>(key, currentX, currentY, sideLength));
        currentX += sideLength;
    }

    public void setSquare(int index, K key) {
        Square<K> currentSquare = squares.get(index);
        this.squares.set(index, new Square<>(key, currentSquare.x, currentSquare.y, currentSquare.sideLength));
    }

    public void removeSquare(K key) {
        squares.remove(squares.stream().filter(s -> s.getKey().equals(key)).findAny().get());
        resetSquarePositions();
    }

    public Square<K> get(int i) {
        return this.squares.elementAt(i);
    }

    private void resetSquarePositions() {
        currentX = xStart;
        squares.forEach(s -> {
            s.x = currentX;
            currentX += sideLength;
        });
    }

    public Vector<Square<K>> getAll() {
        return squares;
    }

    public void swap(int i, int j) {
        Square<K> key = squares.elementAt(i);
        squares.set(i, squares.elementAt(j));
        squares.set(j, key);
    }
}
