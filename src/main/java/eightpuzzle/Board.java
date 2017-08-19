package eightpuzzle;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;

public class Board {
    private final int BLANK_SQUARE = 0;
    private int[][] blocks;
    private Pos blankSquarePosition;

    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++)
            for (int col = 0; col < blocks.length; col++) {
                this.blocks[row][col] = blocks[row][col];
                if (blocks[row][col] == BLANK_SQUARE)
                    blankSquarePosition = new Pos(row, col);
            }
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int count = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (!isBlankSquare(row, col)
                        && blocks[row][col] != getIndex(row, col) + 1)
                    count++;
            }
        }
        return count++;
    }

    public int manhattan() {
        int totalDistance = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (!isBlankSquare(row, col)
                        && blocks[row][col] != getIndex(row, col) + 1)
                    totalDistance += distanceToGoalPosition(row, col,
                            blocks[row][col]);
            }
        }
        return totalDistance;
    }

    public boolean isGoal() {
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (!isValueInTheRightPosition(row, col))
                    return false;
            }

        }
        return true;
    }

    @Override
    public boolean equals(Object y) {
        if (y == null)
            return false;
        if (y instanceof String)
            return y == this || toString().equals(y);
        Board other = (Board) y;
        return y == this || toString().equals(other.toString());
    }

    public Iterable<Board> neighbors() {

        List<Board> list = new ArrayList<Board>();
        if (blankSquarePosition.col != 0)
            list.add(swipe(blankSquarePosition.row, blankSquarePosition.col - 1));
        if (blankSquarePosition.col != blocks.length - 1)
            list.add(swipe(blankSquarePosition.row, blankSquarePosition.col + 1));
        if (blankSquarePosition.row != 0)
            list.add(swipe(blankSquarePosition.row - 1, blankSquarePosition.col));
        if (blankSquarePosition.row != blocks.length - 1)
            list.add(swipe(blankSquarePosition.row + 1, blankSquarePosition.col));

        // all neighboring boards
        return list;
    }

    public Board twin() {
        int[][] copy = cloneBlocks();

        int index = StdRandom.uniform(blocks.length * blocks.length);
        int col = getColByIndex(index);
        int row = getRowByIndex(index);
        if (copy[row][col] == BLANK_SQUARE) {
            index = (index + 1) % (blocks.length * blocks.length);
            col = getColByIndex(index);
            row = getRowByIndex(index);
        }
        int anotherIndex = (index + 1) % (blocks.length * blocks.length);
        int anotherCol = getColByIndex(anotherIndex);
        int anotherRow = getRowByIndex(anotherIndex);
        if (copy[anotherRow][anotherCol] == BLANK_SQUARE) {
            anotherIndex = (anotherIndex + 1) % (blocks.length * blocks.length);
            anotherCol = getColByIndex(anotherIndex);
            anotherRow = getRowByIndex(anotherIndex);
        }
        int aux = copy[row][col];
        copy[row][col] = copy[anotherRow][anotherCol];
        copy[anotherRow][anotherCol] = aux;
        return new Board(copy);

        // for (int row = 0; row < blocks.length; row++) {
        // for (int col = 0; col < blocks.length; col++) {
        // if (copy[row][col] != BLANK_SQUARE
        // && !isValueInTheRightPosition(row, col)) {
        // int aux = copy[row][col];
        // int otherCol = getColByIndex(aux);
        // int otherRow = getRowByIndex(aux);
        // if (copy[otherRow][otherCol] == BLANK_SQUARE)
        // continue;
        // copy[row][col] = copy[otherRow][otherCol];
        // copy[otherRow][otherCol] = aux;
        // return new Board(copy);
        // }

        // }

        // }

        // return new Board(copy);

    }

    private Board swipe(int row, int col) {
        int[][] copy = cloneBlocks();
        int aux = copy[row][col];
        copy[row][col] = BLANK_SQUARE;
        copy[blankSquarePosition.row][blankSquarePosition.col] = aux;
        return new Board(copy);
    }

    private int[][] cloneBlocks() {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++)
            for (int col = 0; col < blocks.length; col++)
                copy[row][col] = blocks[row][col];
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(dimension() + "\n");
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                strBuilder.append(" " + blocks[row][col] + "");
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    private boolean isValueInTheRightPosition(int row, int col) {
        return (!isLastSlot(row, col) && blocks[row][col] == getIndex(row, col) + 1)
                || (isLastSlot(row, col) && blocks[row][col] == BLANK_SQUARE);
    }

    private int getIndex(int row, int col) {
        return row * blocks.length + col;
    }

    private int getColByIndex(int index) {
        return (index == BLANK_SQUARE) ? blocks.length - 1 : (index - 1)
                % blocks.length;
    }

    private int getRowByIndex(int index) {
        return (index == BLANK_SQUARE) ? blocks.length - 1 : (index - 1)
                / blocks.length;
    }

    private boolean isBlankSquare(int row, int col) {
        return blocks[row][col] == BLANK_SQUARE;
    }

    private boolean isLastSlot(int row, int col) {
        return row == blocks.length - 1 && col == blocks.length - 1;
    }

    private int distanceToGoalPosition(int row, int col, int currentValue) {
        currentValue--;
        return Math.abs((currentValue / blocks.length) - row)
                + Math.abs((currentValue % blocks.length) - col);
    }

    private class Pos {
        int row;
        int col;

        private Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
