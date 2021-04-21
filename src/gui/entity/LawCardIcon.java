package gui.entity;

import component.law.LawCard;
import component.weaponCard.WeaponCard;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
		
		
		this.selectedWeapon = weapon;
		
		if (law != null) {
			img = new ImageView(ClassLoader.getSystemResource(img_path).toString());
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
					setId("law-card-unselected-style");
				} else {
					setId("law-card-selected-style");
				}
				setSelected(!isSelected);
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

}
