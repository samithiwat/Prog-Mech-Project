package gui.entity;

import java.util.ArrayList;

import component.law.BanWeapon;
import component.law.LawCard;
import component.law.WeaponTax;
import component.weaponCard.WeaponCard;
import exception.DuplicateLawException;
import exception.FullSlotException;
import gui.MapOverview;
import gui.overlay.Government;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameSetUp;
import logic.Util;
import update.PlayerPanelUpdate;

public class LawCardIcon extends Pane implements Clickable {

	private static int LINE_LENGTH = 45;
	private static final int SELECT_WEAPON_WIDTH = 200;
	private static final int SELECT_WEAPON_HEIGHT = 290;

	private LawCard law;
	private WeaponCard selectedWeapon;
	private LawCardIcon lawCardIcon = this;
	private ImageView img;
	private boolean isSelected = false;
	private int row;

	private static int imgWidth;
	private static int imgHeight;

	private Tooltip info;

// ------------------------------------------------ Constructor ----------------------------------------------------------

	public LawCardIcon(LawCard law) {
		setId("law-card-unselected-style");
		this.law = law;
		this.row = -1;
		if (law != null) {
			img = new ImageView(ClassLoader.getSystemResource(law.getImg_path()).toString());
			info = new Tooltip();
			info.setText(Util.formatDescription(law.getEffectCard(), LINE_LENGTH));
		} else {
			img = new ImageView(ClassLoader.getSystemResource("img/card/Cardback.png").toString());
			info = new Tooltip("Empty");
		}

		info.setFont(Font.font("Bai Jamjuree", 14));

		img.setFitWidth(imgWidth);
		img.setFitHeight(imgHeight);

		interact();
		dragAndDropInteract();

		if (law instanceof BanWeapon) {
			WeaponCard weapon = ((BanWeapon) law).getBannedWeapon();
			if (weapon != null) {
				img = new ImageView(ClassLoader.getSystemResource(weapon.getBan_img_path()).toString());
				info.setText(Util.formatDescription("Ban " + weapon.getName(), LINE_LENGTH));
				img.setFitWidth(SELECT_WEAPON_WIDTH);
				img.setFitHeight(SELECT_WEAPON_HEIGHT);
				selectWeaponInteract();
				ArrayList<WeaponCard> bannedWeapon = GameSetUp.lawSlot.getBannedWeapon();
				for (int i = 0; i < bannedWeapon.size(); i++) {
					if (bannedWeapon.get(i).isSameType(weapon)) {
						setId("law-card-selected-style");
						setSelected(true);
						break;
					}
				}
			}
		}
		if (law instanceof WeaponTax) {
			WeaponCard weapon = ((WeaponTax) law).getListWeapon();
			if (weapon != null) {
				img = new ImageView(ClassLoader.getSystemResource(weapon.getTax_img_path()).toString());
				info.setText(Util.formatDescription("Get tax from " + weapon.getName(), LINE_LENGTH));
				img.setFitWidth(SELECT_WEAPON_WIDTH);
				img.setFitHeight(SELECT_WEAPON_HEIGHT);
				selectWeaponInteract();
				ArrayList<WeaponCard> listedWeapon = GameSetUp.lawSlot.getTaxedWeapon();
				for (int i = 0; i < listedWeapon.size(); i++) {
					if (listedWeapon.get(i).isSameType(weapon)) {
						setId("law-card-selected-style");
						setSelected(true);
						break;
					}
				}
			}
		}

		getChildren().addAll(img);
	}

	public LawCardIcon(LawCard law, int row) {
		setId("law-card-unselected-style");
		this.row = row;
		this.law = law;
		if (law != null) {
			img = new ImageView(ClassLoader.getSystemResource(law.getImg_path()).toString());
			info = new Tooltip();
			info.setText(Util.formatDescription(law.getEffectCard(), LINE_LENGTH));
		} else {
			setDisable(true);
			img = new ImageView(ClassLoader.getSystemResource("img/card/Cardback.png").toString());
			info = new Tooltip("Empty");
		}

		interact();
		dragAndDropInteract();

		if (law instanceof BanWeapon) {
			WeaponCard weapon = ((BanWeapon) law).getBannedWeapon();
			if (weapon != null) {
				img = new ImageView(ClassLoader.getSystemResource(weapon.getBan_img_path()).toString());
				info.setText("Ban " + weapon.getName());
				selectWeaponInteract();
			}

		}
		if (law instanceof WeaponTax) {
			WeaponCard weapon = ((WeaponTax) law).getListWeapon();
			if (weapon != null) {
				img = new ImageView(ClassLoader.getSystemResource(weapon.getTax_img_path()).toString());
				info.setText("Get tax from " + weapon.getName());
				selectWeaponInteract();
			}
		}

		info.setFont(Font.font("Bai Jamjuree", 14));
		img.setFitWidth(imgWidth);
		img.setFitHeight(imgHeight);

		getChildren().addAll(img);
	}

// ------------------------------------- Set Interact Method -------------------------------------------------	

	@Override
	public void interact() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_ENTER.play();
				setCursor(MOUSE_SELECT);
				setEffect(new Glow());
				info.show(lawCardIcon, event.getScreenX() + 10, event.getScreenY() + 25);
			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				setEffect(null);
				info.hide();
			}

		});

		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				try {
					addLaw();
				} catch (DuplicateLawException e) {
					removeLaw();

				} catch (FullSlotException e) {
					EFFECT_ERROR.play();
					PlayerPanelUpdate.setShowMessage("No slot left for this law.", Color.web("0xE04B4B"),
							Color.web("0xFEFDE8"), 90, 1, 2000);
				}
			}
		});

	}

	public void dragAndDropInteract() {
		setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (!(law instanceof BanWeapon) && !(law instanceof WeaponTax)) {
					Dragboard db = img.startDragAndDrop(TransferMode.MOVE);
					ClipboardContent content = new ClipboardContent();
					content.putString(
							"" + MapOverview.allGovernment.get(0).getCardSlot().getChildren().indexOf(lawCardIcon));
					db.setContent(content);
					event.consume();
				}
			}

		});

		setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				setEffect(new Bloom());
				event.consume();
			}
		});

		setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				setEffect(null);
				event.consume();
			}
		});

		setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != lawCardIcon && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.MOVE);
				}

				event.consume();
			}
		});

		setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {

				Dragboard db = event.getDragboard();
				boolean isSuccess = false;
				if (db.hasString()) {
					try {
						if (Integer.parseInt(db.getString()) >= 0) {
							LawCardIcon lawCardIcon = (LawCardIcon) MapOverview.allGovernment.get(0).getCardSlot()
									.getChildren().get(Integer.parseInt(db.getString()));
							addLaw(lawCardIcon);
							isSuccess = true;
							EFFECT_MOUSE_CLICK.play();
						}
					} catch (DuplicateLawException e) {
						if (row > -1) {
							removeLaw();
							EFFECT_MOUSE_CLICK.play();
						}
					} catch (FullSlotException e) {
						EFFECT_ERROR.play();
						PlayerPanelUpdate.setShowMessage("This slot is already actived.", Color.web("0xE04B4B"),
								Color.web("0xFEFDE8"), 90, 1, 2000);
					}
				}
				event.setDropCompleted(isSuccess);
				event.consume();
			}

		});
	}

	public void selectWeaponInteract() {
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				try {
					addLaw();

					ArrayList<WeaponCard> weaponList;
					if (law instanceof BanWeapon) {
						weaponList = GameSetUp.lawSlot.getBannedWeapon();
						weaponList.add(((BanWeapon) law).getBannedWeapon());
						unSelectedAll(new LawCardIcon(new BanWeapon()));
					}
					if (law instanceof WeaponTax) {
						weaponList = GameSetUp.lawSlot.getTaxedWeapon();
						weaponList.add(((WeaponTax) law).getListWeapon());
						unSelectedAll(new LawCardIcon(new WeaponTax()));
					}

					StatusPane.triggerSelectWeapon();

				} catch (FullSlotException e) {
					EFFECT_ERROR.play();
					PlayerPanelUpdate.setShowMessage("No slot left for this law.", Color.web("0xE04B4B"),
							Color.web("0xFEFDE8"), 90, 1, 2000);
				} catch (DuplicateLawException e) {

					ArrayList<WeaponCard> weaponList;
					if (law instanceof BanWeapon) {
						weaponList = GameSetUp.lawSlot.getBannedWeapon();
						weaponList.remove(((BanWeapon) law).getBannedWeapon());
					}
					if (law instanceof WeaponTax) {
						weaponList = GameSetUp.lawSlot.getTaxedWeapon();
						weaponList.remove(((WeaponTax) law).getListWeapon());
					}

					removeLaw();
				}
			}
		});
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}

	private void addLaw() throws FullSlotException, DuplicateLawException {

		if (lawCardIcon.isSelected) {
			throw new DuplicateLawException();
		}

		boolean isAdded = false;

		for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {
			if (GameSetUp.lawSlot.getSlot(i).getLaw() == null) {
				GameSetUp.lawSlot.setSlot(i, this);
				setSelectedAll(lawCardIcon);
				updateActiveLaw();
				isAdded = true;
				break;
			}
		}
		if (!isAdded) {
			throw new FullSlotException();
		}
	}

	public void addLaw(LawCardIcon lawCardIcon) throws FullSlotException, DuplicateLawException {

		if (lawCardIcon.isSelected) {
			throw new DuplicateLawException();
		}

		boolean isAdded = false;

		for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {
			if (GameSetUp.lawSlot.getSlot(row).getLaw() == null) {
				GameSetUp.lawSlot.setSlot(row, lawCardIcon);
				setSelectedAll(lawCardIcon);
				updateActiveLaw();
				isAdded = true;
				break;
			}
		}
		if (!isAdded) {
			throw new FullSlotException();
		}
	}

	private void removeLaw() {
		unSelectedAll(this);
		GameSetUp.lawSlot.setSlot(row, new LawCardIcon(null));
		updateActiveLaw();
	}

	private void updateActiveLaw() {
		for (int i = 0; i < MapOverview.allGovernment.size(); i++) {
			Government overlay = MapOverview.allGovernment.get(i);
			overlay.updateActivedLaw();
		}
		PlayerPanelUpdate.updateActivedLawPane();
	}

	private void setSelectedAll(LawCardIcon lawCardIcon) {
		LawCardSlot cardSlot = MapOverview.allGovernment.get(0).getCardSlot();
		int index = cardSlot.getChildren().indexOf(lawCardIcon);
		if (index >= 0) {
			for (int i = 0; i < MapOverview.allGovernment.size(); i++) {
				cardSlot = MapOverview.allGovernment.get(i).getCardSlot();
				LawCardIcon cardIcon = (LawCardIcon) cardSlot.getChildren().get(index);
				cardIcon.setSelected(true);
				cardIcon.setDisable(true);
				cardIcon.setId("law-card-selected-style");
			}
		}
	}

	private void unSelectedAll(LawCardIcon lawCardIcon) {
		LawCardSlot cardSlot = MapOverview.allGovernment.get(0).getCardSlot();
		int index = cardSlot.getChildren().indexOf(lawCardIcon);
		if (index >= 0) {
			for (int i = 0; i < MapOverview.allGovernment.size(); i++) {
				cardSlot = MapOverview.allGovernment.get(i).getCardSlot();
				LawCardIcon cardIcon = (LawCardIcon) cardSlot.getChildren().get(index);
				cardIcon.setSelected(false);
				cardIcon.setDisable(false);
				cardIcon.setId("law-card-unselected-style");
			}
		}
	}

// -------------------------------------------------- Equal Method ---------------------------------------------------------

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LawCardIcon other = (LawCardIcon) obj;
		if (isSelected != other.isSelected)
			return false;
		if (law == null) {
			if (other.law != null)
				return false;
		} else if (!law.equals(other.law))
			return false;
		return true;
	}

// ------------------------------------------------ Getter and Setter ------------------------------------------------------

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public LawCard getLaw() {
		return law;
	}

	public static int getImgWidth() {
		return imgWidth;
	}

	public static void setImgWidth(int imgWidth) {
		LawCardIcon.imgWidth = imgWidth;
	}

	public static int getImgHeight() {
		return imgHeight;
	}

	public static void setImgHeight(int imgHeight) {
		LawCardIcon.imgHeight = imgHeight;
	}

	public WeaponCard getSelectedWeapon() {
		return selectedWeapon;
	}

	public ImageView getImg() {
		return img;
	}

}
