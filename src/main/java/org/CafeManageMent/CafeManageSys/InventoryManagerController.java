package org.CafeManageMent.CafeManageSys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InventoryManagerController implements Initializable {
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

	private byte[] img;

	@FXML
	private Button add;

	@FXML
	private Button cancel;

	@FXML
	private Button done;
	private EffectsOnButtons[] effects = new EffectsOnButtons[3];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 3; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(add);
		effects[1].setShadowEffectOnButton(cancel);
		effects[2].setShadowEffectOnButton(done);

	}

	@FXML
	public void done(@SuppressWarnings("exports") ActionEvent event) {
		int saveData=-1;
		InventryDAO data = new InventryDAO();
		data.setItem_ID((itemIdText.getText()));
		data.setName(nameText.getText());

//		data.setQuantity(quantityText.getText());
		data.setImage(img);
		try {
			Integer.parseInt(quantityText.getText());
			data.setItemsSold(Integer.parseInt(quantityText.getText()));
		} catch (NumberFormatException e) {
			new AlertClass().showAlert(Alert.AlertType.WARNING, "Wrong Entry",
					"Please Enter Number" + "in the Quantity Field");
			quantityText.setText(null);
		}

		try {
			Integer.parseInt(priceText.getText());
			data.setPrice(priceText.getText());
		} catch (NumberFormatException e) {
			new AlertClass().showAlert(Alert.AlertType.WARNING, "Wrong Entry",
					"Please Enter Number" + "in the Price Field");
			priceText.setText(null);
		}

		if (itemIdText.getText().isBlank() || nameText.getText().isBlank() || itemIimage.getImage() == null
				|| priceText.getText().isBlank() || quantityText.getText().isBlank()) {
			new AlertClass().showAlert(Alert.AlertType.WARNING, "Entry Missing", "Please Fill All the Fields");
		} else {
			PerformOperations operation = new PerformOperations();
			saveData = operation.saveData(data);
		}

		if (saveData > 0) {
			itemIdText.setText(null);
			nameText.setText(null);
			priceText.setText(null);
			quantityText.setText(null);
			itemIimage.setImage(null);
			
			new AlertClass().showAlert(Alert.AlertType.INFORMATION, "DONE",
					"Added SuccesFully");
			
			new Inventory().onRefresh();
		} else {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Something went wrong", "Some Error Occured");

		}

	}

	@FXML
	public void selectFile(@SuppressWarnings("exports") ActionEvent event) {
		// Create a FileChooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		java.io.File selectedFile = fileChooser.showOpenDialog(new Stage());
		System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		String path = selectedFile.getAbsolutePath();
		URL url = null;
		try {
			url = selectedFile.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(url.toString());
		itemIimage.setImage(new Image(url.toString()));

		try (FileInputStream fileInputStream = new FileInputStream(path)) {
			img = new byte[fileInputStream.available()];
			fileInputStream.read(img);
			System.out.println(img);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML public void cancel(@SuppressWarnings("exports") ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}

}
