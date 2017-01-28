package stratego.entities.impl;

import stratego.entities.FigureSet;

public class Player {
	private String playerName;
	private FigureSet figureSet;
	private PlayerType playerType;
	private FieldStatus fs;
	private int count_figures;

	public Player(String playerName, PlayerType pt, FieldStatus fs) {
		this.setPlayerName(playerName);
		this.setPlayerType(pt);
		setFigureSet(new FigureSet(this));
		this.setFieldStatus(fs);
		this.count_figures = 0;
	}

	private void setFieldStatus(FieldStatus fs) {
		this.fs = fs;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public FigureSet getFigureSet() {
		return figureSet;
	}

	public void setFigureSet(FigureSet figureSet) {
		this.figureSet = figureSet;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType pt) {
		this.playerType = pt;
	}

	public FieldStatus getFieldStatus() {
		return fs;
	}

	public void inc_count_figures() {
		count_figures++;
	}

	public void dec_count_figures() {
		count_figures--;
	}

	public int get_count_figures() {
		return count_figures;
	}
}
