package update;

import java.util.ArrayList;



import character.BlackSkull;
import character.Collector;
import character.RedFox;
import character.Teewada;
import character.Teewadee;
import character.ThousandYear;
import gui.GameLobbyMenu;
import gui.StartMenu;
import gui.entity.CharacterCard;
import gui.entity.CharacterSetting;
import gui.entity.TextTitle;
import gui.overlay.CharacterSelectOverlay;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameController;

public class CharacterSelectUpdate implements Updateable{
	
	private static Thread t;
	private static int cBoxId;
	private static ArrayList<CharacterCard> cc = new ArrayList<CharacterCard>();
	
	public static int getcBoxId() {
		return cBoxId;
	}

	public static void setcBoxId(int cBoxId) {
		CharacterSelectUpdate.cBoxId = cBoxId;
	}

	public static ArrayList<CharacterCard> getCc() {
		return cc;
	}

	public static void mouseEnteredUpdate(CharacterCard cc) {
		cc.setCursor(MOUSE_SELECT);
		cc.setId("character-card-hold");
		GameLobbyMenu.getBGM().stop();
		cc.getSoundEffect().play();
		t = new Thread(()->{
			try {
				Thread.sleep(5000);
			}
			catch(InterruptedException e) {
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
	
	public static void closeUpdate() {
		for(int i =0;i<6;i++) {
			cc.get(i).setDisable(true);
		}
		Thread t = new Thread(()->{
			try {
				Thread.sleep(900);
			}
			catch(InterruptedException e) {
			
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					for(int i =0;i<6;i++) {
						cc.get(i).setDisable(false);
					}
				}
			});
		});
		t.start();
	}	
	
	public static void selectCharacterUpdate(int id) {
		//System.out.println("select character update, id : "+id);
		System.out.println(cc);
		System.out.println("id : "+ id);
		switch(id) {
		case 0:
			GameController.gameCharacter.set(cBoxId, new RedFox());
			cc.get(id).setDisable(true);
			cc.get(id).setId("character-box-selected");
			cc.get(id).getBg().setId("character-card-bg-selected");
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			setPortraits(id);
			break;
		case 1:
			GameController.gameCharacter.set(cBoxId, new Collector());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 2:
			GameController.gameCharacter.set(cBoxId, new BlackSkull());
			GameLobbyMenu.getOverlay1().triggerOverlay(0, 825, 1000);
			break;
		case 3:
			GameController.gameCharacter.set(cBoxId, new ThousandYear());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		case 4:
			GameController.gameCharacter.set(cBoxId, new Teewada());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		case 5:
			GameController.gameCharacter.set(cBoxId, new Teewadee());
			GameLobbyMenu.getOverlay2().triggerOverlay(0, 825, 1000);
			break;
		}
		System.out.println(cBoxId);
	}
	
	private static void setPortraits(int id) {
	 CharacterSetting cBox = GameLobbyMenu.getCBoxes().get(id);
	 ArrayList<TextTitle> texts = GameLobbyMenu.getCBoxes().get(id).getTexts();
	 
	 cBox.setSelected(true);
	 
	 switch(id) {
	 case 0:
		 texts.get(0).setVisible(false);
		 texts.get(1).setText("Mr.Red Fox");
		 texts.get(1).setX(47);
		 ImageView portraits = new ImageView(ClassLoader.getSystemResource("img/character/MrRedFox.png").toString());
		 portraits.setX(65);
		 portraits.setY(45);
		 portraits.setFitWidth(90);
		 portraits.setFitHeight(105);
		 cBox.getChildren().add(portraits);
		 cBox.getBg().get(0).setId("character-box-selected");
		 cBox.getBg().get(1).setVisible(true);
		 break;
	 }
	 

	}
	
}
