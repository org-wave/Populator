package org.wave.getters;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class BigDecimalGetter extends Getter {

	@Override
	public BigDecimal getValue(Field field) {
		BigDecimal value = BigDecimal.ZERO;

		if (field.isAnnotationPresent(Min.class)) {
			long minValue = field.getAnnotation(Min.class).value();
			value = BigDecimal.valueOf(minValue);
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String decimalMinValue = field.getAnnotation(DecimalMin.class).value();
			value = new BigDecimal(decimalMinValue);
		}

		return value;
	}

}
