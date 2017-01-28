package stratego.controller.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import stratego.controller.IController;
import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.entities.impl.FieldStatus;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Grid;
import stratego.entities.impl.Player;
import stratego.entities.impl.PlayerType;
import stratego.entities.impl.figures.Aufklärer;
import stratego.entities.impl.figures.Bombe;
import stratego.entities.impl.figures.Fahne;
import stratego.entities.impl.figures.General;
import stratego.entities.impl.figures.Hauptmann;
import stratego.entities.impl.figures.Leutnant;
import stratego.entities.impl.figures.Major;
import stratego.entities.impl.figures.Marschall;
import stratego.entities.impl.figures.Mineur;
import stratego.entities.impl.figures.NoFigure;
import stratego.entities.impl.figures.Oberst;
import stratego.entities.impl.figures.Spion;
import stratego.entities.impl.figures.Unteroffizier;
import stratego.util.collection.Position;
import stratego.util.observer.Observable;

@Singleton
public class Controller extends Observable implements IController {
	private final Grid grid;
	private GameStatus gameStatus;
	private Player player = null;
	private String statusLine = "Stratego!";
	private List<Player> playerList;
	private String figuretoSet = null;
	Logger logger = Logger.getLogger(Controller.class);

	public final static String PLAYER_A = "Player A";
	public final static String PLAYER_B = "Player B";

	@Inject
	public Controller() {
		grid = Grid.getInstance();
		playerList = new LinkedList<Player>();
	}

	public void createNewGame() {
		buildEmptyGrid();
		gameStatus = GameStatus.NOT_STARTED;
		statusLine = "New Stratego Game created";
		enterPlayernames();
		notifyObservers();
	}
	
	public void startNewGame() {
		if (gameStatus != GameStatus.NOT_STARTED) {
			createNewGame();
		}
		// Testaufbau TODO:remove
		testsetup();
		
		// eigentlicher Verlauf
//		playerSetFigures();
	}

	private void buildEmptyGrid() {
		grid.createGrid();
	}

	private void enterPlayernames() {
		playerList.clear();
		playerList.add(new Player(PLAYER_A, PlayerType.PLAYER_A, FieldStatus.A_SIDE));
		playerList.add(new Player(PLAYER_B, PlayerType.PLAYER_B, FieldStatus.B_SIDE));
	}

	public void testsetup() {
		playerSetFigures();

		setFigure(new Position(0, 0), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 1), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 2), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 3), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 4), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 5), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 6), Major.MAJOR);
		setFigure(new Position(0, 7), Aufklärer.AUFKLÄRER);
		setFigure(new Position(0, 8), Spion.SPION);		
		setFigure(new Position(0, 9), Fahne.FAHNE);
		setFigure(new Position(1, 0), Mineur.MINEUR);
		setFigure(new Position(1, 1), Mineur.MINEUR);
		setFigure(new Position(1, 2), Major.MAJOR);
		setFigure(new Position(1, 3), Mineur.MINEUR);
		setFigure(new Position(1, 4), Hauptmann.HAUPTMANN);
		setFigure(new Position(1, 5), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(1, 6), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(1, 7), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(1, 8), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(1, 9), Leutnant.LEUTNANT);
		setFigure(new Position(2, 0), Leutnant.LEUTNANT);
		setFigure(new Position(2, 1), Leutnant.LEUTNANT);
		setFigure(new Position(2, 2), Leutnant.LEUTNANT);
		setFigure(new Position(2, 3), Mineur.MINEUR);
		setFigure(new Position(2, 4), Hauptmann.HAUPTMANN);
		setFigure(new Position(2, 5), Hauptmann.HAUPTMANN);
		setFigure(new Position(2, 6), Hauptmann.HAUPTMANN);
		setFigure(new Position(2, 7), Oberst.OBERST);
		setFigure(new Position(2, 8), Major.MAJOR);
		setFigure(new Position(2, 9), Oberst.OBERST);
		setFigure(new Position(3, 0), General.GENERAL);
		setFigure(new Position(3, 1), Aufklärer.AUFKLÄRER);
		setFigure(new Position(3, 2), Bombe.BOMBE);
		setFigure(new Position(3, 3), Bombe.BOMBE);
		setFigure(new Position(3, 4), Bombe.BOMBE);
		setFigure(new Position(3, 5), Bombe.BOMBE);
		setFigure(new Position(3, 6), Bombe.BOMBE);
		setFigure(new Position(3, 7), Bombe.BOMBE);
		setFigure(new Position(3, 8), Marschall.MARSCHALL);
		setFigure(new Position(3, 9), Mineur.MINEUR);
		playerSetFinished();
		setFigure(new Position(6, 0), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 1), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 2), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 3), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 4), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 5), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 6), Major.MAJOR);
		setFigure(new Position(6, 7), Aufklärer.AUFKLÄRER);
		setFigure(new Position(6, 8), Spion.SPION);		
		setFigure(new Position(6, 9), Fahne.FAHNE);
		setFigure(new Position(7, 0), Mineur.MINEUR);
		setFigure(new Position(7, 1), Mineur.MINEUR);
		setFigure(new Position(7, 2), Major.MAJOR);
		setFigure(new Position(7, 3), Mineur.MINEUR);
		setFigure(new Position(7, 4), Hauptmann.HAUPTMANN);
		setFigure(new Position(7, 5), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(7, 6), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(7, 7), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(7, 8), Unteroffizier.UNTEROFFIZIER);
		setFigure(new Position(7, 9), Leutnant.LEUTNANT);
		setFigure(new Position(8, 0), Leutnant.LEUTNANT);
		setFigure(new Position(8, 1), Leutnant.LEUTNANT);
		setFigure(new Position(8, 2), Leutnant.LEUTNANT);
		setFigure(new Position(8, 3), Mineur.MINEUR);
		setFigure(new Position(8, 4), Hauptmann.HAUPTMANN);
		setFigure(new Position(8, 5), Hauptmann.HAUPTMANN);
		setFigure(new Position(8, 6), Hauptmann.HAUPTMANN);
		setFigure(new Position(8, 7), Oberst.OBERST);
		setFigure(new Position(8, 8), Major.MAJOR);
		setFigure(new Position(8, 9), Oberst.OBERST);
		setFigure(new Position(9, 0), General.GENERAL);
		setFigure(new Position(9, 1), Aufklärer.AUFKLÄRER);
		setFigure(new Position(9, 2), Bombe.BOMBE);
		setFigure(new Position(9, 3), Bombe.BOMBE);
		setFigure(new Position(9, 4), Bombe.BOMBE);
		setFigure(new Position(9, 5), Bombe.BOMBE);
		setFigure(new Position(9, 6), Bombe.BOMBE);
		setFigure(new Position(9, 7), Bombe.BOMBE);
		setFigure(new Position(9, 8), Marschall.MARSCHALL);
//		setFigure(new Position(9, 9), Mineur.MINEUR);
		
		statusLine = "Testsetup";
		notifyObservers();
	}

	public void playerSetFigures() {
		nextPlayer();
		statusLine = "Spieler " + player.getPlayerName() + " Figuren setzen!";
		gameStatus = GameStatus.SET_FIGURES;
		notifyObservers();
	}

	public void setFigure(Position position, String fig) {
		IFigure figure;
		try {
			figure = player.getFigureSet().getfigure(fig);
		} catch (Exception e) {
			statusLine = "Falsche Eingabe!";
			notifyObservers();
			return;
		}
		if (grid.getField(position).getFieldstatus() != player.getFieldStatus()) {
			statusLine = "Platzierung nur auf eigenem Feld möglich!";
		} else if (figure.getAnz() <= 0) {
			statusLine = "Nicht genug Figuren dieser Art!";
		} else {
			grid.getField(position).setFigure(figure);
			statusLine = "Figur gesetzt!";
		}
		notifyObservers();
	}

	public void move_attack(Position pos_One, Position pos_Two) {
		int i = pos_One.getX();
		int j = pos_One.getY();
		int n = pos_Two.getX();
		int m = pos_Two.getY();
		Class<?> from_class = grid.getField(pos_One).getFigure().getClass();

		IFigure from_figure = grid.getField(pos_One).getFigure();
		IFigure to_figure = grid.getField(pos_Two).getFigure();

		if (from_figure.getPlayer() != player) {
			statusLine = "Nur eigene Figuren dürfen bewegt werden";
		} else if (from_class == Bombe.class || from_class == Fahne.class) {
			statusLine = "Figur kann nicht bewegt werden!";
		} else if (grid.getField(pos_Two).getFieldstatus() == FieldStatus.NO_FIELD) {
			statusLine = "Zielfeld darf nicht besetzt werden!";
		} else if (to_figure.getClass() != NoFigure.class && to_figure.getPlayer() == from_figure.getPlayer()) {
			statusLine = "Zielfeld ist von eigener Figur besetzt!";
		} else if (from_class == Aufklärer.class && ((i + 10 > n && n > i - 10) && j == m)
				|| ((j + 10 > m && m > j - 10) && i == n)) {
			if (n > i) {
				for (int z = i + 1; z < n; z++) {
					if (grid.getField(new Position(z, m)).getFigure().getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else if (n < i) {
				for (int z = i - 1; z > n; z--) {
					if (grid.getField(new Position(z, m)).getFigure().getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else if (m > j) {
				for (int z = j + 1; z < m; z++) {
					if (grid.getField(new Position(n, z)).getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			} else
				for (int z = j - 1; z > m; z--) {
					if (grid.getField(new Position(n, z)).getClass() != NoFigure.class) {
						statusLine = "Zug ist nicht erlaubt!";
						break;
					}
				}
			if (statusLine != "Zug ist nicht erlaubt!") {
				move_attack_Impl(from_figure, to_figure, pos_One, pos_Two);
			}
		} else if (((n == i + 1 || n == i - 1) && j == m) || ((m == j + 1 || m == j - 1) && i == n)) {
			move_attack_Impl(from_figure, to_figure, pos_One, pos_Two);
		} else {
			statusLine = "Dieser Zug ist nicht erlaubt, bitte Bewegungsradius beachten!";
		}
		notifyObservers();
	}

	private void move_attack_Impl(IFigure from_figure, IFigure to_figure, Position pos_One, Position pos_Two) {
		if (to_figure.getClass() != NoFigure.class) {
			if (from_figure.getClass() == Spion.class && to_figure.getClass() == Marschall.class) {
				grid.move(pos_One, pos_Two);
				statusLine = "Spion erwischt den Marschall!";
			} else if (from_figure.getClass() == Mineur.class && to_figure.getClass() == Bombe.class) {
				grid.move(pos_One, pos_Two);
				statusLine = "Mineur entschärft Bombe!";
			} else if (to_figure.getClass() == Bombe.class) {
				deleteFigure(pos_One);
				statusLine = "Bombe!";
			} else if (to_figure.getClass() == Fahne.class) {
				statusLine = "Spieler " + from_figure.getPlayer().getPlayerName() + " hat das Spiel gewonnen!!!";
				gameStatus = GameStatus.END;
			} else {
				if (from_figure.getStrength() > to_figure.getStrength()) {
					deleteFigure(pos_Two);
					statusLine = from_figure.getName() + "(" + from_figure.getPlayer().getPlayerName() + ") besiegt "
							+ to_figure.getName() + "(" + to_figure.getPlayer().getPlayerName() + ")!";
				} else if (from_figure.getStrength() < to_figure.getStrength()) {
					deleteFigure(pos_One);
					statusLine = from_figure.getName() + "(" + from_figure.getPlayer().getPlayerName()
							+ ") verliert gegen " + to_figure.getName() + "(" + to_figure.getPlayer().getPlayerName()
							+ ")!";
				} else {
					deleteFigure(pos_One);
					deleteFigure(pos_Two);
					statusLine = "Unentschieden! Beide Krieger fallen (" + from_figure.getName() + ")!";
				}
			}
		} else {
			grid.move(pos_One, pos_Two);
			statusLine = "Figur wurde bewegt!";
		}
		if (getGameStatus() == GameStatus.END) {
			notifyObservers();
		} else {
			changePlayer();
		}
	}

	public Grid getGrid() {
		return grid.getInstance();
	}

	public Player getPlayer() {
		return this.player;
	}

	public String getStatus() {
		return statusLine;
	}

	public GameStatus getGameStatus() {
		return this.gameStatus;
	}

	public void setGameStatus(GameStatus e) {
		gameStatus = e;
	}
	
	public void readyContinue() {
		if (getGameStatus() == GameStatus.PLAYER_CHANGE) {
			continueGame();
		} else {
			playerSetFinished();
		}
	}

	public void playerSetFinished() {
		if (getGameStatus() == GameStatus.SET_FIGURES) {
			if (ready_or_not()) {
				nextPlayer();
				statusLine = "Spieler " + player.getPlayerName() + "Figuren setzten!";
				if (ready_or_not()) {
					gameStatus = GameStatus.PLAYER_CHANGE;
					statusLine = "Lasset die Schlacht beginnen!";
				}
			} else {
				statusLine = "Es müssen alle Figuren gesetzt sein!";
			}
			notifyObservers();
		}
	}

	public void changePlayerTest() {
		nextPlayer();
		notifyObservers();

	}

	public void changePlayer() {
		nextPlayer();
		gameStatus = GameStatus.PLAYER_CHANGE;
		notifyObservers();
	}

	public void continueGame() {
		gameStatus = GameStatus.FIGHT;
		notifyObservers();
	}

	private void nextPlayer() {
		if (playerList.get(0).equals(player)) {
			player = getPlayerFromPlayerList(1);
		} else {
			player = getPlayerFromPlayerList(0);
		}
	}

	private List<Player> getPlayerList() {
		return playerList;
	}

	public Player getPlayerFromPlayerList(int idx) {
		return getPlayerList().get(idx);
	}

	public static int letterCode(char c) {
		if (Character.isLetter(c) && Character.isLowerCase(c))
			return c - 'a';
		if (Character.isLetter(c) && Character.isUpperCase(c))
			return c - 'A';
		throw new IllegalArgumentException("letter: " + c);
	}

	public boolean ready_or_not() {
		if (player.get_count_figures() == 40) {
			return true;
		} else {
			return false;
		}
	}

	public String printGrid() {
		return grid.toString(this.getGameStatus(), player);
	}

	public void show_all() {
		grid.toString(gameStatus, player);
	}

	public String getFieldId(Position position) {
		String fieldId;
		if (gameStatus == GameStatus.PLAYER_CHANGE) {
			fieldId = getFieldId_PlayerChange(position);
		} else {
			fieldId = getFieldId_Fight(position);
		}
		return fieldId;
	}

	private String getFieldId_PlayerChange(Position position) {
		return getField(position).getFieldIdPlayerChange(getGameStatus(), getPlayer());
	}

	private String getFieldId_Fight(Position position) {
		return getField(position).getFieldId(getGameStatus(), getPlayer());
	}

	public IField getField(Position position) {
		return grid.getField(position);
	}

	public void setFigureToSet(String figureToSet) {
		this.figuretoSet = figureToSet;
	}

	public String getFigureToSet() {
		return this.figuretoSet;
	}

	public IFigure deleteFigure(Position position) {
		IFigure figure = getField(position).deleteFigure();
		notifyObservers();
		return figure;

	}

	public void notifyObserversC(GameStatus gameStatus) {
		notifyObservers(gameStatus);
	}
}