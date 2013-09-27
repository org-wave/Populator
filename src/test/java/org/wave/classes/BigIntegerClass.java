package org.wave.classes;

import java.math.BigInteger;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class BigIntegerClass {

	private BigInteger defaultValue;

	@Min(value = 1)
	private BigInteger minValue;

	@DecimalMin(value = "10")
	private BigInteger decimalMinValue;

	public BigInteger getDefaultValue() {
		return this.defaultValue;
	}

	public BigInteger getMinValue() {
		return this.minValue;
	}

	public BigInteger getDecimalMinValue() {
		return this.decimalMinValue;
	}

}
