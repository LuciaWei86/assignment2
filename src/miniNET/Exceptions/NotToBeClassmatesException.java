package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NotToBeClassmatesException extends Exception{

	public void notToBeClassmatesException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setContentText("Can not make a young child in a classmate relation");
		alert.show();
	}
}
