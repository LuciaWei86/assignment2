package miniNET.GUI;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import miniNET.Helper;
import miniNET.Models.PersonProfile;

public class SelectPersonGUI {
	public Scene individualMainScene() {
		GridPane pane = Menu.setUpPane();
		Label label = new Label("Please select one person");
		Button select = new Button("Select");
		Button cancel = new Button("Cancel");
		VBox layout = new VBox(10);

		ListView<String> personList = new ListView<>();
		personList.getItems().addAll(Menu.driver.loadPersonStorage().keySet());
		personList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(personList);

		pane.add(label, 0, 0);
		pane.add(layout, 0, 1);
		pane.add(select, 0, 2);
		pane.add(cancel, 1, 2);

		select.setOnAction(e -> {
			String personName = personList.getSelectionModel().getSelectedItem();
			PersonProfile selectedPerson = Menu.driver.findPersonByName(personName);
			Menu.window.setScene(viewPersonScene(selectedPerson));

		});
		cancel.setOnAction(e -> {
			Menu.window.setScene(Menu.startScene());
		});
		Scene scene = new Scene(pane, 400, 400);
		return scene;
	}

	private Scene viewPersonScene(PersonProfile person) {
		GridPane pane = Menu.setUpPane();
		Label label = new Label("Please choose one: ");
		Button btDisplay = new Button("Display profile");
		Button btRelation = new Button("Display relations");
		Button btFindRelation = new Button("Find out the parent or child of the person");
		Button btDelete = new Button("Delete this person");
		Button btBack = new Button("Back");

		pane.add(label, 0, 0);
		pane.add(btDisplay, 0, 1);
		pane.add(btRelation, 0, 2);
		pane.add(btFindRelation, 0, 3);
		pane.add(btDelete, 0, 4);
		pane.add(btBack, 0, 5);

		btDisplay.setOnAction(e -> {
			displayProfileAction(person);

		});

		btRelation.setOnAction(e -> {
			displayRelationAction(person);
		});

		btFindRelation.setOnAction(e -> {

		});

		btDelete.setOnAction(e -> {

		});

		btBack.setOnAction(e -> {
			Menu.window.setScene(individualMainScene());
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;
	}

	private void displayRelationAction(PersonProfile person) {
		GridPane pane = Menu.setUpPane();
		if (!person.getConnections().keySet().isEmpty()) {
			int i = 0;
			for (String type : person.getConnections().keySet()) {
				ArrayList<PersonProfile> relationship = person.getConnections().get(type);
				for (PersonProfile r : relationship) {
					pane.add(new Text(type + " : " + r.getName()), 0, i);
					i++;
				}
			}
		} else
		{
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

		Scene scene = new Scene(pane, 500, 500);
		Menu.window.setScene(scene);
		Menu.window.show();

	}

}
