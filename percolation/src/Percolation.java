import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] matrix;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int virtualTop;
    private int virtualBottom;
    private int lastRowId;
    private int lastColumnId;
    private int firstRowId = 1;
    private int firstColumnId = 1;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than 0");
        matrix = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 2);
        lastRowId = n;
        lastColumnId = n;
        initializeVirtualSites(n);
    }

    public void open(int i, int j) {
        if (i > matrix.length || i <= 0 || j > matrix.length || j <= 0)
            throw new IndexOutOfBoundsException();
        if (!isOpen(i, j)) {
            matrix[i - 1][j - 1] = true;
            connectToOpenNeighbours(new Site(i, j));
            if (i == firstRowId) {
                uf.union(virtualTop, getIndexByRowAndColumn(i, j));
                uf2.union(virtualTop, getIndexByRowAndColumn(i, j));
            }
            if (i == lastRowId)
                uf.union(virtualBottom, getIndexByRowAndColumn(i, j));
        }
    }

    public boolean isOpen(int i, int j) {
        if (i > matrix.length || i <= 0 || j > matrix.length || j <= 0)
            throw new IndexOutOfBoundsException();
        return matrix[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        if (i > matrix.length || i <= 0 || j > matrix.length || j <= 0)
            throw new IndexOutOfBoundsException();
        return uf2.connected(virtualTop, getIndexByRowAndColumn(i, j));
    }

    public boolean percolates() {
        return uf.connected(virtualBottom, virtualTop);
    }

    private void initializeVirtualSites(int n) {
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    private int getIndexByRowAndColumn(int i, int j) {
        return (i - 1) * matrix.length + (j - 1);
    }

    private void connectToOpenNeighbours(Site itself) {
        connectToNeigbourAbove(itself);
        connectToNeigbourBelow(itself);
        connectToNeigbourOnTheLeft(itself);
        connectToNeigbourOnTheRight(itself);
    }

    private void connectToNeigbourAbove(Site itself) {
        if (itself.row != firstRowId)
            connectSites(itself, new Site(itself.row - 1, itself.col));
    }

    private void connectToNeigbourBelow(Site itself) {
        if (itself.row != lastRowId)
            connectSites(itself, new Site(itself.row + 1, itself.col));
    }

    private void connectToNeigbourOnTheLeft(Site itself) {
        if (itself.col != firstColumnId)
            connectSites(itself, new Site(itself.row, itself.col - 1));
    }

    private void connectToNeigbourOnTheRight(Site itself) {
        if (itself.col != lastColumnId)
            connectSites(itself, new Site(itself.row, itself.col + 1));
    }

    private void connectSites(Site itself, Site neighbour) {
        if (isOpen(neighbour.row, neighbour.col)) {
            uf.union(getIndexBySite(itself), getIndexBySite(neighbour));
            uf2.union(getIndexBySite(itself), getIndexBySite(neighbour));
        }
    }

    private int getIndexBySite(Site site) {
        return getIndexByRowAndColumn(site.row, site.col);
    }

    private class Site {
        private int row;
        private int col;

        Site(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
