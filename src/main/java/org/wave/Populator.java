/*Copyright 2013 Benedito Barbosa Ribeiro Neto/Christian Linhares Peixoto/Mauricio da Silva Marinho

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/
package org.wave;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.validation.constraints.Null;

import net.vidageek.mirror.dsl.Mirror;

import org.wave.enums.CollectionTypes;
import org.wave.enums.MapTypes;
import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;
import org.wave.setters.CollectionSetter;
import org.wave.setters.MapSetter;
import org.wave.setters.Setter;
import org.wave.setters.TypeSetter;

public class Populator {

	private Setter typeSetter;

	private Setter collectionSetter;

	private Setter mapSetter;

	private final EntityManager entityManager;

	public Populator(EntityManager entityManager) {
		Mirror mirror = new Mirror();

		this.typeSetter = new TypeSetter(mirror, this);
		this.collectionSetter = new CollectionSetter(mirror, this);
		this.mapSetter = new MapSetter(mirror, this);

		this.entityManager = entityManager;
	}

	public void populate(Object entity) {
		if (entity == null) {
			throw new NullPointerException(Error.NULL_REFERENCE.getMessage());
		}

		Class<?> clazz = entity.getClass();

		boolean isEntity = clazz.isAnnotationPresent(Entity.class);
		boolean isEmbeddable = clazz.isAnnotationPresent(Embeddable.class);

		if (isEntity || isEmbeddable) {
			List<Field> fields = this.getFields(clazz);

			for (Field field : fields) {
				Class<?> type = field.getType();

				Setter setter = this.getSetter(type);
				setter.setValue(field, entity);
			}
		} else {
			throw new IllegalArgumentException(Error.INVALID_TYPE.getMessage(clazz.getName()));
		}

		if (isEntity) {
			this.persist(entity);
		}
	}

	private List<Field> getFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();

		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field field : declaredFields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				if (!field.isAnnotationPresent(Id.class) && !field.isAnnotationPresent(Null.class)) {
					fields.add(field);
				}
			}
		}

		return fields;
	}

	private Setter getSetter(Class<?> type) {
		if (CollectionTypes.contains(type)) {
			return this.collectionSetter;
		}

		if (MapTypes.contains(type)) {
			return this.mapSetter;
		}

		return this.typeSetter;
	}

	private void persist(Object entity) {
		try {
			this.entityManager.persist(entity);

			this.entityManager.flush();
			this.entityManager.detach(entity);
		} catch (Exception e) {
			throw new PopulatorException(e);
		}
	}

}
