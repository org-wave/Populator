package org.wave.classes;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class LongClass {

	private long defaultValue;

	@Min(value = 1)
	private long minValue;

	@DecimalMin(value = "2")
	private long decimalMinValue;

	public long getDefaultValue() {
		return this.defaultValue;
	}

	public long getMinValue() {
		return this.minValue;
	}

	public long getDecimalMinValue() {
		return this.decimalMinValue;
	}

}
