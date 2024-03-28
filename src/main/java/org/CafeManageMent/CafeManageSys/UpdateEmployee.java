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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateEmployee implements Initializable {
	private String[] empTypes = { "Manager", "Cook", "Cachier", "Cleaner", "Waiter" };

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
	private ComboBox<String> empPost;

	@FXML
	private TextField empSalary;

	@FXML
	private TextField empUserName;

	@FXML
	private TextField searchItemText;

	@FXML
	private Button search;
	@FXML
	private Button cancel;

	@FXML
	private Button done;

	private EffectsOnButtons[] effects = new EffectsOnButtons[3];

	PerformOperations operation = new PerformOperations();
	private List<EmpDAO> list;
	private boolean found = false;
	private String id;

	@FXML
	void done(ActionEvent event) {

		String name = empName.getText();
		String empIDDB = empID.getText();
		String post = empPost.getSelectionModel().getSelectedItem();
		String salary = empSalary.getText();
		String contact = empContact.getText();
		String address = empAddress.getText();
		String username = empUserName.getText();
		String pass = empPassword.getText();
		try {
			Integer.parseInt(salary);
		} catch (NumberFormatException ex) {
			new AlertClass().showAlert(Alert.AlertType.INFORMATION, "Wrong Salary Entered",
					"" + "Please Enter Salary In DIGITS");
			empSalary.setText(null);
		}

		if (name.isBlank() || address.isBlank() || empIDDB.isBlank() || empSalary.getText().isBlank()
				|| contact.isBlank() || post == null || pass.isBlank() || username.isBlank()) {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Missing Data",
					"" + "Please Enter All the Details");

		} else {
			EmpDAO update = new EmpDAO(name, empIDDB, post, salary, contact, address, username, pass);
			update.setId(Integer.parseInt(id));
			operation.updateEmployee(update);
			new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Done", "Employee Updated Successfully");
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		}

	}

	@FXML
	void searchItem(ActionEvent event) {
		for (EmpDAO object : list) {
			if (object.getName().equals(searchItemText.getText())) {
				empName.setText(object.getName());
				empID.setText(object.getEmpID());
				empPost.setValue(object.getPost());
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
		effects[2].setShadowEffectOnButton(search);
		empPost.getItems().addAll(empTypes);
		list = operation.getEmployeeValues();

	}

	@FXML
	void calcel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
