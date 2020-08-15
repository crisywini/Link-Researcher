package co.crisi.linkresearcher.ejb;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

import co.crisi.linkresearcher.ejb.exceptions.NullRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.NullResearchException;
import co.crisi.linkresearcher.ejb.exceptions.NullSearchException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedResearchException;
import co.crisi.linkresearcher.entities.RelevantResult;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.entities.Search;
import co.crisi.linkresearcher.entities.Status;

@Remote
public interface AdministratorEJBRemote {

	static final String JNDI = "java:global/EARProject/BusinessProject/AdministratorEJB!co.crisi.linkresearcher.ejb.AdministratorEJB, java:global/EARProject/BusinessProject/AdministratorEJB!co.crisi.linkresearcher.ejb.AdministratorEJBRemote";

	boolean removeRelevantResult(String link) throws NullRelevantResultException;

	boolean removeSearch(String nameSearch) throws NullSearchException;// PASSED TEST, REMOVED EVERYTHING ABOUT SEARCH
																		// AND RELEVANT RESULTS

	void addRelevantResult(String nameSearch, int indexSearch, String link, Date publicationDate, Status status)
			throws RepeatedRelevantResultException;// PASSED TEST

	void addSearch(String nameResearch, String searchEngine, String equation, long numberOfResult);// PASSED TEST

	boolean removeResearch(String name) throws NullResearchException;// PASSED TEST

	void addResearch(String name) throws RepeatedResearchException;// PASSED TEST

	List<Research> getAllResearches();

	List<Research> getResearchesByName(String name);

	List<Research> getResearchesBySomeName(String name);

	List<Search> getAllSearches();

	List<Search> getSearchesByEquation(String equation);

	List<Search> getSearchesBySomeEquation(String equation);

	List<Search> getSearchesBySearchEngine(String searchEngine);

	List<Search> getSearchesBySomeSearchEngine(String searchEngine);

	List<Search> getSearchesByDate(Date date);

	List<RelevantResult> getAllRelevantResults();

	List<RelevantResult> getRelevantResultByPublicationDate(Date date);

	List<RelevantResult> getRelevantResultByLink(String link);

	List<RelevantResult> getRelevantResultBySomeLink(String link);

	List<RelevantResult> getRelevantResultByStatus(Status status);

}
