package org.wave.classes;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

public class BooleanClass {

	private boolean defaultValue;

	@AssertTrue
	private boolean trueValue;

	@AssertFalse
	private boolean falseValue;

	public boolean isDefaultValue() {
		return this.defaultValue;
	}

	public boolean isTrueValue() {
		return this.trueValue;
	}

	public boolean isFalseValue() {
		return this.falseValue;
	}

}
