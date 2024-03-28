 package org.CafeManageMent.CafeManageSys;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable {

	@FXML
	private CheckBox cb;
	@FXML
	private ImageView login;
	private Image imageLogin;
	@FXML
	TextField userName;
	@FXML
	TextField password;
	private EffectsOnButtons effects1, effects2;

	@FXML
	private Button loginButton;

	@FXML
	private Button quitButton;
	private List<EmpDAO> empList;
	PerformOperations ope = new PerformOperations();
	private boolean found = false;

	@FXML
	public void switchToSecondary() throws IOException {
		// Getting data from db
		empList = ope.getEmployeeValues();
		ope.getAllBookings(); 
		ope.getValues();

		System.out.println("Hello : " + ope.login(userName.getText(), password.getText()));
		// IF No table are created
		if (empList.size() == 0) {
			System.out.println(empList.size());
			EmpDAO empTable = new EmpDAO("demo", "demo", "Manager", "0000", "demo", "demo", "manager", "manager");
			ope.saveData(empTable);
			PopupLayoutDisplayer p = new PopupLayoutDisplayer();
			p.setPopupLayout("AddFirstManager", new Image(App.class.getResource("/Images/cafe.png").toExternalForm()),
					"Add Manager");

			App.setRoot("secondary");
			found = true;
		}

		String user = ope.login(userName.getText(), password.getText());
		if (user != null && user.equals("Manager")) {
			App.setRoot("secondary");
			found = true;
		} else if (user != null && (user.equals("Cook") || user.equals("Cachier"))) {
			App.setRoot("employee");
			found = true;
		}
		
		
		if (!found) {
//			App.setRoot("secondary");
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Invalid",
					"Invalid Username or PassWord" + " Or You Cannot Aceess this Application");
		}

	}

	@FXML
	void quiutAction(ActionEvent event) throws IOException {
		new PopupLayoutDisplayer().setPopupLayout("resetPassword",
				new Image(App.class.getResource("/Images/cafe.png").toExternalForm()), "Reset Password");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		imageLogin = new Image(App.class.getResource("/Images/login2.png").toExternalForm());
		login.setImage(imageLogin);

		effects1 = new EffectsOnButtons();
		effects2 = new EffectsOnButtons();
		effects1.setShadowEffectOnButton(loginButton);
		effects2.setShadowEffectOnButton(quitButton);

		cb.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (cb.isSelected()) {
					cb.setText("Hide Text");
				} else if (!cb.isSelected()) {
					cb.setText("Show Text");
				}

			}
		});

//		password.pass

	}

}
//<!-- created By instagram -> vishalbala_here -->
