package gui.entity;

import gui.MapOverview;
import gui.Sceneable;
import gui.overlay.HandOverlay;
import gui.overlay.PlayerList1;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioLoader;
import logic.GameSetUp;
import update.PlayerPanelUpdate;

public class PlayerPanel extends Pane implements Sceneable {
	
	private static Button endTurn;
	private static PointPane governmentPoint;
	private static PointPane goodnessPoint;
	private static TurnBar turnBar;
	private static StatusPane statusPane;
	private static MenuIcon handsIcon;
	
	public PlayerPanel() {

// -------------------------------------- Add Components Pane ----------------------------------------------------------		

		statusPane = new StatusPane();

		turnBar = new TurnBar();
		turnBar.setLayoutX(820);

		endTurn = new Button("End Turn");
		endTurn.setId("end-turn-button-release-style");
		endTurnInteract();
		
//		handOverlay = new HandOverlay();
//		MapOverview.allHandOverlay.add(handOverlay);
		handsIcon = new MenuIcon("img/icon/HandsIcon.png", 42, 632);
		handInteract();
		
//		playerList1 = new PlayerList1();
//		MapOverview.allPlayerList1.add(playerList1);
		turnBarInteract();
		
		
		governmentPoint = new PointPane(7, 10, Color.web("0xFFFFFF"));
		governmentPoint.setLayoutX(1287);
		governmentPoint.setLayoutY(706);

		goodnessPoint = new PointPane(5, 10, Color.web("0x3D3D3D"));
		goodnessPoint.setLayoutX(65);
		goodnessPoint.setLayoutY(592);

		getChildren().addAll(statusPane, turnBar, handsIcon, endTurn, governmentPoint, goodnessPoint);
	}

// ------------------------------------------------ Set Up Button ---------------------------------------------------------

	private void endTurnInteract() {

		Rectangle shape = new Rectangle(200, 100);
		shape.setArcHeight(25);
		shape.setArcWidth(25);

		endTurn.setShape(shape);
		endTurn.setLayoutX(1287);
		endTurn.setLayoutY(742);
		endTurn.setPrefHeight(100);
		endTurn.setPrefWidth(200);
		endTurn.setFont(Font.font("Bai Jamjuree", FontWeight.BOLD, 34));

		endTurn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_ENTER.play();
				setCursor(MOUSE_SELECT);
				endTurn.setId("end-turn-button-hold-style");
			}
		});

		endTurn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(MOUSE_NORMAL);
				endTurn.setId("end-turn-button-release-style");
			}
		});

		endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				GameSetUp.isEndTurn = true;
				
///////////////////////////////////////////////////////////////// FOR DEBUG ONLY ///////////////////////////////////////////////////////////////////////////////////				
				
				
				System.out.println("---------------------------------------- End turn ---------------------------------------------\n" + GameSetUp.thisTurn);
			
			
///////////////////////////////////////////////////////////////// END OF DEBUG ///////////////////////////////////////////////////////////////////////////////////
			}
		});
	}
	
	private void handInteract() {
		handsIcon.setOnMouseClicked((MouseEvent event) -> {
//			System.out.println("start");
			AudioClip effect = AudioLoader.clickEffect;
			effect.play();
			PlayerPanelUpdate.updateHandOverlay();
			for(int i = 0 ; i < MapOverview.allHandOverlay.size() ; i++) {
				MapOverview.allHandOverlay.get(i).triggerOverlay(0,825,1000);
			}
//			System.out.println("end");
		});
	}
	
	private void turnBarInteract() {
		turnBar.setOnMouseClicked((MouseEvent event) -> {
			AudioClip effect = AudioLoader.clickEffect;
			effect.play();
			PlayerPanelUpdate.updatePlayerList();
			for (int i = 0; i < MapOverview.allPlayerList1.size(); i++) {
				MapOverview.allPlayerList1.get(i).triggerOverlay(0, 825, 1000);
			}
		});
	}
	

	public static Button getEndTurn() {
		return endTurn;
	}

	public static PointPane getGovernmentPoint() {
		return governmentPoint;
	}

	public static PointPane getGoodnessPoint() {
		return goodnessPoint;
	}

	public static TurnBar getTurnBar() {
		return turnBar;
	}

	public static StatusPane getStatusPane() {
		return statusPane;
	}

	public static MenuIcon getHandsIcon() {
		return handsIcon;
	}
	
}
