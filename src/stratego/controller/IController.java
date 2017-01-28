package stratego.controller;

import stratego.entities.IField;
import stratego.entities.IFigure;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Player;
import stratego.util.collection.Position;
import stratego.util.observer.IObservable;

public interface IController extends IObservable {

	public void setFigure(Position position, String figure);

	public Player getPlayer();

	public String getStatus();

	public void createNewGame();
	
	public void startNewGame();

	public GameStatus getGameStatus();

	public String printGrid();

	public void testsetup();

	public void changePlayer();
	
	public IFigure deleteFigure(Position position);

	public void move_attack(Position pos_One, Position pos_Two);

	public boolean ready_or_not();

	public void show_all();

	public String getFieldId(Position position);
	
	public String getFigureToSet();

	public void setFigureToSet(String figure);
	
	public void readyContinue(); 
	
	public Player getPlayerFromPlayerList(int idx);
	
	public void notifyObserversC(GameStatus gameStatus);
	
	public void continueGame();
	
	public IField getField(Position position);
	
	//TODO: test entfernen
	public void changePlayerTest();
}
