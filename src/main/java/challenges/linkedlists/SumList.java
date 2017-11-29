package challenges.linkedlists;

import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.Stack;

public class SumList {
	
	public static void main(String[] args) {
		SumList sl = new SumList();
		Stack<Integer> result = sl.sum(10, 450);
		for(int n : result)
			System.out.print(n);
		System.out.println();
	}
	
	public Stack<Integer> sum(int n1, int n2){
		Stack<Integer> stk1 = transformNumberToLinkedList(n1);
		Stack<Integer> stk2 = transformNumberToLinkedList(n2);
		Stack<Integer> result = new Stack<Integer>();
		int remainder = 0;
		while(!stk1.isEmpty() || !stk2.isEmpty()){
			int sum = add(stk1) + add(stk2) + remainder;
			result.push(sum % 10);
			remainder = sum / 10;
		}
		return result;
	}
	
	private Stack<Integer> transformNumberToLinkedList(int n1){
		String number = "" + n1;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i<number.length(); i++){
			stack.push(new Integer("" + number.charAt(i)));
		}
		return stack;
	}
	
	private Integer transformFromLinkedListToNumber(LinkedQueue<Integer> list){
		StringBuffer buffer = new StringBuffer();
		for(int n :list){
			buffer.append(n);
		}
		return new Integer(buffer.toString());
	}
	
	private int add(Stack<Integer> stack){
		return stack.isEmpty() ? 0 : stack.pop();
	}
}
