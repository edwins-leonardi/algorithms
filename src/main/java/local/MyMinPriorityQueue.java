package local;

public class MyMinPriorityQueue<Key extends Comparable<Key>> {

    private Key[] items;
    private int size;

    public MyMinPriorityQueue(){
        items = (Key[]) new Comparable[16];
    }
    public MyMinPriorityQueue(int initialSize){
        items = (Key[]) new Comparable[initialSize+1];
    }
    public MyMinPriorityQueue(Key[] a){
        items = (Key[]) new Comparable[a.length];
    }

    public void insert(Key v){
        items[++size] = v;
        swin(size);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Key delMin(){
        Key min = items[1];
        items[1] = items[size];
        items[size--] = null;
        sink(1);
        return min;
    }

    public Key getMin(){
        return items[1];
    }

    private void sink(int k){
        while(k * 2 < size){
            int j = k * 2;
            if(less(items[j+1], items[j]))
                j++;
            if (!less(items[j], items[k]))
                break;
            swap(k, j);
            k = j;
        }
    }

    private boolean less(Key x, Key y){
        int r = x.compareTo(y);
        return r <= 0;
    }

    private void swap(int x, int y){
        Key swap = items[x];
        items[x] = items[y];
        items[y] = swap;
    }

    private void swin(int k){
        while(k/2 > 0){
            if (!less(items[k], items[k/2]))
                break;
            swap(k, k/2);
            k = k/2;
        }
    }

    public static void main(String[] args) {
        MyMinPriorityQueue<Character> pq = new MyMinPriorityQueue<Character>(10);
        pq.insert('E');
        pq.insert('D');
        pq.insert('W');
        pq.insert('I');
        pq.insert('N');
        pq.insert('S');
        pq.insert('J');
        pq.insert('U');
        pq.insert('L');
        pq.insert('A');

        System.out.printf("Priority Queue Size=%d\n", pq.size);
        System.out.printf("Priority Queue Min=%s\n", pq.getMin());
        pq.delMin();
        System.out.printf("Priority Queue Min=%s\n", pq.getMin());
        pq.delMin();
        System.out.printf("Priority Queue Min=%s\n", pq.getMin());
        pq.delMin();
        System.out.printf("Priority Queue Min=%s\n", pq.getMin());
        pq.delMin();
        System.out.printf("Priority Queue Min=%s\n", pq.getMin());
        pq.delMin();
        System.out.printf("Priority Queue Min=%s\n", pq.delMin());
        System.out.printf("Priority Queue Size=%d\n", pq.size);
        System.out.printf("Priority Queue Min=%s\n", pq.delMin());
        System.out.printf("Priority Queue Min=%s\n", pq.delMin());
        System.out.printf("Priority Queue Min=%s\n", pq.delMin());
        System.out.printf("Priority Queue Min=%s\n", pq.delMin());

    }

}
