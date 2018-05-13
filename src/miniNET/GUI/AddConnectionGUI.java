package miniNET.GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import miniNET.Helper;
import miniNET.Constants.RelationshipConstant;
import miniNET.Exceptions.*;
import miniNET.Models.AdultProfile;
import miniNET.Models.PersonProfile;

public class AddConnectionGUI {
	private GridPane pane = Menu.setUpPane();

	private ToggleGroup loadRelationType(PersonProfile person) {
		ToggleGroup group = new ToggleGroup();
		VBox vbBox = new VBox(10);
		vbBox.setPadding(new Insets(20, 20, 20, 20));
		pane.add(new Label("Relation Type"), 0, 0);
		pane.add(vbBox, 0, 4);
		if (person instanceof AdultProfile) {
			for (String r : RelationshipConstant.ADULTRELATION) {
				RadioButton button = new RadioButton(r);
				button.setToggleGroup(group);
				button.setUserData(r);
				vbBox.getChildren().add(button);
			}
		} else {
			for (String r : RelationshipConstant.CHILDRELATION) {
				RadioButton button = new RadioButton(r);
				button.setToggleGroup(group);
				button.setUserData(r);
				vbBox.getChildren().add(button);
			}
		}
		return group;
	}

	private ListView<String> loadRelatedPerson(PersonProfile person) {
		ListView<String> list = new ListView<>();
		for (String personName : Menu.driver.loadPersonStorage().keySet()) {
			if (!personName.equals(person.getName()))
				list.getItems().add(personName);
		}
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		pane.add(new Label("Related to"), 6, 0);
		pane.add(list, 6, 4);
		return list;
	}

	public Scene addConnectionScene(PersonProfile person) {
		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");
		pane.add(btAdd, 0, 6);
		pane.add(btBack, 4, 6);
		ToggleGroup group = loadRelationType(person);
		ListView<String> list = loadRelatedPerson(person);
		btAdd.setOnAction(e -> {
			if (group.getSelectedToggle() != null) {
				String relation = (String) group.getSelectedToggle().getUserData();
				PersonProfile relatedPerson = Menu.driver.findPersonByName(list.getSelectionModel().getSelectedItem());
				if (Helper.isExistedRelation(person, relatedPerson)) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("MESSAGES");
					alert.setContentText("The person is already related! Please try to connect anthoer person");
					alert.show();
				} else {
					try {
						person.addRelationship(relation, relatedPerson);
						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setTitle("MESSAGES");
						alert.setContentText("Successfully! You've added a relation");
						alert.show();
					} catch (NoAvailableException e1) {
						e1.noAvailableWarning();
					} catch (NotToBeCoupledException e1) {
						e1.notToBeCoupledException();
					} catch (TooYoungException e1) {
						e1.tooYoungException();
					} catch (NotToBeFriendsException e1) {
						e1.notToBeFriendsException();
					} catch (NotToBeColleaguesException e1) {
						e1.notToBeColleaguesException();
					} catch (NotToBeClassmatesException e1) {
						e1.notToBeClassmatesException();
					} catch (Exception e1) {
					}
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("MESSAGES");
				alert.setContentText("Must choose one from list");
				alert.show();
			}
		});

		// try {
		// person.addRelationship(relation, relatedPerson);
		//// showMessageForAddRelation();
		// }

		// catch (AlreadyHaveRelationException e2) {
		// e2.alreadyHaveRelationWarning();
		// }

		// addRelationAction(person, relation, selectPerson);

		btBack.setOnAction(e -> {
			Menu.window.setScene(new SelectPersonGUI().individualMainScene());
		});
		Scene scene = new Scene(pane, 800, 500);
		return scene;
	}

}
