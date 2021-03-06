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
package org.wave.types;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StringType {

	private String standard;

	@Size(min = 1)
	private String size;

	@DecimalMin(value = "2")
	private String decimalMin;

	@Pattern(regexp = "[ab]{4,6}c")
	private String pattern;

	public String getStandard() {
		return this.standard;
	}

	public String getSize() {
		return this.size;
	}

	public String getDecimalMin() {
		return this.decimalMin;
	}

	public String getPattern() {
		return this.pattern;
	}

}
