package graphs.seamcarver;

import java.awt.Color;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;
    private int bottom, top, left, right;

    public SeamCarver(Picture picture) {
        // create a seam carver object based on the given picture
        checkNull(picture);
        this.picture = new Picture(picture);
    }

    public Picture picture() {
        // current picture
        return this.picture;
    }

    public int width() {
        // width of current picture
        return picture.width();
    }

    public int height() {
        // height of current picture
        return picture.height();
    }

    public double energy(int x, int y) {
        // energy of pixel at column x and row y
        checkImageBoundaries(x, y);
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
            return 1000;

        double horizontalEnergy = calculateHorizontalEnergy(x, y);
        double verticalEnergy = calculateVerticalEnergy(x, y);
        return Math.sqrt(horizontalEnergy + verticalEnergy);
    }

    public int[] findHorizontalSeam() {
        EdgeWeightedDigraph graph = buildDigraphFromPicture(SeamPosition.H);
        AcyclicSP shortestPath = new AcyclicSP(graph, left);
        int[] horizontalSeam = new int[width()];
        int i = 0;
        boolean notFirst = false;
        for (DirectedEdge edge : shortestPath.pathTo(right)) {
            if (notFirst) {
                horizontalSeam[i++] = fromIndexToRow(edge.from());
            }
            notFirst = true;
        }
        return horizontalSeam;
    }

    public int[] findVerticalSeam() {
        EdgeWeightedDigraph graph = buildDigraphFromPicture(SeamPosition.V);
        AcyclicSP shortestPath = new AcyclicSP(graph, top);
        int[] verticalSeam = new int[height()];
        int i = 0;
        boolean notFirst = false;
        for (DirectedEdge edge : shortestPath.pathTo(bottom)) {
            if (notFirst) {
                verticalSeam[i++] = fromIndexToCol(edge.from());
            }
            notFirst = true;
        }
        return verticalSeam;
    }

    public void removeHorizontalSeam(int[] seam) {
        // remove horizontal seam from current picture
        validateHorizontalSeam(seam);
        shrinkPictureHorizontally(seam);
    }

    public void removeVerticalSeam(int[] seam) {
        // remove vertical seam from current picture
        validateVerticalSeam(seam);
        shrinkPictureVertically(seam);
    }

    private void validateVerticalSeam(int[] seam) {
        checkNull(seam);
        if (seam.length != picture.height())
            throw new IllegalArgumentException("Seam has a wrong number of rows");
        if (picture.width() == 1)
            throw new IllegalArgumentException("picture is not large enough to seam");
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] > width() - 1 || distToPrevious(seam, i) > 1)
                throw new IllegalArgumentException("Invalid seam value " + seam[i]);
        }
    }

    private void validateHorizontalSeam(int[] seam) {
        checkNull(seam);
        if (seam.length != picture.width())
            throw new IllegalArgumentException("Seam has a wrong number of columns");
        if (picture.height() == 1)
            throw new IllegalArgumentException("picture is not tall enough to seam");
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] > height() - 1 || distToPrevious(seam, i) > 1)
                throw new IllegalArgumentException("Invalid seam value " + seam[i]);
        }
    }

    private int distToPrevious(int[] seam, int currentIndex) {
        if (currentIndex == seam.length - 1)
            return 0;
        else
            return Math.abs(seam[currentIndex] - seam[currentIndex + 1]);
    }

    private void shrinkPictureVertically(int seam[]) {
        Picture seamPicture = createNewPicture(SeamPosition.V);
        for (int row = 0; row < height(); row++) {
            int offset = 0;
            for (int col = 0; col < width(); col++) {
                if (col == seam[row])
                    offset = 1;
                else
                    setPictureColor(col, row, SeamPosition.V, offset, seamPicture);
            }
        }
        this.picture = seamPicture;
    }

    private void shrinkPictureHorizontally(int seam[]) {
        Picture seamPicture = createNewPicture(SeamPosition.H);
        for (int col = 0; col < width(); col++) {
            int offset = 0;
            for (int row = 0; row < height(); row++) {
                if (row == seam[col])
                    offset = 1;
                else
                    setPictureColor(col, row, SeamPosition.H, offset, seamPicture);
            }
        }
        this.picture = seamPicture;
    }

    private void setPictureColor(int col, int row, SeamPosition position, int offset, Picture seamPicture) {
        if (position.equals(SeamPosition.V))
            seamPicture.set(col - offset, row, picture.get(col, row));
        else
            seamPicture.set(col, row - offset, picture.get(col, row));
    }

    private Picture createNewPicture(SeamPosition position) {
        int x = width();
        int y = height();
        if (position.equals(SeamPosition.V))
            x = x - 1;
        else
            y = y - 1;
        return new Picture(x, y);
    }

    private EdgeWeightedDigraph buildDigraphFromPicture(SeamPosition position) {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(width() * height() + 4);
        top = width() * height();
        bottom = width() * height() + 1;
        left = width() * height() + 2;
        right = width() * height() + 3;
        for (int x = 0; x < width(); x++)
            for (int y = 0; y < height(); y++) {
                int index = getPixelIndex(x, y);
                if (x == 0)
                    graph.addEdge(new DirectedEdge(left, index, 0));
                if (x == width() - 1)
                    graph.addEdge(new DirectedEdge(index, right, 0));
                if (y == 0)
                    graph.addEdge(new DirectedEdge(top, index, 0));
                if (y == height() - 1)
                    graph.addEdge(new DirectedEdge(index, bottom, 0));
                if (position.equals(SeamPosition.V))
                    connectToNeighboursAtBottom(x, y, graph);
                else
                    connectToNeighboursAtLeft(x, y, graph);
            }
        return graph;
    }

    private void connectToNeighboursAtBottom(int x, int y, EdgeWeightedDigraph graph) {
        int row = y + 1;
        if (row < height()) {
            for (int i = -1; i <= 1; i++) {
                int col = x + i;
                if (col >= 0 && col < width()) {
                    graph.addEdge(new DirectedEdge(getPixelIndex(x, y), getPixelIndex(col, row), energy(col, row)));
                }
            }
        }
    }

    private void connectToNeighboursAtLeft(int x, int y, EdgeWeightedDigraph graph) {
        int col = x + 1;
        if (col < width()) {
            for (int i = -1; i <= 1; i++) {
                int row = y + i;
                if (row >= 0 && row < height())
                    graph.addEdge(new DirectedEdge(getPixelIndex(x, y), getPixelIndex(col, row), energy(col, row)));
            }
        }
    }

    private int getPixelIndex(int x, int y) {
        return y * width() + x;
    }

    private int fromIndexToCol(int index) {
        return index % width();
    }

    private int fromIndexToRow(int index) {
        return index / width();
    }

    private double calculateHorizontalEnergy(int x, int y) {
        Color c1 = picture.get(x - 1, y);
        Color c2 = picture.get(x + 1, y);
        return calculateEnergy(c1, c2);
    }

    private double calculateVerticalEnergy(int x, int y) {
        Color c1 = picture.get(x, y - 1);
        Color c2 = picture.get(x, y + 1);
        return calculateEnergy(c1, c2);
    }

    private double calculateEnergy(Color c1, Color c2) {
        return calculateSquaredDiffForRGB(c1.getRed(), c2.getRed())
                + calculateSquaredDiffForRGB(c1.getGreen(), c2.getGreen())
                + calculateSquaredDiffForRGB(c1.getBlue(), c2.getBlue());
    }

    private double calculateSquaredDiffForRGB(int c1, int c2) {
        return Math.abs(c1 - c2) * Math.abs(c1 - c2);
    }

    private void checkNull(Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("Argument cannot be null");
    }

    private void checkImageBoundaries(int x, int y) {
        if (x < 0 || x >= picture.width() || y < 0 || y >= picture.height())
            throw new IllegalArgumentException("Coordinates not in the range " + x + " - " + y);
    }

    private enum SeamPosition {
        V, H;
    }

    public static void main(String[] args) {
        SeamCarver sc = new SeamCarver(new Picture("/seam-carving/6x5.png"));
        System.out.println("Height:" + sc.height());
        System.out.println("Width:" + sc.width());
        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                System.out.printf("%10.2f", sc.energy(col, row));
            System.out.println();
        }
        // int vpath[] = sc.findVerticalSeam();
        // for (int path : vpath) {
        // System.out.println(path);
        // }
        int hpath[] = sc.findHorizontalSeam();
        for (int path : sc.findHorizontalSeam()) {
            System.out.println(path);
        }
        System.out.println("WIDTH = " + sc.width());
        System.out.println("HEIGHT = " + sc.height());
        sc.removeHorizontalSeam(hpath);
        System.out.println("WIDTH = " + sc.width());
        System.out.println("HEIGHT = " + sc.height());
    }
}
