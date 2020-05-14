package thread;

import model.ArrayList;
import model.Constants;

public class ArrayListThread extends Thread {
	private ArrayList al;
	private long[] generatedArray;
	private long[] generatedArraySD;
	private String option;
	private double progress;
	boolean ready;
	public ArrayListThread(ArrayList al, long[] generatedArray, long[] generatedArraySD, String option) {
		this.al = al;
		this.generatedArray = generatedArray;
		this.generatedArraySD = generatedArraySD;
		this.option = option;
	}
	@Override
	public void run() {
		ready = true;
		if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
				option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
			for(long value:generatedArray) {
				al.add(value);
			}
		}
		progress = 0;
		ready = false;
		while(ready==false) {}
		double increase = 1.0/generatedArray.length;
		switch (option) {
		case Constants.OPTION_1:
			for(long value:generatedArray) {
				al.add(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_2:
			for(long value:generatedArraySD) {
				al.iterativeSearch(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_3:
			for(long value:generatedArraySD) {
				al.iterativeDelete(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_4:
			for(long value:generatedArray) {
				al.add(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_5:
			for(long value:generatedArraySD) {
				al.recursiveSearch(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_6:
			for(long value:generatedArraySD) {
				al.recursiveDelete(value);
				progress += increase;
			}
			break;
		}
	}
	public double getProgress() {
		return progress;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
