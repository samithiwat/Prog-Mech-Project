package component.weaponCard;

import java.util.Random;

public class WeaponCard {
	private String name;
	private int attack_min;
	private int attack_max;
	public WeaponCard(String name, int attack_min, int attack_max) {
		this.name = name;
		this.attack_max = attack_max;
		this.attack_min = attack_min;
	}
	public int rand_attack() {
		Random rand = new Random();
		int rand_value = rand.nextInt(this.attack_max-this.attack_max+1)+this.attack_max;
		return rand_value;
	}
	// ---------------------getter/setter------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttack_min() {
		return attack_min;
	}
	public void setAttack_min(int attack_min) {
		this.attack_min = attack_min;
	}
	public int getAttack_max() {
		return attack_max;
	}
	public void setAttack_max(int attack_max) {
		this.attack_max = attack_max;
	}
	
	
}
