package miniNET.GUI;

import javafx.scene.control.Alert;

public class NoFileAlertGUI {
	public void noFileAlert() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("File has not found, will read from database");
		alert.showAndWait();
	}
}
