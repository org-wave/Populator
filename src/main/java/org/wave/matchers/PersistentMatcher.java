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
package org.wave.matchers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.persistence.Id;
import javax.validation.constraints.Null;

import net.vidageek.mirror.list.dsl.Matcher;

public class PersistentMatcher implements Matcher<Field> {

	@Override
	public boolean accepts(Field field) {
		boolean isStatic = Modifier.isStatic(field.getModifiers());

		if (isStatic) {
			return false;
		}

		boolean isIdentifier = field.isAnnotationPresent(Id.class);
		boolean isNull = field.isAnnotationPresent(Null.class);

		if (isIdentifier || isNull) {
			return false;
		}

		return true;
	}

}
