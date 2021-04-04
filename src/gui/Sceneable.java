package gui;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public interface Sceneable {
	ImageCursor MOUSE_NORMAL = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/MouseCursor.png").toString())));
	ImageCursor MOUSE_SELECT = new ImageCursor((new Image(ClassLoader.getSystemResource("img/icon/MouseCursorSelected.png").toString())));
}
