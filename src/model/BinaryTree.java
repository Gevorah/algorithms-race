package model;

public class BinaryTree {
	private Node root;
	public BinaryTree() {
		
	}
	public void recursiveAdd(long value) {
		root = recursiveAdd(root,value);
	}
	private Node recursiveAdd(Node current, long value) {
		if (current==null) return new Node(value);
	    if (value<current.value) {
	        current.left = recursiveAdd(current.left,value);
	    } else if(value>current.value) {
	        current.right = recursiveAdd(current.right,value);
	    } else {
	        return current;
	    }
		return current;
	}
	public boolean recursiveSearch(long value) {
		return recursiveSearch(root,value);
	}
	private boolean recursiveSearch(Node current, long value) {
		if(current==null) return false;
		if(current.value==value) return true;
		return current.value>value?recursiveSearch(current.left,value):recursiveSearch(current.right,value);
	}
	public boolean deleteRecursive(long value) {
		root = deleteRecursive(root,value);
		return true;
	}
	private Node deleteRecursive(Node current,long value) {
	    if (current==null) return null;
	    if (current.value==value) {
	    	if (current.left==null && current.right==null) return null;
	    	if (current.right == null) return current.left;
	    	if (current.left == null) return current.right;
	    	long minimum = findMinimum(current.right);
	    	current.value = minimum;
	    	current.right = deleteRecursive(current.right,minimum);
	    	return current;
	    } 
	    if(current.value>value) {
	        current.left = deleteRecursive(current.left,value);
	        return current;
	    } else {
	    	current.right = deleteRecursive(current.right,value);
	    	return current;
	    }
	}
	private long findMinimum(Node root) {
	    return root.left==null?root.value:findMinimum(root.left);
	}
	private class Node {
		private long value;
		private Node left;
		private Node right;
		public Node(long value) {
			this.value = value;
		}
	}
}
