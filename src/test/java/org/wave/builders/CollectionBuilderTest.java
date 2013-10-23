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
package org.wave.builders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;

public class CollectionBuilderTest {

	private CollectionBuilder builder;

	@Before
	public void inicializa() {
		this.builder = new CollectionBuilder(List.class, String.class);
	}

	@Test
	public void deveRecusarColecoesVazias() {
		try {
			this.builder.numberOfElements(0);

			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(Error.ILLEGAL_NUMBER_OF_ELEMENTS.message(CollectionBuilder.NUMBER_OF_ELEMENTS.toString())));
		}
	}

	@Test
	public void naoDeveGerarColecoesVazias() {
		Collection<Object> collection = this.builder.build();

		assertThat(collection.size(), equalTo(1));
		assertThat(collection, hasItem(""));
	}

	@Test
	public void deveGerarColecoesComElementosPadronizados() {
		this.builder.numberOfElements(2);

		Collection<Object> collection = this.builder.build();

		assertThat(collection.size(), equalTo(2));
		assertThat(collection, hasItem(""));
	}

	@Test
	public void deveGerarColecoesComElementosPredefinidos() {
		Object element01 = "X";
		Object element02 = null;
		this.builder.elements(element01, element02);

		Collection<Object> collection = this.builder.build();

		assertThat(collection.size(), equalTo(2));
		assertThat(collection, hasItems(element01, element02));
	}

	@Test
	public void deveGerarColecoesComElementosPadronizadosEPredefinidos() {
		Object element = "X";
		this.builder.elements(element);
		this.builder.numberOfElements(2);

		Collection<Object> collection = this.builder.build();

		assertThat(collection.size(), equalTo(2));
		assertThat(collection, hasItems(element, ""));
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoNaoForPossivelInstanciarElementosDaColecao() {
		this.builder = new CollectionBuilder(List.class, Serializable.class);

		this.builder.build();
	}

}
