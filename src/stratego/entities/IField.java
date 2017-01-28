package stratego.entities;

import stratego.entities.impl.FieldStatus;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.Player;

public interface IField {
	IFigure setFigure(IFigure figure);
	
	IFigure getFigure();
	
	IFigure deleteFigure();
	
	String toString(GameStatus gs, Player player);
	
	FieldStatus getFieldstatus();
	
	String getFieldId(GameStatus gs, Player player);
	
	String getFieldIdPlayerChange(GameStatus gs, Player player); 
}
