package gui.overlay;

import component.law.BanArWut;
import component.law.PaSeeArWut;
import component.weaponCard.Axe;
import component.weaponCard.Bow;
import component.weaponCard.Gun;
import component.weaponCard.Shield;
import component.weaponCard.Sword;
import gui.entity.LawCardIcon;
import gui.entity.MenuIcon;
import gui.entity.StatusPane;
import gui.entity.TextTitle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

	private LawCardIcon sword;
	private LawCardIcon axe;
	private LawCardIcon bow;
	private LawCardIcon gun;
	private LawCardIcon shield;

	public SelectWeaponOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -850);
		setCursor(CURSOR_NORMAL);

// ---------------------------------------------------- Scene Background ----------------------------------------------------

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(BG_COLOR);

		weaponList = new HBox();
		weaponList.setSpacing(30);
		weaponList.setPadding(new Insets(104, 70, 104, 70));

		sword = new LawCardIcon(null);
		axe = new LawCardIcon(null);
		bow = new LawCardIcon(null);
		gun = new LawCardIcon(null);
		shield = new LawCardIcon(null);

		weaponList.getChildren().addAll(sword, axe, bow, gun, shield);

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
				StatusPane.triggerSelectWeapon();
			}
		});

// ---------------------------------------------- Add Overlay's Component to Root -------------------------------------------

		root.getChildren().addAll(bg, weaponListRoot, title, closeIcon);
	}

	public void updateWeaponList(int mode) {

		ObservableList<Node> allWeapon = weaponList.getChildren();
		for (int i = allWeapon.size() - 1; i >= 0; i--) {
			allWeapon.remove(i);
		}

		// ---------------------- Mode ------------------------
		// 0 = Ban Weapon
		// 1 = Weapon Tax
		// ----------------------------------------------------

		if (mode == 0) {

			sword = new LawCardIcon(new BanArWut(new Sword()));
			axe = new LawCardIcon(new BanArWut(new Axe()));
			bow = new LawCardIcon(new BanArWut(new Bow()));
			gun = new LawCardIcon(new BanArWut(new Gun()));
			shield = new LawCardIcon(new BanArWut(new Shield()));

			weaponList.getChildren().addAll(sword, axe, bow, gun, shield);

		}

		if (mode == 1) {

			sword = new LawCardIcon(new PaSeeArWut(new Sword()));
			axe = new LawCardIcon(new PaSeeArWut(new Axe()));
			bow = new LawCardIcon(new PaSeeArWut(new Bow()));
			gun = new LawCardIcon(new PaSeeArWut(new Gun()));
			shield = new LawCardIcon(new PaSeeArWut(new Shield()));

			weaponList.getChildren().addAll(sword, axe, bow, gun, shield);

		}
	}

// --------------------------------------------- Getter and Setter ------------------------------------------------

	public HBox getWeaponList() {
		return weaponList;
	}
	@Override
	public String toString() {
		return "----------------- Select Weapon ---------------\n" 
				+ "Weapon List\n"
				 + this.weaponList.getChildren()
				+ "\n" + "----------------------------------------------";
	}
}
