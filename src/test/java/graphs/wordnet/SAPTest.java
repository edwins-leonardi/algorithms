package graphs.wordnet;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {
    //

    @Test
    public void digraph1_3_8() {
        int v = 3;
        int w = 8;
        SAP sap = initSAPForDigraph1("graphs/digraph1.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(1));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(3));
    }

    @Test
    public void digraph1_3_11() {
        int v = 3;
        int w = 11;
        SAP sap = initSAPForDigraph1("graphs/digraph1.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(4));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(1));
    }

    @Test
    public void digraph1_9_12() {
        int v = 9;
        int w = 12;
        SAP sap = initSAPForDigraph1("graphs/digraph1.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(3));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(5));
    }

    @Test
    public void digraph1_7_2() {
        int v = 7;
        int w = 2;
        SAP sap = initSAPForDigraph1("graphs/digraph1.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(4));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    @Test
    public void digraph1_1_6() {
        int v = 1;
        int w = 6;
        SAP sap = initSAPForDigraph1("graphs/digraph1.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(-1));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(-1));
    }

    @Test
    public void digraph1_0_3() {
        int v = 0;
        int w = 3;
        In in = new In("graphs/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        // SAP sap = initSAPForDigraph1("graphs/digraph1.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(2));
        G.addEdge(v, w);
        length = sap.length(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(2));
        // Assert.assertThat(ancestor, CoreMatchers.equalTo(-1));
    }

    @Test
    public void digraph2_4_0() {
        int v = 4;
        int w = 0;
        SAP sap = initSAPForDigraph1("graphs/digraph2.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(2));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    @Test
    public void digraph2_1_5() {
        int v = 1;
        int w = 5;
        SAP sap = initSAPForDigraph1("graphs/digraph2.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(2));
        Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    @Test
    public void digraph3_10_11() {
        int v = 10;
        int w = 11;
        SAP sap = initSAPForDigraph1("graphs/digraph3.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(1));
        // Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    @Test
    public void digraph3_2_6() {
        int v = 2;
        int w = 6;
        SAP sap = initSAPForDigraph1("graphs/digraph3.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(2));
        // Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    @Test
    public void digraph4_9_0() {
        int v = 9;
        int w = 0;
        SAP sap = initSAPForDigraph1("graphs/digraph4.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(1));
        // Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    @Test
    public void digraph5_13_11() {
        int v = 13;
        int w = 11;
        SAP sap = initSAPForDigraph1("graphs/digraph5.txt");
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        Assert.assertThat(length, CoreMatchers.equalTo(2));
        // Assert.assertThat(ancestor, CoreMatchers.equalTo(0));
    }

    private SAP initSAPForDigraph1(String fileName) {
        In in = new In(fileName);
        Digraph G = new Digraph(in);
        return new SAP(G);
    }
}
