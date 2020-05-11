package thread;

import model.Constants;
import model.LinkedList;

public class LinkedListThread extends Thread {
	private LinkedList ll;
	private long[] n;
	private String option;
	public LinkedListThread(LinkedList ll, long[] n, String option) {
		this.ll = ll;
		this.n = n;
		this.option = option;
	}
	@Override
	public void run() {
		if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
				option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
			for(long value:n) {
				ll.recursiveAdd(value);
			}
		}
		switch (option) {
		case Constants.OPTION_1:
			for(long value:n) {
				ll.iterativeAdd(value);;
			}
			break;
		case Constants.OPTION_2:
			for(long value:n) {
				ll.iterativeSearch(value);;
			}
			break;
		case Constants.OPTION_3:
			for(long value:n) {
				ll.iterativeDelete(value);;
			}
			break;
		case Constants.OPTION_4:
			for(long value:n) {
				ll.recursiveAdd(value);;
			}
			break;
		case Constants.OPTION_5:
			for(long value:n) {
				ll.recursiveSearch(value);;
			}
			break;
		case Constants.OPTION_6:
			for(long value:n) {
				ll.recursiveDelete(value);;
			}
			break;
		}
	}
}
