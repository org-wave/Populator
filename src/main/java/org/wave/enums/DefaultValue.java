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
package org.wave.enums;

public enum DefaultValue {

	BYTE((byte) 0),
	SHORT((short) 0),
	CHAR('\u0000'),
	INT(0),
	LONG(0L),
	FLOAT(0.0),
	DOUBLE(0.0),
	BOOLEAN(false);

	private final Object value;

	private DefaultValue(Object value) {
		this.value = value;
	}

	public Object value() {
		return this.value;
	}

}
