package org.wave.messages;

import java.text.MessageFormat;

public enum Error {

	NULL_REFERENCE("This reference is null."),
	UNEXPECTED_TYPE("{0} is an unexpected type."),
	TYPE_PARAMETER_NOT_FOUND("Type parameter not found"),
	ILLEGAL_NUMBER_OF_ELEMENTS("The number of elements must be greater than or equal to {0}."),
	EMPTY_ENUM("{0} does not have enum constants."),
	UNSUPPORTED_TYPE("{0} is an unsupported type.");

	private final String value;

	private Error(String value) {
		this.value = value;
	}

	public String message(String... params) {
		return new MessageFormat(this.value).format(params);
	}

}
