package org.wave.getters;

import java.lang.reflect.Field;
import java.util.Calendar;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

public class CalendarGetter extends Getter {

	@Override
	public Calendar getValue(Field field) {
		Calendar value = Calendar.getInstance();

		if (field.isAnnotationPresent(Past.class)) {
			value = (Calendar) value.clone();
			value.add(Calendar.DAY_OF_MONTH, -1);
		}

		if (field.isAnnotationPresent(Future.class)) {
			value = (Calendar) value.clone();
			value.add(Calendar.DAY_OF_MONTH, 1);
		}

		return value;
	}

}
