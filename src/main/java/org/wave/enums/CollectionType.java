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
package org.wave.enums;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

public enum CollectionType {

	COLLECTION(Collection.class),
	SET(Set.class),
	LIST(List.class),
	QUEUE(Queue.class),
	DEQUE(Deque.class),
	SORTED_SET(SortedSet.class);

	private final Class<?> type;

	private CollectionType(Class<?> type) {
		this.type = type;
	}

	public Class<?> type() {
		return this.type;
	}

}
