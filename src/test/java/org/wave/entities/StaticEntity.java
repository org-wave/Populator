package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StaticEntity {

	private static String defaultValue;

	@Id
	@GeneratedValue
	private Long id;

	public static String getDefaultValue() {
		return defaultValue;
	}

	public Long getId() {
		return this.id;
	}

}
