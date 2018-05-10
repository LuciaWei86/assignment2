package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NotIntergerException extends Exception{
	
	public void notIntergerWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Please input an intefer for age");
		alert.show();
	}
}
