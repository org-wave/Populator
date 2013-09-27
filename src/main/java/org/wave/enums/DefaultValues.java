package org.wave.enums;

public enum DefaultValues {

	BYTE((byte) 0),
	SHORT((short) 0),
	CHAR('\u0000'),
	INT(0),
	LONG(0L),
	FLOAT(0.0),
	DOUBLE(0.0),
	BOOLEAN(false);

	private Object value;

	private DefaultValues(Object value) {
		this.value = value;
	}

	public static boolean contains(Object value) {
		DefaultValues[] defaultValues = values();

		for (DefaultValues defaultValue : defaultValues) {
			if (defaultValue.value.equals(value)) {
				return true;
			}
		}

		return false;
	}

}
