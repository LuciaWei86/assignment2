package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NotToBeCoupledException extends Exception {

	public void notToBeCoupledException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setContentText("Can not make a couple when at least one member is not an adult");
		alert.show();
	}
}
