package component.entity;

import java.util.ArrayList;

import character.MainCharacter;
import component.location.Location;
import logic.GameSetUp;

public class Minion implements moveable {
	private MainCharacter possessedBy;
	private int posX;
	private int posY;
	private ArrayList<Minion> myMinion;
	private Location onLocation;
	private int moveLeft;

	public Minion(MainCharacter possessedBy) {
		this.possessedBy = possessedBy;
		possessedBy.addToMyEntity(this);
		myMinion = new ArrayList<Minion>();
		this.moveLeft = 2;
	}

	public void addMinion(Minion minion) {
		this.myMinion.add(minion);
		addGroupMinion(minion);
		minion.possessedBy.removeFromMyEntity(minion);
	}

	public void removeMinion(int index) {
		this.myMinion.remove(index);
	}

	public void addGroupMinion(Minion minion) {
		while (minion.myMinion.size() > 0) {
			this.myMinion.add(minion.myMinion.get(0));
			minion.myMinion.remove(0);
		}
	}

	public void returnThisToOwner() {
		this.possessedBy.addToMyEntity(this);
	}
// overwrite
	public void move(int x ,int y) {

		this.setPosX(this.getPosX()+x);
		this.setPosY(this.getPosY()+y);
		this.onLocation.removeFromLocation(this);
		GameSetUp.map[this.getPosX()][this.getPosY()].addMinonToLocation(this);
		
		
		this.moveLeft -= 1;
	}
	// ----------------------getter/setter--------------------------

	public MainCharacter getPossessedBy() {
		return possessedBy;
	}

	public void setPossessedBy(MainCharacter possessedBy) {
		this.possessedBy = possessedBy;
	}

	public ArrayList<Minion> getMyMinion() {
		return myMinion;
	}

	public void setMyMinion(ArrayList<Minion> myMinion) {
		this.myMinion = myMinion;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(int moveLeft) {
		this.moveLeft = moveLeft;
	}

	public Location getOnLocation() {
		return onLocation;
	}

	public void setOnLocation(Location onLocation) {
		this.onLocation = onLocation;
	}
	

}
// I'm not sure about this part :/