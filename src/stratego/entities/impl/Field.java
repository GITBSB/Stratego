package stratego.entities.impl;

import java.util.HashMap;
import java.util.Map;

import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.entities.impl.figures.NoFigure;

public class Field implements IField {
	private FieldStatus fieldStatus;
	private IFigure figure;

	private Map<PlayerType, String> typetoStringTui = new HashMap<PlayerType, String>();

	public Field(FieldStatus fs) {
		this.fieldStatus = fs;
		figure = new NoFigure();
		typetoStringTui.put(PlayerType.PLAYER_A, "[AA]");
		typetoStringTui.put(PlayerType.PLAYER_B, "[BB]");

	}

	public IFigure setFigure(IFigure figure) {
		deleteFigure();
		this.figure = figure;
		figure.decAnz();
		return figure;
	}

	public IFigure getFigure() {
		return this.figure;
	}

	public IFigure deleteFigure() {
		IFigure figure_old = this.figure;
		this.figure = new NoFigure();
		figure_old.incAnz();
		return figure_old;
	}

	public FieldStatus getFieldstatus() {
		return fieldStatus;
	}

	public String toString(GameStatus gs, Player player) {
		if(gs == GameStatus.END) {
			return figure.toString();
		}
		else if(figure.getPlayer() != player && figure.getClass() != NoFigure.class) {
			return typetoStringTui.get(figure.getPlayer().getPlayerType());
		} else {
			return figure.toString();
		}
	}

	public String getFieldIdPlayerChange(GameStatus gs, Player player) {
		String fieldId;
		if (figure.getClass() == NoFigure.class) {
			fieldId = null;
		} else {
			fieldId = "backside_" + figure.getPlayer().getPlayerType();
		}
		return fieldId;
	}

	public String getFieldId(GameStatus gs, Player player) {
		String fieldId = null;
		if (gs == GameStatus.END) {
			if (figure.getClass() == NoFigure.class) {
				fieldId = null;
			} else {
				fieldId = figure.getName() + "_" + figure.getPlayer().getPlayerType();
			}
		} else if (gs == GameStatus.SET_FIGURES && figure.getClass() == NoFigure.class
				&& ((getFieldstatus() == FieldStatus.A_SIDE && player.getPlayerType() == PlayerType.PLAYER_A)
						|| (getFieldstatus() == FieldStatus.B_SIDE && player.getPlayerType() == PlayerType.PLAYER_B))) {
			fieldId = getFieldstatus().toString();
		} else if (figure.getClass() == NoFigure.class) {
			fieldId = null;
		} else if (figure.getPlayer() != player) {
			fieldId = "backside_" + figure.getPlayer().getPlayerType();
		} else {
			fieldId = figure.getName() + "_" + figure.getPlayer().getPlayerType();
		}
		return fieldId;
	}
}
