package org.CafeManageMent.CafeManageSys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

	private static Scene scene;
	private static Stage stage;

	public static  String AppUserName;
	public static  String AppUserPost;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		App.stage = stage;
		scene = new Scene(loadFXML("primary"));
		stage.setScene(scene);
		stage.getIcons().add(new Image(App.class.getResource("/Images/cafe.png").toExternalForm()));
		stage.show();
		stage.setResizable(false);
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
		if (fxml.equals("secondary")) {
			stage.setWidth(1300);
			stage.setHeight(720);
			stage.setResizable(true);
		} else if (fxml.equals("employee")) {
			stage.setWidth(1300);
			stage.setHeight(720);
			stage.setResizable(true);
		}

	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch(args);
	}

}