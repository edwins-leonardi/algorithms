package edu.princeton.cs.algs4;


public class DetectCollision {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        ll.add(7);
        ll.add(8);
        LinkedList.Node node = ll.add(9);
        ll.addNode(node);
        LinkedList.Node r = detectColision(ll.getFirst(), ll.getFirst().next, 1, ll.size());
        System.out.println(r);
    }

    public static LinkedList.Node detectColision(LinkedList.Node node1, LinkedList.Node node2, int stepNumber, int listSize) {

        if (node1 == null || node2 == null || node2.next == null)
            return null;

        if (node1 == node2) {
            int diff = listSize - stepNumber;
            LinkedList.Node result = node1;
            while (diff > 0) {
                result = result.next;
                diff--;
            }
            return result;
        }
        return detectColision(node1.next, node2.next.next, stepNumber+1, listSize);
    }
}
