package unionfind;

public abstract class UnionFind {
	
	protected int id[];
	public UnionFind(){
	}
	public UnionFind(int N){
		id = new int[N];
		for(int i=0; i<N; i++){
			id[i] = i;
		}		
	}
	
	public abstract boolean isConnected(int p, int q);
	public abstract void addConnection(int p, int q);
	
	public void printOutput(){
		for(int i : id){
			System.out.print(i +"\t");
		}
		System.out.println();
	}
}
