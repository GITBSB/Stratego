package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Bombe extends AFigures implements IFigure {
	public static final String BOMBE = "Bombe";

	public Bombe(Player player) {
		super(100, player, 6, BOMBE);
	}

	public String toString() {
		return "[BO]";
	}
}
