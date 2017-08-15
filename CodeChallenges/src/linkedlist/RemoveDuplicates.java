package linkedlist;

import edu.princeton.cs.algs4.LinkedQueue;

public class RemoveDuplicates {

	public static void main(String[] args) {
		LinkedQueue<String> names = new LinkedQueue<String>();
		names.enqueue("ana");
		names.enqueue("maria");
		names.enqueue("joao");
		names.enqueue("rita");
		names.enqueue("jose");
		names.enqueue("joao");
		names.enqueue("ana");
		for (String s : names) {
			System.out.println(s);
		}
		names.deleteDuplicatesFancyWay();
		System.out.println("--------------");
		for (String s : names) {
			System.out.println(s);
		}
	}

}
