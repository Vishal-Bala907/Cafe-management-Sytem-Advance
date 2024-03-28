package org.CafeManageMent.CafeManageSys;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.Inventory.BookingListData;
import com.dataBases.BookinDAO;
import com.dataBases.PerformOperations;
import com.effects.EffectsOnButtons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;

public class BookingController implements Initializable {
	@FXML
	private TableColumn<BookingListData, String> NumberOfPersons;

	@FXML
	private TableColumn<BookingListData, String> contact;

	@FXML
	private TableColumn<BookingListData, String> cost;

	@FXML
	private TableColumn<BookingListData, String> date;

	@FXML
	private TableColumn<BookingListData, String> id;

	@FXML
	private TableView<BookingListData> bookingTable;

	@FXML
	private TableColumn<BookingListData, String> name;

	@FXML
	private TableColumn<BookingListData, String> payment;
	@FXML

	private TableColumn<BookingListData, String> total;

	// Efects
	private EffectsOnButtons[] effects = new EffectsOnButtons[4];

	@FXML
	private Button refresh;

	@FXML
	private Button remove;

	@FXML
	private Button removeAll;

	@FXML
	private Button book;

	private PerformOperations operation = new PerformOperations();
	private List<BookinDAO> list;
	private ObservableList<BookingListData> values = FXCollections.observableArrayList();;

	@FXML
	void bookNew(ActionEvent event) throws IOException {
		PopupLayoutDisplayer popupLayoutDisplayer = new PopupLayoutDisplayer();
		popupLayoutDisplayer.setPopupLayout("bookNew",new Image(App.class.getResource("/Images/booking.png").toExternalForm()),
				"New Booking");
	}

	@FXML
	void onRefresh(ActionEvent event) {
		values.clear();
		list = operation.getAllBookings();
		for (BookinDAO item : list) {
			BookingListData obj = new BookingListData(String.valueOf(item.getId()), item.getName(),
					item.getNumberOfPersons(), item.getCost(), item.getPayment(), item.getDate(), item.getMob(),
					item.getTotalPrice());
			values.add(obj);
		}

		for (@SuppressWarnings("unused") BookingListData obj : values) {

			NumberOfPersons.setCellValueFactory(cellData -> cellData.getValue().getNumberOfPersons());
			contact.setCellValueFactory(cellData -> cellData.getValue().getMob());
			id.setCellValueFactory(cellData -> cellData.getValue().getId());
			cost.setCellValueFactory(cellData -> cellData.getValue().getCost());
			date.setCellValueFactory(cellData -> cellData.getValue().getDate());
			name.setCellValueFactory(cellData -> cellData.getValue().getName());
			payment.setCellValueFactory(cellData -> cellData.getValue().getPayment());
			total.setCellValueFactory(cellData -> cellData.getValue().getTotal());

			bookingTable.setItems(values);
		}

	}

	@FXML
	void removeAll(ActionEvent event) {
		AlertClass alert = new AlertClass();
		Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
		confirm.setTitle("confirmation");
		confirm.setContentText("Are you Sure");
		confirm.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				alert.showAlert(AlertType.CONFIRMATION, "Done", "All Bookings are Deleted");
			}
		});

	}

	@FXML
	void removeSingle(ActionEvent event) throws IOException {
		PopupLayoutDisplayer popupLayoutDisplayer = new PopupLayoutDisplayer();
		popupLayoutDisplayer.setPopupLayout("updateBooking",
				new Image(App.class.getResource("/Images/booking.png").toExternalForm()),
				"Delete Booking or View only");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < 4; i++) {
			effects[i] = new EffectsOnButtons();
		}
		effects[0].setShadowEffectOnButton(refresh);
		effects[1].setShadowEffectOnButton(book);
		effects[2].setShadowEffectOnButton(remove);
		effects[3].setShadowEffectOnButton(removeAll);
		onRefresh(null);

		list = operation.getAllBookings();
		System.out.println(values);
	}

}
