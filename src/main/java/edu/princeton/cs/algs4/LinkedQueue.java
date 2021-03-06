package edu.princeton.cs.algs4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedQueue} class represents a first-in-first-out (FIFO) queue of
 * generic items. It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item, testing if the
 * queue is empty, and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a singly-linked list with a non-static nested class
 * for linked-list nodes. See {@link Queue} for a version that uses a static
 * nested class. The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>,
 * <em>size</em>, and <em>is-empty</em> operations all take constant time in the
 * worst case.
 * <p>
 * For additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LinkedQueue<Item extends Comparable<Item>> implements Iterable<Item> {
	private int n; // number of elements on queue
	private Node first; // beginning of queue
	private Node last; // end of queue

	// helper linked list class
	protected class Node {
		protected Item item;
		protected Node next;
	}

	/**
	 * Initializes an empty queue.
	 */
	public LinkedQueue() {
		first = null;
		last = null;
		n = 0;
		assert check();
	}

	/**
	 * Is this queue empty?
	 * 
	 * @return true if this queue is empty; false otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of items in this queue.
	 * 
	 * @return the number of items in this queue
	 */
	public int size() {
		return n;
	}

	public Node getFirst(){
		return first;
	}

	public Node getNext(Node current){
		return current.next;
	}


	/**
	 * Returns the item least recently added to this queue.
	 * 
	 * @return the item least recently added to this queue
	 * @throws java.util.NoSuchElementException
	 *             if this queue is empty
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}

	public void deleteDuplicates() {
		if (first == null)
			return;
		HashSet<Item> items = new HashSet<Item>();
		Node current = first;
		Node previous = null;
		while (current != null) {
			if (items.contains(current.item)) {
				previous.next = current.next;
				current.item = null;
			} else {
				items.add(current.item);
				previous = current;
			}
			current = current.next;
		}
	}

	public void deleteDuplicatesFancyWay() {
		if (first == null)
			return;
		Node current = first;
		while (current != null) {
			Node runner = current;
			while (runner.next != null) {
				if (current.item.equals(runner.next.item)) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}

	public Item getKthToLastElement(int kth) {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		if (kth < 0)
			throw new IllegalArgumentException("ktn must not be smaller than zero");
		if (kth > n - 1)
			throw new IllegalArgumentException(
					"kth must not be greater than the number of elements in the queue");
		Node current = first;
		Node runner = first;
		int pointer = 0;
		while (runner.next != null) {
			if (pointer >= kth)
				current = current.next;
			pointer++;
			runner = runner.next;
		}
		return current.item;
	}

	public void deleteMiddleNode(Item item){
		Node prev = null;
		Node current = first;
		while(current != null) {
			if (current.item == item) {
				if(prev == null)
					first = first.next;
				else
					prev.next = current.next;
				return;
			}
			prev = current;
			current = current.next;
		}
	}

	public Item myGetKthToLastElement(int kth) {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		if (kth < 0)
			throw new IllegalArgumentException("ktn must not be smaller than zero");
		if (kth > n - 1)
			throw new IllegalArgumentException(
					"kth must not be greater than the number of elements in the queue");
		int count = 0;
		Node kthElement = first;
		Node current = first;
		while(current.next != null) {
			count++;
			if (count > kth) {
				kthElement = kthElement.next;
				count--;
			}
			current = current.next;
		}
		return kthElement.item;
	}


	/**
	 * Adds the item to this queue.
	 * 
	 * @param item
	 *            the item to add
	 */
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		n++;
		assert check();
	}

	public void partitionByValue(Item value) {
		Node runner = first;
		Node right = null;
		Node lastRight = null;
		Node left = null;
		Node lastLeft = null;
		while (runner != null) {
			Node next = runner.next;
			runner.next = null;
			if (runner.item.compareTo(value) < 0) {
				if (left == null) {
					left = runner;
					lastLeft = left;
				} else {
					lastLeft.next = runner;
					lastLeft = runner;
				}
			} else {
				if (right == null) {
					right = runner;
					lastRight = runner;
				} else {
					lastRight.next = runner;
					lastRight = runner;
				}
			}
			runner = next;
		}
		if (left != null) {
			first = left;
			lastLeft.next = right;
		} else {
			first = right;
		}
	}

	public void myPartitionByValue(Item value) {
		Node current = first;
		Node runner = first.next;
		while(runner != null) {
			if (current.item.compareTo(value) == -1) {
				current = current.next;
			} else if (current.item.compareTo(runner.item) == 1) {
				Item temp = current.item;
				current.item = runner.item;
				runner.item = temp;
				current = current.next;
			}
			runner = runner.next;
		}
	}

	public void myPartitionByValue2(Item value) {
		Node node = first;
		Node head = null;
		Node tail = null;
		while(node != null) {
			Node next = node.next;
			if (node.item.compareTo(value) == -1 ) {
				if (head == null) {
					head = node;
					head.next = null;
				} else {
					Node temp = head;
					head = node;
					head.next = temp;
				}
			} else {
				if (tail == null) {
					tail = node;
					tail.next = null;
				} else {
					Node temp = tail;
					tail = node;
					tail.next = temp;
				}
			}
			node = next;
		}
		first.next = last;
		first = head;
	}

	/**
         * Removes and returns the item on this queue that was least recently added.
         *
         * @return the item on this queue that was least recently added
         * @throws java.util.NoSuchElementException
         *             if this queue is empty
         */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		assert check();
		return item;
	}

	/**
	 * Returns a string representation of this queue.
	 * 
	 * @return the sequence of items in FIFO order, separated by spaces
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	// check internal invariants
	private boolean check() {
		if (n < 0) {
			return false;
		} else if (n == 0) {
			if (first != null)
				return false;
			if (last != null)
				return false;
		} else if (n == 1) {
			if (first == null || last == null)
				return false;
			if (first != last)
				return false;
			if (first.next != null)
				return false;
		} else {
			if (first == null || last == null)
				return false;
			if (first == last)
				return false;
			if (first.next == null)
				return false;
			if (last.next != null)
				return false;

			// check internal consistency of instance variable n
			int numberOfNodes = 0;
			for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
				numberOfNodes++;
			}
			if (numberOfNodes != n)
				return false;

			// check internal consistency of instance variable last
			Node lastNode = first;
			while (lastNode.next != null) {
				lastNode = lastNode.next;
			}
			if (last != lastNode)
				return false;
		}

		return true;
	}

	/**
	 * Returns an iterator that iterates over the items in this queue in FIFO
	 * order.
	 * 
	 * @return an iterator that iterates over the items in this queue in FIFO
	 *         order
	 */
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}