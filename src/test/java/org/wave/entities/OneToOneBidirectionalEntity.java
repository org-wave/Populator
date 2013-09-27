package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneBidirectionalEntity {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(mappedBy = "entity")
	private OneToOneBidirectionalOwner owner;

	public Long getId() {
		return this.id;
	}

	public OneToOneBidirectionalOwner getOwner() {
		return this.owner;
	}

}
