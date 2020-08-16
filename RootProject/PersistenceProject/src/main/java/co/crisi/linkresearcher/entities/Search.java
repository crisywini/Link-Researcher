package co.crisi.linkresearcher.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Search
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Search.GET_ALL, query = "SELECT s FROM Search s"),
		@NamedQuery(name = Search.GET_BY_EQUATION, query = "SELECT s FROM Search s WHERE s.equation = :equation"),
		@NamedQuery(name = Search.GET_BY_SOME_EQUATION, query = "SELECT s FROM Search s WHERE UPPER(s.equation) LIKE CONCAT('%',:equation,'%')"),
		@NamedQuery(name = Search.GET_BY_SEARCH_ENGINE, query = "SELECT s FROM Search s WHERE s.searchEngine = :searchEngine"),
		@NamedQuery(name = Search.GET_BY_SOME_SEARCH_ENGINE, query = "SELECT s FROM Search s WHERE UPPER(s.searchEngine) LIKE CONCAT('%',:searchEngine,'%')"),
		@NamedQuery(name = Search.GET_BY_DATE, query = "SELECT s FROM Search s WHERE s.date = :date"),
		@NamedQuery(name = Search.GET_BY_RESEARCH, query = "SELECT COUNT(s) FROM Search s WHERE s.releatedResearch.name = :researchName") 
})
public class Search implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "search_engine", nullable = false)
	private String searchEngine;

	@Column(name = "equation", nullable = false)
	private String equation;

	@Column(name = "number_of_results", nullable = false)
	private long numberOfResults;

	@JoinColumn(name = "relevant_results")
	@OneToMany(mappedBy = "releatedSearch", cascade = CascadeType.REMOVE) // Entidad no propietaria -> inversa
	private List<RelevantResult> relevantResults;

	@JoinColumn(name = "releated_research")
	@ManyToOne // Entidad propietaria
	private Research releatedResearch;

	private static final long serialVersionUID = 1L;

	// QUERIES
	public static final String GET_ALL = "Search_getAll";
	public static final String GET_BY_EQUATION = "Search_getByEquation";
	public static final String GET_BY_SOME_EQUATION = "Search_getBySomeEquation";
	public static final String GET_BY_SEARCH_ENGINE = "Search_getBySearchEngine";
	public static final String GET_BY_SOME_SEARCH_ENGINE = "Search_getBySomeSearchEngine";
	public static final String GET_BY_DATE = "Search_getByDate";
	public static final String GET_BY_RESEARCH = "Search_getByResearch";

	public Search() {
		super();
	}

	public Search(String searchEngine, String equation, long numberOfResults, List<RelevantResult> relevantResults,
			Research releatedResearch) {
		super();
		this.searchEngine = searchEngine;
		this.equation = equation;
		this.numberOfResults = numberOfResults;
		this.relevantResults = relevantResults;
		this.releatedResearch = releatedResearch;
	}

	public Search(String searchEngine, String equation, long numberOfResults, Research releatedResearch) {
		super();
		this.searchEngine = searchEngine;
		this.equation = equation;
		this.numberOfResults = numberOfResults;
		this.releatedResearch = releatedResearch;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSearchEngine() {
		return this.searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getEquation() {
		return this.equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public long getNumberOfResults() {
		return this.numberOfResults;
	}

	public void setNumberOfResults(long numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public List<RelevantResult> getRelevantResults() {
		return relevantResults;
	}

	public void setRelevantResults(List<RelevantResult> relevantResults) {
		this.relevantResults = relevantResults;
	}

	public Research getReleatedResearch() {
		return releatedResearch;
	}

	public void setReleatedResearch(Research releatedResearch) {
		this.releatedResearch = releatedResearch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Search other = (Search) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Search [id=" + id + ", date=" + date + ", searchEngine=" + searchEngine + ", equation=" + equation
				+ ", numberOfResults=" + numberOfResults + "]";
	}

}
