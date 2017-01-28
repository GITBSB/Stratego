package stratego.entities.impl;

import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.util.collection.Position;

//Singleton
public class Grid {
	private static Grid grid = new Grid();
	private IField[][] field_grid;

	public Grid() {
		field_grid = new Field[10][10];
	}

	public static Grid getInstance() {
		return grid;
	}

	public IField getField(Position position) {
		return field_grid[position.getX()][position.getY()];
	}

	public void move(Position pos_One, Position pos_Two) {
		IFigure mfigure = getField(pos_One).deleteFigure();
		getField(pos_Two).setFigure(mfigure);
	}
	
	public void resetGrid() {
		grid = new Grid();
	}

	public void createGrid() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 10; j++)
				field_grid[i][j] = new Field(FieldStatus.A_SIDE);
		}
		for (int i = 4; i < 6; i++) {
			for (int j = 0; j < 10; j++)
				field_grid[i][j] = new Field(FieldStatus.EMPTY_FIELD);
		}
		for (int i = 6; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				field_grid[i][j] = new Field(FieldStatus.B_SIDE);
		}
		field_grid[4][2] = new Field(FieldStatus.NO_FIELD);
		field_grid[4][3] = new Field(FieldStatus.NO_FIELD);
		field_grid[5][2] = new Field(FieldStatus.NO_FIELD);
		field_grid[5][3] = new Field(FieldStatus.NO_FIELD);
		field_grid[4][6] = new Field(FieldStatus.NO_FIELD);
		field_grid[4][7] = new Field(FieldStatus.NO_FIELD);
		field_grid[5][6] = new Field(FieldStatus.NO_FIELD);
		field_grid[5][7] = new Field(FieldStatus.NO_FIELD);
	}

	public String toString(GameStatus gs, Player player) {
		int idx = 0;
		String gridprint = "";
		for (int i = 0; i < 10; i++) {
			gridprint = gridprint + "\n";
			for (int j = 0; j < 10; j++) {
				gridprint = gridprint + (field_grid[i][j].toString(gs, player));
			}
			gridprint = gridprint + " " + idx++;
		}
		gridprint = gridprint + "\n A   B   C   D   E   F   G   H   I   J";
		return gridprint;
	}
}