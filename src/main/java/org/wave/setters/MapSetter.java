package org.wave.setters;

import java.lang.reflect.Field;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import org.wave.Populator;
import org.wave.messages.Error;

// TODO Implement this class
public class MapSetter extends Setter {

	public MapSetter(Mirror mirror, Populator populator) {
		super(mirror, populator);
	}

	@Override
	public void setValue(Field field, Object instance) {
		// TODO Implement this method
		throw new UnsupportedOperationException(Error.UNSUPPORTED_TYPE.getMessage(Map.class.getName()));
	}

}
