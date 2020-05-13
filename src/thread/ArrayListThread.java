package thread;

import model.ArrayList;
import model.Constants;

public class ArrayListThread extends Thread {
	private ArrayList al;
	private long[] n;
	private String option;
	public ArrayListThread(ArrayList al, long[] n, String option) {
		this.al = al;
		this.n = n;
		this.option = option;
	}
	@Override
	public void run() {
		if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
				option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
			for(long value:n) {
				al.add(value);
			}
		}
		long s = System.currentTimeMillis();
		switch (option) {
		case Constants.OPTION_1:
			for(long value:n) {
				al.add(value);
			}
			break;
		case Constants.OPTION_2:
			for(long value:n) {
				al.iterativeSearch(value);
			}
			break;
		case Constants.OPTION_3:
			for(long value:n) {
				al.iterativeDelete(value);
			}
			break;
		case Constants.OPTION_4:
			for(long value:n) {
				al.add(value);
			}
			break;
		case Constants.OPTION_5:
			for(long value:n) {
				al.recursiveSearch(value);
			}
			break;
		case Constants.OPTION_6:
			for(long value:n) {
				al.recursiveDelete(value);
			}
			break;
		}
		System.out.println(System.currentTimeMillis()-s);
	}
}
