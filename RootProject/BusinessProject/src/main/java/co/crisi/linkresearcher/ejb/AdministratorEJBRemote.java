package co.crisi.linkresearcher.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import co.crisi.linkresearcher.entities.RelevantResult;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.entities.Search;
import co.crisi.linkresearcher.entities.Status;

@Remote
public interface AdministratorEJBRemote {

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
