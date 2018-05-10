package miniNET.Exceptions;

import javafx.scene.control.Alert;
import miniNET.Models.PersonProfile;

public class NotToBeCoupledException extends Exception {

	private PersonProfile parent1, parent2;

	public NotToBeCoupledException(PersonProfile parent1, PersonProfile parent2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
	}

	public void notToBeCoupledException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(parent1.getName() + " is not Ault. He/She cannot have a COUPLE relationship.");
		alert.show();

	}
}
