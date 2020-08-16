/**
 * Sample Skeleton for 'ResearchView.fxml' Controller Class
 */

package co.crisi.linkresearcher.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.crisi.linkresearcher.app.App;
import co.crisi.linkresearcher.ejb.exceptions.NullResearchException;
import co.crisi.linkresearcher.ejb.exceptions.RepeatedResearchException;
import co.crisi.linkresearcher.ejb.exceptions.UpdateException;
import co.crisi.linkresearcher.entities.Research;
import co.crisi.linkresearcher.model.ResearchObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ResearchViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="nameLabel"
	private Label nameLabel; // Value injected by FXMLLoader

	@FXML // fx:id="descriptionLabel"
	private Label descriptionLabel; // Value injected by FXMLLoader

	@FXML // fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	@FXML // fx:id="descriptionField"
	private TextArea descriptionField; // Value injected by FXMLLoader

	@FXML // fx:id="researchTableView"
	private TableView<ResearchObservable> researchTableView; // Value injected by FXMLLoader

	@FXML // fx:id="idResearchColumn"
	private TableColumn<ResearchObservable, String> idResearchColumn; // Value injected by FXMLLoader

	@FXML // fx:id="nameResearchColumn"
	private TableColumn<ResearchObservable, String> nameResearchColumn; // Value injected by FXMLLoader

	@FXML // fx:id="searchesColumn"
	private TableColumn<ResearchObservable, String> searchesColumn; // Value injected by FXMLLoader

	@FXML // fx:id="descriptionResearchColumn"
	private TableColumn<ResearchObservable, String> descriptionResearchColumn; // Value injected by FXMLLoader

	private RootViewController lastControllerRoot;
	private ObservableList<ResearchObservable> data = FXCollections.observableArrayList();

	@FXML
	void handleAddResearchButton(ActionEvent event) {
		if (isInputValid()) {
			try {
				App.DELEGATE.addResearch(nameField.getText(), descriptionField.getText());
				updateDataTable();
				nameField.setText("");
				descriptionField.setText("");
			} catch (RepeatedResearchException e) {
				RootViewController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
			}

		}
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String message = "";
		if (nameField.getText().isEmpty()) {
			message += "The name should not be empty\n";
		}
		if (descriptionField.getText().isEmpty()) {
			message += "The description should not be empty\n";
		}
		if (message.isEmpty()) {
			isValid = true;
		} else {
			RootViewController.showAlert(message, "WARNING", AlertType.WARNING);
		}
		return isValid;

	}

	@FXML
	void handleRemoveButton(ActionEvent event) {

		if (!researchTableView.getSelectionModel().isEmpty()) {
			ResearchObservable researchSelected = researchTableView.getSelectionModel().getSelectedItem();
			try {

				App.DELEGATE.removeResearch(researchSelected.getName().get());
				RootViewController.showAlert("Research: " + researchSelected.getName().get() + " removed", "WARNING",
						AlertType.WARNING);
				researchTableView.getSelectionModel().clearSelection();
				updateDataTable();
			} catch (NullResearchException e) {
				e.printStackTrace();
			}

		} else {
			RootViewController.showAlert("You must select a research to remove", "WARNING", AlertType.WARNING);
		}

	}

	@FXML
	void handleUpdateButton(ActionEvent event) {
		if (!researchTableView.getSelectionModel().isEmpty()) {
			ResearchObservable researchSelected = researchTableView.getSelectionModel().getSelectedItem();
			if (!nameField.getText().isEmpty()) {
				try {
					App.DELEGATE.updateResearch(researchSelected.getName().get(), nameField.getText(),
							descriptionField.getText());
					RootViewController.showAlert(
							"Research: " + researchSelected.getName().get() + " updated to: " + nameField.getText(), "INFO",
							AlertType.INFORMATION);
					nameField.setText("");
					descriptionField.setText("");
					researchTableView.getSelectionModel().clearSelection();
					updateDataTable();
				} catch (UpdateException e) {
					RootViewController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			} else {
				RootViewController.showAlert(
						"You must type a new name to the research: " + researchSelected.getName().get(), "WARNING",
						AlertType.WARNING);
			}
		} else {
			RootViewController.showAlert("You must select a research!", "WARNING", AlertType.WARNING);
		}

	}

	@FXML
	void handleSearchByExactButton(ActionEvent event) {
		if (!nameField.getText().isEmpty()) {
			initTableResearchByNameData();
			researchTableView.refresh();
		} else {
			RootViewController.showAlert("The name must be in", "WARNING", AlertType.WARNING);
		}
	}

	@FXML
	void handleSearchBySomeButton(ActionEvent event) {
		if (!nameField.getText().isEmpty()) {
			initTableResearchBySomeNameData();
			researchTableView.refresh();
		} else {
			RootViewController.showAlert("The name must be in", "WARNING", AlertType.WARNING);
		}
	}

	@FXML
	void handleSeeAllButton(ActionEvent event) {
		updateDataTable();
	}

	@FXML
	void handleSelectButton(ActionEvent event) {
		// INIT THE SEARCH VIEW
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert descriptionField != null : "fx:id=\"descriptionField\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert researchTableView != null : "fx:id=\"researchTableView\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert idResearchColumn != null : "fx:id=\"idResearchColumn\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert nameResearchColumn != null : "fx:id=\"nameResearchColumn\" was not injected: check your FXML file 'ResearchView.fxml'.";
		assert searchesColumn != null : "fx:id=\"searchesColumn\" was not injected: check your FXML file 'ResearchView.fxml'.";
	}

	public void initTableView() {
		researchTableView.getItems().clear();
		searchesColumn.setCellValueFactory(cellData -> cellData.getValue().getSizeSearches());
		idResearchColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
		nameResearchColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		descriptionResearchColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
		updateDataTable();
	}

	public void updateDataTable() {
		initTableResearchData();
		researchTableView.setItems(data);
		researchTableView.refresh();
	}

	public void initTableResearchData() {
		data.clear();
		List<Research> researches = App.DELEGATE.getAllResearches();
		for (Research research : researches) {
			try {
				data.add(new ResearchObservable(research.getName(), research.getId(), research.getDescription(),
						App.DELEGATE.countSearchesByResearchName(research.getName())));
			} catch (NullResearchException e) {
				e.printStackTrace();
			}
		}
	}

	public void initTableResearchByNameData() {
		data.clear();
		List<Research> researches = App.DELEGATE.getResearchesByName(nameField.getText());
		for (Research research : researches) {
			try {
				data.add(new ResearchObservable(research.getName(), research.getId(), research.getDescription(),
						App.DELEGATE.countSearchesByResearchName(research.getName())));
			} catch (NullResearchException e) {
				e.printStackTrace();
			}
		}
	}

	public void initTableResearchBySomeNameData() {
		data.clear();
		List<Research> researches = App.DELEGATE.getResearchesBySomeName(nameField.getText());
		for (Research research : researches) {
			try {
				data.add(new ResearchObservable(research.getName(), research.getId(), research.getDescription(),
						App.DELEGATE.countSearchesByResearchName(research.getName())));
			} catch (NullResearchException e) {
				e.printStackTrace();
			}
		}
	}

	public RootViewController getLastControllerRoot() {
		return lastControllerRoot;
	}

	public void setLastControllerRoot(RootViewController lastControllerRoot) {
		this.lastControllerRoot = lastControllerRoot;
		initTableView();
	}
}
