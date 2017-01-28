package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Spion extends AFigures implements IFigure {
	public static final String SPION = "Spion";

	public Spion(Player player) {
		super(1, player, 1, SPION);
	}

	public String toString() {
		return "[01]";
	}
}
