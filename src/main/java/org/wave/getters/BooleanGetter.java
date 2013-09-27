package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.AssertTrue;

public class BooleanGetter extends Getter {

	@Override
	public Boolean getValue(Field field) {
		boolean value = false;

		if (field.isAnnotationPresent(AssertTrue.class)) {
			value = true;
		}

		return value;
	}

}
