package org.CafeManageMent.CafeManageSys;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PopupLayoutDisplayer {
	private Stage stage;
	PopupLayoutDisplayer(){
		stage = new Stage();
	}

	public void setPopupLayout(String fxml,@SuppressWarnings("exports") Image img,String title) throws IOException {
		Parent root = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource(fxml+".fxml")));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		
		stage.getIcons().add(img);
		stage.setTitle(title);
		
		stage.show();
	}
}
