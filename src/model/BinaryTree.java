package model;

public class BinaryTree {
	private Node root;
	public BinaryTree() {
		
	}
	public void recursiveAdd(long value) {
		root = recursiveAdd(root,value);
	}
	private Node recursiveAdd(Node current, long value) {
		if(current==null) return new Node(value);
	    if(value<current.value) {
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
	public void recursiveDelete(long value) {
		root = recursiveDelete(root,value);
	}
	private Node recursiveDelete(Node current,long value) {
	    if(current==null) return null;
	    if(current.value==value) {
	    	if(current.left==null && current.right==null) return null;
	    	if(current.right == null) return current.left;
	    	if(current.left == null) return current.right;
	    	long minimum = findMinimum(current.right);
	    	current.value = minimum;
	    	current.right = recursiveDelete(current.right,minimum);
	    	return current;
	    } 
	    if(current.value>value) {
	        current.left = recursiveDelete(current.left,value);
	        return current;
	    } else {
	    	current.right = recursiveDelete(current.right,value);
	    	return current;
	    }
	}
	private long findMinimum(Node root) {
	    return root.left==null?root.value:findMinimum(root.left);
	}
	public void iterativeAdd(long value) {
		Node add = new Node(value);
		Node current = root;
		boolean exist = false;
		while(current!=null && !exist) {
			if(current.value>value) {
				current = current.left;
			} else if(current.value<value) {
				current = current.right;
			} else {
				exist = true;
			}
		}
		if(exist==false) current=add;
	}
	public boolean iterativeSearch(long value) {
		Node current = root;
		while(current!=null) {
			if(current.value>value) {
				current = current.left;
			} else if(current.value<value) {
				current = current.right;
			} else {
				return true;
			}
		}
		return false;
	}
	public boolean iterativeDelete(long value) {
		Node current = root;
		boolean exist = false;
		while(current!=null && exist) {
			if(current.value>value) {
				current =current.left;
			} else if(current.value<value) {
				current = current.right;
			} else {
				exist = true;
			}
		}
		if(exist!=false) {
			if (current.left==null && current.right==null) {
				current = null;
			} else if (current.right == null) {
				current = current.left;
			} else if (current.left == null) {
				current = current.right;
			} else {
				long minimum = findMinimum(current.right);
				current.value = minimum;
				current = current.right;
				while(current.value!=minimum) {
					if(current.value>value) {
						current =current.left;
					} else if(current.value<value) {
						current = current.right;
					}
				}
				current = null;
			}
		}
		return exist;
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
