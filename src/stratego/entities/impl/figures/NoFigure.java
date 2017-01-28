package stratego.entities.impl.figures;

import stratego.entities.IFigure;
import stratego.entities.impl.Player;

public class NoFigure implements IFigure {
	public static final String NO_FIGURE = "noFigure";

	public String toString() {
		return "[  ]";
	}

	public int getStrength() {
		return 0;
	}

	public Player getPlayer() {
		return null;
	}

	public int getAnz() {
		return 0;
	}

	public String getName() {
		return NO_FIGURE;
	}

	public void incAnz() {
		// TODO Auto-generated method stub
		
	}

	public void decAnz() {
		// TODO Auto-generated method stub
		
	}
}