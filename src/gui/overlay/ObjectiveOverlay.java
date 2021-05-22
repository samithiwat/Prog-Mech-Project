package gui.overlay;

import character.MainCharacter;
import gui.entity.MenuIcon;
import gui.entity.StatusPane;
import gui.entity.TextTitle;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import logic.GameSetUp;

public class ObjectiveOverlay extends Overlay {

	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	private static final int TEXT_ROOT_WIDTH = 1000;
	private static final int TEXT_ROOT_HEIGHT = 450;

	private TextTitle objective;
	private TextTitle skill;

	private VBox textRow;
	private StackPane textRoot;

	public ObjectiveOverlay() {
		super((new Pane()), WIDTH, HEIGHT, 75, -850);
		setCursor(MOUSE_NORMAL);

// ------------------------------------------------ Scene Background ------------------------------------------------

		Rectangle bg = new Rectangle(WIDTH, HEIGHT);
		bg.setFill(BG_COLOR);

		textRow = new VBox();
		textRow.setAlignment(Pos.CENTER);
		textRow.setSpacing(40);

		textRoot = new StackPane(textRow);
		textRoot.setAlignment(Pos.CENTER);
		textRoot.setPrefWidth(TEXT_ROOT_WIDTH);
		textRoot.setPrefHeight(TEXT_ROOT_HEIGHT);
		textRoot.setLayoutX(200);
		textRoot.setLayoutY(175);

// ---------------------------------------------------- Scene Info -----------------------------------------------------

		TextTitle objectiveTitle = new TextTitle("Objective", Color.WHITE, FontWeight.BOLD, 48);
		objective = new TextTitle("", Color.WHITE, FontWeight.MEDIUM, 48);

		TextTitle skillTitle = new TextTitle("Skill", Color.WHITE, FontWeight.BOLD, 48);
		skill = new TextTitle("", Color.WHITE, FontWeight.MEDIUM, 48);

// ------------------------------------------ Close Icon -------------------------------------------------------

		MenuIcon closeIcon = new MenuIcon("img/icon/Cross.png", 1311, 45);

		closeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				EFFECT_MOUSE_CLICK.play();
				StatusPane.triggerObjective();
			}
		});

// ----------------------------------------------- Add Overlay's Component ---------------------------------------------

		textRow.getChildren().addAll(objectiveTitle, objective, skillTitle, skill);

		root.getChildren().addAll(bg, textRoot, closeIcon);
	}

	public void updateInfo() {
		MainCharacter character = GameSetUp.thisTurn;
		textRoot.setBackground(new Background(new BackgroundFill(GameSetUp.thisTurn.getColor(), null, null)));
		objective.setText(character.getObjectiveInfo1() + " " + (character.getnWinCount() - character.checkIsWin())
				+ " " + character.getObjectiveInfo2());
		skill.setText(character.getSkill());
	}

}
