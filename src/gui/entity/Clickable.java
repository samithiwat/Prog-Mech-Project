package gui.entity;

public interface Clickable {
	
	String FONT_PATH_REGULAR = "font/Bai_Jamjuree/BaiJamjuree-Regular.ttf";
	String FONT_PATH_BOLD = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";
	String FONT_PATH = "font/Bai_Jamjuree/BaiJamjuree-Bold.ttf";

	
	public void interact();

	public void triggerDisable(); 
}
