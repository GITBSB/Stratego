package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Aufklärer extends AFigures implements IFigure {
	public static final String AUFKLÄRER = "Aufklärer";

	public Aufklärer(Player player) {
		super(2, player, 8, AUFKLÄRER);
	}

	public String toString() {
		return "[02]";
	}
}