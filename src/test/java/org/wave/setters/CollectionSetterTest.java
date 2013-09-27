package org.wave.setters;

import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionSetterTest {

	private Collection<String> collection;

	private List<String> list;

	private Set<String> set;

	private byte[] byteArray;

	private Byte[] x;

	private char[] charArray;

	private Character[] y;

	private BigDecimal decimal;

	// @Test
	public void test() {
		fail("Not yet implemented");
	}

	public static void main(String[] args) {
		Field[] fields = CollectionSetterTest.class.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getType().isArray());
			System.out.println(field.getType().getSimpleName().substring(0, 3).toLowerCase());
			System.out.println(field.getType().getSimpleName());
			System.out.println(field.getType());
			System.out.println();
		}

		// byte[] v = new byte[0];
		// Byte[] z = new Byte[0];
		// z = v;
	}

}
