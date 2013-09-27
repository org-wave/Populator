package org.wave.getters;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import org.wave.messages.Error;

public class EnumGetter extends Getter {

	@Override
	public Object getValue(Field field) {
		Class<?> type = field.getType();

		Object[] constants = type.getEnumConstants();

		if (constants.length == 0) {
			throw new NoSuchElementException(Error.EMPTY_ENUM.getMessage(type.getName()));
		}

		return constants[0];
	}
}
