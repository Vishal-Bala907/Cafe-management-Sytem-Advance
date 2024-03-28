package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.PerformOperations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResetPassword implements Initializable {
	@FXML
	private Button loginButton;

	@FXML
	private TextField newPassword;

	@FXML
	private Button quitButton;

	@FXML
	private TextField resetCode;

	@FXML
	private TextField userName;

	private List<EmpDAO> empList;
	private boolean found = false;
	PerformOperations ope = new PerformOperations();

	@FXML
	void quiutAction(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();

	}

	@FXML
	void switchToSecondary(ActionEvent event) {
		String username = userName.getText();
		String rs = resetCode.getText();

		for (EmpDAO obj : empList) {
			if (obj.getUserName().equals(username) && rs.equals("0000"+obj.getUserName())) {
				EmpDAO update = new EmpDAO(obj.getUserName(), obj.getID(), obj.getPost(), 
						obj.getSalary(), obj.getContant(), obj.getAddress(), obj.getUserName(), 
						newPassword.getText());
				update.setId(obj.getId());
				ope.updateEmployee(update);
				
				
				new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Done", "Password reset Done");
				found = true;
				break;
			}
		}
		if (!found) {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Error",
					"Either ResetCode or UserName is " + "Incorrect");
		} else {
//			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		empList = ope.getEmployeeValues();
		ope.getAllBookings();
		ope.getValues();

	}

}
