package unionfind;

public class WeightedQuickUnionPathCompression extends UnionFind {
	
	int size[];
	public WeightedQuickUnionPathCompression(int N){
		size = new int[N];
		id = new int[N];
		for(int i=0; i<N; i++){
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
		//System.out.println(""+p+"-"+q);
		if(rootP != rootQ){
			if(size[rootP] < size[rootQ]){
				id[rootP] = rootQ;
				size[q] += size[p];
			}else{
				id[rootQ] = rootP;
				size[p] += size[q];
			}
		}
		//System.out.println(size[p]+"-"+size[q]);

	}
	public int root(int n){
		while(id[n] != n){
			id[n] = id[id[n]];
			n = id[n];
		}
		return n;
	}
}
