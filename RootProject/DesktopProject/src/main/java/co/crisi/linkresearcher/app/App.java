package co.crisi.linkresearcher.app;

import java.io.IOException;

import com.sun.enterprise.module.bootstrap.Main;

import co.crisi.linkresearcher.controller.RootViewController;
import co.crisi.linkresearcher.model.Delegate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static final Delegate DELEGATE = Delegate.delegate;

	@Override
	public void start(Stage primaryStage) {
		try {
			initApp(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initApp(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/RootView.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		RootViewController controller = loader.getController();
		controller.setApp(this);
		controller.setPrimaryStage(primaryStage);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setTitle("Link Researcher");
		primaryStage.show();

	}

}
