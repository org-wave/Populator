package org.wave.entities;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.wave.enums.NotEmptyEnum;

@Entity
public class BasicEntity {

	@Id
	@GeneratedValue
	private Long id;

	private int primitive;

	private Boolean wrapper;

	private String string;

	private Calendar calendar;

	private byte[] array;

	@Enumerated(EnumType.STRING)
	private NotEmptyEnum enumeration;

	@Transient
	private BigDecimal atTransient;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.array);
		result = prime * result + ((this.atTransient == null) ? 0 : this.atTransient.hashCode());
		result = prime * result + ((this.calendar == null) ? 0 : this.calendar.hashCode());
		result = prime * result + ((this.enumeration == null) ? 0 : this.enumeration.hashCode());
		result = prime * result + this.primitive;
		result = prime * result + ((this.string == null) ? 0 : this.string.hashCode());
		result = prime * result + ((this.wrapper == null) ? 0 : this.wrapper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasicEntity other = (BasicEntity) obj;
		if (!Arrays.equals(this.array, other.array))
			return false;
		if (this.atTransient == null) {
			if (other.atTransient != null)
				return false;
		} else if (!this.atTransient.equals(other.atTransient))
			return false;
		if (this.calendar == null) {
			if (other.calendar != null)
				return false;
		} else if (!this.calendar.equals(other.calendar))
			return false;
		if (this.enumeration != other.enumeration)
			return false;
		if (this.primitive != other.primitive)
			return false;
		if (this.string == null) {
			if (other.string != null)
				return false;
		} else if (!this.string.equals(other.string))
			return false;
		if (this.wrapper == null) {
			if (other.wrapper != null)
				return false;
		} else if (!this.wrapper.equals(other.wrapper))
			return false;
		return true;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Long getId() {
		return this.id;
	}

	public int getPrimitive() {
		return this.primitive;
	}

	public Boolean getWrapper() {
		return this.wrapper;
	}

	public String getString() {
		return this.string;
	}

	public byte[] getArray() {
		return this.array;
	}

	public NotEmptyEnum getEnumeration() {
		return this.enumeration;
	}

	public BigDecimal getAtTransient() {
		return this.atTransient;
	}

}
