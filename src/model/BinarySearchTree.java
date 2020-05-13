package model;

public class BinarySearchTree {
	private Node root;
	public BinarySearchTree() {
		
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
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.iterativeAdd(12);
		bst.iterativeAdd(6);
		bst.iterativeAdd(1);
		bst.iterativeAdd(9);
		bst.iterativeDelete(6);
	}
	public void iterativeAdd(long value) {
		Node add = new Node(value);
		if(root==null) {
			root = add;
		} else {
			Node current = root;
			boolean exist = false;
			while(current!=null && !exist) {
				if(current.value>value) {
					if(current.left!=null) {
						current = current.left;
					} else {
						current.left = add;
						exist = true;
					}
				} else if(current.value<value) {
					if(current.right!=null) {
						current = current.right;
					} else {
						current.right = add;
						exist = true;
					}
				} else {
					exist = true;
				}
			}
		}
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
		Node parent = null;
		Node current = root;
		boolean exist = false;
		while(current!=null && !exist) {
			if(current.value>value) {
				parent = current;
				current = current.left;
			} else if(current.value<value) {
				parent = current;
				current = current.right;
			} else {
				exist = true;
			}
		}
		if(exist==true) {
			if(parent==null) {
				root = null;
			} else if(parent.left==current) {
				if (current.left==null && current.right==null) {
					parent.left = null;
				} else if (current.right == null) {
					parent.left = current.left;
				} else if (current.left == null) {
					parent.left= current.right;
				} else {
					long minimum = findMinimum(current.right);
					parent.left.value = minimum;
					parent = current;
					current = current.right;
					while(current.value!=minimum) {
						if(current.value>value) {
							parent = current;
							current = current.left;
						} else if(current.value<value) {
							parent = current;
							current = current.right;
						}
					}
					if(parent.left==current) {
						parent.left = null;
					} else if(parent.right==current) {
						parent.right = null;
					}
				}
			} else if(parent.right==current) {
				if (current.left==null && current.right==null) {
					parent.right = null;
				} else if (current.right == null) {
					parent.right = current.left;
				} else if (current.left == null) {
					parent.right = current.right;
				} else {
					long minimum = findMinimum(current.right);
					parent.right.value = minimum;
					parent = current;
					current = current.right;
					while(current.value!=minimum) {
						if(current.value>value) {
							parent = current;
							current =current.left;
						} else if(current.value<value) {
							parent = current;
							current = current.right;
						}
					}
					if(parent.left==current) {
						parent.left = null;
					} else if(parent.right==current) {
						parent.right = null;
					}
				}
				
			}
			current = null;
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
