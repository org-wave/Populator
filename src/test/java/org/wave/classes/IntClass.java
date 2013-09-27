package org.wave.classes;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class IntClass {

	private int defaultValue;

	@Min(value = 1)
	private int minValue;

	@DecimalMin(value = "2")
	private int decimalMinValue;

	public int getDefaultValue() {
		return this.defaultValue;
	}

	public int getMinValue() {
		return this.minValue;
	}

	public int getDecimalMinValue() {
		return this.decimalMinValue;
	}

}
