package gui.overlay;

import java.util.ArrayList;

import character.MainCharacter;
import gui.GameLobbyMenu;
import gui.MapOverview;
import gui.entity.Clickable;
import gui.entity.MenuIcon;
import gui.entity.PlayerActionMenu;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import update.GameSettingUpdate;
import update.TradeOverlayUpdate;

public class PlayerList2 extends Overlay {

	protected static final int HEIGHT = 800;
	protected static final int WIDTH = 1400;

	private ArrayList<TextTitle> allText;
	private PlayerList2 instance = this;

	public PlayerList2() {
		super((new Pane()), WIDTH, HEIGHT, 75, -800);

		setId("overlay");
		prefHeight(HEIGHT);
		prefWidth(WIDTH);

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(Color.web("0x393E46"));
		bg.setId("overlay-bg");

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1300, 50);
		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				for (int i = 0; i < MapOverview.allPlayerList1.size(); i++) {
					MapOverview.allPlayerList2.get(i).triggerOverlay(0, 825, 1000);
				}
				GameSetUp.isFightTradeMode = false;
			}
		});

		MenuIcon changePageIcon = new MenuIcon("img/icon/Arrow.png", 30, 700);
		changePageIcon.setRotate(180);
		changePageIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				for (int i = 0; i < MapOverview.allPlayerList1.size(); i++) {
					MapOverview.allPlayerList1.get(i).triggerOverlay(0, 825, 500);
					MapOverview.allPlayerList2.get(i).triggerOverlay(0, 825, 500);
				}
			}
		});

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(120, 95, 60, 88));
		grid.setVgap(40);
		grid.setHgap(45);

		allText = new ArrayList<TextTitle>();
		int n = GameSettingUpdate.getNPlayer();
		for (int i = 3; i < n; i++) {
			System.out.println(i);
			System.out.println(n);
			int j = 7 * (i - 3);
			MainCharacter character = GameSetUp.gameCharacter.get(i);
			ImageView img = new ImageView(ClassLoader.getSystemResource(character.getImg_path()).toString());
			ImageView money = new ImageView(ClassLoader.getSystemResource("img/icon/Coin.png").toString());
			ImageView minion = new ImageView(ClassLoader.getSystemResource("img/icon/FoxMinion.png").toString());
			ImageView land = new ImageView(ClassLoader.getSystemResource("img/icon/House1.png").toString());

			TextTitle money_text = new TextTitle(character.getMoney() / MainCharacter.M + "M", Color.WHITE,
					FontWeight.BOLD, 50, 0, 0);
			TextTitle minion_text = new TextTitle(character.getMyEntity().size() + "", Color.WHITE, FontWeight.BOLD, 50,
					0, 0);
			TextTitle land_text = new TextTitle(character.getArea() + "", Color.WHITE, FontWeight.BOLD, 50, 0, 0);

			img.setOnMouseEntered((MouseEvent event) -> {
				// set cursor
				setCursor(new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/TalkingCursor.png").toString()))));
				Clickable.EFFECT_MOUSE_CLICK.play();
				img.setEffect(new DropShadow());
			});

			img.setOnMouseExited((MouseEvent event) -> {
				// set cursor back
				setCursor(Clickable.MOUSE_NORMAL);
				img.setEffect(null);
			});
			
			PlayerActionMenu playerActionMenu = new PlayerActionMenu();
			img.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

				@Override
				public void handle(ContextMenuEvent event) {
					// TODO Auto-generated method stub
					GameSetUp.selectedCharacter = character;
					if(GameSetUp.isFightTradeMode) {
						TradeOverlayUpdate.traded = character;
					}
					playerActionMenu.getBuyLand().setVisible(false);
					playerActionMenu.getCombine().setVisible(false);
					playerActionMenu.getBuyMinion().setVisible(false);
					playerActionMenu.getSplit().setVisible(false);
					playerActionMenu.getTrade().setVisible(true);
					if(GameSetUp.thisTurn == character || character.isTraded()) {
						playerActionMenu.getTrade().setVisible(false);
					}
					if(GameSetUp.isFightTradeMode == true && TradeOverlayUpdate.trader != character && character.isFightTraded() == false) {
						playerActionMenu.getTrade().setVisible(true);
					}
					playerActionMenu.show(instance, event.getSceneX(), event.getSceneY());
					// no update yet, will write it later
				}
			});
			   
			allText.add(money_text);
			allText.add(minion_text);
			allText.add(land_text);

			grid.add(img, j, 0, 4, 1);
			grid.add(money, j, 1, 2, 1);
			grid.add(money_text, j + 2, 1, 2, 1);
			grid.add(minion, j, 2);
			grid.add(minion_text, j + 1, 2);
			grid.add(land, j + 2, 2);
			grid.add(land_text, j + 3, 2);

		}
		root.getChildren().addAll(bg, grid, closeIcon, changePageIcon);
	}

	// -------------------------------------------------------getter/setter-------------------------------------------------

	public ArrayList<TextTitle> getAllText() {
		return allText;
	}

	public void setAllText(ArrayList<TextTitle> allText) {
		this.allText = allText;
	}

}
