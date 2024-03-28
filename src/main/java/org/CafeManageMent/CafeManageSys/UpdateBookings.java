package org.CafeManageMent.CafeManageSys;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.dataBases.BookinDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class UpdateBookings implements Initializable {
	@FXML
	private TextField contact;

	@FXML
	private DatePicker date;

	@FXML
	private TextField cost;

	@FXML
	private TextField discount;

	@FXML
	private TextField empName;

	@FXML
	private TextField payment;

	@FXML
	private TextField seats;

	@FXML
	private Button ok;

	@FXML
	private TextField tatal;
	@FXML
	private Label lable;

	@FXML
	private Button serach;
	@FXML
	private Button cancel;

	@FXML
	private Button done;

	private EffectsOnButtons[] effects = new EffectsOnButtons[3];

	private List<BookinDAO> allBookings;
	private String totalPrice;
	private int id;
	private boolean booked = false;

	private PerformOperations operation = new PerformOperations();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 3; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(cancel);
		effects[1].setShadowEffectOnButton(done);
		effects[2].setShadowEffectOnButton(serach);
		allBookings = operation.getAllBookings();
		tatal.setEditable(false);

	}

	@FXML
	public void searchItem(@SuppressWarnings("exports") ActionEvent event) {
		String name = empName.getText();
		for (BookinDAO dao : allBookings) {
			if (dao.getName().equals(name)) {
				empName.setText(name);
				date.setValue(LocalDate.parse(dao.getDate()));
				cost.setText(dao.getCost());
				seats.setText(dao.getNumberOfPersons());
				payment.setText(dao.getPayment());
				contact.setText(dao.getMob());
				tatal.setText(dao.getTotalPrice());
				id = Integer.valueOf(dao.getId());
				lable.setText(dao.getNumberOfPersons());
				booked = true;
			}
		}
		if (!booked) {
			AlertClass alert = new AlertClass();
			alert.showAlert(Alert.AlertType.ERROR, "None", "Booking Not Found");
			discount.setText(null);
		}
	}

	@FXML
	void reset(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	public void done(@SuppressWarnings("exports") ActionEvent event) {

		BookinDAO done = new BookinDAO(empName.getText(), seats.getText(), cost.getText(), payment.getText(),
				date.getValue().toString(), contact.getText(), totalPrice);
		done.setId(id);
		Alert con = new Alert(Alert.AlertType.CONFIRMATION);
		con.setContentText("Are you Sure");
		con.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				AlertClass alert = new AlertClass();
				operation.deleteSingleBooking(String.valueOf(id));
				alert.showAlert(AlertType.CONFIRMATION, "Done", " Booking Deleted");
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
			}
		});
	}

}
