package character;

import component.entity.Minion;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Dummy_Government extends MainCharacter {

	public Dummy_Government() {
		super("Council", "");
		this.color = Color.web("0x183F21");
		this.setImg_path("img/card/CouncilCard.png");
		this.setPfp(new Image(ClassLoader.getSystemResource("img/card/CouncilCard.png").toString()));
		this.getMyEntity().add(new Minion(this));
	}

	public int checkIsWin() {
		return 0;
	}
}
