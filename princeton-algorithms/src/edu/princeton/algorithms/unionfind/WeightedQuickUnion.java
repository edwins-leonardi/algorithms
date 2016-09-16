package edu.princeton.algorithms.unionfind;

public class WeightedQuickUnion extends UnionFind {

	int size[];

	public WeightedQuickUnion(int N) {
		size = new int[N];
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	@Override
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}

	@Override
	public void addConnection(int p, int q) {
		int rootP = root(p);
		int rootQ = root(q);
		if (rootP != rootQ) {
			if (size[rootP] < size[rootQ]) {
				id[rootP] = rootQ;
				size[q] += size[p];
			} else {
				id[rootQ] = rootP;
				size[p] += size[q];
			}
		}
	}

	public int root(int n) {
		while (id[n] != n) {
			n = id[n];
		}
		return n;
	}
}
