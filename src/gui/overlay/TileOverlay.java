package gui.overlay;

import gui.entity.MenuIcon;
import gui.entity.MinionPane;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import update.TileOverlayUpdate;

public class TileOverlay extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int INIT_X = 75;
	private static final int INIT_Y = -800;
	private static final int OVERLAY_DX = 0;
	private static final int OVERLAY_DY = 825;
	private static final int OVERLAY_DELAY = 1000;

	private AudioClip bgm;
	private MinionPane minionPane;
	private TileOverlay overlay = this;

	public TileOverlay(String img_path, int[] posXList, int[] posYList) {
		super(new Pane(), WIDTH, HEIGHT, INIT_X, INIT_Y);

		setId("tile-overlay");

		ImageView bg = new ImageView(ClassLoader.getSystemResource(img_path).toString());

		minionPane = new MinionPane(posXList, posYList);

		MenuIcon exitIcon = new MenuIcon("img/icon/ExitIcon.png", 654, 0);
		exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				TileOverlayUpdate.setOverlay(overlay);
				TileOverlayUpdate.closeUpdate();
			}

		});

		root.getChildren().addAll(bg, minionPane, exitIcon);
	}

// ------------------------------------------------- Getter and Setter --------------------------------------------------------

	public MinionPane getMinionPane() {
		return minionPane;
	}

	public void setMinionPane(MinionPane minionPane) {
		this.minionPane = minionPane;
	}

	public static int getOverlayDx() {
		return OVERLAY_DX;
	}

	public static int getOverlayDy() {
		return OVERLAY_DY;
	}

	public static int getOverlayDelay() {
		return OVERLAY_DELAY;
	}

	public AudioClip getBgm() {
		return bgm;
	}

}
