package org.wave.classes;

import javax.validation.constraints.Size;

public class CharArrayClass {

	private char[] defaultValue;

	@Size(min = 1)
	private Character[] minValue;

	public char[] getDefaultValue() {
		return this.defaultValue;
	}

	public Character[] getMinValue() {
		return this.minValue;
	}

}
