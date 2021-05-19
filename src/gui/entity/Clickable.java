package gui.entity;

import component.Constant;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;

public interface Clickable extends Constant {

	public void interact();

	public void triggerDisable();
}
