package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
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
import javafx.stage.Stage;

public class RemoveSingleEmploee implements Initializable {

	@FXML
	private TextField empAddress;

	@FXML
	private TextField empContact;

	@FXML
	private TextField empID;

	@FXML
	private TextField empName;

	@FXML
	private TextField empPassword;

	@FXML
	private TextField empPost;

	@FXML
	private TextField empSalary;

	@FXML
	private TextField empUserName;

	@FXML
	private TextField searchItemText;

	PerformOperations operation = new PerformOperations();
	private List<EmpDAO> list;
	private boolean found = false;
	private String id;
	private int executeUpdate = -1;

	@FXML
	private Button serach;
	@FXML
	private Button cancel;

	@FXML
	private Button done;
	private EffectsOnButtons[] effects = new EffectsOnButtons[3];

	@FXML
	void done(ActionEvent event) {

		String name = empName.getText();
		String empIDDB = empID.getText();
		String post = empPost.getText();
		String salary = empSalary.getText();
		String contact = empContact.getText();
		String address = empAddress.getText();
		String username = empUserName.getText();
		String pass = empPassword.getText();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				EmpDAO update = new EmpDAO(name, empIDDB, post, salary, contact, address, username, pass);
				update.setId(Integer.parseInt(id));
				executeUpdate = operation.removeEmployee(id);
				if (executeUpdate > 0) {
					new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Done", "Employee Removed successfully");
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.close();
				}
			} else {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
			}
		});

	}

	@FXML
	void searchItem(ActionEvent event) {
		for (EmpDAO object : list) {
			if (object.getName().equals(searchItemText.getText())) {
				empName.setText(object.getName());
				empID.setText(object.getEmpID());
				empPost.setText(object.getPost());
				empSalary.setText(object.getSalary());
				empContact.setText(object.getContant());
				empAddress.setText(object.getAddress());
				empUserName.setText(object.getUserName());
				empPassword.setText(object.getPassWord());
				id = String.valueOf(object.getId());
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
		list = operation.getEmployeeValues();
	}

	@FXML
	public void cancel(@SuppressWarnings("exports") ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
