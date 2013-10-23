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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import net.vidageek.mirror.dsl.Mirror;

import org.wave.Populator;
import org.wave.builders.CollectionBuilder;
import org.wave.enums.CollectionType;
import org.wave.messages.Error;

public class CollectionSetter extends Setter {

	private final Populator populator;

	public CollectionSetter(Mirror mirror, Setter next, Populator populator) {
		super(mirror, next);
		this.populator = populator;
	}

	@Override
	public void setValue(Field field, Object instance) {
		Class<?> type = field.getType();

		if (this.accepts(type)) {
			Type genericType = field.getGenericType();

			this.validate(genericType);

			Class<?> typeArgument = this.getTypeArgument(genericType);

			this.validate(typeArgument);

			CollectionBuilder builder = new CollectionBuilder(type, typeArgument);

			Collection<?> value = (Collection<?>) this.mirror.on(instance).get().field(field);

			if (value != null) {
				builder.elements(value.toArray());
			}

			builder.numberOfElements(this.getNumberOfElements(field));
			Collection<Object> collection = builder.build();

			for (Object element : collection) {
				this.populator.populate(element);
			}

			this.mirror.on(instance).set().field(field).withValue(collection);
		} else {
			this.next.setValue(field, instance);
		}
	}

	private boolean accepts(Class<?> type) {
		for (CollectionType collectionType : CollectionType.values()) {
			if (collectionType.type().equals(type)) {
				return true;
			}
		}

		return false;
	}

	private void validate(Type genericType) {
		boolean hasTypeParameter = genericType instanceof ParameterizedType;

		if (!hasTypeParameter) {
			throw new IllegalArgumentException(Error.TYPE_PARAMETER_NOT_FOUND.message());
		}
	}

	private Class<?> getTypeArgument(Type genericType) {
		ParameterizedType parameterizedType = (ParameterizedType) genericType;

		return (Class<?>) parameterizedType.getActualTypeArguments()[0];
	}

	private void validate(Class<?> typeArgument) {
		boolean isEntity = typeArgument.isAnnotationPresent(Entity.class);

		if (!isEntity) {
			throw new IllegalArgumentException(Error.UNEXPECTED_TYPE.message(typeArgument.getName()));
		}
	}

	private int getNumberOfElements(Field field) {
		if (field.isAnnotationPresent(Size.class)) {
			int min = field.getAnnotation(Size.class).min();

			if (min != 0) {
				return min;
			}
		}

		return 1;
	}

}
