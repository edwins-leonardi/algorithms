package graphs.wordnet;

import java.util.HashMap;
import java.util.Map.Entry;

public class Outcast {
	
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet) {
		// constructor takes a WordNet object
		this.wordnet = wordnet;
	}

	public String outcast(String[] nouns) {
		// given an array of WordNet nouns, return an outcast
		HashMap<String, Integer> totalDistance = new HashMap<String, Integer>();
		for(int i =0; i< nouns.length; i++){
			for(int j =i+1; j< nouns.length; j++){
				int distance = wordnet.distance(nouns[i], nouns[j]);
				totalDistance.put(nouns[i], addTotal(totalDistance.get(nouns[i]), distance));
				totalDistance.put(nouns[j], addTotal(totalDistance.get(nouns[j]), distance));
			}
		}
		return getFurthestResult(totalDistance);
	}
	
	private String getFurthestResult(HashMap<String, Integer> results){
		Entry<String, Integer> furthest = null;
		for(Entry<String, Integer> entry: results.entrySet()){
			if(furthest == null || furthest.getValue() < entry.getValue())
				furthest = entry;
		}
		return furthest.getKey();
	}
	
	private Integer addTotal(Integer total, int distance){
		if(total == null)
			total = 0;
		return total + distance;
	}

	public static void main(String[] args) {
		// see test client below
		WordNet wn = new WordNet("graphs/synsets.txt", "graphs/hypernyms.txt");
		Outcast outcast = new Outcast(wn);
        //String[] nouns = new String[]{"horse","zebra","cat","bear","table"};
		//String[] nouns = new String[]{"water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee"};
		String[] nouns = new String[]{"apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry", "mango", "watermelon", "potato"};
        System.out.println(outcast.outcast(nouns));
	}
}