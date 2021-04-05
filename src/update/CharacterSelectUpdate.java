package update;

import java.util.ArrayList;

import character.BlackSkull;
import character.Collector;
import character.RedFox;
import character.Teewada;
import character.Teewadee;
import character.ThousandYear;
import gui.GameLobbyMenu;
import gui.entity.CharacterCard;
import gui.entity.CharacterSetting;
import gui.entity.TextTitle;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.GameController;

public class CharacterSelectUpdate implements Updateable {

	private static Thread t;
	private static CharacterSetting cBox;
	private static ImageView portraits;
	private static ArrayList<CharacterCard> cc = new ArrayList<CharacterCard>();
	private static ArrayList<TextTitle> overlayTexts = new ArrayList<TextTitle>();
	private static ArrayList<TextTitle> overlayInfoTexts = new ArrayList<TextTitle>();

// ------------------------------------------------ Getter and Setter ------------------------------------------------------------

	public static CharacterSetting getcBox() {
		return cBox;
	}

	public static void setcBox(CharacterSetting cBox) {
		CharacterSelectUpdate.cBox = cBox;
	}

	public static ArrayList<CharacterCard> getCc() {
		return cc;
	}

	public static ArrayList<TextTitle> getOverlayTexts() {
		return overlayTexts;
	}

// ------------------------------------------------ Set BGM When Mouse Enter -------------------------------------------------------

	public static void mouseEnteredUpdate(CharacterCard cc) {
		cc.setCursor(MOUSE_SELECT);
		cc.setId("character-card-hold");
		GameLobbyMenu.getBGM().stop();
		cc.getSoundEffect().play();
		t = new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				cc.getSoundEffect().stop();
				GameLobbyMenu.getBGM().play();
			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					cc.getSoundEffect().stop();
					GameLobbyMenu.getBGM().play();
				}
			});
		});
		t.start();
	}

	public static void mouseExitedUpdate(CharacterCard cc) {
		cc.setId("character-card");
		cc.setCursor(MOUSE_NORMAL);
		t.interrupt();
	}

// ------------------------------------------ Set Disable Duration When Click Close Icon For Prevent Sound Bug ----------------------------------------------

	public static void closeUpdate() {
		for (int i = 0; i < 6; i++) {
			cc.get(i).setDisable(true);
		}
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {

			}
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 6; i++) {
						cc.get(i).setDisable(false);
					}
				}
			});
		});
		t.start();
	}

// ------------------------------------------ Update Selected Character to Overlay and GameController ------------------------------------------

	public static void selectCharacterUpdate(int id) {
		System.out.println("id : " + id);
//		cc.get(id).setDisable(true);
//		cc.get(id).getSelectedText().setVisible(true);
//		overlayTexts.get(id).setFill(Color.web("0xA5A5A5"));

// ------------------------------------------ Update Change Character ----------------------------------------------------------------		

		if (cBox.isSelected()) {
			System.out.println("Enable : " + GameController.gameCharacter.get(cBox.getCBoxId()).getName());
			switch (GameController.gameCharacter.get(cBox.getCBoxId()).getName()) {
			case "Mr.RedFox":
				cc.get(0).setSelected(false);
				break;
			case "Ms.Collector":
				cc.get(1).setSelected(false);
				break;
			case "Mr.BlackSkull":
				cc.get(2).setSelected(false);
				break;
			case "Ms.ThousandYear":
				cc.get(3).setSelected(false);
				break;
			case "Teewada":
				cc.get(4).setSelected(false);
				break;
			case "Teewadee":
				cc.get(5).setSelected(false);
				break;
			}
		}

// ------------------------------------------------------- Update Status of Character Is He Selected ----------------------------------------
		
		cBox.setSelected(true);
		cc.get(id).setSelected(true);

		setPortraits(id);
		
// ------------------------------------------------------ Add Selected Character to Array in GameController ----------------------------------
		
		switch (id) {
		case 0:
			GameController.gameCharacter.set(cBox.getCBoxId(), new RedFox());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 1:
			GameController.gameCharacter.set(cBox.getCBoxId(), new Collector());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 2:
			GameController.gameCharacter.set(cBox.getCBoxId(), new BlackSkull());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 3:
			GameController.gameCharacter.set(cBox.getCBoxId(), new ThousandYear());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		case 4:
			GameController.gameCharacter.set(cBox.getCBoxId(), new Teewada());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		case 5:
			GameController.gameCharacter.set(cBox.getCBoxId(), new Teewadee());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		}
		closeUpdate();
		System.out.println("characterCard : " + cc);
		//System.out.println("gameCharacter : " + GameController.gameCharacter);
	}

// --------------------------------------------------------------- Update When Click Ready Button ---------------------------------------------

	public static void readyUpdate() {
		if (cBox.isSelected()) {
			cBox.setDisable(true);
		} else {
			AudioClip effect = AudioLoader.errorSound;
			effect.play();
		}
	}

// --------------------------------------------------------------- Update When Trigger Overlay ------------------------------------------------------

	public static void overlayUpdate() {
		for (int i = 0; i < 6; i++) {
			if (cc.get(i).isSelected()) {
				System.out.println("Disable "+ cc.get(i).toString());
				cc.get(i).setDisable(true);
				cc.get(i).getSelectedText().setVisible(true);
				overlayTexts.get(i).setFill(Color.web("0xA5A5A5"));
			}
			else {
				cc.get(i).setDisable(false);
				cc.get(i).getSelectedText().setVisible(false);
				overlayTexts.get(i).setFill(Color.WHITE);
			}
		}
	}

// --------------------------------------------------------------- Set Portraits For selected Character ---------------------------------------------

	private static void setPortraits(int id) {
		ArrayList<TextTitle> texts = cBox.getTexts();

		switch (id) {
		case 0:
			// --------------------------------------------------- Set Text -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Mr.Red Fox");
			texts.get(1).setX(47);
			// --------------------------------------------------- Set Character Image --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/MrRedFox.png").toString()));
			portraits.setVisible(true);
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 1:
			// --------------------------------------------------- Set Text -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Lady Collector");
			texts.get(1).setX(26);
			// --------------------------------------------------- Set Character Image --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(
					new Image(ClassLoader.getSystemResource("img/character/LadyCollectorFull.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/LadyCollectorFull.png").toString());
			portraits.setX(50);
			portraits.setY(45);
			portraits.setFitWidth(120);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;

		case 2:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Black Skull");
			texts.get(1).setX(46);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/BlackSkull.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/BlackSkull.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 3:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Sir Thousand Year");
			texts.get(1).setX(8);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits
					.setImage(new Image(ClassLoader.getSystemResource("img/character/SirThousandYear.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/SirThousandYear.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 4:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Sir Tewada");
			texts.get(1).setX(48);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/SirTewada.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/SirTewada.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		case 5:
			// --------------------------------------------------- Set Text
			// -------------------------------------------------------------
			texts.get(0).setVisible(false);
			texts.get(1).setText("Sir Tewadee");
			texts.get(1).setX(41);
			// --------------------------------------------------- Set Character Image
			// --------------------------------------------------
			portraits = cBox.getPortraits();
			portraits.setImage(new Image(ClassLoader.getSystemResource("img/character/SirTewadee.png").toString()));
			portraits.setVisible(true);
//			portraits = new ImageView(ClassLoader.getSystemResource("img/character/SirTewadee.png").toString());
			portraits.setX(65);
			portraits.setY(45);
			portraits.setFitWidth(90);
			portraits.setFitHeight(105);

//			cBox.getBgGroup().getChildren().add(portraits);

			cBox.getBg().get(0).setId("character-box-selected");
			cBox.getBg().get(1).setVisible(true);
			break;
		}

	}

}
