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

import net.vidageek.mirror.dsl.Mirror;

import org.wave.enums.DefaultValue;
import org.wave.exceptions.PopulatorException;
import org.wave.getters.Getter;
import org.wave.messages.Error;

public class BasicSetter extends Setter {

	public BasicSetter(Mirror mirror) {
		super(mirror, null);
	}

	@Override
	public void setValue(Field field, Object instance) {
		Object value = this.mirror.on(instance).get().field(field);

		if (value == null || this.knows(value)) {
			Class<?> type = field.getType();

			Getter getter = Getter.getInstance(type);

			if (getter == null) {
				throw new IllegalArgumentException(Error.UNSUPPORTED_TYPE.message(type.getName()));
			}

			try {
				value = getter.getValue(field);
			} catch (Exception e) {
				throw new PopulatorException(e);
			}

			this.mirror.on(instance).set().field(field).withValue(value);
		}
	}

	private boolean knows(Object value) {
		for (DefaultValue defaultValue : DefaultValue.values()) {
			if (defaultValue.value().equals(value)) {
				return true;
			}
		}

		return false;
	}

}
