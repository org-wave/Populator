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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;
import org.wave.Populator;
import org.wave.embeddables.BasicEmbeddable;
import org.wave.entities.AbstractType;
import org.wave.exceptions.PopulatorException;
import org.wave.types.EmbeddableType;
import org.wave.types.EntityType;
import org.wave.types.StringType;

public class CompositeSetterTest {

	private Populator populator;

	private Setter next;

	private Setter setter;

	@Before
	public void inicializa() {
		Mirror mirror = new Mirror();
		this.populator = mock(Populator.class);
		this.next = mock(Setter.class);

		this.setter = new CompositeSetter(mirror, this.next, this.populator);
	}

	@Test
	public void deveAtribuirValoresParaEntidades() throws NoSuchFieldException, SecurityException {
		EntityType instance = new EntityType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		verify(this.populator).populate(instance.getStandard());
	}

	@Test
	public void deveAtribuirValoresParaObjetosEmbutidos() throws NoSuchFieldException, SecurityException {
		EmbeddableType instance = new EmbeddableType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		verify(this.populator).populate(instance.getStandard());
	}

	@Test
	public void deveAtribuirValoresParaObjetosPredefinidos() throws NoSuchFieldException, SecurityException {
		EmbeddableType instance = new EmbeddableType();
		instance.setStandard(new BasicEmbeddable());

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		verify(this.populator).populate(instance.getStandard());
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoNaoForPossivelInstanciarObjetos() throws NoSuchFieldException, SecurityException {
		AbstractType instance = new AbstractType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);
	}

	@Test
	public void naoDeveAtribuirValoresParaOsDemaisTipos() throws NoSuchFieldException, SecurityException {
		StringType instance = new StringType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		verify(this.next).setValue(standard, instance);
	}

}
