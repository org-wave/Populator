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
package org.wave;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.wave.exceptions.PopulatorException;
import org.wave.matchers.PersistentMatcher;
import org.wave.messages.Error;
import org.wave.setters.BasicSetter;
import org.wave.setters.CollectionSetter;
import org.wave.setters.CompositeSetter;
import org.wave.setters.Setter;

public class Populator {

	private final Mirror mirror;

	private final Setter setter;

	private final EntityManager entityManager;

	public Populator(EntityManager entityManager) {
		this.mirror = new Mirror();

		Setter basicSetter = new BasicSetter(this.mirror);
		Setter compositeSetter = new CompositeSetter(this.mirror, basicSetter, this);
		this.setter = new CollectionSetter(this.mirror, compositeSetter, this);

		this.entityManager = entityManager;
	}

	public void populate(Object instance) {
		if (instance == null) {
			throw new NullPointerException(Error.NULL_REFERENCE.message());
		}

		Class<?> clazz = instance.getClass();
		MirrorList<Field> fields = this.mirror.on(clazz).reflectAll().fields().matching(new PersistentMatcher());

		for (Field field : fields) {
			this.setter.setValue(field, instance);
		}

		// TODO E necessario colocar todas as entidades em uma lista para
		// somente depois persistir cada uma.
		boolean isEntity = clazz.isAnnotationPresent(Entity.class);

		if (isEntity) {
			this.persist(instance);
		}
	}

	private void persist(Object instance) {
		try {
			this.entityManager.persist(instance);

			this.entityManager.flush();
			this.entityManager.detach(instance);
		} catch (Exception e) {
			throw new PopulatorException(e);
		}
	}

}
