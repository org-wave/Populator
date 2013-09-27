package org.wave.enums;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public enum BasicTypes {

	BYTE(byte.class, Byte.class),
	SHORT(short.class, Short.class),
	CHAR(char.class, Character.class),
	INT(int.class, Integer.class),
	LONG(long.class, Long.class),
	FLOAT(float.class, Float.class),
	DOUBLE(double.class, Double.class),
	BOOLEAN(boolean.class, Boolean.class),
	STRING(String.class),
	BIG_INTEGER(BigInteger.class),
	BIG_DECIMAL(BigDecimal.class),
	DATE(Date.class),
	CALENDAR(Calendar.class);

	private Class<?>[] types;

	private BasicTypes(Class<?>... types) {
		this.types = types;
	}

	public static boolean contains(Class<?> type) {
		BasicTypes[] basicTypes = values();

		for (BasicTypes basicType : basicTypes) {
			for (int i = 0; i < basicType.types.length; i++) {
				if (basicType.types[i].equals(type)) {
					return true;
				}
			}
		}

		return type.isEnum() || type.isArray();
	}

}
