package co.crisi.linkresearcher.entities;

import co.crisi.linkresearcher.entities.Status;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RelevantResult
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = RelevantResult.GET_ALL, query = "SELECT rr FROM RelevantResult rr"),
	@NamedQuery(name = RelevantResult.GET_BY_DATE, query = "SELECT rr FROM RelevantResult rr WHERE rr.publicationDate = :date"),
	@NamedQuery(name = RelevantResult.GET_BY_LINK, query = "SELECT rr FROM RelevantResult rr WHERE rr.link = :link"),
	@NamedQuery(name = RelevantResult.GET_BY_SOME_LINK, query = "SELECT rr FROM RelevantResult rr WHERE UPPER(rr.link) LIKE CONCAT('%',:link,'%')"),
	@NamedQuery(name = RelevantResult.GET_BY_STATUS, query = "SELECT rr FROM RelevantResult rr WHERE rr.status = :status")
})
public class RelevantResult implements Serializable {

	@Id
	@Column(name = "link", nullable = false)
	private String link;

	@Column(name = "publication_date", nullable = false)
	private Date publicationDate;

	@Column(name = "status", nullable = false)
	private Status status;

	@JoinColumn(name = "releated_search", nullable = false)
	@ManyToOne // Entidad propietaria
	private Search releatedSearch;

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL = "RelevantResult_getAll";
	public static final String GET_BY_LINK = "RelevantResult_getByLink";
	public static final String GET_BY_SOME_LINK = "RelevantResult_getBySomeLink";
	public static final String GET_BY_DATE = "RelevantResult_getByDate";
	public static final String GET_BY_STATUS = "RelevantResult_getByStatus";

	public RelevantResult() {
		super();
	}

	public RelevantResult(String link, Date publicationDate, Status status) {
		super();
		this.link = link;
		this.publicationDate = publicationDate;
		this.status = status;
	}

	public RelevantResult(String link, Date publicationDate, Status status, Search releatedSearch) {
		super();
		this.link = link;
		this.publicationDate = publicationDate;
		this.status = status;
		this.releatedSearch = releatedSearch;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Search getReleatedSearch() {
		return releatedSearch;
	}

	public void setReleatedSearch(Search releatedSearch) {
		this.releatedSearch = releatedSearch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
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
		RelevantResult other = (RelevantResult) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RelevantResult [link=" + link + ", publicationDate=" + publicationDate + ", status=" + status + "]";
	}

}
