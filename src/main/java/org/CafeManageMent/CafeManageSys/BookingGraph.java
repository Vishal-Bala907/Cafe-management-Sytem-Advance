package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.BookinDAO;
import com.dataBases.PerformOperations;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BookingGraph implements Initializable {

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
		List<BookinDAO> list = operation.getAllBookings();

		for (BookinDAO doa : list) {
			series.getData().add(new XYChart.Data<String , Integer>(doa.getDate(), Integer.parseInt(doa.getNumberOfPersons())));
		}
		
		lineChart.getData().add(series);

	}

}
