package component.location;

import character.MainCharacter;
import logic.GameSetUp;

public class Council extends Location {
	public static int beginCycle;

	public Council() {
		super("Council", "Publish laws", 0, 0);
	}

	public void changeTheGovernment(MainCharacter character) {
		GameSetUp.theGovernment = character;
		beginCycle = GameSetUp.cycle;
	}

	public static int howLong() {
		return GameSetUp.cycle - beginCycle;
	}
}
