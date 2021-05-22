package gui.overlay;

import component.Constant;
import javafx.scene.paint.Color;

public interface Overlayable extends Constant {

	Color BG_COLOR = Color.web("0x393E46", 0.85);

	public boolean triggerOverlay(int dx, int dy, int delay);

}
