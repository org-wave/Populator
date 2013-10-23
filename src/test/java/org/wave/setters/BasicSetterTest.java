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
package org.wave.setters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;
import org.wave.enums.NotEmptyEnum;
import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;
import org.wave.types.BigDecimalType;
import org.wave.types.BigIntegerType;
import org.wave.types.BooleanType;
import org.wave.types.ByteArrayType;
import org.wave.types.ByteType;
import org.wave.types.CalendarType;
import org.wave.types.CharArrayType;
import org.wave.types.CharType;
import org.wave.types.DateType;
import org.wave.types.DoubleType;
import org.wave.types.EnumType;
import org.wave.types.FloatType;
import org.wave.types.IntType;
import org.wave.types.LongType;
import org.wave.types.MapType;
import org.wave.types.SerializableType;
import org.wave.types.ShortType;
import org.wave.types.StringType;

public class BasicSetterTest {

	private Setter setter;

	@Before
	public void inicializa() {
		Mirror mirror = new Mirror();

		this.setter = new BasicSetter(mirror);
	}

	@Test
	public void naoDeveModificarValoresPredefinidos() throws SecurityException, NoSuchFieldException {
		Serializable value = "String";

		SerializableType instance = new SerializableType();
		instance.setStandard(value);

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		assertThat(instance.getStandard(), equalTo(value));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaByte() throws SecurityException, NoSuchFieldException {
		ByteType instance = new ByteType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field min = instance.getClass().getDeclaredField("min");
		this.setter.setValue(min, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		assertThat(instance.getStandard(), equalTo((byte) 0));
		assertThat(instance.getMin(), equalTo((byte) 1));
		assertThat(instance.getDecimalMin(), equalTo((byte) 2));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaShort() throws SecurityException, NoSuchFieldException {
		ShortType instance = new ShortType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field min = instance.getClass().getDeclaredField("min");
		this.setter.setValue(min, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		assertThat(instance.getStandard(), equalTo((short) 0));
		assertThat(instance.getMin(), equalTo((short) 1));
		assertThat(instance.getDecimalMin(), equalTo((short) 2));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaChar() throws SecurityException, NoSuchFieldException {
		CharType instance = new CharType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		assertThat(instance.getStandard(), equalTo(' '));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaInt() throws SecurityException, NoSuchFieldException {
		IntType instance = new IntType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field min = instance.getClass().getDeclaredField("min");
		this.setter.setValue(min, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		assertThat(instance.getStandard(), equalTo(0));
		assertThat(instance.getMin(), equalTo(1));
		assertThat(instance.getDecimalMin(), equalTo(2));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaLong() throws SecurityException, NoSuchFieldException {
		LongType instance = new LongType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field min = instance.getClass().getDeclaredField("min");
		this.setter.setValue(min, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		assertThat(instance.getStandard(), equalTo(0L));
		assertThat(instance.getMin(), equalTo(1L));
		assertThat(instance.getDecimalMin(), equalTo(2L));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaFloat() throws SecurityException, NoSuchFieldException {
		FloatType instance = new FloatType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		assertThat(instance.getStandard(), equalTo(0F));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaDouble() throws SecurityException, NoSuchFieldException {
		DoubleType instance = new DoubleType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		assertThat(instance.getStandard(), equalTo(0D));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaBoolean() throws SecurityException, NoSuchFieldException {
		BooleanType instance = new BooleanType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field assertTrue = instance.getClass().getDeclaredField("assertTrue");
		this.setter.setValue(assertTrue, instance);

		Field assertFalse = instance.getClass().getDeclaredField("assertFalse");
		this.setter.setValue(assertFalse, instance);

		assertFalse(instance.isStandard());
		assertTrue(instance.isAssertTrue());
		assertFalse(instance.isAssertFalse());
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaString() throws SecurityException, NoSuchFieldException {
		StringType instance = new StringType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field size = instance.getClass().getDeclaredField("size");
		this.setter.setValue(size, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		Field pattern = instance.getClass().getDeclaredField("pattern");
		this.setter.setValue(pattern, instance);

		assertThat(instance.getStandard(), equalTo(""));
		assertThat(instance.getSize(), equalTo(" "));
		assertThat(instance.getDecimalMin(), equalTo("2"));
		assertTrue(instance.getPattern().matches("[ab]{4,6}c"));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaBigInteger() throws SecurityException, NoSuchFieldException {
		BigIntegerType instance = new BigIntegerType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field min = instance.getClass().getDeclaredField("min");
		this.setter.setValue(min, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		assertThat(instance.getStandard(), equalTo(BigInteger.ZERO));
		assertThat(instance.getMin(), equalTo(BigInteger.ONE));
		assertThat(instance.getDecimalMin(), equalTo(BigInteger.TEN));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaBigDecimal() throws SecurityException, NoSuchFieldException {
		BigDecimalType instance = new BigDecimalType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field min = instance.getClass().getDeclaredField("min");
		this.setter.setValue(min, instance);

		Field decimalMin = instance.getClass().getDeclaredField("decimalMin");
		this.setter.setValue(decimalMin, instance);

		assertThat(instance.getStandard(), equalTo(BigDecimal.ZERO));
		assertThat(instance.getMin(), equalTo(BigDecimal.ONE));
		assertThat(instance.getDecimalMin(), equalTo(BigDecimal.TEN));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaDate() throws SecurityException, NoSuchFieldException {
		DateType instance = new DateType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field past = instance.getClass().getDeclaredField("past");
		this.setter.setValue(past, instance);

		Field future = instance.getClass().getDeclaredField("future");
		this.setter.setValue(future, instance);

		Calendar calendar = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		calendar.setTime(instance.getStandard());
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), equalTo(today.get(Calendar.DAY_OF_MONTH)));

		Calendar yesterday = (Calendar) today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);

		calendar.setTime(instance.getPast());
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), equalTo(yesterday.get(Calendar.DAY_OF_MONTH)));

		Calendar tomorrow = (Calendar) today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);

		calendar.setTime(instance.getFuture());
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), equalTo(tomorrow.get(Calendar.DAY_OF_MONTH)));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaCalendar() throws SecurityException, NoSuchFieldException {
		CalendarType instance = new CalendarType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field past = instance.getClass().getDeclaredField("past");
		this.setter.setValue(past, instance);

		Field future = instance.getClass().getDeclaredField("future");
		this.setter.setValue(future, instance);

		Calendar today = Calendar.getInstance();
		assertThat(instance.getStandard().get(Calendar.DAY_OF_MONTH), equalTo(today.get(Calendar.DAY_OF_MONTH)));

		Calendar yesterday = (Calendar) today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		assertThat(instance.getPast().get(Calendar.DAY_OF_MONTH), equalTo(yesterday.get(Calendar.DAY_OF_MONTH)));

		Calendar tomorrow = (Calendar) today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		assertThat(instance.getFuture().get(Calendar.DAY_OF_MONTH), equalTo(tomorrow.get(Calendar.DAY_OF_MONTH)));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaEnum() throws SecurityException, NoSuchFieldException {
		EnumType instance = new EnumType();

		Field notEmpty = instance.getClass().getDeclaredField("notEmpty");
		this.setter.setValue(notEmpty, instance);

		assertThat(instance.getNotEmpty(), equalTo(NotEmptyEnum.CONSTANT));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaArrayDeBytes() throws NoSuchFieldException, SecurityException {
		ByteArrayType instance = new ByteArrayType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field size = instance.getClass().getDeclaredField("size");
		this.setter.setValue(size, instance);

		assertThat(instance.getStandard().length, equalTo(0));

		assertThat(instance.getSize().length, equalTo(1));
		assertThat(instance.getSize()[0], equalTo((byte) 0));
	}

	@Test
	public void deveAtribuirValoresPadronizadosParaArrayDeCaracteres() throws NoSuchFieldException, SecurityException {
		CharArrayType instance = new CharArrayType();

		Field standard = instance.getClass().getDeclaredField("standard");
		this.setter.setValue(standard, instance);

		Field size = instance.getClass().getDeclaredField("size");
		this.setter.setValue(size, instance);

		assertThat(instance.getStandard().length, equalTo(0));

		assertThat(instance.getSize().length, equalTo(1));
		assertThat(instance.getSize()[0], equalTo(' '));
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoNaoForPossivelAtribuirValores() throws SecurityException, NoSuchFieldException {
		EnumType instance = new EnumType();

		Field empty = instance.getClass().getDeclaredField("empty");
		this.setter.setValue(empty, instance);
	}

	@Test
	public void deveRecusarTiposNaoSuportados() throws NoSuchFieldException, SecurityException {
		MapType instance = new MapType();

		try {
			Field standard = instance.getClass().getDeclaredField("standard");
			this.setter.setValue(standard, instance);

			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(Error.UNSUPPORTED_TYPE.message(Map.class.getName())));
		}
	}

}
