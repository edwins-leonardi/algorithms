package graphs.wordnet;

import java.util.HashMap;

public class Outcast {

    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {
        // given an array of WordNet nouns, return an outcast
        HashMap<String, Integer> totalDistance = new HashMap<String, Integer>();
        for (int i = 0; i < nouns.length; i++) {
            for (int j = i + 1; j < nouns.length; j++) {
                int distance = wordnet.distance(nouns[i], nouns[j]);
                totalDistance.put(nouns[i], addTotal(totalDistance.get(nouns[i]), distance));
                totalDistance.put(nouns[j], addTotal(totalDistance.get(nouns[j]), distance));
            }
        }
        return getFurthestResult(totalDistance);
    }

    private String getFurthestResult(HashMap<String, Integer> results) {
        NounEntry furthest = null;
        for (String noun : results.keySet()) {
            NounEntry entry = new NounEntry(noun, results.get(noun));
            if (furthest == null || furthest.length < entry.length)
                furthest = entry;
        }
        return furthest.noun;
    }

    private class NounEntry {
        private final String noun;
        private final int length;

        public NounEntry(String noun, int length) {
            this.noun = noun;
            this.length = length;
        }
    }

    private int addTotal(Integer total, int distance) {
        if (total == null)
            total = 0;
        return total + distance;
    }

    public static void main(String[] args) {
        // see test client below
        WordNet wn = new WordNet("graphs/synsets.txt", "graphs/hypernyms.txt");
        Outcast outcast = new Outcast(wn);
        // String[] nouns = new String[]{"horse","zebra","cat","bear","table"};
        // String[] nouns = new String[]{"water", "soda", "bed", "orange_juice",
        // "milk", "apple_juice", "tea", "coffee"};
        String[] nouns = new String[] { "apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry",
                "mango", "watermelon", "potato" };
        System.out.println(outcast.outcast(nouns));
    }
}