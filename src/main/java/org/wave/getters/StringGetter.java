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

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import nl.flotsam.xeger.Xeger;

public class StringGetter extends Getter {

	@Override
	public String getValue(Field field) {
		if (field.isAnnotationPresent(Size.class)) {
			int min = field.getAnnotation(Size.class).min();

			if (min != 0) {
				return this.createString(min);
			}
		}

		if (field.isAnnotationPresent(DecimalMin.class)) {
			return field.getAnnotation(DecimalMin.class).value();
		}

		if (field.isAnnotationPresent(Pattern.class)) {
			String regexp = field.getAnnotation(Pattern.class).regexp();

			Xeger xeger = new Xeger(regexp);
			return xeger.generate();
		}

		return "";
	}

	private String createString(int length) {
		char[] chars = new char[length];

		for (int i = 0; i < chars.length; i++) {
			chars[i] = ' ';
		}

		return String.valueOf(chars);
	}

}
