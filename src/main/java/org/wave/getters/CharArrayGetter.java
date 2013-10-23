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

import javax.validation.constraints.Size;

public class CharArrayGetter extends Getter {

	@Override
	public Object getValue(Field field) {
		int min = 0;

		if (field.isAnnotationPresent(Size.class)) {
			min = field.getAnnotation(Size.class).min();
		}

		if (field.getType().equals(Character[].class)) {
			Character[] chars = new Character[min];

			for (int i = 0; i < chars.length; i++) {
				chars[i] = ' ';
			}

			return chars;
		}

		return new char[min];
	}

}
