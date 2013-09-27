package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneBidirectionalOwner {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private OneToOneBidirectionalEntity entity;

	public Long getId() {
		return this.id;
	}

	public OneToOneBidirectionalEntity getEntity() {
		return this.entity;
	}

}
