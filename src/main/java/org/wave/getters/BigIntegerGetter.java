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
package org.wave.getters;

import java.lang.reflect.Field;
import java.math.BigInteger;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class BigIntegerGetter extends Getter {

	@Override
	public BigInteger getValue(Field field) {
		if (field.isAnnotationPresent(Min.class)) {
			long value = field.getAnnotation(Min.class).value();

			return BigInteger.valueOf(value);
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			String value = field.getAnnotation(DecimalMin.class).value();

			return new BigInteger(value);
		}

		return BigInteger.ZERO;
	}

}
