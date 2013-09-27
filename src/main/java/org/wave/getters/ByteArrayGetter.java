package org.wave.getters;

import java.lang.reflect.Field;

import javax.validation.constraints.Size;

public class ByteArrayGetter extends Getter {

	@Override
	public Object getValue(Field field) {
		int min = 0;

		if (field.isAnnotationPresent(Size.class)) {
			min = field.getAnnotation(Size.class).min();
		}

		if (field.getType().equals(Byte[].class)) {
			Byte[] bytes = new Byte[min];

			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = 0;
			}

			return bytes;
		}

		return new byte[min];
	}

}
