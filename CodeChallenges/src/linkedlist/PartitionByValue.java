package linkedlist;

import edu.princeton.cs.algs4.LinkedQueue;

public class PartitionByValue {
	public static void main(String[] args) {
		LinkedQueue<Integer> list = new LinkedQueue<Integer>();
		list.enqueue(3);
		list.enqueue(5);
		list.enqueue(8);
		list.enqueue(5);
		list.enqueue(10);
		list.enqueue(2);
		list.enqueue(1);

		for (Integer i : list)
			System.out.println(i);

		System.out.println("----------------------");

		list.partitionByValue(5);

		for (Integer i : list)
			System.out.println(i);

	}
}
