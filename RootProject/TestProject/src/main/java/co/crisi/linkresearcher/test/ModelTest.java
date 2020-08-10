package co.crisi.linkresearcher.test;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;


import co.crisi.linkresearcher.entities.RelevantResult;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.entities.Search;
import co.crisi.linkresearcher.entities.Status;

@RunWith(Arquillian.class)
public class ModelTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Research.class.getPackage()).addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void persistenceResearchTest() {
		Research research = new Research("Mental health social networks analysis");

		entityManager.persist(research);

	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void persistenceSearchTest() {
		Research research = new Research("Depression in social network");
		entityManager.persist(research);
		System.out.println("Research persisted");

		Search search = new Search();
		search.setDate(new Date());
		search.setEquation("Depression");
		search.setNumberOfResults(156510000);
		search.setSearchEngine("Google");
		search.setReleatedResearch(research);

		entityManager.persist(search);

	}

	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void persistenceRelevantResultTest() {
		Search search = entityManager.find(Search.class, 1);
		RelevantResult relevant = new RelevantResult("www.acm.google.com", new Date(), Status.GREEN, search);
		entityManager.persist(relevant);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getAllResearchTest() {
		TypedQuery<Research> query = entityManager.createNamedQuery(Research.GET_ALL, Research.class);
		List<Research> result = query.getResultList();
		System.out.println(result.toString());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getResearchesByNameTest() {
		TypedQuery<Research> query = entityManager.createNamedQuery(Research.FIND_BY_NAME, Research.class);
		query.setParameter("name", "Depression");
		List<Research> result = query.getResultList();
		System.out.println(result.toString());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getResearchBySomeNameTest() {
		TypedQuery<Research> query = entityManager.createNamedQuery(Research.FIND_BY_SOME_NAME, Research.class);
		query.setParameter("name", "in");
		List<Research> result = query.getResultList();
		System.out.println(result.toString());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getAllSearchesTest() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_ALL, Search.class);
		List<Search> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getSearchByEquationTest() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_EQUATION, Search.class);
		query.setParameter("equation", "Depression");
		List<Search> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getSearchBySomeEquationTest() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_SOME_EQUATION, Search.class);
		query.setParameter("equation", "Depression");
		List<Search> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getSearchByDateTest() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_DATE, Search.class);
		query.setParameter("date", new Date());
		List<Search> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getSearchBySearchEngineTest() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_SEARCH_ENGINE, Search.class);
		query.setParameter("searchEngine", "google");
		List<Search> result = query.getResultList();
		System.out.println(result);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getSearchBySomeSearchEngineTest() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_SOME_SEARCH_ENGINE, Search.class);
		query.setParameter("searchEngine", "goog");
		List<Search> result = query.getResultList();
		System.out.println(result);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getAllRelevantResultTest() {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_ALL, RelevantResult.class);
		List<RelevantResult> result = query.getResultList();
		System.out.println(result);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getRelevantResultByLinkTest() {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_LINK, RelevantResult.class);
		query.setParameter("link", "www.acm.google.com");
		List<RelevantResult> result = query.getResultList();
		System.out.println(result);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getRelevantResultBySomeLinkTest() {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_SOME_LINK, RelevantResult.class);
		query.setParameter("link", "www.acm.");
		List<RelevantResult> result = query.getResultList();
		System.out.println(result);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getRelevantResultByStatusTest() {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_STATUS, RelevantResult.class);
		query.setParameter("status", Status.GREEN);
		List<RelevantResult> result = query.getResultList();
		System.out.println(result);
	}
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void getRelevantResultByDateTest() {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_DATE, RelevantResult.class);
		query.setParameter("date", new Date());
		List<RelevantResult> result = query.getResultList();
		System.out.println(result);
	}
}
