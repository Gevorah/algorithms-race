package thread;

import model.BinarySearchTree;
import model.Constants;

public class BinarySearchTreeThread extends Thread {
	private BinarySearchTree bst;
	private long[] n;
	private String option;
	public BinarySearchTreeThread(BinarySearchTree bst, long[] n, String option) {
		this.bst = bst;
		this.n = n;
		this.option = option;
	}
	@Override
	public void run() {
		if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
				option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
			for(long value:n) {
				bst.recursiveAdd(value);
			}
		}
		switch (option) {
		case Constants.OPTION_1:
			for(long value:n) {
				bst.iterativeAdd(value);
			}
			break;
		case Constants.OPTION_2:
			for(long value:n) {
				bst.iterativeSearch(value);
			}
			break;
		case Constants.OPTION_3:
			for(long value:n) {
				bst.iterativeDelete(value);
			}
			break;
		case Constants.OPTION_4:
			for(long value:n) {
				bst.recursiveAdd(value);
			}
			break;
		case Constants.OPTION_5:
			for(long value:n) {
				bst.recursiveSearch(value);
			}
			break;
		case Constants.OPTION_6:
			for(long value:n) {
				bst.recursiveDelete(value);
			}
			break;
		}
	}
}
