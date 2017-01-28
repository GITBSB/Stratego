package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Oberst extends AFigures implements IFigure {
	public static final String OBERST = "Oberst";

	public Oberst(Player player) {
		super(8, player, 2, OBERST);
	}

	public String toString() {
		return "[08]";
	}
}
