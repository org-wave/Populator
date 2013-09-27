package org.wave.setters;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;

import org.wave.Populator;
import org.wave.enums.DefaultValues;
import org.wave.exceptions.PopulatorException;
import org.wave.getters.Getter;
import org.wave.messages.Error;

public class TypeSetter extends Setter {

	public TypeSetter(Mirror mirror, Populator populator) {
		super(mirror, populator);
	}

	@Override
	public void setValue(Field field, Object instance) {
		Object value = this.mirror.on(instance).get().field(field);

		if (value == null || DefaultValues.contains(value)) {
			Class<?> type = field.getType();

			Getter getter = Getter.getInstance(type);
			if (getter == null) {
				value = this.getValue(type);

				this.populator.populate(value);
			} else {
				try {
					value = getter.getValue(field);
				} catch (Exception e) {
					throw new PopulatorException(e);
				}
			}

			this.mirror.on(instance).set().field(field).withValue(value);
		}

	}

	private Object getValue(Class<?> type) {
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(Error.UNEXPECTED_TYPE.getMessage(type.getName()));
		} catch (IllegalAccessException e) {
			throw new PopulatorException(e);
		}
	}

}
