//Author：Fandi Wei, Student Number: s3667669

package miniNET.GUI;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
		pane.add(new Label("Relation Type"), 0, 3);
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

	private ToggleGroup loadRelatedPerson(PersonProfile person) {
		ToggleGroup group = new ToggleGroup();
		VBox vbBox = new VBox(10);
		vbBox.setPadding(new Insets(20, 20, 20, 20));
		pane.add(new Label("Related to"), 6, 3);
		pane.add(vbBox, 6, 4);
		for (String personName : Menu.driver.loadPersonStorage().keySet()) {
			if (!personName.equals(person.getName())) {
				RadioButton button = new RadioButton(personName);
				button.setToggleGroup(group);
				button.setUserData(personName);
				vbBox.getChildren().add(button);
			}
		}
		return group;
	}

	public Scene addConnectionScene(PersonProfile person) {
		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");
		pane.add(btAdd, 0, 6);
		pane.add(btBack, 4, 6);
		pane.add(new Label("current person: " + person.getName()), 3, 0);
		ToggleGroup group = loadRelationType(person);
		ToggleGroup list = loadRelatedPerson(person);
		btAdd.setOnAction(e -> {
			if (group.getSelectedToggle() != null && list.getSelectedToggle() != null) {
				String relation = (String) group.getSelectedToggle().getUserData();
				PersonProfile relatedPerson = Menu.driver
						.findPersonByName((String) list.getSelectedToggle().getUserData());
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

		btBack.setOnAction(e -> {
			Menu.window.setScene(new SelectPersonGUI().viewPersonScene(person));
		});
		Scene scene = new Scene(pane, 800, 500);
		return scene;
	}

	public Scene addParent1ConnectionScene(PersonProfile currentPerson) {
		GridPane pane = Menu.setUpPane();
		Text text1 = new Text("The person is under 18 years old, must add parents");
		text1.setFill(Color.RED);
		pane.add(text1, 0, 0);
		Button btNext = new Button("Next");
		Button btBack = new Button("Back");
		ComboBox<String> comboBox1 = new ComboBox<String>();
		comboBox1.setValue("First Parent");

		for (String name : Menu.driver.loadAdultStorage().keySet()) {
			comboBox1.getItems().add(name);
		}
		pane.add(btBack, 0, 4);
		pane.add(btNext, 4, 4);
		pane.add(comboBox1, 0, 1);
		GridPane.setHalignment(comboBox1, HPos.CENTER);

		btNext.setOnAction(e -> {

			String parent1Name = comboBox1.getValue();
			PersonProfile parent1 = Menu.driver.findPersonByName(parent1Name);
			Menu.window.setScene(addParent2ConnectionScene(currentPerson, parent1));

		});

		btBack.setOnAction(e -> {
			Menu.window.setScene(Menu.startScene());

		});
		Scene scene = new Scene(pane, 700, 500);
		return scene;

	}

	private Scene addParent2ConnectionScene(PersonProfile currentPerson, PersonProfile parent1) {
		GridPane pane = Menu.setUpPane();
		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");
		ComboBox<String> comboBox2 = new ComboBox<String>();
		if (parent1.getConnections().containsKey(RelationshipConstant.COUPLE)) {
			Text text1 = new Text("The person has a partner in record");
			text1.setFill(Color.RED);
			pane.add(text1, 0, 0);
			comboBox2.setValue(parent1.getConnections().get(RelationshipConstant.COUPLE).get(0).getName());
		} else {
			Text text1 = new Text("Please select the second parent");
			text1.setFill(Color.RED);
			pane.add(text1, 0, 0);
			comboBox2.setValue("Second Parent");
			for (PersonProfile p : Menu.driver.loadAdultStorage().values()) {
				if (!p.getConnections().containsKey(RelationshipConstant.COUPLE)
						&& !(p.getName().equals(parent1.getName())))
					comboBox2.getItems().add(p.getName());
			}
			if (comboBox2.getItems().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("MESSAGES");
				alert.setContentText(
						"You must add a partner for " + parent1.getName() + " before choose him/her as parent" + "\n"
								+ currentPerson.getName() + " will not be added to network!");
				alert.showAndWait();
				Menu.driver.deletePerson(currentPerson);
			}
		}
		pane.add(btBack, 0, 4);
		pane.add(btAdd, 4, 4);
		pane.add(comboBox2, 0, 1);
		GridPane.setHalignment(comboBox2, HPos.CENTER);
		btAdd.setOnAction(e -> {
			String boxValue2 = comboBox2.getValue();
			if (!boxValue2.equals("Second Parent")) {
				try {
					addParentsAction(parent1, boxValue2, currentPerson);
				} catch (NoAvailableException exception) {
					exception.noAvailableWarning();
				} catch (NotToBeCoupledException exception) {
					exception.notToBeCoupledException();
				} catch (Exception exception) {

				}
			}

		});
		btBack.setOnAction(e -> {
			Menu.window.setScene(Menu.startScene());
		});
		Scene scene = new Scene(pane, 600, 500);
		return scene;
	}

	private void addParentsAction(PersonProfile parent1, String parent2Name, PersonProfile currentPerson)
			throws Exception {
		PersonProfile parent2 = Menu.driver.findPersonByName(parent2Name);
		Alert alert = new Alert(Alert.AlertType.WARNING);		
		if (parent2.getConnections().containsKey(RelationshipConstant.COUPLE)
				&& !(parent1.getConnections().get(RelationshipConstant.COUPLE).get(0).getName().equals(parent2.getName()))) {
			throw new NoAvailableException(parent1, parent2);
		} else {
			currentPerson.addRelationship(RelationshipConstant.PARENT, parent1);
			currentPerson.addRelationship(RelationshipConstant.PARENT, parent2);
			alert.setTitle("MESSAGES");
			alert.setContentText("Add parents successfully!");
			alert.showAndWait();
			Menu.window.setScene(Menu.startScene());
		}
	}

}
