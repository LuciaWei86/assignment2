	//Author：Fandi Wei, Student Number: s3667669

package miniNET.GUI;

import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import miniNET.Helper;
import miniNET.Constants.RelationshipConstant;
import miniNET.Exceptions.DeleteParentAlertGUI;
import miniNET.Models.PersonProfile;

public class SelectPersonGUI {
	public Scene individualMainScene() {
		GridPane pane = Menu.setUpPane();
		Label label = new Label("Please select one person");
		Button select = new Button("Select");
		Button back = new Button("Back");
		VBox layout = new VBox(10);

		ListView<String> personList = new ListView<>();
		personList.getItems().addAll(Menu.driver.loadPersonStorage().keySet());
		personList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(personList);

		pane.add(label, 0, 0);
		pane.add(layout, 0, 1);
		pane.add(select, 0, 2);
		pane.add(back, 1, 2);

		select.setOnAction(e -> {
			String personName = personList.getSelectionModel().getSelectedItem();
			if (personName != null){
				PersonProfile selectedPerson = Menu.driver.findPersonByName(personName);
				Menu.window.setScene(viewPersonScene(selectedPerson));
			}else{
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("MESSAGES");
				alert.setContentText("Must choose one from list");
				alert.show();
			}
		});
		back.setOnAction(e -> {
			Menu.window.setScene(Menu.startScene());
		});
		Scene scene = new Scene(pane, 400, 400);
		return scene;
	}

	public Scene viewPersonScene(PersonProfile person) {
		GridPane pane = Menu.setUpPane();
		Label label = new Label("Please choose one: ");
		Button btDisplay = new Button("1.Display profile");
		Button btRelation = new Button("2.Display relations");
		Button btDelete = new Button("3.Delete this person");
		Button btConnect = new Button("4.Make connection with other person");
		Button btBack = new Button("Back");

		pane.add(label, 0, 0);
		pane.add(btDisplay, 0, 2);
		pane.add(btRelation, 0, 4);
		pane.add(btDelete, 0, 6);
		pane.add(btConnect, 0, 8);
		pane.add(btBack, 0, 11);

		btDisplay.setOnAction(e -> {
			displayProfileAction(person);

		});

		btRelation.setOnAction(e -> {
			displayRelationAction(person);
		});

		btDelete.setOnAction(e -> {
			deletePersonAction(person);
		});
		
		btConnect.setOnAction(e -> {
			Menu.window.setScene(new AddConnectionGUI().addConnectionScene(person));
		});
		
		btBack.setOnAction(e -> {
			Menu.window.setScene(individualMainScene());
		});

		Scene scene = new Scene(pane, 400, 400);
		return scene;
	}

	private void deletePersonAction(PersonProfile person) {
		if (deletePerson(true)) {
			Menu.driver.deletePerson(person);
			if(person.getConnections().containsKey(RelationshipConstant.CHILD)){
				DeleteParentAlertGUI.deleteParentAlert();
			}
			deletePerson(false);
		}
	}

	private boolean deletePerson(boolean b) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		if (b) {
			alert.setHeaderText("WARNING!");
			alert.setContentText(
					"Are you sure you want to delete this person?");
		} else {
			alert.setHeaderText("successful!");
			Menu.window.setScene(individualMainScene());
		}
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			return true;
		}else if(result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}

	private void displayRelationAction(PersonProfile person) {
		GridPane pane = Menu.setUpPane();
		if (!person.getConnections().keySet().isEmpty() || !person.getConnections().values().isEmpty()) {
			int i = 0;
			for (String type : person.getConnections().keySet()) {
				ArrayList<PersonProfile> relationship = person.getConnections().get(type);
				for (PersonProfile r : relationship) {
					pane.add(new Text(type + " : " + r.getName()), 0, i);
					i++;
				}
			}
		} else {
			Text text1 = new Text("No relationship found of" + " " + person.getName());
			text1.setFill(Color.RED);
			pane.add(text1, 0, 0);
		}

		Button btBack = new Button("Back");
		pane.add(btBack, 2, 12);
		btBack.setOnAction(e -> {
			Menu.window.setScene(viewPersonScene(person));
		});
		Scene scene = new Scene(pane, 700, 500);
		Menu.window.setScene(scene);

	}

	private void displayProfileAction(PersonProfile person) {
		String name = person.getName();
		int age = person.getAge();
		String status = Helper.isEmptyString(person.getStatus()) ? "N/A" : person.getStatus();
		String gender = person.getGender();
		String image = Helper.isEmptyString(person.getImage()) ? "No Image" : person.getImage();

		Button btBack = new Button("Back");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		pane.add((new Label("Name: ")), 1, 3);
		pane.add((new Label("Image: ")), 1, 4);
		pane.add(new Label("Age: "), 1, 5);
		pane.add(new Label("Status: "), 1, 6);
		pane.add(new Label("Gender: "), 1, 7);
		pane.add(btBack, 7, 10);
		btBack.setOnAction(e -> {
			Menu.window.setScene(viewPersonScene(person));
		});
		pane.add((new Label(name)), 4, 3);
		pane.add((new Label(image)), 4, 4);
		pane.add(new Label(Integer.toString(age)), 4, 5);
		pane.add(new Label(status), 4, 6);
		pane.add(new Label(gender), 4, 7);

		Scene scene = new Scene(pane, 400, 400);
		Menu.window.setScene(scene);
		Menu.window.show();

	}

}
