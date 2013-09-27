package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class ShortGetter extends Getter {

	@Override
	public Short getValue(Field field) {
		short value = 0;

		if (field.isAnnotationPresent(Min.class)) {
			Long minValue = field.getAnnotation(Min.class).value();
			value = minValue.shortValue();
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String decimalMinValue = field.getAnnotation(DecimalMin.class).value();
			value = Short.valueOf(decimalMinValue);
		}

		return value;
	}

}
