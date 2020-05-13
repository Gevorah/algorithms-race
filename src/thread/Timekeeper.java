package thread;

public class Timekeeper extends Thread {
	private String timer;
	private boolean active;
	public Timekeeper() {
		this.timer = "00:00:000";
		this.active = true;
	}
	public void run() {
		int min=0,sec=0,millis=0;
		try {
			long a = System.currentTimeMillis();
			while(active) {
				Thread.sleep(4);
				millis+=5;
				if(millis==1000) {
					millis = 0;
					sec++;
					if(sec==60) {
						sec = 0;
						min++;
					}
				}
				timer = String.format("%02d:%02d:%d",min,sec,millis);
				System.out.println(millis+"   "+(System.currentTimeMillis()-a));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	public void stopTimekeeper() {
		active = false;
	}
	public String getTimer() {
		return timer;
	}
	
}
