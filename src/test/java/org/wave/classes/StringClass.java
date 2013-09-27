package org.wave.classes;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StringClass {

	private String defaultValue;

	@Size(min = 1)
	private String minValue;

	@DecimalMin(value = "1")
	private String decimalMinValue;

	@Pattern(regexp = "[ab]{4,6}c")
	private String patternValue;

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public String getMinValue() {
		return this.minValue;
	}

	public String getDecimalMinValue() {
		return this.decimalMinValue;
	}

	public String getPatternValue() {
		return this.patternValue;
	}

}
