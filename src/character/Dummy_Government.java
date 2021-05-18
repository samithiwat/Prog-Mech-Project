package character;

import component.entity.Minion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.AudioLoader;
import logic.FightController;
import logic.GameSetUp;

public class Dummy_Government extends MainCharacter {
	
	public static final String IMG_PATH = "img/card/CouncilCard.png";
	
	public Dummy_Government() {
		super("Council", "");
		this.color = Color.web("0x183F21");
		this.setImg_path("img/card/CouncilCard.png");
		this.setPfp(new Image(ClassLoader.getSystemResource("img/card/CouncilCard.png").toString()));
		this.getMyEntity().add(new Minion(this));
	}

	public static void beingChallenged() {
		FightController.challenged_slot.add(GameSetUp.weaponDeck.drawCard());
		FightController.challenged_slot.add(GameSetUp.weaponDeck.drawCard());
	}
	
	@Override
	public int checkIsWin() {
		// TODO Auto-generated method stub
		return 0;
	}
}
