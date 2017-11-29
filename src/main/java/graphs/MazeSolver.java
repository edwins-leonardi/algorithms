package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeSolver {
	
	private Graph graph;
	private boolean marked[];
	private Integer edgeTo[];
	
	public MazeSolver(int v){
		this.graph = new Graph(v);
		this.marked = new boolean[v];
		this.edgeTo = new Integer[v];
	}
	
	public void findConnectedVertices(int v, Integer origin){
		if(isAlreadyMarked(v))
			return;
		mark(v);
		addEdgeTo(origin, v);
		for(int adj: graph.adj(v)){
			findConnectedVertices(adj, v);
		}
	}
	
	private void printResults(){
		for(int i = 0; i<edgeTo.length; i++)
			if(edgeTo[i] != null)
				System.out.println(i + "\t" + edgeTo[i]);
	}
	
	private void mark(int v){
		this.marked[v] = true;
	}
	
	private boolean isAlreadyMarked(int v){
		return this.marked[v];
	}
	
	private void addEdgeTo(Integer origin, int target){
		this.edgeTo[target] = origin;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine().trim());
		MazeSolver ms = new MazeSolver(n);
		while(scanner.hasNext()){
			String s = scanner.nextLine();
			String params[] = s.split(" ");
			int v = Integer.valueOf(params[0]);
			int w = Integer.valueOf(params[1]);
			ms.graph.addEdge(v, w);
		}
		ms.findConnectedVertices(0, null);
		ms.printResults();
		scanner.close();
	}

}
