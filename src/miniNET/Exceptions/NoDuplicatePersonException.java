package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NoDuplicatePersonException extends Exception{

	public void noDuplicatePersonWarning() {
		
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("The person is already existing in datatbase");
		alert.show();
	}
}
