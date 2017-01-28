package stratego.entities.impl;

public enum FieldStatus {
	EMPTY_FIELD ("[  ]"),
	NO_FIELD ("[--]"),
	A_SIDE ("[AA]"),
	B_SIDE ("[BB]");

	private final String name;

	private FieldStatus(String s) {
	    name = s;
	}

	public String toString() {
	   return this.name;
	}
}