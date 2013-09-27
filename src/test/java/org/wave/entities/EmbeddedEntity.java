package org.wave.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.wave.classes.EmbeddableClass;

@Entity
public class EmbeddedEntity {

	@Id
	@GeneratedValue
	private Long id;

	private EmbeddableClass embeddedValue;

	public Long getId() {
		return this.id;
	}

	public EmbeddableClass getEmbeddedValue() {
		return this.embeddedValue;
	}

}
