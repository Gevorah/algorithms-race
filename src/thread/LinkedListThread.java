package thread;

import model.Constants;
import model.LinkedList;

public class LinkedListThread extends Thread {
	private LinkedList ll;
	private long[] generatedArray;
	private long[] generatedArraySD;
	private String option;
	public LinkedListThread(LinkedList ll, long[] generatedArray, long[] generatedArraySD, String option) {
		this.ll = ll;
		this.generatedArray = generatedArray;
		this.generatedArraySD = generatedArraySD;
		this.option = option;
	}
	@Override
	public void run() {
		if(option.equals(Constants.OPTION_2)||option.equals(Constants.OPTION_3)||
				option.equals(Constants.OPTION_5)||option.equals(Constants.OPTION_6)) {
			for(long value:generatedArray) {
				ll.iterativeAdd(value);
			}
		}
		Timekeeper a = new Timekeeper();
		a.start();
		long start = System.currentTimeMillis();
		switch (option) {
		case Constants.OPTION_1:
			for(long value:generatedArray) {
				ll.iterativeAdd(value);
			}
			break;
		case Constants.OPTION_2:
			for(long value:generatedArraySD) {
				ll.iterativeSearch(value);
			}
			break;
		case Constants.OPTION_3:
			for(long value:generatedArraySD) {
				ll.iterativeDelete(value);
				System.out.println("a");
			}
			break;
		case Constants.OPTION_4:
			for(long value:generatedArray) {
				ll.recursiveAdd(value);
			}
			break;
		case Constants.OPTION_5:
			for(long value:generatedArraySD) {
				ll.recursiveSearch(value);
			}
			break;
		case Constants.OPTION_6:
			for(long value:generatedArraySD) {
				ll.recursiveDelete(value);
			}
			break;
		}
		System.out.println(a.getTimer());
		a.stopTimekeeper();
		int min = (int)(( (System.currentTimeMillis()-start) /1000)/60);
		int sec = (int)(((System.currentTimeMillis()-start)/1000)%60);
		System.out.println(min+" "+sec+" "+(System.currentTimeMillis()-start));
	}
}
