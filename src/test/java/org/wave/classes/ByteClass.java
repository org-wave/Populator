package org.wave.classes;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class ByteClass {

	private byte defaultValue;

	@Min(value = 1)
	private byte minValue;

	@DecimalMin(value = "2")
	private byte decimalMinValue;

	public byte getDefaultValue() {
		return this.defaultValue;
	}

	public byte getMinValue() {
		return this.minValue;
	}

	public byte getDecimalMinValue() {
		return this.decimalMinValue;
	}

}
