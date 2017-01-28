package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Mineur extends AFigures implements IFigure {
	public static final String MINEUR = "Mineur";

	public Mineur(Player player) {
		super(3, player, 5, MINEUR);
	}

	public String toString() {
		return "[03]";
	}
}
