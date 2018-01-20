import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class BoggleSolver {

    private final TST<Integer> trie = new TST<>();

    // (You can assume each word in the dictionary contains only the uppercase
    // letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        for (String s : dictionary)
            trie.put(s, getScoreOfWord(s));
    }

    // Returns the set of all valid words in the given Boggle board, as an
    // Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Digraph digraph = new Digraph(26);
        Position[] allpositions = new Position[board.rows() * board.cols()];
        initBoardDigraph(digraph, board, allpositions);

        Set<String> matches = new HashSet<>();
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                boolean[] marked = new boolean[allpositions.length];
                dfs(new StringBuilder(allpositions.length),
                        allpositions[getIndexFromPosition(new Position(row, col), board)], marked, board, matches,
                        digraph, allpositions);
            }
        }

        return matches;
    }

    private void initBoardDigraph(Digraph digraph, BoggleBoard board, Position[] allpositions) {
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                Position myself = new Position(row, col);
                getAllpositions(myself, board.rows(), board.cols(), digraph, board);
                allpositions[getIndexFromPosition(myself, board)] = myself;
            }
        }
    }

    private void dfs(StringBuilder sb, Position position, boolean[] marked, BoggleBoard board, Set<String> matches,
            Digraph digraph, Position[] allpositions) {

        char c = board.getLetter(position.row, position.col);
        String prefix = "" + c;
        if ("Q".equals(prefix))
            prefix += "U";
        Iterable<String> it = trie.keysWithPrefix(sb.toString() + prefix);
        if (!it.iterator().hasNext()) {
            return;
        } else {
            int idx = getIndexFromPosition(position, board);
            marked[idx] = true;
            sb.append(prefix);
            String key = sb.toString();
            Integer score = trie.get(key);
            if (key.length() > 2 && score != null) {
                matches.add(key);
            }
            for (int w : digraph.adj(idx)) {
                if (!marked[w]) {
                    dfs(sb, allpositions[w], marked, board, matches, digraph, allpositions);

                }
            }
            marked[idx] = false;
            sb.delete(key.length() - 1, key.length());
        }
    }

    private int getIndexFromPosition(Position position, BoggleBoard board) {
        return position.row * board.cols() + position.col;
    }

    private int getIndexFromPosition(int row, int col, BoggleBoard board) {
        return row * board.cols() + col;
    }

    private void getAllpositions(Position myself, int rowLimit, int colLimit, Digraph digraph, BoggleBoard board) {
        addPositionAtRight(myself, colLimit, digraph, board);
        addPositionAtBottom(myself, rowLimit, digraph, board);
        addPositionAtLeft(myself, digraph, board);
        addPositionAtTop(myself, digraph, board);
        addPositionAtNE(myself, digraph, board);
        addPositionAtNW(myself, colLimit, digraph, board);
        addPositionAtSE(myself, rowLimit, digraph, board);
        addPositionAtSW(myself, rowLimit, colLimit, digraph, board);
    }

    private void addPositionAtRight(Position myself, int colLimit, Digraph digraph, BoggleBoard board) {
        if (myself.col + 1 >= colLimit)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board), getIndexFromPosition(myself.row, myself.col + 1, board));
    }

    private void addPositionAtLeft(Position myself, Digraph digraph, BoggleBoard board) {
        if (myself.col - 1 < 0)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board), getIndexFromPosition(myself.row, myself.col - 1, board));
    }

    private void addPositionAtTop(Position myself, Digraph digraph, BoggleBoard board) {
        if (myself.row - 1 < 0)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board), getIndexFromPosition(myself.row - 1, myself.col, board));
    }

    private void addPositionAtBottom(Position myself, int rowLimit, Digraph digraph, BoggleBoard board) {
        if (myself.row + 1 >= rowLimit)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board), getIndexFromPosition(myself.row + 1, myself.col, board));
    }

    private void addPositionAtSW(Position myself, int rowLimit, int colLimit, Digraph digraph, BoggleBoard board) {
        if (myself.row + 1 >= rowLimit || myself.col + 1 >= colLimit)
            return;
        try {
            digraph.addEdge(getIndexFromPosition(myself, board),
                    getIndexFromPosition(myself.row + 1, myself.col + 1, board));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addPositionAtSE(Position myself, int rowLimit, Digraph digraph, BoggleBoard board) {
        if (myself.row + 1 >= rowLimit || myself.col - 1 < 0)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board),
                getIndexFromPosition(myself.row + 1, myself.col - 1, board));
    }

    private void addPositionAtNW(Position myself, int colLimit, Digraph digraph, BoggleBoard board) {
        if (myself.row - 1 < 0 || myself.col + 1 >= colLimit)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board),
                getIndexFromPosition(myself.row - 1, myself.col + 1, board));
    }

    private void addPositionAtNE(Position myself, Digraph digraph, BoggleBoard board) {
        if (myself.row - 1 < 0 || myself.col - 1 < 0)
            return;
        digraph.addEdge(getIndexFromPosition(myself, board),
                getIndexFromPosition(myself.row - 1, myself.col - 1, board));
    }

    // Returns the score of the given word if it is in the dictionary, zero
    // otherwise.
    // (You can assume the word contains only the uppercase letters A through
    // Z.)
    public int scoreOf(String word) {
        return Optional.ofNullable(trie.get(word)).orElse(0);
    }

    private int getScoreOfWord(String s) {
        if (s == null || s.length() < 3)
            return 0;
        if (s.length() < 5)
            return 1;
        if (s.length() < 6)
            return 2;
        if (s.length() < 7)
            return 3;
        if (s.length() < 8)
            return 5;
        else
            return 11;
    }

    private static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        In in = new In("/boggle/dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        // BoggleBoard board = new BoggleBoard("/boggle/board-q.txt");

        System.out.println("Begin");
        int times = 0;
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) / 1000 < 5) {
            int score = 0;
            times++;
            BoggleBoard board = new BoggleBoard();
            /*
             * for (String word : solver.getAllValidWords(board)) {
             * StdOut.println(word); score += solver.scoreOf(word); }
             */
            for (String s : solver.getAllValidWords(board)) {
                // System.out.println(s);
                score += solver.scoreOf(s);
            }
            StdOut.println("Score = " + score);
        }
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time(secs): " + (end - start) / 1000);
        System.out.println("Times ran: " + times);
    }
}
