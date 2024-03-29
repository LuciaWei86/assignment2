	//Author：Fandi Wei, Student Number: s3667669

package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NoSuchAgeException extends Exception {

	public void noSuchAgeWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Can not input age over 150");
		alert.show();
	}
}