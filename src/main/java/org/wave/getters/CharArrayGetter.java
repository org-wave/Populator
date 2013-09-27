package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.Size;

public class CharArrayGetter extends Getter {

	@Override
	public Object getValue(Field field) {
		int min = 0;

		if (field.isAnnotationPresent(Size.class)) {
			min = field.getAnnotation(Size.class).min();
		}

		if (field.getType().equals(Character[].class)) {
			Character[] chars = new Character[min];

			for (int i = 0; i < chars.length; i++) {
				chars[i] = ' ';
			}

			return chars;
		}

		return new char[min];
	}

}
