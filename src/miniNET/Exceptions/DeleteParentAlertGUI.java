package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class DeleteParentAlertGUI {
	public static void deleteParentAlert(){
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Delete parent relationship will also delete his/her children");
		alert.showAndWait();
	}
}
