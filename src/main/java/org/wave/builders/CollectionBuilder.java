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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;

public class CollectionBuilder {

	static final Integer NUMBER_OF_ELEMENTS = 1;

	private final Class<?> type;

	private final Class<?> typeArgument;

	private int numberOfElements;

	private Object[] elements;

	public CollectionBuilder(Class<?> type, Class<?> typeArgument) {
		this.type = type;
		this.typeArgument = typeArgument;

		this.numberOfElements = NUMBER_OF_ELEMENTS;
	}

	public void numberOfElements(int numberOfElements) {
		if (numberOfElements < NUMBER_OF_ELEMENTS) {
			throw new IllegalArgumentException(Error.ILLEGAL_NUMBER_OF_ELEMENTS.message(NUMBER_OF_ELEMENTS.toString()));
		}

		this.numberOfElements = numberOfElements;
	}

	public void elements(Object... elements) {
		this.elements = elements;
	}

	public Collection<Object> build() {
		Collection<Object> collection = this.createCollection(this.type);

		if (this.elements != null) {
			for (Object element : this.elements) {
				collection.add(element);

				this.numberOfElements--;
			}
		}

		for (int i = 0; i < this.numberOfElements; i++) {
			Object element = this.createElement(this.typeArgument);

			collection.add(element);
		}

		return collection;
	}

	private Collection<Object> createCollection(Class<?> type) {
		if (type.equals(Set.class)) {
			return new HashSet<Object>();
		}

		if (type.equals(Queue.class) || type.equals(Deque.class)) {
			return new LinkedList<Object>();
		}

		if (type.equals(SortedSet.class)) {
			return new TreeSet<Object>();
		}

		return new ArrayList<Object>();
	}

	private Object createElement(Class<?> typeArgument) {
		try {
			return typeArgument.newInstance();
		} catch (InstantiationException e) {
			throw new PopulatorException(e);
		} catch (IllegalAccessException e) {
			throw new PopulatorException(e);
		}
	}

}
