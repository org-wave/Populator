package org.wave.enums;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

public enum CollectionTypes {

	COLLECTION(Collection.class),
	SET(Set.class),
	LIST(List.class),
	QUEUE(Queue.class),
	DEQUE(Deque.class),
	SORTED_SET(SortedSet.class);

	private Class<?> type;

	private CollectionTypes(Class<?> type) {
		this.type = type;
	}

	public static boolean contains(Class<?> type) {
		CollectionTypes[] collectionTypes = values();

		for (CollectionTypes collectionType : collectionTypes) {
			if (collectionType.type.equals(type)) {
				return true;
			}
		}

		return false;
	}

}
