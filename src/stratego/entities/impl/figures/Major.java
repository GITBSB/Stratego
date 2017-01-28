package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Major extends AFigures implements IFigure {
	public static final String MAJOR = "Major";

	public Major(Player player) {
		super(7, player, 3, MAJOR);
	}

	public String toString() {
		return "[07]";
	}
}