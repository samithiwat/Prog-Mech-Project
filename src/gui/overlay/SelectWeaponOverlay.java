package gui.overlay;

import component.law.BanArWut;
import component.weaponCard.Axe;
import component.weaponCard.Bow;
import component.weaponCard.Gun;
import component.weaponCard.Shield;
import component.weaponCard.Sword;
import gui.entity.LawCardIcon;
import gui.entity.MenuIcon;
import gui.entity.StatusPane;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;

public class SelectWeaponOverlay extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int WEAPON_LIST_ROOT_WIDTH = 1260;
	private static final int WEAPON_LIST_ROOT_HEIGHT = 500;

	private HBox weaponList;

	public SelectWeaponOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -850);
		setCursor(CURSOR_NORMAL);

// ---------------------------------------------------- Scene Background ----------------------------------------------------

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(BG_COLOR);

		weaponList = new HBox();
		weaponList.setSpacing(30);
		weaponList.setPadding(new Insets(104, 70, 104, 70));

		StackPane weaponListRoot = new StackPane(weaponList);
		weaponListRoot.setId("law-overlay-bg");
		weaponListRoot.setPrefWidth(WEAPON_LIST_ROOT_WIDTH);
		weaponListRoot.setPrefHeight(WEAPON_LIST_ROOT_HEIGHT);
		weaponListRoot.setAlignment(Pos.CENTER);
		weaponListRoot.setLayoutX(70);
		weaponListRoot.setLayoutY(200);

// ---------------------------------------------------------- Title --------------------------------------------------------

		TextTitle title = new TextTitle("Select Weapon", Color.WHITE, FontWeight.BOLD, 96, 356, 135);
		
// ------------------------------------------ Close Icon -------------------------------------------------------

				MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1311, 45);

				closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						EFFECT_MOUSE_CLICK.play();
						triggerOverlay(0, 875, 1500);
					}
				});

// ---------------------------------------------- Add Overlay's Component to Root -------------------------------------------

		root.getChildren().addAll(bg, weaponListRoot, title,closeIcon);
	}

	public void updateWeaponList(int mode) {
		
		weaponList.getChildren().clear();
		
		// ---------------------- Mode ------------------------
		// 0 = Ban Weapon
		// 1 = Weapon Tax
		// ----------------------------------------------------

		 if(mode == 0) {
			 
			 LawCardIcon sword = new LawCardIcon("img/card/BanWeaponSword.png", new Sword());
			 LawCardIcon axe = new LawCardIcon("img/card/BanWeaponAxe.png", new Axe());
			 LawCardIcon bow = new LawCardIcon("img/card/BanWeaponBow.png", new Bow());
			 LawCardIcon gun = new LawCardIcon("img/card/BanWeaponGun.png", new Gun());
			 LawCardIcon shield = new LawCardIcon("img/card/BanWeaponShield.png", new Shield());
			 
			 weaponList.getChildren().addAll(sword, axe, bow, gun, shield);
			 
		 }

		if(mode == 1) {
			
			LawCardIcon sword = new LawCardIcon("img/card/WeaponTaxSword.png", new Sword());
			LawCardIcon axe = new LawCardIcon("img/card/WeaponTaxAxe.png", new Axe());
			LawCardIcon bow = new LawCardIcon("img/card/WeaponTaxBow.png", new Bow());
			LawCardIcon gun = new LawCardIcon("img/card/WeaponTaxGun.png", new Gun());
			LawCardIcon shield = new LawCardIcon("img/card/WeaponTaxShield.png", new Shield());

			weaponList.getChildren().addAll(sword, axe, bow, gun, shield);

		}
	}

}
