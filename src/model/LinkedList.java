package model;

public class LinkedList {
	private Node first;
	public LinkedList() {
		
	}
	public void recursiveAdd(long value) {
		first = recursiveAdd(first,value);
	}
	private Node recursiveAdd(Node current, long value) {
		if(current==null) {
			return new Node(value);
		} else {
			current.next = recursiveAdd(current.next,value);
		}
		return current;
	}
	public boolean recursiveSearch(long value) {
		return recursiveSearch(first,value);
	}
	private boolean recursiveSearch(Node current, long value) {
		if(current==null) {
			return false;
		} else if(current.value==value) {
			return true;
		}
		return recursiveSearch(current.next,value);
	}
	public void recursiveDelete(long value) {
		first = recursiveDelete(first,value);
	}
	private Node recursiveDelete(Node current, long value) {
		  if (current==null) return null;
		  if (current.value==value) {
		    Node tmpNext;
		    tmpNext = current.next;
		    current = null;
		    return tmpNext;
		  }
		  current.next = recursiveDelete(current.next,value);
		  return current;
	}
	public void iterativeAdd(long value) {
		Node add = new Node(value);
		if(first==null) {
			first = add;
		} else {
			Node current = first;
			while(current.next!=null) {
				current = current.next;
			}
			current.next = add;
		}
	}
	public boolean iterativeSearch(long value) {
		boolean exist = false;
		Node current = first;
		while(current!=null && exist) {
			if(current.value==value) exist=true;
			current = current.next;
		}
		return exist;
	}
	public boolean iterativeDelete(long value) {
		boolean deleted = false;
		Node previous = null;
		Node current = first;
		while(current!=null && deleted) {
			if(current.value==value) {
				previous.next = current.next;
				current = null;
				deleted = true;
			} else {
				previous = previous.next;
				current = current.next;
			}
		}
		return deleted;
	}
	private class Node {
		private long value;
		private Node next;
		public Node(long value) {
			this.value = value;
		}
	}
}
