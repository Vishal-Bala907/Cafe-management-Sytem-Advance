package org.CafeManageMent.CafeManageSys;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.Inventory.InventoryDAO;
import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Inventory implements Initializable {
	@FXML
	private TableView<InventoryDAO> InventoryTreeView;

	private Stage stageS = new Stage();
	private Scene sceneS;
	ObservableList<InventoryDAO> data = FXCollections.observableArrayList();
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
	private TableColumn<InventoryDAO, String> itemIdColumn;

	@FXML
	private TableColumn<InventoryDAO, String> nameColumn;

	@FXML
	private TableColumn<InventoryDAO, String> priceColumn;

	@FXML
	private TableColumn<InventoryDAO, String> quantityColumn;

	@FXML
	private TableColumn<InventoryDAO, String> sn;
	@FXML
	private TableColumn<InventoryDAO, String> itemSold;

	private List<InventryDAO> list;

	@FXML
	private Button addnew;
	@FXML
	private Button refresh;

	@FXML
	private Button removeAll;

	@FXML
	private Button removeSingle;

	@FXML
	private Button update;

	private EffectsOnButtons[] effects = new EffectsOnButtons[5];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 5; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(addnew);
		effects[1].setShadowEffectOnButton(refresh);
		effects[2].setShadowEffectOnButton(removeAll);
		effects[3].setShadowEffectOnButton(removeSingle);
		effects[4].setShadowEffectOnButton(update);
		onRefresh();

	}

	@FXML
	public void setNewItem(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageInventory.fxml")));
		sceneS = new Scene(root);
		stageS.setScene(sceneS);
		stageS.setResizable(false);
		stageS.getIcons().add(new Image(App.class.getResource("/Images/inventory.png").toExternalForm()));
		stageS.setTitle("Add New Item to The Inventory");
		stageS.show();
//		onRefresh();
	}

	@FXML
	public void updateItem(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UpdateInventory.fxml")));
		sceneS = new Scene(root);
		stageS.setScene(sceneS);
		stageS.getIcons().add(new Image(App.class.getResource("/Images/inventory.png").toExternalForm()));
		stageS.setTitle("Update Item from The Inventory");
		stageS.setResizable(false);
		stageS.show();
		onRefresh();
	}

	@FXML
	public void removeItem(@SuppressWarnings("exports") ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Remove all ");
		alert.setContentText("Are you sure you want to remove all items");
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				PerformOperations values = new PerformOperations();
				values.removeData();
				new AlertClass().showAlert(Alert.AlertType.INFORMATION, "Done", "All items Removed Successfully");
			}
			// Perform action on Cancel button click}
		});

//		}

	}

	@FXML
	public void done(@SuppressWarnings("exports") ActionEvent event) {

	}

	public void addItems(@SuppressWarnings("exports") InventoryDAO dao) {

	}

	@FXML
	public void onRefresh() {
		PerformOperations values = new PerformOperations();
		list = values.getValues();
		data.clear();
		for (InventryDAO obj : list) {
			InventoryDAO dao = new InventoryDAO(String.valueOf(obj.getId()), obj.getName(), obj.getPrice(),
					obj.getItem_ID(), String.valueOf(obj.getItemsSold()));
			data.add(dao);
			priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice());
			itemIdColumn.setCellValueFactory(cellData -> cellData.getValue().getItem_ID());

			sn.setCellValueFactory(cellData -> cellData.getValue().getId());
			nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
			itemSold.setCellValueFactory(cellData -> cellData.getValue().getItemsSold());
			InventoryTreeView.setItems(data);
		}
//		}
	}

	@FXML
	public void removeSingleItem() throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("removeSingleItem.fxml")));
		sceneS = new Scene(root);
		stageS.getIcons().add(new Image(App.class.getResource("/Images/inventory.png").toExternalForm()));
		stageS.setTitle("Remove Single item");
		stageS.setScene(sceneS);
		stageS.setResizable(false);
		stageS.show();
		onRefresh();
	}
}
