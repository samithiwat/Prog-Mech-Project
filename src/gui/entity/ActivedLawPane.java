package gui.entity;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import update.Updateable;

public class ActivedLawPane extends HBox implements Updateable{

	private ActivedLawIcon activedLaw1;
	private ActivedLawIcon activedLaw2;
	
	public ActivedLawPane() {
		
// --------------------------------------------------------- Set Up ----------------------------------------------------------------------
	
		setSpacing(50);
		
// -------------------------------------------------------- Component --------------------------------------------------------------------
		
		activedLaw1 = new ActivedLawIcon(null);
		activedLaw2 = new ActivedLawIcon(null);
		
// -------------------------------------------------------- Add Component ----------------------------------------------------------------
	
		getChildren().addAll(activedLaw1,activedLaw2);
		
	}

	@Override
	public void update() {
		
	}
	
}
