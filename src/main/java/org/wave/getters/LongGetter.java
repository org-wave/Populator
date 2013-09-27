package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class LongGetter extends Getter {

	@Override
	public Long getValue(Field field) {
		long value = 0;

		if (field.isAnnotationPresent(Min.class)) {
			value = field.getAnnotation(Min.class).value();
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String decimalMinValue = field.getAnnotation(DecimalMin.class).value();
			value = Long.valueOf(decimalMinValue);
		}

		return value;
	}

}
