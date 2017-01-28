package stratego.entities.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldStatusTest {

	@Test
	public void TestFieldStatus() {
		assertEquals("[  ]", FieldStatus.EMPTY_FIELD.toString());
		FieldStatus.valueOf("A_SIDE");
	}
}
