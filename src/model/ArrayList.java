package model;

import java.util.List;

public class ArrayList {
	private List<Element> list;
	public ArrayList() {
		list = new java.util.ArrayList<>();
	}
	public void add(long value) {
		list.add(new Element(value));
	}
	public boolean recursiveSearch(long value) {
		return recursiveSearch(0,value);
	}
	private boolean recursiveSearch(int index, long value) {
		if(index>=list.size()){
			return false;
		} else if(list.get(index).value==value) {
			return true;
		}
		return recursiveSearch(index++,value);
	}
	public boolean recursiveDelete(long value) {
		return recursiveDelete(0,value);
	}
	private boolean recursiveDelete(int index, long value) {
		if(index>=list.size()){
			return false;
		} else if(list.get(index).value==value) {
			list.remove(index);
			return true;
		}
		return recursiveDelete(index++,value);
	}
	public boolean iterativeSearch(long value) {
		boolean exist = false;
		for(int i=0;i<list.size() && exist;i++) {
			if(list.get(i).value==value) {
				exist = true;
			}
		}
		return exist;
	}
	public boolean iterativeDelete(long value) {
		boolean deleted = false;
		for (int i=0;i<list.size() && deleted;i++) {
			if(list.get(i).value==value) {
				list.remove(i);
				deleted = true;
			}
		}
		return deleted;
	}
	private class Element{
		private long value;
		public Element(long value) {
			this.value = value;
		}
	}
}
