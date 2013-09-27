package org.wave;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wave.classes.EmbeddableClass;
import org.wave.entities.BasicEntity;
import org.wave.entities.EmbeddedEntity;
import org.wave.entities.IllegalEntity;
import org.wave.entities.NullEntity;
import org.wave.entities.OneToOneBidirectionalEntity;
import org.wave.entities.OneToOneBidirectionalOwner;
import org.wave.entities.OneToOneEntity;
import org.wave.entities.PrimaryKeyEntity;
import org.wave.entities.StaticEntity;
import org.wave.entities.UnsupportedTypeEntity;
import org.wave.enums.NotEmptyEnum;
import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;

public class PopulatorTest {

	private static EntityManagerFactory factory;

	private EntityManager entityManager;

	private Populator populator;

	@BeforeClass
	public static void carrega() {
		factory = Persistence.createEntityManagerFactory("populatorPU");
	}

	@Before
	public void inicializa() {
		this.entityManager = factory.createEntityManager();

		this.populator = new Populator(this.entityManager);

		this.entityManager.getTransaction().begin();
	}

	@Test
	public void deveRecusarReferenciaNula() {
		try {
			this.populator.populate(null);

			fail();
		} catch (NullPointerException e) {
			assertThat(e.getMessage(), equalTo(Error.NULL_REFERENCE.getMessage()));
		}
	}

	@Test
	public void deveRecusarTipoInvalido() {
		try {
			this.populator.populate("");

			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), equalTo(Error.INVALID_TYPE.getMessage(String.class.getName())));
		}
	}

	@Test
	public void naoDevePreencherAtributoDeClasse() {
		StaticEntity entity = new StaticEntity();

		this.populator.populate(entity);

		assertNull(StaticEntity.getDefaultValue());
	}

	@Test
	public void naoDevePreencherChavePrimaria() {
		PrimaryKeyEntity entity = new PrimaryKeyEntity();

		try {
			this.populator.populate(entity);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void naoDevePreencherAtributoNulo() {
		NullEntity entity = new NullEntity();

		this.populator.populate(entity);

		assertNull(entity.getNullValue());
	}

	@Test
	public void devePreencherAtributoNaoNulo() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, 10);

		BasicEntity entity = new BasicEntity();
		entity.setCalendar(today);

		this.populator.populate(entity);

		assertFalse(this.entityManager.contains(entity));

		BasicEntity object = this.entityManager.find(BasicEntity.class, entity.getId());
		assertNotNull(object.getId());
		assertThat(object.getPrimitive(), equalTo(0));
		assertThat(object.getWrapper(), equalTo(false));
		assertThat(object.getString(), equalTo(""));
		assertThat(object.getCalendar().get(Calendar.DAY_OF_MONTH), equalTo(today.get(Calendar.DAY_OF_MONTH)));
		assertThat(object.getArray(), equalTo(new byte[0]));
		assertThat(object.getEnumeration(), equalTo(NotEmptyEnum.CONSTANT));
		assertNull(object.getAtTransient());
	}

	@Test
	public void devePreencherObjetoEmbutido() {
		EmbeddedEntity entity = new EmbeddedEntity();

		this.populator.populate(entity);

		EmbeddedEntity object = this.entityManager.find(EmbeddedEntity.class, entity.getId());
		assertNotNull(object.getId());

		EmbeddableClass embeddedValue = object.getEmbeddedValue();
		assertThat(embeddedValue.getPrimitive(), equalTo(0));
		assertThat(embeddedValue.getWrapper(), equalTo(false));
		assertThat(embeddedValue.getString(), equalTo(""));
		assertThat(embeddedValue.getCalendar().get(Calendar.DAY_OF_MONTH), equalTo(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
		assertThat(embeddedValue.getArray(), equalTo(new byte[0]));
		assertThat(embeddedValue.getEnumeration(), equalTo(NotEmptyEnum.CONSTANT));
		assertNull(embeddedValue.getAtTransient());
	}

	@Test
	public void devePreencherRelacionamentoOneToOne() {
		OneToOneEntity entity = new OneToOneEntity();

		this.populator.populate(entity);

		assertFalse(this.entityManager.contains(entity));

		OneToOneEntity object = this.entityManager.find(OneToOneEntity.class, entity.getId());
		assertNotNull(object.getId());

		BasicEntity basicEntity = object.getBasicEntity();
		assertNotNull(basicEntity.getId());
	}

	@Test
	public void devePreencherRelacionamentoOneToOneBidirecional() {
		OneToOneBidirectionalEntity entity = new OneToOneBidirectionalEntity();

		this.populator.populate(entity);

		assertFalse(this.entityManager.contains(entity));

		OneToOneBidirectionalEntity object = this.entityManager.find(OneToOneBidirectionalEntity.class, entity.getId());
		assertNotNull(object.getId());

		OneToOneBidirectionalOwner owner = object.getOwner();
		assertNotNull(owner.getId());
		assertThat(owner.getEntity(), equalTo(entity));
	}

	@Test
	public void deveRecusarMapas() {
		UnsupportedTypeEntity entity = new UnsupportedTypeEntity();

		try {
			this.populator.populate(entity);

			fail();
		} catch (UnsupportedOperationException e) {
			assertThat(e.getMessage(), equalTo(Error.UNSUPPORTED_TYPE.getMessage(Map.class.getName())));
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoOObjetoNaoForPersistido() {
		IllegalEntity entity = new IllegalEntity();

		this.populator.populate(entity);
	}

	// TODO Deve lancar excecao para autorelacionamento.

	@After
	public void finaliza() {
		this.entityManager.getTransaction().rollback();
		this.entityManager.close();
	}

	@AfterClass
	public static void libera() {
		factory.close();
	}

}
