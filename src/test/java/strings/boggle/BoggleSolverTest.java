package strings.boggle;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import edu.princeton.cs.algs4.In;
import string.boggle.BoggleBoard;
import string.boggle.BoggleSolver;

public class BoggleSolverTest {

    @Test
    public void testAlgs4DictionaryOn4By4Board() {
        In in = new In("/boggle/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        int score = 0;
        BoggleBoard board = new BoggleBoard("/boggle/board4x4.txt");
        for (String s : solver.getAllValidWords(board)) {
            // System.out.println(s);
            score += solver.scoreOf(s);
        }
        Assert.assertThat(score, CoreMatchers.equalTo(33));
    }

    @Test
    public void testAlgs4DictionaryOnBoardQ() {
        In in = new In("/boggle/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        int score = 0;
        BoggleBoard board = new BoggleBoard("/boggle/board-q.txt");
        for (String s : solver.getAllValidWords(board)) {
            // System.out.println(s);
            score += solver.scoreOf(s);
        }
        Assert.assertThat(score, CoreMatchers.equalTo(84));
    }

    @Test
    public void testYAWLDictionaryOnBoard26539() {
        In in = new In("/boggle/dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        int score = 0;
        BoggleBoard board = new BoggleBoard("/boggle/board-points26539.txt");
        for (String s : solver.getAllValidWords(board)) {
            // System.out.println(s);
            score += solver.scoreOf(s);
        }
        Assert.assertThat(score, CoreMatchers.equalTo(26539));
    }

    @Test
    public void testYAWLDictionaryOnBoardQwerty() {
        In in = new In("/boggle/dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        int score = 0;
        BoggleBoard board = new BoggleBoard("/boggle/board-quinquevalencies.txt");
        for (String s : solver.getAllValidWords(board)) {
            System.out.println(s);
            score++;
            // score += solver.scoreOf(s);
        }
        Assert.assertThat(score, CoreMatchers.equalTo(130));
    }

}
