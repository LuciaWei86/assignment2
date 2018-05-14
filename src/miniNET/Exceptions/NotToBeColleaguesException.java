	//Author：Fandi Wei, Student Number: s3667669

package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NotToBeColleaguesException extends Exception{

	public void notToBeColleaguesException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setContentText("Can not connect a child in a colleague relation");
		alert.show();
	}
}
