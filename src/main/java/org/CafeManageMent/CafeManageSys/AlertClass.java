package org.CafeManageMent.CafeManageSys;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertClass {
	public void showAlert(@SuppressWarnings("exports") Alert.AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		if (type == Alert.AlertType.CONFIRMATION) {
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					System.out.println("User clicked OK");
					// Perform action on OK button click} elseif(response == ButtonType.CANCEL) {
					System.out.println("User clicked Cancel");
					// Perform action on Cancel button click}
				}
			});

		} else {
			alert.showAndWait();
		}

	}
}
