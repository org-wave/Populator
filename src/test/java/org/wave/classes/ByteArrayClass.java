package org.wave.classes;

import javax.validation.constraints.Size;

public class ByteArrayClass {

	private byte[] defaultValue;

	@Size(min = 1)
	private Byte[] minValue;

	public byte[] getDefaultValue() {
		return this.defaultValue;
	}

	public Byte[] getMinValue() {
		return this.minValue;
	}

}
