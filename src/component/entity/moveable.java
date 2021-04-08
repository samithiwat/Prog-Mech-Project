package component.entity;

public interface moveable {
	public static final int[] UP = {-1,0};
	public static final int[] DOWN = {1,0};
	public static final int[] LEFTUP = {-1,-1};
	public static final int[] RIGHTUP = {-1,1};
	public static final int[] LEFTDOWN = {0,-1};
	public static final int[] RIGHTDOWN  = {0,1};
	
	public void move(String cmd);
	
}
