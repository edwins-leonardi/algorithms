package binarygap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Booking1 {
	
	
	private static HashMap<Integer, Hotel> hotelsRating = new HashMap<Integer, Hotel>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine().trim());

		for(int i = 0; i<n; i++){
			String s = in.nextLine();
			String[] tokens = s.split(" ");
			Integer id = Integer.valueOf(tokens[0]);
			Integer rating = new Integer(tokens[1]);
			if(hotelsRating.containsKey(id))
				rating = (rating + hotelsRating.get(id).rating) / 2;
			Hotel hotel = new Hotel(id, rating);
			hotelsRating.put(id, hotel);
		}
		best_hotels();
		
	}

	static void best_hotels() {
		Hotel[] hotels =  hotelsRating.values().toArray(new Hotel[hotelsRating.size()]);
		Comparator<Hotel> comparator = new Comparator<Hotel>() {
		    public int compare(Hotel left, Hotel right) {
		        if(left.rating > right.rating)
		        	return -1;
		        else if (left.rating < right.rating)
		        	return 1;
		        else 
		        	return left.id - right.id;
		    }
		};

		Arrays.sort(hotels, comparator);
		
		for(Hotel h : hotels){
			System.out.println(h.id);
		}
	}
	
	static class Hotel{
		Integer id;
		Integer rating;
		public Hotel(Integer id, Integer rating){
			this.id = id;
			this.rating = rating;
		}
		
		public String toString(){
			return id + " " + rating;
		}
	}

	static int[] oddNumbers(int l, int r) {

		int numberOfOdds = (r - l) / 2;
		if (r % 2 == 1 || l % 2 == 1)
			numberOfOdds++;

		int[] result = new int[numberOfOdds];
		int i = 0;
		for (int o = l; o <= r; o = o + 2) {
			if (o % 2 == 0)
				o++;
			result[i] = o;
			i++;
		}
		return result;
	}

	/**
	 * public static void main(String[] args) throws IOException{ Scanner in =
	 * new Scanner(System.in); final String fileName =
	 * System.getenv("OUTPUT_PATH"); BufferedWriter bw = new BufferedWriter(new
	 * FileWriter(fileName)); int[] res; int _l; _l =
	 * Integer.parseInt(in.nextLine().trim());
	 * 
	 * int _r; _r = Integer.parseInt(in.nextLine().trim());
	 * 
	 * res = oddNumbers(_l, _r); for(int res_i=0; res_i < res.length; res_i++) {
	 * bw.write(String.valueOf(res[res_i])); bw.newLine(); }
	 * 
	 * bw.close(); }
	 */
}
