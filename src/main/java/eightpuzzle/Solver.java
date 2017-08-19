package eightpuzzle;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private boolean solvable = false;
    private Node solution;
    private Stack<Board> solutionPath;

    public Solver(Board initial) {

        if (initial == null)
            throw new NullPointerException();

        if (initial.isGoal()) {
            solvable = true;
        }

        MinPQ<Node> pq_solvable = new MinPQ<Node>();
        MinPQ<Node> pq_unsolvable = new MinPQ<Node>();

        pq_solvable.insert(new Node(initial, 0, null));
        pq_unsolvable.insert(new Node(initial.twin(), 0, null));
        while (true) {
            Node nodeSolvable = searchNode(pq_solvable);
            if (nodeSolvable != null) {
                solvable = true;
                solution = nodeSolvable;
                addSolutionPath(nodeSolvable);
                break;
            }
            Node nodeUnSolvable = searchNode(pq_unsolvable);
            if (nodeUnSolvable != null) {
                break;
            }
        }
    }

    public boolean isSolvable() {
        // is the initial board solvable?
        return solvable;
    }

    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return solution == null ? -1 : solution.moves;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        return solutionPath;
    }

    public static void main(String[] args) {

        // int t[][] = new int[][] { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } };
        // int t[][] = new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
        // int t[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } };
        int t[][] = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }, { 13, 15, 14, 0 } };
        Board test = new Board(t);

        Solver s = new Solver(test);
        System.out.println("Solvable: " + s.isSolvable());
        System.out.println("Moves: " + s.moves());
        for (Board b : s.solution()) {
            System.out.println(b);
        }

    }

    private Node searchNode(MinPQ<Node> pq) {
        Node node = pq.delMin();
        if (node.board.isGoal()) {
            return node;
        }
        int moves = node.moves + 1;
        for (Board neighbor : node.board.neighbors()) {
            if (node.previous == null || !neighbor.equals(node.previous.board)) {
                pq.insert(new Node(neighbor, moves, node));
            }
        }
        return null;
    }

    private void addSolutionPath(Node node) {
        solutionPath = new Stack<Board>();
        solutionPath.push(node.board);
        while (node.previous != null) {
            node = node.previous;
            solutionPath.push(node.board);
        }
    }

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node previous;

        private Node(Board board, int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        public int compareTo(Node n2) {
            if (this.board.manhattan() + this.moves > n2.board.manhattan()
                    + n2.moves)
                return 1;
            return (this.board.manhattan() + this.moves < n2.board.manhattan()
                    + n2.moves) ? -1 : 0;
        }
    }
}
