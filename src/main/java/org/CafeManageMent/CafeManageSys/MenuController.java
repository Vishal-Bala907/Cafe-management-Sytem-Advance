package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.effects.EffectsOnButtons;

import com.Inventory.PrintBillItems;
import com.Inventory.ShowBillItemsOnBill;
import com.dataBases.DataManagement;
import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.ByteArrayInputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class MenuController implements Initializable {
	@FXML
	private FlowPane menuArea;

	@FXML
	private ScrollPane scPane;

	private AnchorPane[] menuItems;
	private Label[] itemNameAndPrice;
	private ImageView[] itemImage;
	private HBox[] hbox;
	private Spinner<Integer>[] spinner;
	private Button[] addButton;
	private int items = 0; 
	private int height = 700;

	private ArrayList<PrintBillItems> PrintBillItems = new ArrayList<PrintBillItems>();

	// BIll Content
	@FXML
	private TableView<ShowBillItemsOnBill> InventoryTreeView;

	@FXML
	private TableColumn<ShowBillItemsOnBill, String> nameColumn;

	@FXML
	private TableColumn<ShowBillItemsOnBill, String> priceColumn;

	@FXML
	private TableColumn<ShowBillItemsOnBill, String> quantityColumn;

	@FXML
	private Button done;
	@FXML
	private Button reset;
	@FXML
	private Label GT;
	private int GrandTotal = 0;

	ArrayList<String> quantity = new ArrayList<>();
	ArrayList<String> itemId = new ArrayList<>();

	ObservableList<ShowBillItemsOnBill> showBillItemsOnBills = FXCollections.observableArrayList();

	List<InventryDAO> update; // values need to update

	List<InventryDAO> values; // values from the db

	public void setMenuArea(@SuppressWarnings("exports") FlowPane menuArea) {
		this.menuArea = menuArea;
	}

	public void setMenuItems(@SuppressWarnings("exports") AnchorPane[] menuItems) {
		for (int i = 0; i < menuItems.length; i++) {
			menuItems[i] = new AnchorPane();
			menuItems[i].setMaxHeight(Double.MAX_VALUE);
			menuItems[i].setMaxWidth(Double.MAX_VALUE);
			menuItems[i].setMinHeight(225.0);
			menuItems[i].setMinWidth(225.0);
			menuItems[i].setPrefHeight(225.0);
			menuItems[i].setPrefWidth(225.0);
			menuItems[i].setStyle("-fx-background-color: #96eaff; -fx-border-color: #00838c; -fx-border-width: 3px;");
			EffectsOnButtons ef = new EffectsOnButtons();
			ef.setShadowEffectOnMenuItems(menuItems[i]);
		}

	}

	public void setItemNameAndPrice(@SuppressWarnings("exports") Label[] itemNameAndPrice) {
		for (int i = 0; i < itemNameAndPrice.length; i++) {
			itemNameAndPrice[i] = new Label();
			itemNameAndPrice[i].setAlignment(javafx.geometry.Pos.CENTER);
			itemNameAndPrice[i].setLayoutY(6.0);
			itemNameAndPrice[i].setPrefHeight(32.0);
			itemNameAndPrice[i].setPrefWidth(225.0);
			itemNameAndPrice[i].setText(values.get(i).getName() + "  Rs." + values.get(i).getPrice());
			itemNameAndPrice[i].setStyle("-fx-background-color: pink;");
			AnchorPane.setLeftAnchor(itemNameAndPrice[i], 0.0);
			AnchorPane.setRightAnchor(itemNameAndPrice[i], 0.0);
			AnchorPane.setTopAnchor(itemNameAndPrice[i], 0.0);
			Font font = new Font(13.0);
			itemNameAndPrice[i].setFont(font);

//			itemId[i] = values.get(i).getId();
		}
	}

	public void setItemImage(@SuppressWarnings("exports") ImageView[] itemImage) {
		for (int i = 0; i < itemImage.length; i++) {
			itemImage[i] = new ImageView();
			itemImage[i].setFitHeight(150.0);
			itemImage[i].setFitWidth(200.0);
			itemImage[i].setLayoutX(13.0);
			itemImage[i].setLayoutY(32.0);

			byte[] arr = values.get(i).getImage();
//			InputStream inputStream = ImageIO.read(new ByteArrayInputStream(arr));
			Image image = new Image(new ByteArrayInputStream(arr));

			itemImage[i].setImage(image);
			itemImage[i].setPreserveRatio(false);

			itemImage[i].setPickOnBounds(true);
			itemImage[i].setPreserveRatio(false);
			AnchorPane.setBottomAnchor(itemImage[i], 43.0);
		}
	}

	public void setHbox(@SuppressWarnings("exports") HBox[] hbox) {
		for (int i = 0; i < hbox.length; i++) {
			hbox[i] = new HBox();
			hbox[i].setAlignment(javafx.geometry.Pos.CENTER);
			hbox[i].setLayoutX(13.0);
			hbox[i].setLayoutY(180.0);
			hbox[i].setPrefHeight(45.0);
			hbox[i].setPrefWidth(200.0);
			hbox[i].setSpacing(25.0);
			hbox[i].setStyle("-fx-background-color: pink;");
			AnchorPane.setBottomAnchor(hbox[i], 0.0);
			AnchorPane.setLeftAnchor(hbox[i], 0.0);
			AnchorPane.setRightAnchor(hbox[i], 0.0);
			AnchorPane.setTopAnchor(hbox[i], 180.0);

		}
	}

	public void setSpinner(Spinner<Integer>[] spinner) {
		for (int i = 0; i < spinner.length; i++) {
			Spinner<Integer> range = new Spinner<Integer>(0, 50, 0);
			spinner[i] = range;
			spinner[i].setEditable(true);
			spinner[i].setPrefHeight(25.0);
			spinner[i].setPrefWidth(71.0);
		}
	}

	public void setAddButton(@SuppressWarnings("exports") Button[] addButton) {
		for (int i = 0; i < addButton.length; i++) {
			addButton[i] = new Button();
			addButton[i].setAlignment(javafx.geometry.Pos.CENTER);
			addButton[i].setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
			addButton[i].setMnemonicParsing(false);
			addButton[i].setText("Add");
			addButton[i].setOnMouseClicked(handler);
			addButton[i].setId(String.valueOf(i));

		}
	}

	public void refresh() {
		for (int i = 0; i < values.size(); i++) {
			hbox[i].getChildren().addAll(addButton[i], spinner[i]);
			menuItems[i].getChildren().addAll(itemNameAndPrice[i], itemImage[i], hbox[i]);
		}
		if (items <= 6) {
			scPane.setMinHeight(height);
		} else {
			scPane.setMinHeight((int) ((items / 6) + 1) * height);
		}
		scPane.setFitToWidth(true);

		menuArea.getChildren().addAll(menuItems);

	}

	@SuppressWarnings("unchecked")
	public void initializeMenuItems() {
		PerformOperations ope = new PerformOperations();
		update = new ArrayList<InventryDAO>();

		values = ope.getValues();
		items = values.size();

//		itemId = new int[items];

		menuItems = new AnchorPane[items];
		itemNameAndPrice = new Label[items];
		itemImage = new ImageView[items];
		hbox = new HBox[items];
		spinner = new Spinner[items];
		addButton = new Button[items];

		setMenuItems(menuItems);
		setItemNameAndPrice(itemNameAndPrice);
		setItemImage(itemImage);
		setHbox(hbox);
		setAddButton(addButton);
		setSpinner(spinner);

//		setItemId();
		refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeMenuItems();
		new EffectsOnButtons().setShadowEffectOnButton(done);
		new EffectsOnButtons().setShadowEffectOnButton(reset);

	}

	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			PrintBillItems items = new PrintBillItems(); // bill printing

			ShowBillItemsOnBill billData; // class

			int numberOfItemsSelected = 0;
			PerformOperations ope = new PerformOperations();

			List<InventryDAO> values2 = ope.getValues(); // old values from DB

			int id = Integer.parseInt(((Button) event.getSource()).getId()); // button id

			numberOfItemsSelected = spinner[id].getValueFactory().getValue();

			// quantity to be updated
			quantity.add(String.valueOf(values2.get(id).getItemsSold() + numberOfItemsSelected));

			// item to be updated
			itemId.add(String.valueOf(values2.get(id).getId()));

			// Billing
			float itemTotalPrice = Integer.valueOf(values2.get(id).getPrice()) * numberOfItemsSelected;
			GrandTotal += itemTotalPrice;
			GT.setText("Grand Total :" + String.valueOf(GrandTotal) + " Rs.");

			items.setItemName(values2.get(id).getName());
			items.setItemPrice(String.valueOf(itemTotalPrice));
			items.setItemQuantity(String.valueOf(numberOfItemsSelected));
			PrintBillItems.add(items);

			billData = new ShowBillItemsOnBill(values2.get(id).getName(), String.valueOf(itemTotalPrice),
					String.valueOf(numberOfItemsSelected));

			showBillItemsOnBills.add(billData);

			priceColumn.setCellValueFactory(cellData -> cellData.getValue().getItemPrice());
			nameColumn.setCellValueFactory(cellData -> cellData.getValue().getItemName());
			quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getItemQuantity());

			InventoryTreeView.setItems(showBillItemsOnBills);

		}

	};

	@FXML
	public void printBill(@SuppressWarnings("exports") ActionEvent event) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null && job.showPrintDialog(InventoryTreeView.getScene().getWindow())) {
			boolean success = job.printPage(InventoryTreeView);
			if (success) {
				job.endJob();
			}
		}
	}

	@FXML
	public void makeOrder(@SuppressWarnings("exports") ActionEvent event) {
		GrandTotal = 0;

		GT.setText("Grand Total :" + String.valueOf(GrandTotal) + " Rs.");

		InventryDAO object = new InventryDAO();
		PerformOperations ope = new PerformOperations();

		int save = -1;

		int listSize = quantity.size();

//		DataManagement manage; // = new DataManagement();
		for (int i = 0; i < listSize; i++) {
			save = ope.updateQuantity(object, quantity.get(i), String.valueOf(itemId.get(i)));

			// Saving data for data management

			ope.updateInBillDatabase(new DataManagement(nameColumn.getCellData(i), priceColumn.getCellData(i),
					quantityColumn.getCellData(i), LocalDate.now().toString(),
					String.valueOf(Float.parseFloat(priceColumn.getCellData(i))
							* Float.parseFloat(quantityColumn.getCellData(i)))));

//			ope.updateInBillDatabase(manage);
//			manage = null;

		}

		if (save > 0) {
			new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Done", "Done");
			showBillItemsOnBills.clear();
			InventoryTreeView.setItems(showBillItemsOnBills);
			values.clear();
			update.clear();
			itemId.clear();
			quantity.clear();
//			quantityColumn.

		} else {
			new AlertClass().showAlert(Alert.AlertType.ERROR, "Something went wrong", "Some Error Occured");
		}

	}

	@FXML
	public void resetBill(@SuppressWarnings("exports") ActionEvent event) {
		showBillItemsOnBills.clear();
		values.clear();
		update.clear();
		itemId.clear();
		quantity.clear();
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getItemPrice());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getItemQuantity());
		GT.setText("GrandTotal : ");
		InventoryTreeView.setItems(showBillItemsOnBills);

	}

	// Responsive layout

}
