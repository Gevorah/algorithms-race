package thread;

import model.Constants;
import model.LinkedList;

public class LinkedListThread extends Thread {
	private LinkedList ll;
	private long[] generatedArray;
	private long[] generatedArraySD;
	private String option;
	private double progress;
	private boolean ready;
	public LinkedListThread(LinkedList ll, long[] generatedArray, long[] generatedArraySD, String option) {
		this.ll = ll;
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
				ll.iterativeAdd(value);
			}
		}
		progress = 0;
		ready = false;
		while(ready==false) {}
		long start = System.currentTimeMillis();
		double increase = 1.0/generatedArray.length;
		switch (option) {
		case Constants.OPTION_1:
			for(long value:generatedArray) {
				ll.iterativeAdd(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_2:
			for(long value:generatedArraySD) {
				ll.iterativeSearch(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_3:
			for(long value:generatedArraySD) {
				ll.iterativeDelete(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_4:
			for(long value:generatedArray) {
				ll.recursiveAdd(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_5:
			for(long value:generatedArraySD) {
				ll.recursiveSearch(value);
				progress += increase;
			}
			break;
		case Constants.OPTION_6:
			for(long value:generatedArraySD) {
				ll.recursiveDelete(value);
				progress += increase;
			}
			break;
		}
		int min = (int)(((System.currentTimeMillis()-start) /1000)/60);
		int sec = (int)(((System.currentTimeMillis()-start)/1000)%60);
		System.out.println(min+" "+sec+" "+(System.currentTimeMillis()-start));
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
