package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class IntGetter extends Getter {

	@Override
	public Integer getValue(Field field) {
		int value = 0;

		if (field.isAnnotationPresent(Min.class)) {
			Long minValue = field.getAnnotation(Min.class).value();
			value = minValue.intValue();
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String decimalMinValue = field.getAnnotation(DecimalMin.class).value();
			value = Integer.valueOf(decimalMinValue);
		}

		return value;
	}

}
