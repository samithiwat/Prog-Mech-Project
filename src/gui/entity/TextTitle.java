package gui.entity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TextTitle extends Text{
	
	public TextTitle(String content,Color color,FontWeight fontWeight,int size,int x,int y) {
		super(content);
		setFill(color);
		setX(x);
		setY(y);
		setFont(Font.font("Bai Jamjuree",fontWeight,size));
	} 
	
	public TextTitle(String content,Color color,FontWeight fontWeight,int size) {
		super(content);
		setFill(color);
		setFont(Font.font("Bai Jamjuree",fontWeight,size));
	} 
}
