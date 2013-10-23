/*
   Copyright 2013 Benedito Barbosa Ribeiro Neto/Christian Linhares Peixoto/Mauricio da Silva Marinho

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.wave.setters;

import java.lang.reflect.Field;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import net.vidageek.mirror.dsl.Mirror;

import org.wave.Populator;
import org.wave.exceptions.PopulatorException;

public class CompositeSetter extends Setter {

	private final Populator populator;

	public CompositeSetter(Mirror mirror, Setter next, Populator populator) {
		super(mirror, next);
		this.populator = populator;
	}

	@Override
	public void setValue(Field field, Object instance) {
		Class<?> type = field.getType();

		boolean isEntity = type.isAnnotationPresent(Entity.class);
		boolean isEmbeddable = type.isAnnotationPresent(Embeddable.class);

		if (isEntity || isEmbeddable) {
			Object value = this.mirror.on(instance).get().field(field);

			if (value == null) {
				try {
					value = type.newInstance();
				} catch (InstantiationException e) {
					throw new PopulatorException(e);
				} catch (IllegalAccessException e) {
					throw new PopulatorException(e);
				}

				this.mirror.on(instance).set().field(field).withValue(value);
			}

			// this.name(value, instance);
			this.populator.populate(value);
		} else {
			this.next.setValue(field, instance);
		}
	}

	// private void name(Object value, Object instance) {
	// Class<?> clazz = value.getClass();
	// MirrorList<Field> fields =
	// this.mirror.on(clazz).reflectAll().fields().matching(new
	// PersistentMatcher());
	//
	// for (Field field : fields) {
	// if (field.getType().equals(instance.getClass())) {
	// this.mirror.on(value).set().field(field).withValue(instance);
	// }
	// }
	// }

}
