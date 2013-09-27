package org.wave.getters;

import java.lang.reflect.Field;
import java.math.BigInteger;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class BigIntegerGetter extends Getter {

	@Override
	public BigInteger getValue(Field field) {
		BigInteger value = BigInteger.ZERO;

		if (field.isAnnotationPresent(Min.class)) {
			long minValue = field.getAnnotation(Min.class).value();
			value = BigInteger.valueOf(minValue);
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String decimalMinValue = field.getAnnotation(DecimalMin.class).value();
			value = new BigInteger(decimalMinValue);
		}

		return value;
	}

}
