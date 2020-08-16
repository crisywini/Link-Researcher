package co.crisi.linkresearcher.ejb;

import java.util.Date;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.crisi.linkresearcher.ejb.exceptions.NullRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.NullResearchException;
import co.crisi.linkresearcher.ejb.exceptions.NullSearchException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedResearchException;
import co.crisi.linkresearcher.ejb.exceptions.UpdateException;
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
		TypedQuery<Research> query = entityManager.createNamedQuery(Research.GET_ALL, Research.class);
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

	@Override
	public boolean removeRelevantResult(String link) throws NullRelevantResultException {

		List<RelevantResult> query = getRelevantResultByLink(link);
		if (query.isEmpty()) {
			throw new NullRelevantResultException("The link: " + link + " does not exist");
		}
		entityManager.remove(query.get(0));
		return true;
	}

	@Override
	public boolean removeSearch(String nameSearch) throws NullSearchException {

		List<Search> query = getSearchesByEquation(nameSearch);
		if (query.isEmpty()) {
			throw new NullSearchException("The equation: " + nameSearch + " does not exist!");
		}
		entityManager.remove(query.get(0));
		return true;
	}

	@Override
	public void addRelevantResult(String nameSearch, int indexSearch, String link, Date publicationDate, Status status)
			throws RepeatedRelevantResultException {
		List<Search> query = getSearchesByEquation(nameSearch);
		Search search = query.get(indexSearch);
		List<RelevantResult> query2 = getRelevantResultByLink(link);
		if (!query2.isEmpty()) {
			throw new RepeatedRelevantResultException("The link: " + link + " already exist!");
		}
		RelevantResult relevantResult = new RelevantResult(link, publicationDate, status, search);
		entityManager.persist(relevantResult);
	}

	@Override
	public void addSearch(String nameResearch, String searchEngine, String equation, long numberOfResult) {
		List<Research> query = getResearchesByName(nameResearch);
		// IMPOSIBLE THAT THERE IS NOT A RESEARCH IF A SEARCH IS GONNA BE ADDED
		Research research = query.get(0);
		Search search = new Search(searchEngine, equation, numberOfResult, research);
		search.setDate(new Date());
		entityManager.persist(search);
	}

	@Override
	public boolean removeResearch(String name) throws NullResearchException {
		boolean removed = false;
		List<Research> query = getResearchesByName(name);
		if (query.isEmpty()) {
			throw new NullResearchException("There is no a Research with: " + name + " as a name!");
		}
		Research result = query.get(0);
		entityManager.remove(result);
		removed = true;
		return removed;
	}

	@Override
	public void addResearch(String name, String description) throws RepeatedResearchException {
		if (!getResearchesByName(name).isEmpty()) {
			throw new RepeatedResearchException("That name: " + name + " already exist!");
		}
		Research research = new Research();
		research.setName(name);
		research.setDescription(description);
		entityManager.persist(research);

	}

	@Override
	public void updateResearch(String originalName, String newName, String newDescription) throws UpdateException {

		List<Research> query = getResearchesByName(originalName);
		Research research = query.get(0);
		List<Research> query2 = getResearchesByName(newName);
		if (!query2.isEmpty()) {
			throw new UpdateException("The new name: " + newName + " is already used!");
		}
		if (!newDescription.isEmpty()) {
			research.setDescription(newDescription);
		}
		research.setName(newName);
		entityManager.merge(research);
	}

	@Override
	public long countSearchesByResearchName(String researchName) throws NullResearchException {

		List<Research> query1 = getAllResearches();
		if (query1.isEmpty()) {
			throw new NullResearchException("The name: " + researchName + " does not exist");
		}
		TypedQuery<Long> query = entityManager.createNamedQuery(Search.GET_BY_RESEARCH, Long.class);
		query.setParameter("researchName", researchName);
		return query.getResultList().get(0);
	}
}