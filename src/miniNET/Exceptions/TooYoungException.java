package miniNET.Exceptions;

import javafx.scene.control.Alert;
import miniNET.Models.PersonProfile;

public class TooYoungException extends Exception{
	PersonProfile person;
	public TooYoungException(PersonProfile person) {
		this.person = person;
	}
	public void tooYoungException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("Can not make friend with a young child.");
		alert.showAndWait();
	}
}
