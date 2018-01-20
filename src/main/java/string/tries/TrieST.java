package string.tries;

import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {

    private static int R = 256;
    private Node root = new Node();
    private int n;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        if (val == null)
            delete(key);
        else
            root = put(key, val, 0, root);
    }

    private Node put(String key, Value val, int d, Node x) {

        if (x == null)
            x = new Node();

        if (d == key.length()) {
            if (x.val == null)
                n++;
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(key, val, d + 1, x.next[c]);
        return x;
    }

    public boolean contains(String key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null)
            return;
        if (x.val != null)
            results.enqueue(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null)
            return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
            results.enqueue(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public String longestPrefixOf(String query) {
        if (query == null)
            throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1)
            return null;
        else
            return query.substring(0, length);
    }

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null)
            return length;
        if (x.val != null)
            length = d;
        if (d == query.length())
            return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length);
    }

    public void delete(String key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length()) {
            if (x.val != null)
                n--;
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null)
            return x;
        for (int c = 0; c < R; c++)
            if (x.next[c] != null)
                return x;
        return null;
    }

    public static void main(String[] args) {

        TrieST<String> trie = new TrieST<>();
        trie.put("ana", "bia");
        trie.put("priscila", "pri");
        trie.put("edwins", "edi");
        trie.put("julia", "ju");
        trie.put("pai", "ivo");
        trie.put("parana", "curitiba");

        System.out.println(trie.get("edwins"));
        System.out.println(trie.get("julia"));
        System.out.println(trie.get("priscila"));
        System.out.println(trie.get("ana"));

        System.out.println(trie.contains("ivo"));
        System.out.println(trie.contains("pai"));

        for (String s : trie.keysWithPrefix("p"))
            System.out.println(s);

        System.out.println(trie.longestPrefixOf("paranaue"));
    }
}
