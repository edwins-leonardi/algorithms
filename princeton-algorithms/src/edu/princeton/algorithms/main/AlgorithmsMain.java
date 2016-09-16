package edu.princeton.algorithms.main;

import edu.princeton.algorithms.unionfind.UnionFind;
import edu.princeton.algorithms.unionfind.WeightedQuickUnion;

public class AlgorithmsMain {

	public static void main(String[] args) {
		System.out.println("0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
		System.out.println("------------------------------------------------------------------------");
		UnionFind uf = new WeightedQuickUnion(10);
		uf.addConnection(3, 4);
		uf.printOutput();
		uf.addConnection(4, 9);
		uf.printOutput();
		uf.addConnection(8, 0);
		uf.printOutput();
		uf.addConnection(2, 3);
		uf.printOutput();
		uf.addConnection(5, 6);
		uf.printOutput();
		uf.addConnection(5, 9);
		uf.printOutput();
		uf.addConnection(7, 3);
		uf.printOutput();
		uf.addConnection(4, 8);
		uf.printOutput();
		uf.addConnection(6, 1);
		uf.printOutput();
	}

}
