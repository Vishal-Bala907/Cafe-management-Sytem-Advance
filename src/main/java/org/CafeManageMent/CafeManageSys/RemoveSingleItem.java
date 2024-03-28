package org.CafeManageMent.CafeManageSys;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RemoveSingleItem implements Initializable {
	PerformOperations operation = new PerformOperations();
	List<InventryDAO> list;
	private boolean found = false;
	private String id;
	private byte[] img;

	@FXML
	private TextField itemIdText;

	@FXML
	private ImageView itemIimage;

	@FXML
	private TextField nameText;

	@FXML
	private TextField priceText;

	@FXML
	private TextField quantityText;

	@FXML
	private TextField searchItemText;

	@FXML
	private Button serach;
	@FXML
	private Button cancel;

	@FXML
	private Button done;
	private EffectsOnButtons[] effects = new EffectsOnButtons[3];

	@FXML
	void removeSingleItem(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				PerformOperations ope = new PerformOperations();
				quantityText.setText("");
				nameText.setText("");
				priceText.setText("");
				itemIdText.setText("");
				itemIimage.setImage(null);
				ope.removeSingleDataFromInventory(id);
				new AlertClass().showAlert(Alert.AlertType.INFORMATION,
						"Done", "Item Removed Successfully");
			}
			// Perform action on Cancel button click}
		});

	}

	@FXML
	void searchItem(ActionEvent event) {
		for (InventryDAO object : list) {
			if (object.getName().equals(searchItemText.getText())) {
				quantityText.setText(String.valueOf(object.getItemsSold()));
				nameText.setText(object.getName());
				priceText.setText(object.getPrice());
				itemIdText.setText(object.getItem_ID());
				id = String.valueOf(object.getId());
				// image

				img = object.getImage();
//				InputStream inputStream = ImageIO.read(new ByteArrayInputStream(arr));
				Image image = new Image(new ByteArrayInputStream(img));
				itemIimage.setImage(image);
				found = true;
			}
		}
		if (!found) {
			AlertClass alertClass = new AlertClass();
			alertClass.showAlert(Alert.AlertType.ERROR, "No Data Found",
					"The Value your searching for is not available");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 3; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(cancel);
		effects[1].setShadowEffectOnButton(done);
		effects[2].setShadowEffectOnButton(serach);
		list = operation.getValues();

	}

	@FXML
	public void cancel(@SuppressWarnings("exports") ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
