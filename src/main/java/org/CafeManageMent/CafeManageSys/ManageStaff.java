package org.CafeManageMent.CafeManageSys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.Inventory.EmpLists;
import com.dataBases.EmpDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ManageStaff implements Initializable {
	private Stage stage = new Stage();
	ObservableList<EmpLists> data = FXCollections
			.observableArrayList(new EmpLists(null, null, null, null, null, null, null));
	List<EmpDAO> list;

	@FXML
	private TableColumn<EmpLists, String> AddressColumn;

	@FXML
	private TableView<EmpLists> managerTableView;

	@FXML
	private TableColumn<EmpLists, String> contactColumn;

	@FXML
	private TableColumn<EmpLists, String> idColumn;

	@FXML
	private TableColumn<EmpLists, String> nameColumn;

	@FXML
	private TableColumn<EmpLists, String> postColumn;

	@FXML
	private Button refresh;
	@FXML
	private Button add;

	@FXML
	private Button update;

	@FXML
	private Button removeAll;

	@FXML
	private Button removeOne;

	private EffectsOnButtons[] effects = new EffectsOnButtons[5];

	@FXML
	private TableColumn<EmpLists, String> salaryColumn;

	@FXML
	private TableColumn<EmpLists, String> sn;

	@FXML
	public void onRefresh(@SuppressWarnings("exports") ActionEvent event) {
		PerformOperations values = new PerformOperations();
		list = values.getEmployeeValues();
		data.clear();
		for (EmpDAO obj : list) {
			EmpLists dao = new EmpLists(String.valueOf(obj.getId()), obj.getName(), obj.getID(), obj.getPost(),
					obj.getSalary(), obj.getContant(), obj.getAddress());
			data.add(dao);
			sn.setCellValueFactory(cellData -> cellData.getValue().getSn());
			nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
			idColumn.setCellValueFactory(cellData -> cellData.getValue().getID());

			postColumn.setCellValueFactory(cellData -> cellData.getValue().getPost());
			salaryColumn.setCellValueFactory(cellData -> cellData.getValue().getSalary());
			AddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
			contactColumn.setCellValueFactory(cellData -> cellData.getValue().getContact());
			managerTableView.setItems(data);
		}
	}

	@FXML
	public void removeItem(@SuppressWarnings("exports") ActionEvent event) {
	}

	@FXML
	public void updateItem(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UpdateEmployee.fxml")));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	public void setNewEmployee(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Parent root = FXMLLoader
				.load(Objects.requireNonNull(getClass().getResource("manageSatff_AddRemoveUpdate.fxml")));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 5; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(add);
		effects[1].setShadowEffectOnButton(refresh);
		effects[2].setShadowEffectOnButton(removeAll);
		effects[3].setShadowEffectOnButton(removeOne);
		effects[4].setShadowEffectOnButton(update);
		onRefresh(null);
	}

	@FXML
	public void removeSingle(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("removeSingleEmployee.fxml")));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	public void removeAllEmployee(@SuppressWarnings("exports") ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Remove all employees");
		alert.setContentText("Are you sure You want to remove all employee");
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				new PerformOperations().removeAllEmployee();
				new AlertClass().showAlert(Alert.AlertType.INFORMATION, "Removed", "All Employes Removed");
			}

		});

	}

}
