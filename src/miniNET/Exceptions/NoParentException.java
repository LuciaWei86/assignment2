	//Author：Fandi Wei, Student Number: s3667669

package miniNET.Exceptions;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import miniNET.Models.PersonProfile;

public class NoParentException extends Exception {

	private ArrayList<PersonProfile> noParent;

	public NoParentException(ArrayList<PersonProfile> noParent) {
		this.noParent = noParent;
	}

	public void noParentException() {

		String name = "";
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		for (PersonProfile child : noParent) {
			name += child.getName() + " ";
		}
		alert.setContentText("Child: " + name + " have/has no parent.Can't be added into network.");
		alert.show();
	}
}
