package model;

public class LinkedList {
	private Element first;
	public LinkedList() {
		
	}
	public void iterativeAdd(long value) {
		Element add = new Element(value);
		if(first==null) {
			first = add;
		} else {
			Element curr = first;
			while(curr.next!=null) {
				curr = curr.next;
			}
			add.previous = curr;
			curr.next = add;
		}
	}
	public boolean iterativeSearch(long value) {
		boolean exist = false;
		Element curr = first;
		while(curr!=null || !exist) {
			if(curr.value==value) exist=true;
			curr = curr.next;
		}
		return exist;
	}
	public boolean iterativeDelete(long value) {
		boolean deleted = false;
		Element curr = first;
		while(curr!=null && !deleted) {
			if(curr.value==value) {
				curr.previous.next = curr.next;
				curr.next.previous = curr.previous;
				curr = null;
				deleted = true;
			} else {
				curr = curr.next;
			}
		}
		return deleted;
	}
	private class Element {
		private long value;
		private Element previous;
		private Element next;
		public Element(long value) {
			this.value = value;
		}
	}
}
