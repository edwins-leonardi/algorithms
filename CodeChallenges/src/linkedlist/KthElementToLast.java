package linkedlist;

import edu.princeton.cs.algs4.LinkedQueue;

public class KthElementToLast {

	public static void main(String[] args) {
		LinkedQueue<Character> chars = new LinkedQueue<Character>();
		chars.enqueue('a');
		chars.enqueue('b');
		chars.enqueue('c');
		chars.enqueue('d');
		chars.enqueue('e');
		chars.enqueue('f');
		chars.enqueue('g');
		chars.enqueue('h');

		System.out.println(chars.getKthToLastElement(7));
	}

}
