/**
 * Sample Skeleton for 'RootView.fxml' Controller Class
 */

package co.crisi.linkresearcher.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.linkresearcher.app.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RootViewController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="rootPane"
	private BorderPane rootPane; // Value injected by FXMLLoader

	@FXML // fx:id="researchesMenu"
	private Menu researchesMenu; // Value injected by FXMLLoader
	ResearchViewController researchController;
	VBox researchPane;
	private App app;
	private Stage primaryStage;

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'RootView.fxml'.";
		assert researchesMenu != null : "fx:id=\"researchesMenu\" was not injected: check your FXML file 'RootView.fxml'.";
		initResearcherView();
	}

	public void initResearcherView() {
		if (researchPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader(App.class.getResource("/ResearchView.fxml"));
				researchPane = loader.load();
				researchController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		researchController.setLastControllerRoot(this);
		rootPane.setCenter(researchPane);
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	

	public static void showAlert(String message, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.showAndWait();
	}

}
