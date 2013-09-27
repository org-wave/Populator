package org.wave.messages;

import java.text.MessageFormat;

public enum Error {

	NULL_REFERENCE("This reference is null."),
	INVALID_TYPE("{0} is not an entity or embeddable class."),
	EMPTY_ENUM("{0} does not have enum constants."),
	UNEXPECTED_TYPE("{0} is an unexpected type."),
	UNSUPPORTED_TYPE("{0} is an unsupported type.");

	private String value;

	private Error(String value) {
		this.value = value;
	}

	public String getMessage(String... params) {
		return new MessageFormat(this.value).format(params);
	}

}
