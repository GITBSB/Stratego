package stratego.entities.impl.figures;

import stratego.entities.AFigures;
import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class Marschall extends AFigures implements IFigure {
	public static final String MARSCHALL = "Marschall";
	
	public Marschall(Player player) {
		super(10, player, 1, MARSCHALL);
	}

	public String toString() {
		return "[10]";
	}
}
