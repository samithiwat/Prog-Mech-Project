package gui.entity;

import gui.Sceneable;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;

public class PlayerPanel extends Pane implements Sceneable {

	private Button endTurn;
	private static PointPane governmentPoint;
	private static PointPane goodnessPoint;

	public PlayerPanel() {

// -------------------------------------- Add Components Pane ----------------------------------------------------------		

		StatusPane statusPane = new StatusPane();

		TurnBar turnBar = new TurnBar();
		turnBar.setLayoutX(820);

		endTurn = new Button("End Turn");
		endTurn.setId("end-turn-button-release-style");
		endTurnInteract();

		MenuIcon handsIcon = new MenuIcon("img/icon/HandsIcon.png", 42, 632);

		governmentPoint = new PointPane(7, 10, Color.web("0xFFFFFF"));
		governmentPoint.setLayoutX(1287);
		governmentPoint.setLayoutY(706);

		goodnessPoint = new PointPane(5, 10, Color.web("0x3D3D3D"));
		goodnessPoint.setLayoutX(65);
		goodnessPoint.setLayoutY(592);

		getChildren().addAll(statusPane, turnBar, handsIcon, endTurn, governmentPoint, goodnessPoint);
	}

// ------------------------------------------------ Set Up End Turn Button ---------------------------------------------------------

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
				setCursor(CURSOR_SELECTED);
				endTurn.setId("end-turn-button-hold-style");
			}
		});

		endTurn.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setCursor(CURSOR_NORMAL);
				endTurn.setId("end-turn-button-release-style");
			}
		});

		endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CLICK_EFFECT.play();
				GameSetUp.isEndTurn = true;
			}
		});
	}

//	private Pane ClipShape() {
//
//		Pane shape = new Pane();
//
//		Rectangle statusPaneShape = new Rectangle(730, 150);
//
//		Rectangle turnBarShape = new Rectangle(730, 150);
//		turnBarShape.setX(820);
//
//		Rectangle endTurnShape = new Rectangle(200, 100);
//		endTurnShape.setId("end-turn-button-release-style");
//		endTurnShape.setX(1287);
//		endTurnShape.setY(742);
//
//		Rectangle governmentPointShape = new Rectangle(210, 20);
//		governmentPointShape.setX(1287);
//		governmentPointShape.setY(706);
//
//		Rectangle goodnessPointShape = new Rectangle(150, 20);
//		governmentPointShape.setX(65);
//		governmentPointShape.setY(592);
//		
//		ImageView handsShape = new ImageView(ClassLoader.getSystemResource("img/icon/HandsIcon.png").toString());
//		handsShape.setX(42);
//		handsShape.setY(632);
//
//		shape.getChildren().addAll(statusPaneShape, turnBarShape, endTurnShape, governmentPointShape,
//				goodnessPointShape,handsShape);
//
//		return shape;
//	}

	public Button getEndTurn() {
		return endTurn;
	}

	public static PointPane getGovernmentPoint() {
		return governmentPoint;
	}

	public static PointPane getGoodnessPoint() {
		return goodnessPoint;
	}

}
