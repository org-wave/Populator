package org.wave.setters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;
import org.wave.Populator;
import org.wave.classes.BigDecimalClass;
import org.wave.classes.BigIntegerClass;
import org.wave.classes.BooleanClass;
import org.wave.classes.ByteArrayClass;
import org.wave.classes.ByteClass;
import org.wave.classes.CalendarClass;
import org.wave.classes.CharArrayClass;
import org.wave.classes.CharClass;
import org.wave.classes.DateClass;
import org.wave.classes.DoubleClass;
import org.wave.classes.EmbeddableClass;
import org.wave.classes.EmbeddedClass;
import org.wave.classes.EntityClass;
import org.wave.classes.EnumClass;
import org.wave.classes.FloatClass;
import org.wave.classes.IntClass;
import org.wave.classes.LongClass;
import org.wave.classes.NotNullClass;
import org.wave.classes.ShortClass;
import org.wave.classes.StringClass;
import org.wave.classes.UnexpetedTypeClass;
import org.wave.entities.BasicEntity;
import org.wave.enums.NotEmptyEnum;
import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;

public class TypeSetterTest {

	private Mirror mirror;

	private Populator populator;

	private Setter setter;

	@Before
	public void inicializa() {
		this.mirror = new Mirror();
		this.populator = mock(Populator.class);

		this.setter = new TypeSetter(this.mirror, this.populator);
	}

	@Test
	public void naoDeveModificarAtributosPreenchidos() throws SecurityException, NoSuchFieldException {
		NotNullClass instance = new NotNullClass();
		instance.setNotNullValue("String");

		Field notNullValue = instance.getClass().getDeclaredField("notNullValue");
		this.setter.setValue(notNullValue, instance);

		assertThat(instance.getNotNullValue(), equalTo("String"));
	}

	@Test
	public void deveAtribuirValoresParaByte() throws SecurityException, NoSuchFieldException {
		ByteClass instance = new ByteClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		assertThat(instance.getDefaultValue(), equalTo((byte) 0));
		assertThat(instance.getMinValue(), equalTo((byte) 1));
		assertThat(instance.getDecimalMinValue(), equalTo((byte) 2));
	}

	@Test
	public void deveAtribuirValoresParaShort() throws SecurityException, NoSuchFieldException {
		ShortClass instance = new ShortClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		assertThat(instance.getDefaultValue(), equalTo((short) 0));
		assertThat(instance.getMinValue(), equalTo((short) 1));
		assertThat(instance.getDecimalMinValue(), equalTo((short) 2));
	}

	@Test
	public void deveAtribuirValoresParaChar() throws SecurityException, NoSuchFieldException {
		CharClass instance = new CharClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(' '));
	}

	@Test
	public void deveAtribuirValoresParaInt() throws SecurityException, NoSuchFieldException {
		IntClass instance = new IntClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(0));
		assertThat(instance.getMinValue(), equalTo(1));
		assertThat(instance.getDecimalMinValue(), equalTo(2));
	}

	@Test
	public void deveAtribuirValoresParaLong() throws SecurityException, NoSuchFieldException {
		LongClass instance = new LongClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(0L));
		assertThat(instance.getMinValue(), equalTo(1L));
		assertThat(instance.getDecimalMinValue(), equalTo(2L));
	}

	@Test
	public void deveAtribuirValoresParaFloat() throws SecurityException, NoSuchFieldException {
		FloatClass instance = new FloatClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(0f));
	}

	@Test
	public void deveAtribuirValoresParaDouble() throws SecurityException, NoSuchFieldException {
		DoubleClass instance = new DoubleClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(0d));
	}

	@Test
	public void deveAtribuirValoresParaBoolean() throws SecurityException, NoSuchFieldException {
		BooleanClass instance = new BooleanClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field trueValue = instance.getClass().getDeclaredField("trueValue");
		this.setter.setValue(trueValue, instance);

		Field falseValue = instance.getClass().getDeclaredField("falseValue");
		this.setter.setValue(falseValue, instance);

		assertFalse(instance.isDefaultValue());
		assertTrue(instance.isTrueValue());
		assertFalse(instance.isFalseValue());
	}

	@Test
	public void deveAtribuirValoresParaString() throws SecurityException, NoSuchFieldException {
		StringClass instance = new StringClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		Field patternValue = instance.getClass().getDeclaredField("patternValue");
		this.setter.setValue(patternValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(""));
		assertThat(instance.getMinValue(), equalTo(" "));
		assertThat(instance.getDecimalMinValue(), equalTo("1"));
		assertTrue(instance.getPatternValue().matches("[ab]{4,6}c"));
	}

	@Test
	public void deveAtribuirValoresParaBigInteger() throws SecurityException, NoSuchFieldException {
		BigIntegerClass instance = new BigIntegerClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(BigInteger.ZERO));
		assertThat(instance.getMinValue(), equalTo(BigInteger.ONE));
		assertThat(instance.getDecimalMinValue(), equalTo(BigInteger.TEN));
	}

	@Test
	public void deveAtribuirValoresParaBigDecimal() throws SecurityException, NoSuchFieldException {
		BigDecimalClass instance = new BigDecimalClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		Field decimalMinValue = instance.getClass().getDeclaredField("decimalMinValue");
		this.setter.setValue(decimalMinValue, instance);

		assertThat(instance.getDefaultValue(), equalTo(BigDecimal.ZERO));
		assertThat(instance.getMinValue(), equalTo(BigDecimal.ONE));
		assertThat(instance.getDecimalMinValue(), equalTo(BigDecimal.TEN));
	}

	@Test
	public void deveAtribuirValoresParaDate() throws SecurityException, NoSuchFieldException {
		DateClass instance = new DateClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field pastValue = instance.getClass().getDeclaredField("pastValue");
		this.setter.setValue(pastValue, instance);

		Field futureValue = instance.getClass().getDeclaredField("futureValue");
		this.setter.setValue(futureValue, instance);

		Calendar calendar = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		calendar.setTime(instance.getDefaultValue());
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), equalTo(today.get(Calendar.DAY_OF_MONTH)));

		Calendar yesterday = (Calendar) today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);

		calendar.setTime(instance.getPastValue());
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), equalTo(yesterday.get(Calendar.DAY_OF_MONTH)));

		Calendar tomorrow = (Calendar) today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);

		calendar.setTime(instance.getFutureValue());
		assertThat(calendar.get(Calendar.DAY_OF_MONTH), equalTo(tomorrow.get(Calendar.DAY_OF_MONTH)));
	}

	@Test
	public void deveAtribuirValoresParaCalendar() throws SecurityException, NoSuchFieldException {
		CalendarClass instance = new CalendarClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field pastValue = instance.getClass().getDeclaredField("pastValue");
		this.setter.setValue(pastValue, instance);

		Field futureValue = instance.getClass().getDeclaredField("futureValue");
		this.setter.setValue(futureValue, instance);

		Calendar today = Calendar.getInstance();
		assertThat(instance.getDefaultValue().get(Calendar.DAY_OF_MONTH), equalTo(today.get(Calendar.DAY_OF_MONTH)));

		Calendar yesterday = (Calendar) today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		assertThat(instance.getPastValue().get(Calendar.DAY_OF_MONTH), equalTo(yesterday.get(Calendar.DAY_OF_MONTH)));

		Calendar tomorrow = (Calendar) today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		assertThat(instance.getFutureValue().get(Calendar.DAY_OF_MONTH), equalTo(tomorrow.get(Calendar.DAY_OF_MONTH)));
	}

	@Test
	public void deveAtribuirValoresParaEnum() throws SecurityException, NoSuchFieldException {
		EnumClass instance = new EnumClass();

		Field notEmptyValue = instance.getClass().getDeclaredField("notEmptyValue");
		this.setter.setValue(notEmptyValue, instance);

		assertThat(instance.getNotEmptyValue(), equalTo(NotEmptyEnum.CONSTANT));
	}

	@Test
	public void deveAtribuirValoresParaArrayDeBytes() throws NoSuchFieldException, SecurityException {
		ByteArrayClass instance = new ByteArrayClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		assertThat(instance.getDefaultValue().length, equalTo(0));

		assertThat(instance.getMinValue().length, equalTo(1));
		assertThat(instance.getMinValue()[0], equalTo((byte) 0));
	}

	@Test
	public void deveAtribuirValoresParaArrayDeCaracteres() throws NoSuchFieldException, SecurityException {
		CharArrayClass instance = new CharArrayClass();

		Field defaultValue = instance.getClass().getDeclaredField("defaultValue");
		this.setter.setValue(defaultValue, instance);

		Field minValue = instance.getClass().getDeclaredField("minValue");
		this.setter.setValue(minValue, instance);

		assertThat(instance.getDefaultValue().length, equalTo(0));

		assertThat(instance.getMinValue().length, equalTo(1));
		assertThat(instance.getMinValue()[0], equalTo(' '));
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoNaoForPossivelAtribuirValores() throws SecurityException, NoSuchFieldException {
		EnumClass instance = new EnumClass();

		Field emptyValue = instance.getClass().getDeclaredField("emptyValue");
		this.setter.setValue(emptyValue, instance);
	}

	// TODO Fiquei aqui...

	@Test
	public void deveAtribuirValoresParaEntidade() throws NoSuchFieldException, SecurityException {
		EntityClass instance = new EntityClass();
		Field entityValue = instance.getClass().getDeclaredField("entityValue");

		this.setter.setValue(entityValue, instance);

		BasicEntity entity = new BasicEntity();
		verify(this.populator, times(1)).populate(entity);
	}

	@Test
	public void deveAtribuirValoresParaObjetoEmbutido() throws NoSuchFieldException, SecurityException {
		EmbeddedClass instance = new EmbeddedClass();
		Field embeddedValue = instance.getClass().getDeclaredField("embeddedValue");

		this.setter.setValue(embeddedValue, instance);

		EmbeddableClass embeddable = new EmbeddableClass();
		verify(this.populator, times(1)).populate(embeddable);
	}

	@Test
	public void deveRecusarTipoInesperado() throws NoSuchFieldException, SecurityException {
		UnexpetedTypeClass instance = new UnexpetedTypeClass();
		Field unexpetedType = instance.getClass().getDeclaredField("unexpetedType");

		try {
			this.setter.setValue(unexpetedType, instance);

			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(Error.UNEXPECTED_TYPE.getMessage(Serializable.class.getName())));
		}
	}

}
