package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageSatff_AddRemoveUpdate implements Initializable {
	private String[] empTypes = { "Manager", "Cook", "Cachier", "Cleaner", "Waiter" };
	@FXML
	private TextField empAddress;

	@FXML
	private TextField empContact;

	@FXML
	private TextField empId;

	@FXML
	private TextField empName;

	@FXML
	private ComboBox<String> empPost;

	@FXML
	private TextField empSalary;

	@FXML
	private TextField passWord;

	@FXML
	private TextField userName;

	@FXML
	private Button cancel;

	@FXML
	private Button done;

	EffectsOnButtons[] effects = new EffectsOnButtons[2];

	@FXML
	void done(ActionEvent event) {
		String name = empName.getText();
		String address = empAddress.getText();
		String Id = empId.getText();
		String salary = empSalary.getText();
		String contact = empContact.getText();
		String selectedItem = empPost.getSelectionModel().getSelectedItem();
		String enteredUserName = userName.getText();
		String enterdPass = passWord.getText();
		int saveData = -1;
		try {
			Integer.parseInt(salary);
		} catch (NumberFormatException ex) {
			new AlertClass().showAlert(Alert.AlertType.WARNING, "Wrong Salary Entered",
					"" + "Please Enter Salary In DIGITS");
			empSalary.setText("");
		}

		if (empName.getText().isBlank() || empAddress.getText().isBlank() || Id.isBlank()
				|| empSalary.getText().isBlank() || contact.isBlank() || selectedItem == null || enterdPass.isBlank()
				|| enteredUserName.isBlank()) {
			new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Missing Data",
					"" + "Please Enter All the Details");

		} else {
			EmpDAO emp = new EmpDAO(name, Id, selectedItem, salary, contact, address, enteredUserName, enterdPass);
			PerformOperations ope = new PerformOperations();
			saveData = ope.saveData(emp);
		}

		if (saveData > 0) {
			empName.setText(null);
			empAddress.setText(null);
			empId.setText(null);
			empSalary.setText(null);
			empContact.setText(null);
			empPost.setSelectionModel(null);
			userName.setText(null);
			passWord.setText(null);
			empPost.setValue(null);
			new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Done", "Employee Added Successfully");
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Something went wrong", "Some Error Occured");

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 2; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(cancel);
		effects[1].setShadowEffectOnButton(done);

		System.out.println("Hello Ima Here");
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(empTypes);
		empPost.setItems(list);

	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
}
