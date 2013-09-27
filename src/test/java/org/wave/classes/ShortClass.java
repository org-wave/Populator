package org.wave.classes;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class ShortClass {

	private short defaultValue;

	@Min(value = 1)
	private short minValue;

	@DecimalMin(value = "2")
	private short decimalMinValue;

	public short getDefaultValue() {
		return this.defaultValue;
	}

	public short getMinValue() {
		return this.minValue;
	}

	public short getDecimalMinValue() {
		return this.decimalMinValue;
	}

}
