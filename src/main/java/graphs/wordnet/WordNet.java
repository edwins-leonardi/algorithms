package graphs.wordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

public class WordNet {

    private final Digraph digraph;
    private static final int DICTIONARY_SIZE = 82191;
    private final HashMap<String, ArrayList<Integer>> nouns = new HashMap<String, ArrayList<Integer>>();
    private final ArrayList<String> words = new ArrayList<String>(DICTIONARY_SIZE);

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        validateNullArgs(synsets, hypernyms);
        int numberOfNouns = initializeNounsAndGetNumberOfNouns(synsets);
        digraph = new Digraph(numberOfNouns);
        addEdgesToDiagraph(hypernyms);
        validateDAG(digraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return nouns.keySet().iterator();
            }
        };
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        validateNullArgs(word);
        return nouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validateNullArgs(nounA, nounB);
        validateNouns(nounA, nounB);
        SAP sap = new SAP(digraph);
        return sap.length(nouns.get(nounA), nouns.get(nounB));
        // return sap.length(digraph.adj(nouns.get(nounA)) ,
        // digraph.adj(nouns.get(nounB)));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of
    // nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validateNullArgs(nounA, nounB);
        validateNouns(nounA, nounB);
        SAP sap = new SAP(digraph);
        int ancestor = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
        // int ancestor = sap.ancestor(digraph.adj(nouns.get(nounA)) ,
        // digraph.adj(nouns.get(nounB)));
        return words.get(ancestor);
    }

    private void validateNullArgs(String... args) {
        for (String arg : args)
            if (arg == null)
                throw new IllegalArgumentException();
    }

    private void validateNouns(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();
    }

    private void addEdgesToDiagraph(String fileName) {
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String row = in.readLine();
            String[] columns = row.split(",");
            int synset = Integer.parseInt(columns[0]);
            for (int i = 1; i < columns.length; i++) {
                int hypernym = Integer.parseInt(columns[i]);
                digraph.addEdge(synset, hypernym);
            }
        }
    }

    private int initializeNounsAndGetNumberOfNouns(String fileName) {
        In in = new In(fileName);
        int numberOfRows = 0;
        while (in.hasNextLine()) {
            String row = in.readLine();
            String[] values = row.split(",");
            String noun = values[1];
            String[] syns = values[1].split(" ");
            for (String n : syns)
                addNoun(n, Integer.parseInt(values[0]));
            words.add(noun);
            numberOfRows++;
        }
        return numberOfRows;
    }

    private void addNoun(String noun, int id) {
        if (!nouns.containsKey(noun))
            nouns.put(noun, new ArrayList<Integer>());
        nouns.get(noun).add(id);
    }

    private void validateDAG(Digraph digraph) {
        DirectedCycle dicycle = new DirectedCycle(digraph);
        if (dicycle.hasCycle())
            throw new IllegalArgumentException();
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wn = new WordNet("graphs/synsets3.txt", "graphs/hypernyms3InvalidTwoRoots.txt");
        int d = wn.distance("vascular_hemophilia", "nonaccomplishment");
        String anc = wn.sap("vascular_hemophilia", "nonaccomplishment");

        System.out.println(d);
        System.out.println(anc);
    }
}