package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IllegalEntity {

	@Id
	@GeneratedValue
	private String id;

	public String getId() {
		return this.id;
	}

}
