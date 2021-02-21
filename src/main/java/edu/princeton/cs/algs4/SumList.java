package edu.princeton.cs.algs4;

import java.util.Iterator;

public class SumList {

    private class SumSummary{
        private LinkedList.Node node;
        private int remainder;
    }

    private LinkedList sumLists(LinkedList l1, LinkedList l2) {
        if (l1 == null || l2 == null)
            return null;

        if (l1.size() != l2.size()) {
            if (l1.size() < l2.size())
                padWithZeros(l1, l2.size() - l1.size());
            else
                padWithZeros(l2, l1.size() - l2.size());
        }
		LinkedList.Node n1 = l1.getFirst();
        LinkedList.Node n2 = l2.getFirst();
        SumSummary sumSummary = sumLists(n1, n2);
        LinkedList result = new LinkedList();
        if (sumSummary.remainder > 0)
            result.add(1);

        LinkedList.Node node = sumSummary.node;
        while(node != null) {
            result.add(node.value);
            node = node.next;
        }

		return result;
    }

    private SumSummary sumLists(LinkedList.Node n1, LinkedList.Node n2) {
        if (n1 == null || n2 == null)
            return new SumSummary();

        SumSummary sum = sumLists(n1.next, n2.next);

        int total = n1.value + n2.value + sum.remainder;
        LinkedList.Node node = createNode(sum.node, total % 10);

        sum.node = node;
        sum.remainder = total > 9 ? 1 : 0;
        return sum;
    }

    private LinkedList.Node createNode(LinkedList.Node list, Integer value) {
        LinkedList.Node node = new LinkedList.Node(value);
        if(list != null) {
            node.next = list;
        }
        return node;
    }

    private void padWithZeros(LinkedList list, int qtd) {
        for (int i = 0; i < qtd; i++) {
            list.addAsFirst(0);
        }
    }

    public static void main(String[] args) {

        LinkedList l1 = new LinkedList();
        l1.add(9);
        l1.add(0);
        l1.add(0);
        l1.add(0);
        for (int i : l1) {
            System.out.print(i);
        }
        System.out.println();
//        l1.add(2);

        LinkedList l2 = new LinkedList();
        l2.add(2);
        l2.add(8);
        l2.add(1);
        for (int i : l2) {
            System.out.print(i);
        }
        System.out.println();

        SumList sumList = new SumList();
        LinkedList result = sumList.sumLists(l1, l2);

        for (int i : result) {
            System.out.print(i);
        }
        System.out.println();
    }
}
