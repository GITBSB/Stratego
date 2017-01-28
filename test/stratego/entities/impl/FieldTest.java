package stratego.entities.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stratego.entities.IFigure;
import stratego.entities.impl.figures.Fahne;

public class FieldTest {
	Field field;
	IFigure figure;
	IFigure newfigure;
	Player player;

	@Before
	public void setup() {
		field = new Field(FieldStatus.A_SIDE);
		figure = field.getFigure();

		player = new Player("Testplayer", PlayerType.PLAYER_A, FieldStatus.A_SIDE);
		newfigure = new Fahne(player);
		field.setFigure(newfigure);
	}

	@Test
	public void TestgetsetFigure() {
		field.setFigure(newfigure);
		assertEquals(newfigure, field.getFigure());
	}

	@Test
	public void TestgetFieldStatus() {
		assertEquals(FieldStatus.A_SIDE, field.getFieldstatus());
	}

	@Test
	public void TesttoString() {
		assertEquals("[FF]", field.toString(GameStatus.END, player));

//		field.setFieldStatus(FieldStatus.NO_FIELD);
//		assertEquals("[--]", field.toString(GameStatus.FIGHT, player));
//
//		field.setFieldStatus(FieldStatus.A_SIDE);
//		assertEquals("[AA]", field.toString(GameStatus.FIGHT, null));

		field.setFigure(newfigure);
		assertEquals("[FF]", field.toString(GameStatus.FIGHT, player));
	}
}
