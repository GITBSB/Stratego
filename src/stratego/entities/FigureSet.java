package stratego.entities;

import java.util.HashMap;
import java.util.Map;

import stratego.entities.impl.Player;
import stratego.entities.impl.figures.Aufklärer;
import stratego.entities.impl.figures.Bombe;
import stratego.entities.impl.figures.Fahne;
import stratego.entities.impl.figures.General;
import stratego.entities.impl.figures.Hauptmann;
import stratego.entities.impl.figures.Leutnant;
import stratego.entities.impl.figures.Major;
import stratego.entities.impl.figures.Marschall;
import stratego.entities.impl.figures.Mineur;
import stratego.entities.impl.figures.Oberst;
import stratego.entities.impl.figures.Spion;
import stratego.entities.impl.figures.Unteroffizier;

public class FigureSet {
	private Map<String, IFigure> figures;
	public FigureSet(Player player) {
		figures = new HashMap<String, IFigure>();
		figures.put(Spion.SPION, new Spion(player));
		figures.put(Aufklärer.AUFKLÄRER, new Aufklärer(player));
		figures.put(Mineur.MINEUR, new Mineur(player));
		figures.put(Unteroffizier.UNTEROFFIZIER, new Unteroffizier(player));
		figures.put(Leutnant.LEUTNANT, new Leutnant(player));
		figures.put(Hauptmann.HAUPTMANN, new Hauptmann(player));
		figures.put(Major.MAJOR, new Major(player));
		figures.put(Oberst.OBERST, new Oberst(player));
		figures.put(General.GENERAL, new General(player));
		figures.put(Marschall.MARSCHALL, new Marschall(player));
		figures.put(Bombe.BOMBE, new Bombe(player));
		figures.put(Fahne.FAHNE, new Fahne(player));
	}

	public IFigure getfigure(String s) {
		return figures.get(s);
	}
}