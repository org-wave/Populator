package org.wave.entities;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UnsupportedTypeEntity {

	@Id
	@GeneratedValue
	private Long id;

	@ElementCollection
	private Map<String, String> unsupportedType;

	public Long getId() {
		return this.id;
	}

	public Map<String, String> getUnsupportedType() {
		return this.unsupportedType;
	}

}
