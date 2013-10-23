package org.wave;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wave.embeddables.BasicEmbeddable;
import org.wave.entities.BasicEntity;
import org.wave.entities.EmbeddedEntity;
import org.wave.entities.IllegalEntity;
import org.wave.entities.OneToOneBidirectionalEntity;
import org.wave.entities.OneToOneBidirectionalOwner;
import org.wave.entities.OneToOneEntity;
import org.wave.enums.NotEmptyEnum;
import org.wave.exceptions.PopulatorException;
import org.wave.messages.Error;
import org.wave.types.IdentifierType;
import org.wave.types.NotNullType;
import org.wave.types.NullType;
import org.wave.types.StaticType;

public class PopulatorTest {

	private static EntityManagerFactory factory;

	private EntityManager entityManager;

	private Populator populator;

	@BeforeClass
	public static void adquire() {
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
			assertThat(e.getMessage(), equalTo(Error.NULL_REFERENCE.message()));
		}
	}

	@Test
	public void naoDevePreencherAtributosDeClasse() {
		StaticType instance = new StaticType();

		this.populator.populate(instance);

		assertNull(StaticType.getStandard());
	}

	@Test
	public void naoDevePreencherIdentificadores() {
		IdentifierType instance = new IdentifierType();

		this.populator.populate(instance);

		assertNull(instance.getStandard());
	}

	@Test
	public void naoDevePreencherAtributosQueDevemSerNulos() {
		NullType instance = new NullType();

		this.populator.populate(instance);

		assertNull(instance.getStandard());
	}

	@Test
	public void devePreencherOsDemaisAtributos() throws NoSuchFieldException, SecurityException {
		NotNullType instance = new NotNullType();

		this.populator.populate(instance);

		assertThat(instance.getStandard(), equalTo(""));
		assertThat(instance.getAnnotated(), equalTo(""));
	}

	// TODO Revisado ate aqui.

	@Test
	public void devePovoarEntidades() {
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
		assertNull(object.getAtTransient());
		assertThat(object.getCalendar().get(Calendar.DAY_OF_MONTH), equalTo(today.get(Calendar.DAY_OF_MONTH)));
		assertThat(object.getEnumeration(), equalTo(NotEmptyEnum.CONSTANT));
		assertThat(object.getArray(), equalTo(new byte[0]));
	}

	@Test
	public void devePovoarObjetosEmbutidos() {
		EmbeddedEntity entity = new EmbeddedEntity();

		this.populator.populate(entity);

		assertFalse(this.entityManager.contains(entity));

		EmbeddedEntity object = this.entityManager.find(EmbeddedEntity.class, entity.getId());
		assertNotNull(object.getId());

		BasicEmbeddable standard = object.getStandard();
		assertThat(standard.getPrimitive(), equalTo(0));
		assertThat(standard.getWrapper(), equalTo(false));
		assertThat(standard.getString(), equalTo(""));
		assertNull(standard.getAtTransient());
		assertThat(standard.getCalendar().get(Calendar.DAY_OF_MONTH), equalTo(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
		assertThat(standard.getEnumeration(), equalTo(NotEmptyEnum.CONSTANT));
		assertThat(standard.getArray(), equalTo(new byte[0]));
	}

	@Test
	public void devePovoarRelacionamentosOneToOne() {
		OneToOneEntity entity = new OneToOneEntity();

		this.populator.populate(entity);

		assertFalse(this.entityManager.contains(entity));

		OneToOneEntity object = this.entityManager.find(OneToOneEntity.class, entity.getId());
		assertNotNull(object.getId());

		BasicEntity standard = object.getStandard();
		assertNotNull(standard.getId());
	}

	@Test
	public void devePovoarRelacionamentosOneToOneBidirecional() {
		OneToOneBidirectionalEntity entity = new OneToOneBidirectionalEntity();

		this.populator.populate(entity);

		assertFalse(this.entityManager.contains(entity));

		OneToOneBidirectionalEntity object = this.entityManager.find(OneToOneBidirectionalEntity.class, entity.getId());
		assertNotNull(object.getId());

		OneToOneBidirectionalOwner owner = object.getOwner();
		assertNotNull(owner.getId());
		assertThat(owner.getEntity(), equalTo(entity));
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoNaoForPossivelPersistirOObjeto() {
		IllegalEntity entity = new IllegalEntity();

		this.populator.populate(entity);
	}

	// @Test
	// public void deveRecusarMapas() {
	// UnsupportedTypeEntity entity = new UnsupportedTypeEntity();
	//
	// try {
	// this.populator.populate(entity);
	//
	// fail();
	// } catch (UnsupportedOperationException e) {
	// assertThat(e.getMessage(),
	// equalTo(Error.UNSUPPORTED_TYPE.getMessage(Map.class.getName())));
	// }
	// }

	// TODO Deve lancar excecao para autorelacionamento.
	// TODO Contar a quantidade de objetos no banco de dados
	// TODO Criar metodoo que recebe varios objetos.
	// TODO Criar projeto de exemplo.

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
