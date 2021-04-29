package gui.entity;

import character.MainCharacter;
import component.entity.Minion;
import component.location.Prison;
import exception.InvalidOwnershipException;
import exception.LackOfMoneyException;
import exception.OutOfActionException;
import exception.TooFarException;
import exception.WaterTileException;
import gui.MainIsland;
import gui.PrisonIsland;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class MinionIcon extends MenuIcon {

	public static final Effect SELECTED_EFFECT = new Bloom();

	private MinionList minionList;
	private Minion minion;
	private MinionIcon minionIcon = this;

	public MinionIcon(String img_path, int x, int y, Minion minion) {
		super(img_path, x, y);
		this.minion = minion;
		minionList = new MinionList(minion);
		moveMode();
	}

	@Override
	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				minionList.update();
				minionList.show(minionIcon, event.getScreenX() + 20, event.getScreenY() - 20);
				setCursor(MOUSE_SELECT);
				EFFECT_MOUSE_ENTER.play();
				if (getEffect() == null) {
					setEffect(new DropShadow());
				}
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				minionList.hide();
				setCursor(MOUSE_NORMAL);
				if (getEffect() instanceof DropShadow) {
					setEffect(null);
				}
			}
		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getButton().equals(MouseButton.SECONDARY)) {

					if (!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon, event.getScreenX(), event.getScreenY());
					} else {
						minionList.hide();
					}
				}

			}
		});
	}
// ---------------------------------------- Set Minion Icon Mode -------------------------------------------------

	public void moveMode() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					MainIsland.dataInteractMode();
					MainIsland.getInfoRoot().setVisible(false);
					GameSetUp.initialTile = GameSetUp.selectedTile;
					GameSetUp.initialTile.triggerOverlay();
					GameSetUp.initialTile.highlight();
					GameSetUp.selectedTile = null;
					Thread confirmMove = new Thread(() -> {
						while (true) {
							System.out.print("");
							if (GameSetUp.selectedTile != null) {
								if (GameSetUp.initialTile == GameSetUp.selectedTile) {
									break;
								} else {
									try {
										minion.move(
												GameSetUp.selectedTile.getColumn() - GameSetUp.initialTile.getColumn(),
												GameSetUp.selectedTile.getRow() - GameSetUp.initialTile.getRow());
										MainIsland.setShowMessage("Walking...!", Color.web("0xFEFDE8"),
												Color.web("0x89949B"), 120, 1, 1000);

										if (minion.getMoveLeft() <= 0) {
//											MainIsland.setShowMessage("I can't move anymore", Color.web("0xFEFDE8"),
//													Color.web("0x89949B"), 120, 1, 1000);
											break;
										}

										GameSetUp.initialTile.unhighlight();
										GameSetUp.initialTile = GameSetUp.selectedTile;
										GameSetUp.selectedTile = null;
										GameSetUp.initialTile.highlight();
									} catch (InvalidOwnershipException e) {
										EFFECT_ERROR.play();
										MainIsland.setShowMessage("I only Lister to my *Master*", Color.web("E04B4B"),
												110, 3000);
										break;
									} catch (WaterTileException e) {
										EFFECT_ERROR.play();
										MainIsland.setShowMessage("Bruhh I hate water!", Color.web("E04B4B"), 120,
												3000);
										break;
									} catch (TooFarException e) {
										EFFECT_ERROR.play();
										MainIsland.setShowMessage("That's too far!", Color.web("E04B4B"), 120, 3000);
										break;
									} catch (OutOfActionException e) {
										EFFECT_ERROR.play();
										MainIsland.setShowMessage("I need some rest!", Color.web("E04B4B"), 120, 3000);
										break;
									}

								}
							}
						}
						MainIsland.overlayInteractMode("");
						GameSetUp.initialTile.unhighlight();
						GameSetUp.selectedTile.unhighlight();
						GameSetUp.selectedTile = null;
					});
					confirmMove.start();

				}
				if (event.getButton().equals(MouseButton.SECONDARY)) {

					if (!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon, event.getScreenX(), event.getScreenY());
					} else {
						minionList.hide();
					}
				}

			}
		});
	}

	public void selectOneMinionMode() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					GameSetUp.selectedIcon.add(minionIcon);
				}

				if (event.getButton().equals(MouseButton.SECONDARY)) {

					if (!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon, event.getScreenX(), event.getScreenY());
					} else {
						minionList.hide();
					}
				}
			}
		});
	}

	public void selectTwoMinionMode() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getButton().equals(MouseButton.PRIMARY)) {

					if (GameSetUp.selectedIcon.size() > 0) {
						if (!(minionIcon.equals(GameSetUp.selectedIcon.get(0)))) {
							GameSetUp.selectedIcon.add(minionIcon);
							minionIcon.setEffect(SELECTED_EFFECT);
						} else {
							GameSetUp.selectedIcon.remove(0);
							minionIcon.setEffect(null);
						}
					} else {
						GameSetUp.selectedIcon.add(minionIcon);
						minionIcon.setEffect(SELECTED_EFFECT);
					}

				}

				if (event.getButton().equals(MouseButton.SECONDARY)) {

					if (!minionList.isShowing()) {
						minionList.update();
						minionList.show(minionIcon, event.getScreenX(), event.getScreenY());
					} else {
						minionList.hide();
					}
				}

			}
		});
	}

	public void ransomMode() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GameSetUp.selectedIcon.clear();
				minionIcon.setEffect(SELECTED_EFFECT);
				GameSetUp.selectedIcon.add(minionIcon);
				Tooltip message = new Tooltip(
						"Do you want to ransom this minion for $" + (Prison.PLEDGE / MainCharacter.M) + "M\n"
								+ "click agian to confirm \n" + "or click another minion to cancel.");
				message.setFont(Font.font(FONT_NAME,20));
				message.show(minionIcon, event.getScreenX() + 20, event.getScreenY() + 20);
				PrisonIsland.getOverlay().getMinionPane().setOneMinionSelectMode();
				Thread confirm = new Thread(() -> {
					while (true) {
						System.out.print("");
						if (GameSetUp.selectedIcon.size() > 1) {
							if (GameSetUp.selectedIcon.get(0) == GameSetUp.selectedIcon.get(1)) {
								try {
									getMinion().ransom();
									PlayerPanelUpdate.setShowMessage("Successfully ransom your minion.", COLOR_INFO,
											COLOR_STROKE_INFO, 100, 1, 2000);
								} catch (LackOfMoneyException e) {
									PlayerPanelUpdate.setShowMessage("You don't have enough money.", COLOR_ERROR, 100,
											2000);
								}
								PrisonIsland.getOverlay().triggerOverlay(0, 825, 1000);
								GameSetUp.selectedIcon.clear();
								break;
							} else {
								minionIcon.setEffect(null);
								message.hide();
								PrisonIsland.getOverlay().getMinionPane().setRansomMode();
//								PrisonIsland.getOverlay().triggerOverlay(0, 825, 1000);
								GameSetUp.selectedIcon.clear();
								break;
							}
						}
					}
				});
				confirm.start();
			}

		});
	}

// ---------------------------------------- Getter and Setter -------------------------------------------------

	public MinionList getMinionList() {
		return minionList;
	}

	public Minion getMinion() {
		return this.minion;
	}

}
