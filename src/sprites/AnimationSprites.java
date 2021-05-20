package sprites;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimationSprites extends Transition {

	private final ImageView img; // image
	private final int offsetX; // distance between edge of picture and character
	private final int offsetY; // distance between edge of picture and character
	private final int width; // width of picture
	private final int height; // height of character
	private final int row; // row of character (start from 0)
	private final int dw; // distance between character horizontal
	private final int dh; // distance between character vertically
	private final int nPic; // number of character in picture

	public AnimationSprites(ImageView img, int duration, int offsetX, int offsetY, int width, int height, int row,
			int dw, int dh, int nPic) {
		this.img = img;
		setCycleDuration(Duration.millis(duration));
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		this.row = row;
		this.dw = dw;
		this.dh = dh;
		this.nPic = nPic;
	}

	@Override
	protected void interpolate(double frac) {
		final int n = Math.round(nPic * (float) frac);
		final int x = ((width + dw) * n) + offsetX;
		final int y = (((height + dh) * row)) + offsetY;
		img.setViewport(new Rectangle2D(x, y, width, height));

	}

}
