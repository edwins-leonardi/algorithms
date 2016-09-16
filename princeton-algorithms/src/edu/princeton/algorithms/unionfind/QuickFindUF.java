package edu.princeton.algorithms.unionfind;

public class QuickFindUF extends UnionFind {
	
	public QuickFindUF(int N){
		super(N);
	}
	
	public boolean isConnected(int p, int q){
		if (id[p] == id[q]){
			return true;
		}
		return false;
	}
	
	public void addConnection(int p, int q){
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i<id.length; i++){
			if (id[i] == pid){
				id[i] = qid;
			}
		}
	}
}
