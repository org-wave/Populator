package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import nl.flotsam.xeger.Xeger;

public class StringGetter extends Getter {

	@Override
	public String getValue(Field field) {
		String value = "";

		if (field.isAnnotationPresent(Size.class)) {
			int min = field.getAnnotation(Size.class).min();

			if (min != 0) {
				value = this.getValue(min);
			}
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			value = field.getAnnotation(DecimalMin.class).value();
		}

		if (field.isAnnotationPresent(Pattern.class)) {
			String regexp = field.getAnnotation(Pattern.class).regexp();

			Xeger xeger = new Xeger(regexp);
			value = xeger.generate();
		}

		return value;
	}

	private String getValue(int size) {
		char[] chars = new char[size];

		for (int i = 0; i < chars.length; i++) {
			chars[i] = ' ';
		}

		return String.valueOf(chars);
	}

}
