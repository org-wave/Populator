package org.wave.enums;

import java.util.Map;
import java.util.SortedMap;

public enum MapTypes {

	MAP(Map.class),
	SORTED_MAP(SortedMap.class);

	private Class<?> type;

	private MapTypes(Class<?> type) {
		this.type = type;
	}

	public static boolean contains(Class<?> type) {
		MapTypes[] mapTypes = values();

		for (MapTypes mapType : mapTypes) {
			if (mapType.type.equals(type)) {
				return true;
			}
		}

		return false;
	}

}
