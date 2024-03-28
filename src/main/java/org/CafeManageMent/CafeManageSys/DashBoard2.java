package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashBoard2 implements Initializable {
	@FXML
	private AnchorPane mainPane;

	@FXML
	private HBox graphBox;
	@FXML
	private VBox myVBOX1;

	@FXML
	private Label ne;

	@FXML
	private Label np;

	@FXML
	private ImageView numSoldProducts;

	@FXML
	private ImageView numberOfCustomer;

	@FXML
	private ImageView tdsIncome;

	@FXML
	private Label ti;

	@FXML
	private Label tsp;

	@FXML
	private ImageView ttlIncome;
	@FXML
	private Button showGraph;

	@FXML
	private ComboBox<String> graphNode;
	private String[] graphNodeItems = { "Booking Graph", "Sales Graph", "Employee" };

	private PerformOperations ope = new PerformOperations();
	private List<EmpDAO> empList;
	private List<InventryDAO> inventryList;

	private int totalIncome;
	private int totalSold;
	private ScrollPane salesPane; // to display my grapj

//	private Label[] label;
//	private Label[] staff;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Adding graph node items
		graphNode.getItems().addAll(graphNodeItems);

		empList = ope.getEmployeeValues();
		ope.getAllBookings();
		inventryList = ope.getValues();

		inventryList.size();

		ne.setText(String.valueOf(empList.size()));
		np.setText(String.valueOf(inventryList.size()));
		// Getting total income
		for (InventryDAO dao : inventryList) {
			totalIncome += Integer.valueOf(dao.getPrice()) * Integer.valueOf(dao.getItemsSold());
			totalSold += Integer.valueOf(dao.getItemsSold());
		}
		ti.setText(String.valueOf(totalIncome));
		tsp.setText(String.valueOf(totalSold));

		numberOfCustomer.setImage(new Image(App.class.getResource("/Images/people.png").toExternalForm()));
		tdsIncome.setImage(new Image(App.class.getResource("/Images/save-money.png").toExternalForm()));
		ttlIncome.setImage(new Image(App.class.getResource("/Images/money-bag.png").toExternalForm()));
		numSoldProducts.setImage(new Image(App.class.getResource("/Images/dish.png").toExternalForm()));

//	********************************************************************************************  //

		graphNode.valueProperty().addListener((observable, oldValue, newValue) -> {
			String item = newValue;
			try {
				if (item == null) {
					graphBox.getChildren().clear();
					salesPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("salesGraph.fxml")));
					graphBox.getChildren().add(salesPane);
				} else if (item.equals("Sales Graph")) {
					graphBox.getChildren().clear();
					salesPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("salesGraph.fxml")));
					graphBox.getChildren().add(salesPane);
				} else if (item.equals("Booking Graph")) {
					new AlertClass().showAlert(Alert.AlertType.INFORMATION, "Not Available", "Comming Soon...");
				/*	graphBox.getChildren().clear();
					salesPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BookingGraph.fxml")));
					graphBox.getChildren().add(salesPane); */
				} else if (item.equals("Employee")) {
					new AlertClass().showAlert(Alert.AlertType.INFORMATION, "Not Available", "Comming Soon...");
				/*	graphBox.getChildren().clear();
					salesPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("employeeGraph.fxml")));
					graphBox.getChildren().add(salesPane); */
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
