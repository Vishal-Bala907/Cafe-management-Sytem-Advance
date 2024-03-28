package org.CafeManageMent.CafeManageSys;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class SecondaryController implements Initializable {
    @FXML
    private Button switchUser;
	@FXML
	private ImageView numberOfCustomer;
	@FXML
	private ImageView tdsIncome;
	@FXML
	private ImageView ttlIncome;
	@FXML
	private ImageView numSoldProducts;
	@FXML
	private AnchorPane mainPane;

	@FXML
	private Button menu;
	@FXML
	private Button booking;

	@FXML
	private Button dashboard;

	@FXML
	private Button inventory;

	private PerformOperations ope = new PerformOperations();
	private List<EmpDAO> empList;
	private List<InventryDAO> inventryList;

	@FXML
	private Label ti;

	@FXML
	private Label tsp;

	@FXML
	private Label ne;

	@FXML
	private Label np;
	private int totalIncome;
	private int totalSold;

	@FXML
	private TextField changeName;

	@FXML
	private TextField changePass;

//	@FXML
//	private Label userName;

//	@FXML
//	private Label userPost;

	private EffectsOnButtons[] effects = new EffectsOnButtons[5];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			setLayout("DashBoard");
		} catch (IOException e) {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Error", "Error While Going to DashBoard");
			e.printStackTrace();
		}
		
		new EffectsOnButtons().setShadowEffectOnButton(switchUser);

		empList = ope.getEmployeeValues();
		ope.getAllBookings();
		inventryList = ope.getValues();
		ne.setText(String.valueOf(empList.size()));
		np.setText(String.valueOf(inventryList.size()));
		// Getting total income
		for (InventryDAO dao : inventryList) {
			totalIncome += Integer.valueOf(dao.getPrice()) * Integer.valueOf(dao.getItemsSold());
			totalSold += Integer.valueOf(dao.getItemsSold());
		}
		ti.setText(String.valueOf(totalIncome));
		tsp.setText(String.valueOf(totalSold));

		for (int i = 0; i < 4; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(booking);
		effects[1].setShadowEffectOnButton(dashboard);
		effects[2].setShadowEffectOnButton(inventory);
		effects[3].setShadowEffectOnButton(menu);

		numberOfCustomer.setImage(new Image(App.class.getResource("/Images/people.png").toExternalForm()));
		tdsIncome.setImage(new Image(App.class.getResource("/Images/save-money.png").toExternalForm()));
		ttlIncome.setImage(new Image(App.class.getResource("/Images/money-bag.png").toExternalForm()));
		numSoldProducts.setImage(new Image(App.class.getResource("/Images/dish.png").toExternalForm()));

	}

	@FXML
	public void gotoInventory(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Stage stageO = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stageO.setResizable(true);
		setLayout("inventory");

	}

	@FXML
	public void gotoDashBoard(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Stage stageO = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stageO.setResizable(true);
		setLayout("DashBoard");
	}

	@FXML
	public void manageStaff(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Stage stageO = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stageO.setResizable(true);
		setLayout("manageStaff");
	}

	public void setLayout(String fxml) throws IOException {
		AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml + ".fxml")));
		mainPane.getChildren().add(pane);
		AnchorPane.setRightAnchor(pane, 0.0d);
		AnchorPane.setBottomAnchor(pane, 0.0d);
		AnchorPane.setTopAnchor(pane, 0.0d);
		AnchorPane.setLeftAnchor(pane, 333.0d);
	}

	@FXML
	public void showMenuAndBill(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		Stage stageO = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stageO.setResizable(true);
		setLayout("MenuAndBill");
	}

	boolean found = false;

	@FXML
	public void logOut(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		for (EmpDAO emp : empList) {
			if (changePass.getText().equals(emp.getPassWord()) && changeName.getText().equals(emp.getUserName())
					&& emp.getPost().equals("Manager")) {
				App.setRoot("secondary");
//				new ManagerLayout().setLayout("DashBoard2");
				found = true;

			} else if (changePass.getText().equals(emp.getPassWord()) && changeName.getText().equals(emp.getUserName())
					&& (emp.getPost().equals("Cook") || emp.getPost().equals("Cachier"))) {
				App.setRoot("employee");
				found = true;
			}

		}
		if (!found) {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Invalid",
					"Invalid Username or PassWord" + " Or You Cannot Aceess this Application");
		}

	}

	@FXML
	public void getBookinDetails(@SuppressWarnings("exports") ActionEvent event) throws IOException {
		setLayout("Bookings");
	}

}

//<!-- created By instagram -> vishalbala_here -->