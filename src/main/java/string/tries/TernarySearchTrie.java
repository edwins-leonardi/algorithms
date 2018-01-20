package string.tries;

public class TernarySearchTrie<Value> {
    private Node<Value> root;
    private int n;

    private static class Node<Value> {
        private Value val;
        private Node<Value> left, mid, right;
        private char c;
    }

    public Value get(String key) {
        Node<Value> x = get(root, key, 0);
        if (x == null)
            return null;
        return x.val;
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null)
            return null;
        char c = key.charAt(d);
        if (c < x.c)
            return get(x.left, key, d);
        else if (c > x.c)
            return get(x.right, key, d);
        else if (d < key.length() - 1)
            return get(x.mid, key, d + 1);
        else
            return x;
    }

    public void put(String key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        else
            root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {

        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c)
            x.left = put(x.left, key, val, d);
        else if (c > x.c)
            x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)
            x.mid = put(x.mid, key, val, d + 1);
        else
            x.val = val;
        return x;
    }

    public static void main(String[] args) {
        TernarySearchTrie<String> trie = new TernarySearchTrie<>();
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

    }
}
