package stratego.entities;

import stratego.entities.impl.Player;

public abstract class AFigures implements IFigure {
	private int strength;
	private Player player;
	private int number;
	private String name;
	
	
	public AFigures(int strength, Player player, int number, String name) {
		this.strength = strength;
		this.player = player;
		this.number = number;
		this.name = name;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public int getStrength() {
		return this.strength;
	}

	public int getAnz() {
		return number;
	}

	public void incAnz() {
		this.number++;
		player.dec_count_figures();
	}

	public void decAnz() {
		this.number--;
		player.inc_count_figures();
	}

	public String getName() {
		return this.name;
	}
}
