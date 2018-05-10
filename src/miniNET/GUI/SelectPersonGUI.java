package miniNET.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import miniNET.Models.PersonProfile;

public class SelectPersonGUI {
	public Scene selectPersonScene() {
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

	private Scene viewPersonScene(PersonProfile selectedPerson) {
		GridPane pane = Menu.setUpPane();
		Label label = new Label("Please choose one: ");
		Button btDisplay = new Button("Display profile");
		Button btRelation = new Button("Display relations");
		Button btFindOutPC = new Button("Find out the parent or child of the person");
		Button btDelete = new Button("Delete this person");
		Button btBack = new Button("Back");

		pane.add(label, 0, 0);
		pane.add(btDisplay, 0, 1);
		pane.add(btRelation, 0, 2);
		pane.add(btFindOutPC, 0, 3);
		pane.add(btDelete, 0, 4);
		pane.add(btBack, 0, 5);

		btDisplay.setOnAction(e -> {
			displayProfileAction(selectedPerson);

		});

		btRelation.setOnAction(e -> {

		});

		btFindOutPC.setOnAction(e -> {

		});

		btDelete.setOnAction(e -> {

		});

		btBack.setOnAction(e -> {
			Menu.window.setScene(selectPersonScene());
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;
	}

	private void displayProfileAction(PersonProfile selectedPerson) {
		String name =selectedPerson.getName();
	    int age = selectedPerson.getAge();
	    String status = selectedPerson.getStatus();
	    String gender = selectedPerson.getGender();
	    String photo = selectedPerson.getImage();

	    Button btBack = new Button("Back");

	    GridPane pane = new GridPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(5, 5, 5, 5));
	    pane.setHgap(5.5);
	    pane.setVgap(5.5);

	    pane.add((new Label("Name: ")), 1, 3);
	    pane.add(new Label("Age: "), 1, 4);
	    pane.add(new Label("Status: "), 1, 5);
	    pane.add(new Label("Gender: "), 1, 6);
	    pane.add(new Label("State: "), 1, 7);
	    pane.add(btBack, 7, 10);
	    // btBack action, put it in the right position after
	    btBack.setOnAction(e -> {
	    	Menu.window.setScene(viewPersonScene(selectedPerson));
	    });

	    // pane.add(new Label(photo), 2, 1);

//	    ImageView imageView = new ImageView();
//
//	    if( photo.equals("")) {
//
//	        Image defaultImage = new Image(new FileInputStream("image/default.png"));
//	        imageView.setImage(defaultImage);
//	    }else {
//
//	    		Image image = new Image(new FileInputStream("image/"+ photo));
//	        imageView.setImage(image);
//
//	    }
//	    imageView.setFitHeight(100);
//	    imageView.setFitWidth(100);
//	    pane.add(imageView, 3, 0);

	    pane.add((new Label(name)), 4, 3);
	    pane.add(new Label(Integer.toString(age)), 4, 4);
	    pane.add(new Label(status), 4, 5);
	    pane.add(new Label(gender), 4, 6);

	    Scene scene = new Scene(pane, 700, 500);
	    Menu.window.setScene(scene);
	    Menu.window.show();
		
	}

}
