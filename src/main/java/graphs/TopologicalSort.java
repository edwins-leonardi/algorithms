package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Topological;

public class TopologicalSort {

    private boolean marked[];
    private Queue<Integer> queue;

    public TopologicalSort(Digraph graph){
        marked = new boolean[graph.V()];
        queue = new Queue<>();
        for (int v =0; v<graph.V(); v++){
            if(!marked[v])
                dfs(graph, v);
        }
    }

    private void dfs(Digraph graph, int v){
        marked[v] = true;
        for(int w : graph.adj(v)) {
            if(!marked[w]){
                dfs(graph, w);
            }
        }
        queue.enqueue(v);
    }


    public void print(){
        for(int v: queue)
            System.out.println(v);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph graph = new Digraph(in);
        TopologicalSort topologicalSort = new TopologicalSort(graph);
        topologicalSort.print();
    }


}
