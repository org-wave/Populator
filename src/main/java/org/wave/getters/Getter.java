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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Getter {

	private static Map<Class<?>, Getter> gettersMap;

	static {
		gettersMap = new HashMap<Class<?>, Getter>();

		ByteGetter byteGetter = new ByteGetter();
		gettersMap.put(byte.class, byteGetter);
		gettersMap.put(Byte.class, byteGetter);

		ShortGetter shortGetter = new ShortGetter();
		gettersMap.put(short.class, shortGetter);
		gettersMap.put(Short.class, shortGetter);

		CharGetter charGetter = new CharGetter();
		gettersMap.put(char.class, charGetter);
		gettersMap.put(Character.class, charGetter);

		IntGetter intGetter = new IntGetter();
		gettersMap.put(int.class, intGetter);
		gettersMap.put(Integer.class, intGetter);

		LongGetter longGetter = new LongGetter();
		gettersMap.put(long.class, longGetter);
		gettersMap.put(Long.class, longGetter);

		FloatGetter floatGetter = new FloatGetter();
		gettersMap.put(float.class, floatGetter);
		gettersMap.put(Float.class, floatGetter);

		DoubleGetter doubleGetter = new DoubleGetter();
		gettersMap.put(double.class, doubleGetter);
		gettersMap.put(Double.class, doubleGetter);

		BooleanGetter booleanGetter = new BooleanGetter();
		gettersMap.put(boolean.class, booleanGetter);
		gettersMap.put(Boolean.class, booleanGetter);

		gettersMap.put(String.class, new StringGetter());
		gettersMap.put(BigInteger.class, new BigIntegerGetter());
		gettersMap.put(BigDecimal.class, new BigDecimalGetter());
		gettersMap.put(Date.class, new DateGetter());
		gettersMap.put(Calendar.class, new CalendarGetter());

		gettersMap.put(Enum.class, new EnumGetter());

		ByteArrayGetter byteArrayGetter = new ByteArrayGetter();
		gettersMap.put(byte[].class, byteArrayGetter);
		gettersMap.put(Byte[].class, byteArrayGetter);

		CharArrayGetter charArrayGetter = new CharArrayGetter();
		gettersMap.put(char[].class, charArrayGetter);
		gettersMap.put(Character[].class, charArrayGetter);
	}

	public static Getter getInstance(Class<?> type) {
		return type.isEnum() ? gettersMap.get(Enum.class) : gettersMap.get(type);
	}

	public abstract Object getValue(Field field);

}
