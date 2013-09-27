package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneEntity {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(optional = false)
	private BasicEntity basicEntity;

	public Long getId() {
		return this.id;
	}

	public BasicEntity getBasicEntity() {
		return this.basicEntity;
	}

}
