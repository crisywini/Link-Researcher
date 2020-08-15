package co.crisi.linkresearcher.model;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.crisi.linkresearcher.ejb.AdministratorEJBRemote;
import co.crisi.linkresearcher.ejb.exceptions.NullRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.NullResearchException;
import co.crisi.linkresearcher.ejb.exceptions.NullSearchException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedRelevantResultException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedResearchException;
import co.crisi.linkresearcher.entities.RelevantResult;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.entities.Search;
import co.crisi.linkresearcher.entities.Status;

public class Delegate implements AdministratorEJBRemote {

	private AdministratorEJBRemote admin;

	public static Delegate delegate = instance();

	private Delegate() {
		try {
			admin = (AdministratorEJBRemote) new InitialContext().lookup(AdministratorEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static Delegate instance() {
		if (delegate == null) {
			delegate = new Delegate();
			return delegate;
		}
		return delegate;
	}

	@Override
	public boolean removeRelevantResult(String link) throws NullRelevantResultException {
		return admin.removeRelevantResult(link);
	}

	@Override
	public boolean removeSearch(String nameSearch) throws NullSearchException {
		return admin.removeSearch(nameSearch);
	}

	@Override
	public void addRelevantResult(String nameSearch, int indexSearch, String link, Date publicationDate, Status status)
			throws RepeatedRelevantResultException {
		admin.addRelevantResult(nameSearch, indexSearch, link, publicationDate, status);
	}

	@Override
	public void addSearch(String nameResearch, String searchEngine, String equation, long numberOfResult) {
		admin.addSearch(nameResearch, searchEngine, equation, numberOfResult);
	}

	@Override
	public boolean removeResearch(String name) throws NullResearchException {
		return admin.removeResearch(name);
	}

	@Override
	public void addResearch(String name) throws RepeatedResearchException {
		admin.addResearch(name);
	}

	@Override
	public List<Research> getAllResearches() {
		return admin.getAllResearches();
	}

	@Override
	public List<Research> getResearchesByName(String name) {
		return admin.getResearchesByName(name);
	}

	@Override
	public List<Research> getResearchesBySomeName(String name) {
		return admin.getResearchesBySomeName(name);
	}

	@Override
	public List<Search> getAllSearches() {
		return admin.getAllSearches();
	}

	@Override
	public List<Search> getSearchesByEquation(String equation) {
		return admin.getSearchesByEquation(equation);
	}

	@Override
	public List<Search> getSearchesBySomeEquation(String equation) {
		return admin.getSearchesBySomeEquation(equation);
	}

	@Override
	public List<Search> getSearchesBySearchEngine(String searchEngine) {
		return admin.getSearchesBySearchEngine(searchEngine);
	}

	@Override
	public List<Search> getSearchesBySomeSearchEngine(String searchEngine) {
		return admin.getSearchesBySomeSearchEngine(searchEngine);
	}

	@Override
	public List<Search> getSearchesByDate(Date date) {
		return admin.getSearchesByDate(date);
	}

	@Override
	public List<RelevantResult> getAllRelevantResults() {
		return admin.getAllRelevantResults();
	}

	@Override
	public List<RelevantResult> getRelevantResultByPublicationDate(Date date) {
		return admin.getRelevantResultByPublicationDate(date);
	}

	@Override
	public List<RelevantResult> getRelevantResultByLink(String link) {
		return admin.getRelevantResultByLink(link);
	}

	@Override
	public List<RelevantResult> getRelevantResultBySomeLink(String link) {
		return admin.getRelevantResultBySomeLink(link);
	}

	@Override
	public List<RelevantResult> getRelevantResultByStatus(Status status) {
		return admin.getRelevantResultByStatus(status);
	}

}
