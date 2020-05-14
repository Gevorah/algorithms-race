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
			while(active) {
				Thread.sleep(4);
				//millis+=4 is slow than the currentTimeMillis method 
				millis+=5;
				if(millis==1000) {
					millis = 0;
					sec++;
					if(sec==60) {
						sec = 0;
						min++;
					}
				}
				timer = String.format("%02d:%02d:%03d",min,sec,millis);
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
