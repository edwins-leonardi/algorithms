package challenges.linkedlists;

import edu.princeton.cs.algs4.LinkedList;
import edu.princeton.cs.algs4.Stack;

public class Palindrome {

    public static boolean isPalindrome(LinkedList ll){
        Stack<Integer> stack = new Stack<>();
        for (Integer v : ll){
            stack.push(v);
        }
        for (Integer v : ll){
            if (v != stack.pop())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(2);
        ll.add(4);
        ll.add(17);
        ll.add(23);
        ll.add(45);
        ll.add(51);
        ll.add(67);
        ll.add(68);
        ll.add(51);
        ll.add(45);
        ll.add(23);
        ll.add(17);
        ll.add(4);
        ll.add(2);

        System.out.println(ll);
        System.out.printf("Is Palindrome? %s" , isPalindrome(ll));
    }
}
