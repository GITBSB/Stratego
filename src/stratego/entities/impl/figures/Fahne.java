package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Fahne extends AFigures implements IFigure {
	public static final String FAHNE = "Fahne";

	public Fahne(Player player) {
		super(50, player, 1, FAHNE);
	}

	public String toString() {
		return "[FF]";
	}
}
