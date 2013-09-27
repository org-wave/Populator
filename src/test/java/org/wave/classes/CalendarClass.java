package org.wave.classes;

import java.util.Calendar;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

public class CalendarClass {

	private Calendar defaultValue;

	@Past
	private Calendar pastValue;

	@Future
	private Calendar futureValue;

	public Calendar getDefaultValue() {
		return this.defaultValue;
	}

	public Calendar getPastValue() {
		return this.pastValue;
	}

	public Calendar getFutureValue() {
		return this.futureValue;
	}

}
