	//Author：Fandi Wei, Student Number: s3667669

package miniNET.Exceptions;

import javafx.scene.control.Alert;
import miniNET.Models.PersonProfile;

public class NotToBeFriendsException extends Exception{
	PersonProfile person, friend;
	public NotToBeFriendsException(PersonProfile person, PersonProfile friend) {
		this.person = person;
		this.friend = friend;
	}
	public void notToBeFriendsException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText("You cannot make friends connection with this person.");
		alert.showAndWait();
	}
}
