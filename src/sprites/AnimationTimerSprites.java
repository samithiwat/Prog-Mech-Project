package sprites;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class AnimationTimerSprites {

	private final ImageView img; // image
	private final long duration;// duration of between frame(milli sec)
	private final int offsetX; // distance between edge of picture and character
	private final int offsetY; // distance between edge of picture and character
	private final int width; // width of picture
	private final int height; // height of character
	private final int row; // row of character (start from 0)
	private final int d; // distance between character
	private final int nPic; // number of character in picture

	private boolean isLoop = true;
	private boolean isForward = true;

	private static int count = 0;
	private static AnimationTimer animationTimer;
	private static long lastTimeTrigger;

	public AnimationTimerSprites(ImageView img, int duration, int offsetX, int offsetY, int width, int height, int row,
			int d, int nPic) {
		this.img = img;
		this.duration = duration * 1000000;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		this.row = row;
		this.d = d;
		this.nPic = nPic;
	}

	public void play() {
		lastTimeTrigger = -1;
		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);
				if (now - lastTimeTrigger > duration) {
					// System.out.println(++count);
					if (count + 1 == nPic) {
						if (isLoop) {
							count = 0;
							int x1 = ((width + d) * count) + offsetX;
							int y2 = (height * row) + offsetY;
							img.setViewport(new Rectangle2D(x1, y2, width, height));
						}
						else {
							animationTimer.stop();
						}
					}
					else {
						int x = ((width + d) * count) + offsetX;
						int y = (height * row) + offsetY;
						img.setViewport(new Rectangle2D(x, y, width, height));
						lastTimeTrigger = now;
						count++;	
					}
				}
			}

		};
		animationTimer.start();
	}
	
	public void boomerang() {
		lastTimeTrigger = -1;
		animationTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				System.out.println(count);
				lastTimeTrigger = (lastTimeTrigger < 0 ? now : lastTimeTrigger);
				if (now - lastTimeTrigger > duration) {
					// System.out.println(++count);
					if (isForward) {
						if(count + 1 == nPic) {
							isForward = false;
							count--;
						}
						else {
							int x1 = ((width + d) * count) + offsetX;
							int y2 = (height * row) + offsetY;
							img.setViewport(new Rectangle2D(x1, y2, width, height));
							lastTimeTrigger = now;
							count++;
						}
					}
					else {
						if(count == 0) {
							isForward = true;
							//count++;
						}
						else {
							int x2 = ((width + d) * count) + offsetX;
							int y2 = (height * row) + offsetY;
							img.setViewport(new Rectangle2D(x2, y2, width, height));
							lastTimeTrigger = now;
							count--;
						}
					}
				}
			}
			
		};
		animationTimer.start();
	}
	
	public void stop() {
		animationTimer.stop();
	}
	
	public void setLoop(boolean isLoop) {
		this.isLoop = isLoop;
	}
	
	public boolean isLoop() {
		return this.isLoop;
	}
}
