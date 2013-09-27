package org.wave.classes;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

public class DateClass {

	private Date defaultValue;

	@Past
	private Date pastValue;

	@Future
	private Date futureValue;

	public Date getDefaultValue() {
		return this.defaultValue;
	}

	public Date getPastValue() {
		return this.pastValue;
	}

	public Date getFutureValue() {
		return this.futureValue;
	}

}
