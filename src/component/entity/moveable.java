package component.entity;

import exception.InvalidOwnershipException;
import exception.OutOfActionException;
import exception.TooFarException;
import exception.WaterTileException;

public interface moveable {
	
	public final static int[][] DIRECTION = {{0,0,-1,-1,-1,0,-1,1,0,-1,0,1,1,0},{0,0,0,-1,-1,0,0,1,1,-1,1,0,1,1}};
	
	public void move(int x ,int y) throws WaterTileException, OutOfActionException, InvalidOwnershipException, TooFarException;
	
}
