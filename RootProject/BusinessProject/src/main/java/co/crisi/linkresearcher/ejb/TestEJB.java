package co.crisi.linkresearcher.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class TestEJB
 */
@Stateless
@LocalBean
public class TestEJB implements TestEJBRemote {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public TestEJB() {
		// TODO Auto-generated constructor stub
	}

}
