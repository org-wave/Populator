/*
   Copyright 2013 Benedito Barbosa Ribeiro Neto/Christian Linhares Peixoto/Mauricio da Silva Marinho

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.wave.embeddables;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.wave.enums.NotEmptyEnum;

@Embeddable
public class BasicEmbeddable {

	private int primitive;

	private Boolean wrapper;

	private String string;

	@Transient
	private BigDecimal atTransient;

	private Calendar calendar;

	@Enumerated(EnumType.STRING)
	private NotEmptyEnum enumeration;

	private byte[] array;

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
		BasicEmbeddable other = (BasicEmbeddable) obj;
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

	public int getPrimitive() {
		return this.primitive;
	}

	public Boolean getWrapper() {
		return this.wrapper;
	}

	public String getString() {
		return this.string;
	}

	public BigDecimal getAtTransient() {
		return this.atTransient;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public NotEmptyEnum getEnumeration() {
		return this.enumeration;
	}

	public byte[] getArray() {
		return this.array;
	}

}
