package stratego.entities.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import stratego.entities.FigureSet;

public class PlayerTest {
	Player player;
	FigureSet fs;

	@Before
	public void setup() {
		player = new Player("Testplayer", PlayerType.PLAYER_A, FieldStatus.A_SIDE);
		fs = player.getFigureSet();
	}

	@Test
	public void TestgetPlayerName() {
		assertEquals("Testplayer", player.getPlayerName());
	}

	@Test
	public void TestgetFigureSet() {
		assertEquals(fs, player.getFigureSet());
	}

	@Test
	public void TestgetFieldStatus() {
		assertEquals(FieldStatus.A_SIDE, player.getFieldStatus());
	}

	@Test
	public void TestgetPlayerType() {
		assertEquals(PlayerType.PLAYER_A, player.getPlayerType());
	}

	@Test
	public void TestCountFigures() {
		player.inc_count_figures();
		assertEquals(1, player.get_count_figures());
		player.dec_count_figures();
		assertEquals(0, player.get_count_figures());
	}

	@After
	public void teardown() {
		player = null;
		fs = null;
	}
}
