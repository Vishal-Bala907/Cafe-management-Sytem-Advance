package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.dataBases.DataManagement;
import com.dataBases.InventryDAO;
import com.dataBases.PerformOperations;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SalesGraph implements Initializable {

	@FXML
	private CategoryAxis XAxis;
	@FXML
	private NumberAxis YAxis;
	@FXML
	private LineChart<String, Float> lineChart;
	@FXML
	private ComboBox<String> selectDaysBox;
	@FXML
	private Label maxIncomeLable;

	@FXML
	private Label maxQuantityLabel;
 
	@FXML
	private Label minIncomeLabel;

	@FXML
	private Label minQuantityLabel; 

	private PerformOperations operation = new PerformOperations();
	private String[] selectDaysBoxItems = { "Today", "Previous Day", "Last 7 Days", "Last 30 Days", "All" };

	private XYChart.Series<String, Float> series = new XYChart.Series<String, Float>();
	private Map<Float, String> salesMap;
	private Map<Float, String> incomeMap;
	private List<Float> salesList;
	private List<Float> incomeList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Populating Combobox
		selectDaysBox.getItems().addAll(selectDaysBoxItems);

		// Getting ALl Values from DB
		List<InventryDAO> list = operation.getValues();

		salesMap = new HashMap<Float, String>();
		incomeMap = new HashMap<Float, String>();

		salesList = new ArrayList<Float>();
		incomeList = new ArrayList<Float>();

		for (InventryDAO doa : list) {
			series.getData().add(new XYChart.Data<String, Float>(doa.getName(), Float.parseFloat(doa.getPrice())));
		}
		lineChart.getData().add(series);

		// Event listener on combobox
		selectDaysBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			String item = newValue;
			try {
				if (item == null) {

				} else if (item.equals("Previous Day")) {
					incomeList.clear();
					salesList.clear();
					incomeMap.clear();
					salesMap.clear();
					List<DataManagement> previosDayList = operation
							.previosDayList(LocalDate.now().minusDays(1).toString());

					// Removing OLD data
					lineChart.getData().clear();
					series.getData().clear();

					for (DataManagement dao : previosDayList) {
						series.getData().add(
								new XYChart.Data<String, Float>(dao.getName(), Float.parseFloat(dao.getQuantity())));

						salesMap.put(Float.parseFloat(dao.getQuantity()), dao.getName());
						incomeMap.put(Float.parseFloat(dao.getIncome()), dao.getName());

						salesList.add(Float.parseFloat(dao.getQuantity()));
						incomeList.add(Float.parseFloat(dao.getIncome()));

						maxQuantityLabel.setText(String.valueOf(Collections.max(salesList)) + " : "
								+ salesMap.get(Collections.max(salesList)));
						minQuantityLabel.setText(String.valueOf(Collections.min(salesList)) + " : "
								+ salesMap.get(Collections.min(salesList)));

						maxIncomeLable.setText(String.valueOf(Collections.max(incomeList)) + " : "
								+ incomeMap.get(Collections.max(incomeList)));
						minIncomeLabel.setText(String.valueOf(Collections.min(incomeList)) + " : "
								+ incomeMap.get(Collections.min(incomeList)));

					}
					lineChart.getData().add(series);
				} else if (item.equals("Last 7 Days")) { 
 					plotGraph(7);

				} else if (item.equals("Last 30 Days")) {
					plotGraph(30);
				}else if  (item.equals("All")){
					plotGraph(0);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
	
	public void plotGraph(int dates) {
		incomeList.clear();
		salesList.clear();
		incomeMap.clear();
		salesMap.clear();
		// This instance will hold the sum of all data of the last 7 days
		List<DataManagement> commonList = new ArrayList<DataManagement>();

		// This Store the names of all the unique items
		List<String> itemNames = new ArrayList<String>();

		// Takes single objects from multiple lists and add into a single list
		List<DataManagement> onlyListOfObjects = new ArrayList<DataManagement>();
		
		if(dates != 0) {
		for (int i = 1; i <= dates; i++) {
			List<DataManagement> previosDayList = operation
					.previosDayList(LocalDate.now().minusDays(i).toString());
			for (DataManagement sigleObject : previosDayList) {
				onlyListOfObjects.add(sigleObject);
			} 
			previosDayList.clear();
		}}
		else {
			List<DataManagement> previosDayList = operation.getAllPreviousData();
			for (DataManagement sigleObject : previosDayList) {
				onlyListOfObjects.add(sigleObject);
			} 
			previosDayList.clear();
		}
		
		// Creating a unique object every time when a unique item name found
		for (DataManagement myData : onlyListOfObjects) {
			if (!itemNames.contains(myData.getName())) {
				itemNames.add(myData.getName());
				commonList.add(new DataManagement(myData.getName(), "0.0", "0.0", "", "0.0"));
			}
		}

		for (DataManagement names : commonList) {
			for (DataManagement data : onlyListOfObjects) {
				if (names.getName().equals(data.getName())) {
					String income = String.valueOf(
							Float.parseFloat(names.getIncome()) + Float.parseFloat(data.getIncome()));
					String qt = String.valueOf(
							Float.parseFloat(names.getQuantity()) + Float.parseFloat(data.getQuantity()));
					names.setIncome(income);
					names.setQuantity(qt);
					income = "";
					qt = "";
				}
			}
		}
		// Adding data to list and Maps
		for (DataManagement data : commonList) {
			incomeMap.put(Float.parseFloat(data.getIncome()), data.getName());
			salesMap.put(Float.parseFloat(data.getQuantity()), data.getName());
			incomeList.add(Float.parseFloat(data.getIncome()));
			salesList.add(Float.parseFloat(data.getQuantity()));
		}
		
		lineChart.getData().clear();
		series.getData().clear();
		
		// Plotting graph with new values
		for (DataManagement dao : commonList) {
			series.getData().add(
					new XYChart.Data<String, Float>(dao.getName(), 
							Float.parseFloat(dao.getQuantity())));
		}
		// Updating labels

		maxQuantityLabel.setText(String.valueOf(Collections.max(salesList)) + " : "
				+ salesMap.get(Collections.max(salesList)));
		minQuantityLabel.setText(String.valueOf(Collections.min(salesList)) + " : "
				+ salesMap.get(Collections.min(salesList)));

		maxIncomeLable.setText(String.valueOf(Collections.max(incomeList)) + " : "
				+ incomeMap.get(Collections.max(incomeList)));
		minIncomeLabel.setText(String.valueOf(Collections.min(incomeList)) + " : "
				+ incomeMap.get(Collections.min(incomeList)));
		lineChart.getData().add(series);

	}
	
	public String getMaxNumber(String[] arr) {

		return null;
	}

}
