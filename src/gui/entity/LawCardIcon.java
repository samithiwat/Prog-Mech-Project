package gui.entity;

import java.util.ArrayList;

import component.law.LawCard;
import component.weaponCard.WeaponCard;
import exception.FullSlotException;
import gui.MainIsland;
import gui.MapOverview;
import gui.overlay.Government;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class LawCardIcon extends Pane implements Clickable {

	private static int LINE_LENGTH = 50;

	private LawCard law;
	private WeaponCard selectedWeapon;
	private LawCardIcon lawCardIcon = this;
	private ImageView img;
	private boolean isSelected = false;

	private static int imgWidth;
	private static int imgHeight;

	private Tooltip info;

	public LawCardIcon(LawCard law) {
		setId("law-card-unselected-style");
		this.law = law;
		if (law != null) {
			img = new ImageView(ClassLoader.getSystemResource(law.getImg_path()).toString());
		} else {
			img = new ImageView(ClassLoader.getSystemResource("img/card/Cardback.png").toString());
		}
		img.setFitWidth(imgWidth);
		img.setFitHeight(imgHeight);

		if (law != null) {
			info = new Tooltip();
			setInfo();
		} else {
			info = new Tooltip("Empty");
		}

		interact();

		getChildren().addAll(img);
	}

	public LawCardIcon(String img_path, WeaponCard weapon) {
		setId("law-card-unselected-style");

//		ArrayList<WeaponCard> bannedWeapon = GameSetUp.lawSlot.getBannedWeapon();
//		for (int i = 0; i < bannedWeapon.size(); i++) {
//			if (bannedWeapon.get(i).isSameType(weapon)) {
//				setId("law-card-selected-style");
//				setSelected(true);
//			}
//		}

		this.selectedWeapon = weapon;

		img = new ImageView(ClassLoader.getSystemResource(img_path).toString());
		img.setFitWidth(imgWidth);
		img.setFitHeight(imgHeight);

		info = new Tooltip();
		setInfo("Ban " + weapon.getName());

		interact();

		getChildren().addAll(img);
	}

	@Override
	public void interact() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_ENTER.play();
				setCursor(MOUSE_SELECT);
				setEffect(new Glow());
				info.show(lawCardIcon, event.getScreenX() + 20, event.getScreenY() - 20);
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
				if (isSelected) {
					removeLaw();

				} else {
					try {
						addLaw();
						setId("law-card-selected-style");
					} catch (FullSlotException e) {
						EFFECT_ERROR.play();
						PlayerPanelUpdate.setShowMessage("No slot left for this law.", Color.web("0xE04B4B"),
								Color.web("0xFEFDE8"), 90, 1, 2000);
					}
				}
			}
		});
	}

	@Override
	public void triggerDisable() {
		setDisable(!isDisable());
	}

// -------------------------------------------- Private Method ------------------------------------------------

	private void setInfo() {
		String description = law.getEffectCard();
		String[] word = description.split(" ");

		String[] temp = new String[0];
		String[] infoWord = new String[0];

		for (int i = 0; i < word.length; i++) {
			temp = addString(temp, word[i]);

			if (String.join(" ", temp).length() > LINE_LENGTH) {
				popString(temp);
				temp = addString(temp, "\n");
				infoWord = addString(infoWord, String.join(" ", temp));
				temp = new String[0];
			}
		}

		if (temp.length > 0) {
			infoWord = addString(infoWord, String.join(" ", temp));
		}

		info.setText(String.join("", infoWord));
	}

	public void setInfo(String description) {
		String[] word = description.split(" ");

		String[] temp = new String[0];
		String[] infoWord = new String[0];

		for (int i = 0; i < word.length; i++) {
			temp = addString(temp, word[i]);

			if (String.join(" ", temp).length() > LINE_LENGTH) {
				popString(temp);
				temp = addString(temp, "\n");
				infoWord = addString(infoWord, String.join(" ", temp));
				temp = new String[0];
			}
		}

		if (temp.length > 0) {
			infoWord = addString(infoWord, String.join(" ", temp));
		}

		info.setText(String.join("", infoWord));
	}

	private void addLaw() throws FullSlotException {

		boolean isAdded = false;
		for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {
			try {
				if (GameSetUp.lawSlot.getSlot(i).getLaw() == null) {
					GameSetUp.lawSlot.setSlot(i, this);
					setSelected(true);
					updateActiveLaw();
					isAdded = true;
					break;
				}
			} catch (Exception e) {
				GameSetUp.lawSlot.setSlot(i, this);
				setSelected(true);
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
		for (int i = 0; i < GameSetUp.lawSlot.nSlot(); i++) {
			LawCardIcon lawCard = GameSetUp.lawSlot.getSlot(i);
			if (lawCard.getLaw() != null) {
				if (lawCard.getLaw().equals(law)) {
					lawCard.setSelected(false);
					lawCard.setId("law-card-unselected-style");
					GameSetUp.lawSlot.setSlot(i, null);
					updateActiveLaw();
					break;
				}
			}
		}
	}

	private void updateActiveLaw() {
		for (int i = 0; i < MapOverview.allGovernment.size(); i++) {
			Government overlay = MapOverview.allGovernment.get(i);
			overlay.updateActivedLaw();
		}
	}

// ------------------------------------------------ Array Method -----------------------------------------------------------

	private String[] addString(String[] oldArray, String data) {

		int len = oldArray.length;

		String[] newArray = new String[len + 1];

		for (int i = 0; i < len; i++) {
			newArray[i] = oldArray[i];
		}

		newArray[len] = data;

		return newArray;
	}

	private String popString(String[] oldArray) {
		int len = oldArray.length;

		String lastString = oldArray[len - 1];

		String[] newArray = new String[len - 1];
		for (int i = 0; i < len - 1; i++) {
			newArray[i] = oldArray[i];
		}

		oldArray = newArray;

		return lastString;
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

/////////////////////////////////////////////// DEBUG /////////////////////////////////////////////////////////

	public String toString() {
		if (law != null) {
			return "-------------------------------" + "\n" + "Law : " + law.getName() + "\n" + "select : " + isSelected
					+ "\n" + "---------------------------------";
		}
		return "-------------------------------" + "\n" + "Law : null\n" + "---------------------------------";
	}

//////////////////////////////////////////// END OF DEBUG /////////////////////////////////////////////////////
}
