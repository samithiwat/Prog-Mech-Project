package update;

import gui.overlay.TileOverlay;
import javafx.application.Platform;

public class TileOverlayUpdate {

	private static TileOverlay overlay;

// ---------------------------------------- Disable Minion Pane When Click Exit Icon -------------------------------------------------
	
	public static void closeUpdate() {
		Thread t = new Thread(()->{
			
			overlay.getMinionPane().setDisable(true);
			
			try {
				Thread.sleep(TileOverlay.getOverlayDelay());
			}
			catch(InterruptedException e) {
				
			}
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					overlay.getMinionPane().setDisable(false);
				}
			});
			
		});
		t.start();
		
		overlay.triggerOverlay(TileOverlay.getOverlayDx(), TileOverlay.getOverlayDy(), TileOverlay.getOverlayDelay());
	}

// --------------------------------------------- Getter and Setter ----------------------------------------------------

	public static TileOverlay getOvelay() {
		return overlay;
	}

	public static void setOverlay(TileOverlay overlay) {
		TileOverlayUpdate.overlay = overlay;
	}

}
