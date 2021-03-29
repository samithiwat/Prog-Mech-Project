package gui.enity;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import logic.AudioLoader;

public class MenuIcon extends ImageView implements Clickable{
	
	public MenuIcon(String img_path) {
		super(ClassLoader.getSystemResource(img_path).toString());
		interact();
	}

	@Override
	public void interact() {
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				AudioClip effect = AudioLoader.mouseEnterSound;
				effect.play();
				setEffect(new DropShadow());
			}
			
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
}
