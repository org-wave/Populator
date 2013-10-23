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
import javax.validation.constraints.Min;

public class IntType {

	private int standard;

	@Min(value = 1)
	private int min;

	@DecimalMin(value = "2")
	private int decimalMin;

	public int getStandard() {
		return this.standard;
	}

	public int getMin() {
		return this.min;
	}

	public int getDecimalMin() {
		return this.decimalMin;
	}

}
