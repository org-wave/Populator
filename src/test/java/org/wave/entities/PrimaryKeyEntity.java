package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PrimaryKeyEntity {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return this.id;
	}

}
