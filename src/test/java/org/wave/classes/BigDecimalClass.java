package org.wave.classes;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class BigDecimalClass {

	private BigDecimal defaultValue;

	@Min(value = 1)
	private BigDecimal minValue;

	@DecimalMin(value = "10")
	private BigDecimal decimalMinValue;

	public BigDecimal getDefaultValue() {
		return this.defaultValue;
	}

	public BigDecimal getMinValue() {
		return this.minValue;
	}

	public BigDecimal getDecimalMinValue() {
		return this.decimalMinValue;
	}

}
