package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.BookinDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class NewBooking implements Initializable {
	@FXML
	private TextField contact;

	@FXML
	private DatePicker date;

	@FXML
	private Spinner<Integer> cost;

	@FXML
	private TextField discount;

	@FXML
	private TextField empName;

	@FXML
	private TextField payment;

	@FXML
	private Spinner<Integer> seats;

	@FXML
	private TextField tatal;

	@FXML
	private Label lable;

	private String newDate;
	private List<BookinDAO> allBookings;
	private String discountValue = "";
	private int discountPercen = 0;
	private String totalPrice;

	@FXML
	private Button done;
	@FXML
	private Button cancel;
	@FXML
	private Button serach;
	@FXML
	private Button ok;
	EffectsOnButtons effects[] = new EffectsOnButtons[4];

	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);

	SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
			Integer.MAX_VALUE, 1);

	SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
			Integer.MAX_VALUE, 1);

	private PerformOperations operation = new PerformOperations();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 4; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(ok);
		effects[1].setShadowEffectOnButton(cancel);
		effects[2].setShadowEffectOnButton(done);
		effects[3].setShadowEffectOnButton(serach);

		allBookings = operation.getAllBookings();
		tatal.setEditable(false);

		cost.setValueFactory(valueFactory2);
		seats.setValueFactory(valueFactory3);

	}

	@FXML
	public void searchItem(@SuppressWarnings("exports") ActionEvent event) {
		int data = 0;
		newDate = date.getValue().toString();

		if (allBookings.size() == 0) {
			lable.setText("BOOKED : 00");
		}
		for (BookinDAO obj : allBookings) {
			if (obj.getDate().equals(newDate)) {
				data += Integer.parseInt(obj.getNumberOfPersons());
				lable.setText("Booked : " + data);
			} else {
				System.out.println("Hola");
				lable.setText("Booked : 00");
			}
		}
	}

	@FXML
	void okDiscount(ActionEvent event) {

		try {
			discountValue = String.valueOf(discount.getText());
			discountPercen = Integer.parseInt(discountValue);
			totalPrice = String.valueOf((seats.getValueFactory().getValue() * cost.getValueFactory().getValue()));
			totalPrice = String
					.valueOf(Integer.parseInt(totalPrice) - ((Integer.parseInt(totalPrice) * discountPercen) / 100));
			tatal.setText(String.valueOf(totalPrice));
		} catch (NumberFormatException e) {
			AlertClass alert = new AlertClass();
			alert.showAlert(Alert.AlertType.WARNING, "Wrong Values Enterd", "Please Enter Numbers Only");
			discount.setText(null);
		}
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	public void done(@SuppressWarnings("exports") ActionEvent event) {
		int saveBooking = -1;
		if (empName.getText().isEmpty() || payment.getText().isEmpty() || date.getValue() == null
				|| contact.getText().isEmpty() || discount.getText().isEmpty() || cost.getValueFactory().getValue() < 1
				|| seats.getValueFactory().getValue() < 1) {
			System.out.println("Name HERE dfasfdsfdsf" + empName.getText().isBlank());
			AlertClass alert = new AlertClass();
			alert.showAlert(Alert.AlertType.INFORMATION, "Something is empty", "Please Fill All The Fields");
		} else {
			try {
				date.getValue().toString();
			} catch (NullPointerException exp) {
				AlertClass alert = new AlertClass();
				alert.showAlert(Alert.AlertType.INFORMATION, "Empty Date", "Please Enter Date");

			}
			BookinDAO done = new BookinDAO(empName.getText(), String.valueOf(seats.getValueFactory().getValue()),
					String.valueOf(cost.getValueFactory().getValue()), payment.getText(), date.getValue().toString(),
					contact.getText(), totalPrice);
			saveBooking = operation.saveBooking(done);
		}
		if (saveBooking > 0) {
			new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Done", "Booked Succesfully");
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		} else {
			new AlertClass().showAlert(Alert.AlertType.CONFIRMATION, "Error !", "Something went wrong");
		}

	}

}
