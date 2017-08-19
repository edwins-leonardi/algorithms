package symboltables;

public interface ST<Key extends Comparable<Key>, Value> {
    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Key min();

    Key max();

    Key floor();

    Key ceiling();

    int rank(Key key);

    Key select(int rank);

    void deleteMin();

    void deleteMax();

    int size(Key lo, Key hi);

    Iterable<Key> keys();
}
