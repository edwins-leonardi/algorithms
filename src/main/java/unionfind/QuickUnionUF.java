package unionfind;

public class QuickUnionUF extends UnionFind{
	public QuickUnionUF(int N){
		super(N);
	}
	public int root(int n){
		while(id[n] != n){
			n = id[n];
		}
		return n;
	}
	@Override
	public boolean isConnected(int p, int q) {
		return root(p) == root(q);
	}
	@Override
	public void addConnection(int p, int q) {
		if(!isConnected(p, q)){
			id[root(p)] = root(q);
		}
	}
}