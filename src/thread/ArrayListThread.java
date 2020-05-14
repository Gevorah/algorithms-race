package thread;

import model.ArrayList;
import model.Constants;

public class ArrayListThread extends Thread {
	private ArrayList al;
	private long[] generatedArray;
	private long[] generatedArraySD;
	private String option;
	public ArrayListThread(ArrayList al, long[] generatedArray, long[] generatedArraySD, String option) {
		this.al = al;
		this.generatedArray = generatedArray;
		this.generatedArraySD = generatedArraySD;
		this.option = option;
	}
	@Override
	public void run() {
		if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
				option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
			for(long value:generatedArray) {
				al.add(value);
			}
		}
		switch (option) {
		case Constants.OPTION_1:
			for(long value:generatedArray) {
				al.add(value);
			}
			break;
		case Constants.OPTION_2:
			for(long value:generatedArraySD) {
				al.iterativeSearch(value);
			}
			break;
		case Constants.OPTION_3:
			for(long value:generatedArraySD) {
				al.iterativeDelete(value);
			}
			break;
		case Constants.OPTION_4:
			for(long value:generatedArray) {
				al.add(value);
			}
			break;
		case Constants.OPTION_5:
			for(long value:generatedArraySD) {
				al.recursiveSearch(value);
			}
			break;
		case Constants.OPTION_6:
			for(long value:generatedArraySD) {
				al.recursiveDelete(value);
			}
			break;
		}
	}
}
