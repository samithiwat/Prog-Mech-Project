package gui;

import java.util.ArrayList;

import character.Collector;
import character.MainCharacter;
import component.entity.Minion;
import component.location.Prison;
import component.location.SecretBase;
import component.weaponCard.WeaponCard;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;
import logic.SceneController;
import update.AudioUpdate;

public class EndScene implements Sceneable {

	private ArrayList<MainCharacter> winnerList;
	private ArrayList<MainCharacter> loserList;

	private MainCharacter winner;
	private static MainCharacter coWinner;

	private Scene scene;
	private VBox infoPane;
	private VBox playerListPane;
	private VBox descriptionPane;
	private HBox root;

	private final Color REDFOX_BG_COLOR = Color.web("0xFFD0B3");
	private final Color COLLECTOR_BG_COLOR = Color.web("0x85C4D2");
	private final Color BLACKSKULL_BG_COLOR = Color.web("0x828D68");
	private final Color THOUSANDYEAR_BG_COLOR = Color.web("0xEBC0D0");
	private final Color TEWADA_BG_COLOR = Color.web("0xEFDEBC");
	private final Color TEWADEE_BG_COLOR = Color.web("0xFFB6B6");

	public EndScene(MainCharacter winner) {

		this.winner = winner;

		root = new HBox();
		root.setPadding(new Insets(50, 40, 0, 40));
		if (winner instanceof Collector) {
			root.setPadding(new Insets(50, 40, 0, 0));
		}
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER_LEFT);

		ImageView winnerPortraits = winner.getWinnerImg();

		createResultList();

		infoPane = new VBox();
		infoPane.setSpacing(80);
		infoPane.setAlignment(Pos.CENTER);

		descriptionPane = new VBox();
		descriptionPane.setSpacing(0);
		descriptionPane.setAlignment(Pos.TOP_CENTER);

		setUpDescriptionPane();

		playerListPane = new VBox();
		playerListPane.setSpacing(50);
		playerListPane.setAlignment(Pos.CENTER);

		setUpPlayerListPane();

		infoPane.getChildren().addAll(descriptionPane, playerListPane);

		root.getChildren().addAll(winnerPortraits, infoPane);

		scene = new Scene(root, FULLSCREEN_WIDTH, FULLSCREEN_HEIGHT);
		scene.setCursor(MOUSE_NORMAL);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ESCAPE) {
					AudioUpdate.changeEnv(StartMenu.getMenuThemeSong());
					SceneController.resetGame();
					SceneController.setScene((new MainMenu()).getScene());
				}
			}
		});
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

	private void createResultList() {
		winnerList = new ArrayList<MainCharacter>();
		loserList = new ArrayList<MainCharacter>();

		ArrayList<MainCharacter> players = GameSetUp.gameCharacter;

		switch (winner.getName()) {
		case "Mr.Red Fox":
			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						ArrayList<Minion> minions = player.getMyEntity();
						boolean isOnSecretBase = false;
						for (Minion minion : minions) {
							if (minion.getOnLocation() instanceof SecretBase) {
								loserList.add(player);
								isOnSecretBase = true;
								break;
							}
						}
						if (!isOnSecretBase) {
							winnerList.add(player);
						}
					}
				}
			}
			sortByMoney();
			break;
		case "Lady Collector":
			ArrayList<MainCharacter> maxMoneyPlayers = new ArrayList<MainCharacter>();
			maxMoneyPlayers.add(players.get(0));
			for (int i = 1; i < players.size(); i++) {
				MainCharacter player = players.get(i);
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						if (player.getMoney() > maxMoneyPlayers.get(0).getMoney()) {
							maxMoneyPlayers.clear();
							maxMoneyPlayers.add(player);
						} else if (player.getMoney() == maxMoneyPlayers.get(0).getMoney()) {
							maxMoneyPlayers.add(player);
						}
					}
				}
			}
			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						boolean isLoser = false;
						for (MainCharacter maxMoneyPlayer : maxMoneyPlayers) {
							if (player.getName().equals(maxMoneyPlayer.getName())) {
								isLoser = true;
								loserList.add(player);
								break;
							}
						}
						if (!isLoser) {
							winnerList.add(player);
						}
					}
				}
			}
			sortByGoodPoint();
			break;
		case "Black Skull":
			for (int i = 0; i < players.size(); i++) {
				MainCharacter player = players.get(i);
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						ArrayList<Minion> minions = player.getMyEntity();
						boolean isOnSecretBase = false;
						for (Minion minion : minions) {
							if (minion.getOnLocation() instanceof SecretBase) {
								winnerList.add(player);
								isOnSecretBase = true;
								break;
							}
						}
						if (!isOnSecretBase) {
							loserList.add(player);
						}
					}
				}
			}
			break;

		case "Sir Thousand Year":
			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						ArrayList<Minion> minions = player.getMyEntity();
						boolean isPrisoner = false;
						for (Minion minion : minions) {
							if (minion.getOnLocation() instanceof Prison) {
								if (coWinner != null) {
									if (player.getName().equals(coWinner.getName())) {
										coWinner = null;
									}
								}
								isPrisoner = true;
								loserList.add(player);
								break;
							}
						}
						if (!isPrisoner) {
							if (coWinner != null) {
								if (!player.getName().equals(coWinner.getName())) {
									winnerList.add(player);
								}
							} else {
								winnerList.add(player);
							}
						}
					}
				}
			}
			break;
		case "Sir Tewada":
			ArrayList<MainCharacter> maxAtkPlayers = new ArrayList<MainCharacter>();
			maxAtkPlayers.add(players.get(0));
			int maxTotalAtk = 0;
			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						int totalAtk = calTotalAtk(player);
						if (totalAtk > maxTotalAtk) {
							maxTotalAtk = totalAtk;
							maxAtkPlayers.clear();
							maxAtkPlayers.add(player);
						}
						if (totalAtk == maxTotalAtk) {
							maxAtkPlayers.add(player);
						}
					}
				}
			}

			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						boolean isLoser = false;
						for (MainCharacter maxAtkPlayer : maxAtkPlayers) {
							if (player.equals(maxAtkPlayer)) {
								isLoser = true;
								if (coWinner != null) {
									if (player.getName().equals(coWinner.getName())) {
										coWinner = null;
									}
								}
								loserList.add(player);
								break;
							}
						}
						if (!isLoser) {
							if (coWinner != null) {
								if (!player.getName().equals(coWinner.getName())) {
									winnerList.add(player);
								}
							} else {
								winnerList.add(player);
							}
						}
					}
				}
			}
			sortByTotalAtk();
			break;
		case "Sir Tewadee":
			ArrayList<MainCharacter> maxWeaponOnHandPlayers = new ArrayList<MainCharacter>();
			maxWeaponOnHandPlayers.add(players.get(0));
			int maxWeaponOnHand = players.get(0).getWeaponHand().size();
			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						int nWeaponOnHand = player.getWeaponHand().size();
						if (nWeaponOnHand > maxWeaponOnHand) {
							maxWeaponOnHandPlayers.clear();
							maxWeaponOnHandPlayers.add(player);
							maxWeaponOnHand = nWeaponOnHand;
						} else if (nWeaponOnHand == maxWeaponOnHand) {
							maxWeaponOnHandPlayers.add(player);
						}

					}
				}
			}

			for (MainCharacter player : players) {
				if (player != null) {
					if (!player.getName().equals(winner.getName())) {
						boolean isLoser = false;
						for (MainCharacter maxWeaponPlayer : maxWeaponOnHandPlayers) {
							if (player.equals(maxWeaponPlayer)) {
								isLoser = true;
								loserList.add(player);
								break;
							}
						}
						if (!isLoser) {
							winnerList.add(player);
						}
					}
				}
			}
			sortByNCard();
			break;
		}
	}

	private void sortByMoney() {

		for (int i = 1; i < winnerList.size(); i++) {
			MainCharacter currentCharacter = winnerList.get(i);
			double current = winnerList.get(i).getMoney();
			int j = i - 1;
			while (j >= 0 && current > winnerList.get(j).getMoney()) {
				winnerList.set(j + 1, winnerList.get(j));
				j--;
			}
			winnerList.set(j + 1, currentCharacter);
		}
	}

	private void sortByGoodPoint() {
		for (int i = 1; i < winnerList.size(); i++) {
			MainCharacter currentCharacter = winnerList.get(i);
			double current = winnerList.get(i).getGoodPoint();
			int j = i - 1;
			while (j >= 0 && current > winnerList.get(j).getGoodPoint()) {
				winnerList.set(j + 1, winnerList.get(j));
				j--;
			}
			winnerList.set(j + 1, currentCharacter);
		}
	}

	private void sortByTotalAtk() {
		for (int i = 1; i < winnerList.size(); i++) {
			MainCharacter currentCharacter = winnerList.get(i);
			double current = calTotalAtk(winnerList.get(i));
			int j = i - 1;
			while (j >= 0 && current < calTotalAtk(winnerList.get(j))) {
				winnerList.set(j + 1, winnerList.get(j));
				j--;
			}
			winnerList.set(j + 1, currentCharacter);
		}
	}

	private void sortByNCard() {
		for (int i = 1; i < winnerList.size(); i++) {
			MainCharacter currentCharacter = winnerList.get(i);
			double current = winnerList.get(i).getWeaponHand().size();
			int j = i - 1;
			while (j >= 0 && current < winnerList.get(j).getWeaponHand().size()) {
				winnerList.set(j + 1, winnerList.get(j));
				j--;
			}
			winnerList.set(j + 1, currentCharacter);
		}
	}

	private int calTotalAtk(MainCharacter player) {
		int totalAtk = 0;
		for (WeaponCard weapon : player.getWeaponHand()) {
			totalAtk += weapon.getAttack_max();
		}
		return totalAtk;
	}

	private void setUpDescriptionPane() {
		TextTitle quote = new TextTitle("", Color.WHITE, FontWeight.BOLD, 64);
		TextTitle loserCondition = new TextTitle("", COLOR_ENDSCENE_PLAYER_LIST, FontWeight.BOLD, 36);
		descriptionPane.getChildren().addAll(quote, loserCondition);
		switch (winner.getName()) {
		case "Mr.Red Fox":
			quote.setText("“With more money is better.”");
			loserCondition.setText("who is in secret base is the loser");
			root.setBackground(new Background(new BackgroundFill(REDFOX_BG_COLOR, null, null)));
			break;
		case "Lady Collector":
			quote.setText("“More people more power.”");
			loserCondition.setText("who has the most money is the loser");
			root.setBackground(new Background(new BackgroundFill(COLLECTOR_BG_COLOR, null, null)));
			break;
		case "Black Skull":
			quote.setText("“Military is the best choice.”");
			loserCondition.setText("who not in secret base is the loser");
			root.setBackground(new Background(new BackgroundFill(BLACKSKULL_BG_COLOR, null, null)));
			break;
		case "Sir Thousand Year":
			quote.setText("“More power is the best.”");
			loserCondition.setText("who is prisoner is the loser");
			root.setBackground(new Background(new BackgroundFill(THOUSANDYEAR_BG_COLOR, null, null)));
			break;
		case "Sir Tewada":
			quote.setText("“Stable of power is the best.”");
			loserCondition.setText("who has the most weapon atk in hand is the loser");
			root.setBackground(new Background(new BackgroundFill(TEWADA_BG_COLOR, null, null)));
			break;
		case "Sir Tewadee":
			quote.setText("“I am your master.”");
			loserCondition.setText("who has the most weapon in hand is the loser");
			root.setBackground(new Background(new BackgroundFill(TEWADEE_BG_COLOR, null, null)));
			break;
		}
	}

	private void setUpPlayerListPane() {

		if (coWinner != null) {
			HBox playerInfo = new HBox();
			playerInfo.setAlignment(Pos.CENTER_LEFT);
			playerInfo.setPadding(new Insets(0, 0, 0, 215));
			playerInfo.setSpacing(100);
			playerInfo.setMinWidth(150);

			ImageView icon = new ImageView(ClassLoader.getSystemResource("img/icon/coWinnerIcon.png").toString());
			TextTitle playerName = new TextTitle(coWinner.getName(), COLOR_ENDSCENE_PLAYER_LIST, FontWeight.BOLD, 36);

			playerInfo.getChildren().addAll(icon, playerName);
			playerListPane.getChildren().add(playerInfo);
		}
		for (MainCharacter winner : winnerList) {
			HBox playerInfo = new HBox();
			playerInfo.setAlignment(Pos.CENTER_LEFT);
			playerInfo.setPadding(new Insets(0, 0, 0, 215));
			playerInfo.setSpacing(100);
			playerInfo.setMinWidth(150);

			ImageView icon = new ImageView(ClassLoader.getSystemResource("img/icon/winnerIcon.png").toString());
			TextTitle playerName = new TextTitle(winner.getName(), COLOR_ENDSCENE_PLAYER_LIST, FontWeight.BOLD, 36);

			playerInfo.getChildren().addAll(icon, playerName);
			playerListPane.getChildren().add(playerInfo);
		}
		for (MainCharacter loser : loserList) {
			HBox playerInfo = new HBox();
			playerInfo.setAlignment(Pos.CENTER_LEFT);
			playerInfo.setPadding(new Insets(0, 0, 0, 215));
			playerInfo.setSpacing(100);
			playerInfo.setMinWidth(150);

			ImageView icon = new ImageView(ClassLoader.getSystemResource("img/icon/loserIcon.png").toString());
			TextTitle playerName = new TextTitle(loser.getName(), COLOR_ENDSCENE_PLAYER_LIST, FontWeight.BOLD, 36);

			playerInfo.getChildren().addAll(icon, playerName);
			playerListPane.getChildren().add(playerInfo);
		}
	}

// -------------------------------- Getter and Setter --------------------------------------

	public static MainCharacter getCoWinner() {
		return coWinner;
	}

	public static void setCoWinner(MainCharacter coWinner) {
		EndScene.coWinner = coWinner;
	}

}
