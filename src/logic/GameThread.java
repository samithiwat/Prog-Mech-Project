package logic;

public class GameThread implements Runnable{

	private boolean isKilled;
	private Thread t ;
	
	public GameThread() {
		t = new Thread(this);
		isKilled = false;
		t.start();
	}
	
	@Override
	public void run() {
		
	}
	
	public void stop() {
		isKilled = true;
	}
	
}
