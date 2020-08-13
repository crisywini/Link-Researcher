package co.crisi.linkresearcher.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Research
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Research.GET_ALL, query = "SELECT r FROM Research r"),
	@NamedQuery(name = Research.FIND_BY_NAME, query = "SELECT r FROM Research r WHERE r.name = :name"),
	@NamedQuery(name = Research.FIND_BY_SOME_NAME, query = "SELECT r FROM Research r WHERE UPPER(r.name) LIKE CONCAT('%',:name,'%')")
})
public class Research implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "searches")
	@OneToMany(mappedBy = "releatedResearch")
	private List<Search> searches;//Entidad no propietaria
	
	//QUERIES
	public static final String GET_ALL = "Research_getAll";
	public static final String FIND_BY_NAME = "Research_findByName";
	public static final String FIND_BY_SOME_NAME = "Research_findBySomeName";
	
	public Research() {
		super();
		
	}   
	
	public Research(String name, List<Search> searches) {
		super();
		this.name = name;
		this.searches = searches;
	}


	public Research(String name) {
		super();
		this.name = name;
		searches = new ArrayList<Search>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Search> getSearches() {
		return searches;
	}
	public void setSearches(List<Search> searches) {
		this.searches = searches;
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
		Research other = (Research) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Research [id=" + id + ", name=" + name + "]";
	}
	
   
}
