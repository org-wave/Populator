package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Entity
public class NullEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Null
	private String nullValue;

	public Long getId() {
		return this.id;
	}

	public String getNullValue() {
		return this.nullValue;
	}

}
