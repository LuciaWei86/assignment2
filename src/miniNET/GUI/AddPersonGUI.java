	//Author：Fandi Wei, Student Number: s3667669

package miniNET.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import miniNET.Helper;
import miniNET.Exceptions.NoDuplicatePersonException;
import miniNET.Exceptions.NoSuchAgeException;
import miniNET.Exceptions.NotFillMandatoryCellException;
import miniNET.Exceptions.NotIntergerException;
import miniNET.Models.AdultProfile;
import miniNET.Models.PersonProfile;

public class AddPersonGUI {
	public Scene addPersonScene() {
		GridPane pane = Menu.setUpPane();
		pane.add(new Label("Name:"), 0, 0);
		TextField personName = new TextField();
		pane.add(personName, 1, 0);
		pane.add(new Label("Age"), 0, 1);
		TextField personAge = new TextField();
		pane.add(personAge, 1, 1);
		pane.add(new Label("Gender"), 0, 2);
		HBox root = new HBox();
		ToggleGroup group = new ToggleGroup();
		RadioButton female = new RadioButton("Female");
		female.setToggleGroup(group);
		female.setSelected(true);
		female.setUserData("F");
		RadioButton male = new RadioButton("Male");
		male.setToggleGroup(group);
		male.setUserData("M");
		root.getChildren().add(female);
		root.getChildren().add(male);
		pane.add(root, 1, 2);
		pane.add(new Label("Status"), 0, 3);
		TextField personStatus = new TextField();
		pane.add(personStatus, 1, 3);
		pane.add(new Label("(Optional)"), 2, 3);
		pane.add(new Label("Photo"), 0, 4);
		Label personPhoto = new Label();
		pane.add(personPhoto, 1, 4);
		Button upload = new Button("Upload");
		pane.add(upload, 2, 4);
		pane.add(new Label("(Optional)"), 3, 4);

		Button btAdd = new Button("Add");
		pane.add(btAdd, 0, 5);
		Button btCancel = new Button("Back to Main Menu");
		pane.add(btCancel, 2, 5);

		upload.setOnAction(e -> {
			File photoFile;
			photoFile = uploadPhoto(personName.getText().trim(), personPhoto);
			personPhoto.setText(photoFile.getAbsolutePath());
		});

		btAdd.setOnAction(e -> {
			String name = personName.getText().trim();
			String image = personPhoto.getText().trim();
			String status = personStatus.getText().trim();
			String gender = (String) group.getSelectedToggle().getUserData();
			String age = personAge.getText().trim();
			try {
				if(addPerson(name, image, status, gender, age)){
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("MESSAGES");
					alert.setContentText("Congratulations! Add person successfully!");
					alert.showAndWait();
//					Menu.window.setScene(Menu.startScene());
				}
			} catch (NotFillMandatoryCellException e1) {
				e1.notFillMandatoryCellWarning();
			} catch (NotIntergerException exception) {
				exception.notIntergerWarning();
			} catch (NoSuchAgeException exception) {
				exception.noSuchAgeWarning();
			} catch (NoDuplicatePersonException e2) {
				e2.noDuplicatePersonWarning();
			}

		});

		btCancel.setOnAction(e -> {
			Menu.window.setScene(Menu.startScene());
		});

		Scene scene = new Scene(pane, 800, 500);
		return scene;
	}

	private boolean addPerson(String name, String image, String status, String gender, String age)
			throws NotFillMandatoryCellException, NotIntergerException, NoSuchAgeException, NoDuplicatePersonException {

		PersonProfile currentPerson;

		if (Helper.isEmptyString(name) || Helper.isEmptyString(age)) {
			throw new NotFillMandatoryCellException();
		} else if (!(age.matches("\\d*") || age.matches("-\\d*"))) {
			throw new NotIntergerException();
		} else {
			int personAge = Integer.parseInt(age);
			if (personAge < 0 || personAge > 150)
				throw new NoSuchAgeException();
			else {
				currentPerson = Menu.driver.addPerson(name, image, status, gender, personAge);
				if (currentPerson == null)
					throw new NoDuplicatePersonException();
				else if (currentPerson instanceof AdultProfile) {
					currentPerson.setImage(savePhoto(name, image));
					Menu.window.setScene(new AddConnectionGUI().addConnectionScene(currentPerson));
					return true;
				}else{
					Menu.window.setScene(new AddConnectionGUI().addParent1ConnectionScene(currentPerson));
					return false;
				}
			}
		}
	}

	private File uploadPhoto(String name, Label personPhoto) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter photoFilter = new FileChooser.ExtensionFilter("PNG files, JPG files, JPEG files",
				"*.PNG", "*.JPG", "*JPEG");
		fileChooser.getExtensionFilters().addAll(photoFilter);
		File file = fileChooser.showOpenDialog(null);
		return file;
	}

	private String savePhoto(String name, String path) {
		String fileType;
		if (!path.equals("")) {
			File file = new File(path);
			fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				File output = new File("src/miniNET/DataSeed/photoImage/" + name.trim() + "Photo." + fileType);
				ImageIO.write(bufferedImage, fileType, output);
				return output.getName();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return path;
	}

}
