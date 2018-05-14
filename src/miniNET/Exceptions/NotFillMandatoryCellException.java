	//Author：Fandi Wei, Student Number: s3667669

package miniNET.Exceptions;

import javafx.scene.control.Alert;

public class NotFillMandatoryCellException extends Exception{
	
	public void notFillMandatoryCellWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING!");
		alert.setContentText("Please fill in all cells except optional ones");
		alert.show();
	}

}
