package co.crisi.linkresearcher.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResearchObservable {

	private StringProperty name;
	private StringProperty id;
	private StringProperty description;
	private StringProperty sizeSearches;

	public ResearchObservable(String name, int id, String description, long sizeSearches) {
		this.name = new SimpleStringProperty(name);
		this.id = new SimpleStringProperty(id + "");
		this.description = new SimpleStringProperty(description);
		this.sizeSearches = new SimpleStringProperty(sizeSearches + "");
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public StringProperty getDescription() {
		return description;
	}

	public void setDescription(StringProperty description) {
		this.description = description;
	}

	public StringProperty getSizeSearches() {
		return sizeSearches;
	}

	public void setSizeSearches(StringProperty sizeSearches) {
		this.sizeSearches = sizeSearches;
	}

}
