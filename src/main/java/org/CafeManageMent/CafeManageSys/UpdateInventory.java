package org.CafeManageMent.CafeManageSys;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class UpdateInventory implements Initializable {
	PerformOperations operation = new PerformOperations();
	List<InventryDAO> list;

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
	private Button serach;
	@FXML
	private Button cancel;
	@FXML
	private Button add;
	@FXML
	private Button done;
	private EffectsOnButtons[] effects = new EffectsOnButtons[3];

	@FXML
	private TextField searchItemText;

	private boolean found = false;
	private String id;

	private byte[] img;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 3; i++) {
			effects[i] = new EffectsOnButtons();
		}

		effects[0].setShadowEffectOnButton(cancel);
		effects[1].setShadowEffectOnButton(cancel);
		effects[2].setShadowEffectOnButton(cancel);
		new EffectsOnButtons().setShadowEffectOnButton(add);

		list = operation.getValues();

	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
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

	@FXML
	public void done(@SuppressWarnings("exports") ActionEvent event) {

		String name = nameText.getText();
		String ID = id;
		String price = priceText.getText();
		String itemsSold = quantityText.getText();
		String itemId = itemIdText.getText();
		try {
			Integer.parseInt(price);
		} catch (NumberFormatException e) {
			AlertClass alertClass = new AlertClass();
			alertClass.showAlert(Alert.AlertType.ERROR, "Wrong Data Entered", "" + "Please Enter Price in Numbers ");
		}

		try {
			Integer.parseInt(quantityText.getText());
		} catch (NumberFormatException e) {
			AlertClass alertClass = new AlertClass();
			alertClass.showAlert(Alert.AlertType.ERROR, "Wrong Data Entered",
					"" + "Please Enter Sold Items in Numbers ");
		}

		if (nameText.getText().isBlank() || itemIimage.getImage() == null || priceText.getText().isBlank()
				|| quantityText.getText().isBlank() || itemIdText.getText().isBlank()) {
			AlertClass alertClass = new AlertClass();
			alertClass.showAlert(Alert.AlertType.WARNING, "Empty Fields", "" + "Please Fill All the FIELDS ");

		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					InventryDAO obj = new InventryDAO(Integer.valueOf(ID), name, price, itemId, img,
							Integer.valueOf(itemsSold));
					PerformOperations opera = new PerformOperations();
					opera.updateInventry(obj);
					new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "DONE", "" + "Item Updated ");
				}
				// Perform action on Cancel button click}
			});

		}

	}

	@FXML
	public void searchItem(@SuppressWarnings("exports") ActionEvent event) {
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

}
