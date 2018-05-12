package miniNET.GUI;

import javafx.scene.control.Alert;

public class NoDbAlertGUI {
	public void noDbAlert() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText("WARNING!");
		alert.setContentText("Database has not found! Close the window!");
		alert.showAndWait();
	}
}
