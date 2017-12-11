package graphs.wordnet;

import java.util.HashMap;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

    private static final Anscestor ANCESTOR_NOT_FOUND = new Anscestor(-1, -1);
    private final Digraph digraph;

    // // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        validateNullArg(G);
        digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        Anscestor ancestor = getSAP(v, w);
        return ancestor.length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral
    // path; -1 if no such path
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        Anscestor ancestor = getSAP(v, w);
        return ancestor.id;
    }

    // length of shortest ancestral path between any vertex in v and any vertex
    // in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateNullArg(v);
        validateNullArg(w);
        Anscestor anscestor = getSAP(v, w);
        return anscestor.length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no
    // such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateNullArg(v);
        validateNullArg(w);
        Anscestor anscestor = getSAP(v, w);
        return anscestor.id;
    }

    private HashMap<Integer, Integer> getAncestorsOfVertex(int vertex) {
        HashMap<Integer, Integer> anscestors = new HashMap<Integer, Integer>();
        Queue<Integer> q = new Queue<Integer>();
        boolean[] marked = new boolean[digraph.V()];
        int distTo[] = new int[digraph.V()];
        marked[vertex] = true;
        distTo[vertex] = 0;
        q.enqueue(vertex);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : digraph.adj(v)) {
                if (!marked[w]) {
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                    anscestors.put(w, distTo[w]);
                }
            }
        }

        return anscestors;
    }

    private Anscestor getSAP(Iterable<Integer> vs, Iterable<Integer> ws) {
        Anscestor shortest = null;
        for (int v : vs) {
            for (int w : ws) {
                validateVertex(v);
                validateVertex(w);
                Anscestor ancestor = getSAP(v, w);
                if (shortest == null || ancestor.length < shortest.length)
                    shortest = ancestor;
            }
        }
        return (shortest == null) ? ANCESTOR_NOT_FOUND : shortest;
    }

    private Anscestor getSAP(int v, int w) {

        if (v == w)
            return new Anscestor(v, 0);

        HashMap<Integer, Integer> ancestors = getAncestorsOfVertex(v);

        Anscestor ancestor = null;
        if (ancestors.containsKey(w))
            ancestor = new Anscestor(w, ancestors.get(w));
        Queue<Integer> q = new Queue<Integer>();
        boolean[] marked = new boolean[digraph.V()];
        int distTo[] = new int[digraph.V()];
        marked[w] = true;
        distTo[w] = 0;
        q.enqueue(w);
        while (!q.isEmpty()) {
            int vertex = q.dequeue();
            for (int parentOfW : digraph.adj(vertex)) {
                if (!marked[parentOfW]) {
                    distTo[parentOfW] = distTo[vertex] + 1;
                    marked[parentOfW] = true;
                    if (parentOfW == v && (ancestor == null || distTo[parentOfW] < ancestor.length))
                        return new Anscestor(parentOfW, distTo[parentOfW]);
                    q.enqueue(parentOfW);
                    if (ancestors.containsKey(parentOfW)
                            && (ancestor == null || distTo[parentOfW] + ancestors.get(parentOfW) < ancestor.length))
                        ancestor = new Anscestor(parentOfW, distTo[parentOfW] + ancestors.get(parentOfW));
                }
            }
        }

        return (ancestor != null) ? ancestor : ANCESTOR_NOT_FOUND;
    }

    private void validateNullArg(Object arg) {
        if (arg == null)
            throw new IllegalArgumentException();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= digraph.V())
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (digraph.V() - 1));
    }

    private static class Anscestor {
        private final int id;
        private final int length;

        public Anscestor(int id, int length) {
            this.id = id;
            this.length = length;
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("graphs/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

}
