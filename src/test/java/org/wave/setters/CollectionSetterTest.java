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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;
import org.wave.Populator;
import org.wave.entities.BasicEntity;
import org.wave.entities.CollectionType;
import org.wave.messages.Error;
import org.wave.types.EntityType;

public class CollectionSetterTest {

	private Populator populator;

	private Setter next;

	private Setter setter;

	@Before
	public void inicializa() {
		Mirror mirror = new Mirror();
		this.populator = mock(Populator.class);
		this.next = mock(Setter.class);

		this.setter = new CollectionSetter(mirror, this.next, this.populator);
	}

	@Test
	public void deveRecusarColecoesSemTipagemDosElementos() throws NoSuchFieldException, SecurityException {
		CollectionType instance = new CollectionType();

		try {
			Field rawtype = instance.getClass().getDeclaredField("rawtype");
			this.setter.setValue(rawtype, instance);

			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(Error.TYPE_PARAMETER_NOT_FOUND.message()));
		}
	}

	@Test
	public void deveRecusarColecoesCujaTipagemDosElementosEInesperada() throws NoSuchFieldException, SecurityException {
		CollectionType instance = new CollectionType();

		try {
			Field unexpected = instance.getClass().getDeclaredField("unexpected");
			this.setter.setValue(unexpected, instance);

			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(Error.UNEXPECTED_TYPE.message(String.class.getName())));
		}
	}

	@Test
	public void deveAtribuirValoresParaColecoesNulas() throws NoSuchFieldException, SecurityException {
		CollectionType instance = new CollectionType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field size = instance.getClass().getDeclaredField("size");
		this.setter.setValue(size, instance);

		assertThat(instance.getStandard().size(), equalTo(1));
		assertThat(instance.getSize().size(), equalTo(2));

		verify(this.populator, times(3)).populate(new BasicEntity());
	}

	@Test
	public void deveAtribuirValoresParaColecoesVazias() throws NoSuchFieldException, SecurityException {
		List<BasicEntity> collection = new ArrayList<BasicEntity>();

		CollectionType instance = new CollectionType();
		instance.setStandard(collection);
		instance.setSize(collection);

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field size = instance.getClass().getDeclaredField("size");
		this.setter.setValue(size, instance);

		assertThat(instance.getStandard().size(), equalTo(1));
		assertThat(instance.getSize().size(), equalTo(2));

		verify(this.populator, times(3)).populate(new BasicEntity());
	}

	@Test
	public void deveAtribuirValoresParaColecoesComElementosPredefinidos() throws NoSuchFieldException, SecurityException {
		List<BasicEntity> collection = new ArrayList<BasicEntity>();
		collection.add(new BasicEntity());

		CollectionType instance = new CollectionType();
		instance.setStandard(collection);
		instance.setSize(collection);

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field size = instance.getClass().getDeclaredField("size");
		this.setter.setValue(size, instance);

		assertThat(instance.getStandard().size(), equalTo(1));
		assertThat(instance.getSize().size(), equalTo(2));

		verify(this.populator, times(3)).populate(new BasicEntity());
	}

	@Test
	public void naoDeveAtribuirValoresParaOsDemaisTipos() throws NoSuchFieldException, SecurityException {
		EntityType instance = new EntityType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		verify(this.next).setValue(standard, instance);
	}

}
