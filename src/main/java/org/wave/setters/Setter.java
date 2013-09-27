package org.wave.setters;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;

import org.wave.Populator;

public abstract class Setter {

	protected final Mirror mirror;

	protected final Populator populator;

	public Setter(Mirror mirror, Populator populator) {
		this.mirror = mirror;
		this.populator = populator;
	}

	public abstract void setValue(Field field, Object instance);

}
