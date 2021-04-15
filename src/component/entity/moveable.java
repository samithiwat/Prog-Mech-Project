package component.entity;

public interface moveable {
	
	public final static int[][] DIRECTION = {{0,0,-1,-1,-1,0,-1,1,0,-1,0,1,1,0},{0,0,0,-1,-1,0,0,1,1,-1,1,0,1,1}};
	
	public void move(int x ,int y);
	
}
