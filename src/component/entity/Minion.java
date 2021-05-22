package component.entity;

import java.util.ArrayList;

import character.BlackSkull;
import character.MainCharacter;
import component.Component;
import component.location.Location;
import component.location.Ocean;
import component.location.Prison;
import component.location.SecretBase;
import component.location.Water;
import exception.ExceedMinionInTileException;
import exception.InvalidActionException;
import exception.InvalidOwnershipException;
import exception.LackOfMoneyException;
import exception.OutOfActionException;
import exception.SupportArmyException;
import exception.TooFarException;
import exception.WaterTileException;
import gui.entity.HexagonPane;
import javafx.scene.paint.Color;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class Minion extends Component implements moveable {
	public static final int COST = 3 * MainCharacter.M;
	public static final int MAX_MINION = 6;

	private MainCharacter possessedBy;
	private int posX;
	private int posY;
	private int prisonerNumber;
	private ArrayList<Minion> myMinion;
	private Location onLocation;
	private int moveLeft;

	public Minion(MainCharacter possessedBy) {
		super("");
		this.possessedBy = possessedBy;
		possessedBy.addToMyEntity(this);
		myMinion = new ArrayList<Minion>();
		this.moveLeft = 2;
		this.prisonerNumber = -1;
	}

	public void addMinion(Minion minion) {
		this.myMinion.add(minion);
		addGroupMinion(minion);
		minion.getOnLocation().removeFromLocation(minion);
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

	public void returnToOwner() {
		for (int i = 0; i < myMinion.size(); i++) {
			myMinion.get(i).setOnLocation(null);
		}
		myMinion.clear();
	}

// overwrite
	public void move(int x, int y) throws WaterTileException, OutOfActionException, InvalidOwnershipException,
			TooFarException, LackOfMoneyException, SupportArmyException {

		HexagonPane tile = GameSetUp.selectedTile;

		if (this.possessedBy != GameSetUp.thisTurn) {
			throw new InvalidOwnershipException();
		}

		else if (this.moveLeft == 0) {
			throw new OutOfActionException();
		}

		else if (tile.getLocationType() instanceof Water) {
			throw new WaterTileException();
		} else if (!(tile.isMoveable())) {
			throw new TooFarException();
		} else if (GameSetUp.gameLaw.taxPerTile) {
			if (GameSetUp.thisTurn.getMoney() < MainCharacter.M) {
				throw new LackOfMoneyException();
			}
			GameSetUp.thisTurn.setMoney(GameSetUp.thisTurn.getMoney() - MainCharacter.M);
		} else if (GameSetUp.gameLaw.supportArmy) {
			if (onLocation instanceof SecretBase) {
				throw new SupportArmyException();
			}
		}

		this.setPosX(this.getPosX() + x);
		this.setPosY(this.getPosY() + y);
		this.onLocation.removeFromLocation(this);
		if (this.onLocation.getName().equals("SecretBase")) {
			this.getPossessedBy().getPossessedArea().remove(this.onLocation);
		}
		setOnLocation(GameSetUp.map[this.getPosY()][this.getPosX()]);
		this.onLocation.addMinionToLocation(this);
		if (this.onLocation.getName().equals("SecretBase")) {
			this.getPossessedBy().getPossessedArea().add(this.onLocation);
		}
		this.moveLeft -= 1;
		if (GameSetUp.blackSkull != null) {
			GameSetUp.blackSkull.checkIsWin();
		}
	}
// -------------------------------------------------- Jail Method -------------------------------------------------------

	public void jailed() throws ExceedMinionInTileException, OutOfActionException, InvalidActionException {
		// now jail all of minion in group

		if (!Prison.canCapture) {
			throw new OutOfActionException();
		}

		if (Prison.minionInPrison.size() >= 6) {
			throw new ExceedMinionInTileException();
		}
		
		if(this.possessedBy == GameSetUp.theGovernment) {
			throw new InvalidActionException();
		}

		Prison.canCapture = false;
		onLocation.removeFromLocation(this);
		onLocation = GameSetUp.prison;
		Prison.addToPrison(this);
		this.prisonerNumber = Prison.minionInPrison.size() - 1;
		if (GameSetUp.sirThousand != null) {
			GameSetUp.sirThousand.checkIsWin();
		}
	}

	public void ransom() throws LackOfMoneyException {
		if (!GameSetUp.thisTurn.equals(GameSetUp.theGovernment)) {

			if (GameSetUp.thisTurn.getMoney() < Prison.PLEDGE) {
				throw new LackOfMoneyException();
			}
			
			if(!(GameSetUp.thisTurn instanceof BlackSkull) || !BlackSkull.prisonOutBreakSkill) {
				GameSetUp.thisTurn.setMoney(GameSetUp.thisTurn.getMoney() - Prison.PLEDGE);				
			}
			else {
				PlayerPanelUpdate.setShowMessage("Prison Break!! (Black Skull Skill)", Color.web("0xFEFDE8"), 90, 2000);
				BlackSkull.prisonOutBreakSkill = false;
			}
			
		}

		Prison.minionInPrison.remove(prisonerNumber);
		returnToOwner();
		prisonerNumber = -1;
		onLocation = null;
		updateMinionLeft();
	}

	private void updateMinionLeft() {
		for (int i = 0; i < GameSetUp.gameCharacter.size(); i++) {
			if (GameSetUp.gameCharacter.get(i) != null) {
				GameSetUp.gameCharacter.get(i).updateMinionLeft();
			}
		}
	}

// ------------------------------------------------------ Pardon Method -------------------------------------------------------------

	public void exiled() throws OutOfActionException, ExceedMinionInTileException {
		if (Ocean.banishedMinion.size() >= 6) {
			throw new ExceedMinionInTileException();
		}

		Ocean.canPardon = false;
		onLocation.removeFromLocation(this);
		onLocation = GameSetUp.ocean;
		Ocean.addToOcean(this);
		this.prisonerNumber = Ocean.banishedMinion.size() - 1;
	}

	public void pardon() throws OutOfActionException {

		if (!Ocean.canPardon) {
			throw new OutOfActionException();
		}

		Ocean.banishedMinion.remove(prisonerNumber);
		returnToOwner();
		prisonerNumber = -1;
		onLocation = null;
		updateMinionLeft();

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
		if (myMinion.size() > 0) {
			for (Minion minion : myMinion) {
				minion.setOnLocation(onLocation);
			}
		}
		this.onLocation = onLocation;
	}

	public static double getCost() {
		return COST;
	}
}