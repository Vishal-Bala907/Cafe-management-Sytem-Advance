package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DashBoard implements Initializable {
	@FXML
	private AnchorPane mainPane;

	@FXML
	private VBox myVBOX;

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
	
	private PerformOperations ope = new PerformOperations();
	private List<EmpDAO> empList;
	private List<InventryDAO> inventryList;

	private int totalIncome;
	private int totalSold;
	private int totalInventory;
	private Label[] label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		empList = ope.getEmployeeValues();
		ope.getAllBookings();
		inventryList = ope.getValues();
		totalInventory = inventryList.size();
		label = new Label[totalInventory];
		setItemsLabels();
		if( totalInventory%7==0) {
			myVBOX.setMinHeight(480 * (totalInventory/7)*2 );
		}
		
		
		ne.setText(String.valueOf(empList.size()));
		np.setText(String.valueOf(inventryList.size()));
		//Getting total income
		for(InventryDAO dao : inventryList) {
			totalIncome += Integer.valueOf(dao.getPrice()) * Integer.valueOf(dao.getItemsSold()); 
			totalSold += Integer.valueOf(dao.getItemsSold());
		}
		ti.setText(String.valueOf(totalIncome));
		tsp.setText(String.valueOf(totalSold));


		numberOfCustomer.setImage(new Image(App.class.getResource("/Images/people.png").toExternalForm()));
		tdsIncome.setImage(new Image(App.class.getResource("/Images/save-money.png").toExternalForm()));
		ttlIncome.setImage(new Image(App.class.getResource("/Images/money-bag.png").toExternalForm()));
		numSoldProducts.setImage(new Image(App.class.getResource("/Images/dish.png").toExternalForm()));
	}
	
	public void setItemsLabels() {
		for(int i=0;i<totalInventory;i++) {
			label[i] = new Label();
			label[i].setAlignment(javafx.geometry.Pos.CENTER);
			label[i].setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
			label[i].setMinHeight(50.0);
			label[i].setMinWidth(600.0);
			label[i].setStyle("-fx-background-color: #9D76C1; -fx-background-radius: 10px; -fx-border-color: Black; -fx-border-radius: 10px;");
			label[i].setTextFill(javafx.scene.paint.Color.WHITE);
			label[i].setFont(Font.font("System Bold", 20.0));
			
			label[i].setText("Name : "+inventryList.get(i).getName()+ " | Price : "+
					inventryList.get(i).getPrice()+" | Sold : "+inventryList.get(i).getItemsSold());
			
		}
		myVBOX.getChildren().addAll(label);
		
	}

}
