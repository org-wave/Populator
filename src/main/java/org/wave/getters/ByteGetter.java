package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class ByteGetter extends Getter {

	@Override
	public Byte getValue(Field field) {
		byte value = 0;

		if (field.isAnnotationPresent(Min.class)) {
			Long minValue = field.getAnnotation(Min.class).value();
			value = minValue.byteValue();
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String decimalMinValue = field.getAnnotation(DecimalMin.class).value();
			value = Byte.valueOf(decimalMinValue);
		}

		return value;
	}

}
