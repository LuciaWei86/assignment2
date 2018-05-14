	//Author：Fandi Wei, Student Number: s3667669

package miniNET.Exceptions;

import javafx.scene.control.Alert;
import miniNET.Models.PersonProfile;

public class NoAvailableException extends Exception {
	private PersonProfile parent1, parent2;

	public NoAvailableException(PersonProfile parent1, PersonProfile parent2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
	}

	public void noAvailableWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		if (parent1.getConnections().containsKey("couple")) {
			alert.setContentText(parent1.getName() + " already has a partner ("
					+ parent1.getConnections().get("couple").get(0).getName() + "). " + parent1.getName() + " and "
					+ parent2.getName() + " cannot be couple. " + "Please rechoose partner.");
		} else {
			alert.setContentText(parent2.getName() + " already has a partner ("
					+ parent2.getConnections().get("couple").get(0).getName() + "). " + parent1.getName() + " and "
					+ parent2.getName() + " cannot be couple. " + "Please rechoose partner.");
		}
		alert.show();
	}
}
