package stratego.entities;

import stratego.entities.impl.Player;

public interface IFigure {
	public int getStrength();
	public Player getPlayer();
	public int getAnz();
	public void incAnz();
	public void decAnz();
	public String getName();
}
