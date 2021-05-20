package gui.overlay;

import java.util.ArrayList;

import character.MainCharacter;
import gui.MapOverview;
import gui.entity.MenuIcon;
import gui.entity.PlayerActionMenu;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import update.GameSettingUpdate;
import update.TradeOverlayUpdate;

public class PlayerListPage2 extends Overlay {

	protected static final int HEIGHT = 800;
	protected static final int WIDTH = 1400;
	protected static final int PORTRAITS_WIDTH = 320;
	protected static final int PORTRAITS_HEIGHT = 400;

	private ArrayList<TextTitle> allText;
	private PlayerListPage2 instance = this;

	public PlayerListPage2() {
		super((new Pane()), WIDTH, HEIGHT, 75, -800);

		setId("overlay");
		prefHeight(HEIGHT);
		prefWidth(WIDTH);

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(BG_COLOR);
		bg.setId("overlay-bg");

		TextTitle title = new TextTitle("Player List", Color.WHITE, FontWeight.BOLD, 72, 512, 110);

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

		MenuIcon changePageIcon = new MenuIcon("img/icon/Arrow.png", 30, 710);
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

		HBox imgPane = new HBox();
		imgPane.setLayoutY(164);
		imgPane.setSpacing(130);
		imgPane.setPadding(new Insets(0, 90, 0, 90));
		HBox playerStatusPane = new HBox();
		playerStatusPane.setAlignment(Pos.CENTER);
		playerStatusPane.setLayoutY(584);
		playerStatusPane.setSpacing(110);
		playerStatusPane.setPadding(new Insets(0, 70, 0, 70));

		allText = new ArrayList<TextTitle>();
		int n = GameSettingUpdate.getNPlayer();
		for (int i = 3; i < n; i++) {
			HBox moneyInfo = new HBox();
			moneyInfo.setSpacing(30);
			moneyInfo.setAlignment(Pos.CENTER);
			HBox minionInfo = new HBox();
			minionInfo.setAlignment(Pos.CENTER);
			minionInfo.setSpacing(30);
			HBox landInfo = new HBox();
			landInfo.setAlignment(Pos.CENTER);
			landInfo.setSpacing(30);
			HBox goodPointInfo = new HBox();
			goodPointInfo.setAlignment(Pos.CENTER);
			goodPointInfo.setSpacing(30);
			HBox etcInfo = new HBox();
			etcInfo.setSpacing(40);
			VBox info = new VBox();
			info.setAlignment(Pos.CENTER);
			info.setSpacing(30);
			MainCharacter character = GameSetUp.gameCharacter.get(i);
			StackPane img = createPortraits(character.getImg_path());
			ImageView money = new ImageView(ClassLoader.getSystemResource("img/icon/GoldIngot.png").toString());
			ImageView minion = new ImageView(ClassLoader.getSystemResource("img/icon/FoxMinion.png").toString());
			ImageView land = new ImageView(ClassLoader.getSystemResource("img/icon/LandIcon.png").toString());
			ImageView goodPoint = new ImageView(ClassLoader.getSystemResource("img/icon/GoodPointIcon.png").toString());

			TextTitle money_text = new TextTitle(character.getMoney() / MainCharacter.M + "M", Color.WHITE,
					FontWeight.BOLD, 36, 0, 0);
			TextTitle minion_text = new TextTitle(character.getMyEntity().size() + "", Color.WHITE, FontWeight.BOLD, 36,
					0, 0);
			TextTitle land_text = new TextTitle(character.getArea() + "", Color.WHITE, FontWeight.BOLD, 36, 0, 0);
			TextTitle goodPoint_text = new TextTitle(character.getArea() + "", Color.WHITE, FontWeight.BOLD, 36, 0, 0);

			moneyInfo.getChildren().addAll(money, money_text);
			minionInfo.getChildren().addAll(minion, minion_text);
			landInfo.getChildren().addAll(land, land_text);
			goodPointInfo.getChildren().addAll(goodPoint, goodPoint_text);
			etcInfo.getChildren().addAll(minionInfo, landInfo, goodPointInfo);
			info.getChildren().addAll(moneyInfo, etcInfo);
			playerStatusPane.getChildren().add(info);

			img.setOnMouseEntered((MouseEvent event) -> {
				// set cursor
				setCursor(new ImageCursor(
						(new Image(ClassLoader.getSystemResource("img/icon/TalkingCursor.png").toString()))));
				EFFECT_MOUSE_CLICK.play();
				img.setBackground(new Background(new BackgroundFill(Color.web("0x393E46"), new CornerRadii(20), null)));
			});

			img.setOnMouseExited((MouseEvent event) -> {
				// set cursor back
				setCursor(MOUSE_NORMAL);
				img.setBackground(new Background(new BackgroundFill(Color.web("0xFEFDE8"), new CornerRadii(20), null)));
			});

			PlayerActionMenu playerActionMenu = new PlayerActionMenu();
			img.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

				@Override
				public void handle(ContextMenuEvent event) {
					// TODO Auto-generated method stub
					GameSetUp.selectedCharacter = character;
					if (GameSetUp.isFightTradeMode) {
						TradeOverlayUpdate.traded = character;
					}
					playerActionMenu.getBuyLand().setVisible(false);
					playerActionMenu.getCombine().setVisible(false);
					playerActionMenu.getBuyMinion().setVisible(false);
					playerActionMenu.getSplit().setVisible(false);
					playerActionMenu.getAddGoodPoint().setVisible(false);
					playerActionMenu.getReduceGoodPoint().setVisible(false);
					playerActionMenu.getTrade().setVisible(true);
					if (GameSetUp.thisTurn == character || character.isTraded()
							|| TradeOverlayUpdate.trader == character) {
						playerActionMenu.getTrade().setVisible(false);
					}
					if (GameSetUp.isFightTradeMode == true && TradeOverlayUpdate.trader != character
							&& character.isFightTraded() == false) {
						playerActionMenu.getTrade().setVisible(true);
					}
					if (GameSetUp.thisTurn == GameSetUp.theGovernment) {
						if (character.getGoodPoint() < 5) {
							if (character != GameSetUp.theGovernment) {
								playerActionMenu.getAddGoodPoint().setVisible(true);
							}
						}
					}
					if (GameSetUp.thisTurn == GameSetUp.theGovernment) {

						if (character.getGoodPoint() > 0) {
							if (character != GameSetUp.theGovernment) {
								playerActionMenu.getReduceGoodPoint().setVisible(true);
							}
						}
					}
					playerActionMenu.show(instance, event.getSceneX(), event.getSceneY());
				}
			});

			allText.add(money_text);
			allText.add(minion_text);
			allText.add(land_text);
			allText.add(goodPoint_text);

			imgPane.getChildren().add(img);

		}
		root.getChildren().addAll(bg, title, playerStatusPane, imgPane, closeIcon, changePageIcon);
	}

	// --------------------------------------------- Private Method
	// -------------------------------------------------------

	private StackPane createPortraits(String img_path) {
		ImageView img = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		StackPane imgRoot = new StackPane(img);
		imgRoot.setPrefWidth(PORTRAITS_WIDTH);
		imgRoot.setPrefHeight(PORTRAITS_HEIGHT);
		imgRoot.setAlignment(Pos.CENTER);
		imgRoot.setBackground(new Background(new BackgroundFill(Color.web("0xFEFDE8"), new CornerRadii(20), null)));
		return imgRoot;
	}

	// -------------------------------------------------------getter/setter-------------------------------------------------

	public ArrayList<TextTitle> getAllText() {
		return allText;
	}

	public void setAllText(ArrayList<TextTitle> allText) {
		this.allText = allText;
	}

}
