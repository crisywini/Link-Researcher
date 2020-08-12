package co.crisi.linkresearcher.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.crisi.linkresearcher.entities.RelevantResult;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.entities.Search;
import co.crisi.linkresearcher.entities.Status;

/**
 * Session Bean implementation class AdministratorEJB
 */
@Stateless
@LocalBean
public class AdministratorEJB implements AdministratorEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdministratorEJB() {
	}

	@Override
	public List<Research> getAllResearches() {
		TypedQuery<Research> query = entityManager.createNamedQuery(RelevantResult.GET_ALL, Research.class);
		return query.getResultList();
	}

	@Override
	public List<Research> getResearchesByName(String name) {
		TypedQuery<Research> query = entityManager.createNamedQuery(Research.FIND_BY_NAME, Research.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<Research> getResearchesBySomeName(String name) {
		TypedQuery<Research> query = entityManager.createNamedQuery(Research.FIND_BY_SOME_NAME, Research.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public List<Search> getAllSearches() {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_ALL, Search.class);
		return query.getResultList();
	}

	@Override
	public List<Search> getSearchesByEquation(String equation) {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_EQUATION, Search.class);
		query.setParameter("equation", equation);
		return query.getResultList();
	}

	@Override
	public List<Search> getSearchesBySomeEquation(String equation) {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_SOME_EQUATION, Search.class);
		query.setParameter("equation", equation);
		return query.getResultList();
	}

	@Override
	public List<Search> getSearchesBySearchEngine(String searchEngine) {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_SEARCH_ENGINE, Search.class);
		query.setParameter("searchEngine", searchEngine);
		return query.getResultList();
	}

	@Override
	public List<Search> getSearchesBySomeSearchEngine(String searchEngine) {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_SOME_SEARCH_ENGINE, Search.class);
		query.setParameter("searchEngine", searchEngine);
		return query.getResultList();
	}

	@Override
	public List<Search> getSearchesByDate(Date date) {
		TypedQuery<Search> query = entityManager.createNamedQuery(Search.GET_BY_DATE, Search.class);
		query.setParameter("date", date);
		return query.getResultList();
	}

	@Override
	public List<RelevantResult> getAllRelevantResults() {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_ALL, RelevantResult.class);
		return query.getResultList();
	}

	@Override
	public List<RelevantResult> getRelevantResultByPublicationDate(Date date) {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_DATE,
				RelevantResult.class);
		query.setParameter("publicationDate", date);
		return query.getResultList();
	}

	@Override
	public List<RelevantResult> getRelevantResultByLink(String link) {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_LINK,
				RelevantResult.class);
		query.setParameter("link", link);
		return query.getResultList();
	}

	@Override
	public List<RelevantResult> getRelevantResultByStatus(Status status) {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_STATUS,
				RelevantResult.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	@Override
	public List<RelevantResult> getRelevantResultBySomeLink(String link) {
		TypedQuery<RelevantResult> query = entityManager.createNamedQuery(RelevantResult.GET_BY_SOME_LINK,
				RelevantResult.class);
		query.setParameter("link", link);
		return query.getResultList();
	}

}
