package component;

public abstract class Component {
	
	protected String name;
	
	public Component(String name) {
		setName(name);
	}

	
// ------------------------------------ Getter and Setter --------------------------------------
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
