package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.EmpDAO;
import com.dataBases.PerformOperations;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class EmployeeGraph implements Initializable {

	@FXML
	private CategoryAxis XAxis;
	@FXML
	private NumberAxis YAxis;
	@FXML
	private LineChart<String, Integer> lineChart;

	private PerformOperations operation = new PerformOperations();

	private XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<EmpDAO> list = operation.getEmployeeValues();

		for (EmpDAO doa : list) {
			series.getData().add(new XYChart.Data<String , Integer>(doa.getName(), Integer.parseInt(doa.getSalary())));
		}
		
		lineChart.getData().add(series);

	}

}
