package challenges.stacks;

import java.util.Collection;
import java.util.HashMap;

import edu.princeton.cs.algs4.Stack;

public class ArrayToThreeStacks<T> {
	
	
	public static void main(String[] args) {
		ArrayToThreeStacks<Integer> arrayToStack = new ArrayToThreeStacks<Integer>();
		Integer[] numbers = new Integer[]{1,2,3,4,5,6,7,8};
		Collection<Stack<Integer>> stacks = arrayToStack.getThreeStacks(numbers);
		for(Stack<Integer> stack : stacks){
			System.out.println("new stack");
			for(Integer n : stack)
				System.out.println("\t" + n);
		}
	}
	
	public Collection<Stack<T>> getThreeStacks(T[] array){
		HashMap<Integer, Stack<T>> stacks = new HashMap<Integer, Stack<T>>(); 
		stacks.put(0, new Stack<T>());
		stacks.put(1, new Stack<T>());
		stacks.put(2, new Stack<T>());
		
		for(int i = 0; i< array.length; i++){
			int stackId = i % 3;
			stacks.get(stackId).push(array[i]);
		}
		
		return stacks.values();
	} 
	
}
