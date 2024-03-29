	//Author：Fandi Wei, Student Number: s3667669

package miniNET.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import miniNET.Driver;

public class Menu {

	static public Stage window;
	static public Driver driver;
	public Menu(Stage window, Driver driver) {
		Menu.window = window;
		Menu.driver = driver;
	}

	static public GridPane setUpPane() {
		GridPane primaryPane = new GridPane();
		primaryPane.setAlignment(Pos.CENTER);
		primaryPane.setPadding(new Insets(5, 5, 5, 5));
		primaryPane.setHgap(5.5);
		primaryPane.setVgap(5.5);
		return primaryPane;
	}

	static public Scene startScene() {

		GridPane pane = setUpPane();
		Label label1 = new Label("Mininet Menu");
		label1.setStyle("-fx-font-size: 13pt");
		pane.add(label1, 0, 0);
		Button addPersonBt = new Button("1. Add person");
		pane.add(addPersonBt, 0, 1);
		Button selectPersonBt = new Button("2. Select a Person");
		pane.add(selectPersonBt, 0, 2);
		Button exitBt = new Button("Exit");
		pane.add(exitBt, 0, 5);

		addPersonBt.setOnAction(event -> {
			window.setScene(new AddPersonGUI().addPersonScene());
		});
		
        selectPersonBt.setOnAction(event -> {
          window.setScene(new SelectPersonGUI().individualMainScene());
        });

		exitBt.setOnAction(event -> {
			window.close();
		});

		Scene scene = new Scene(pane, 400, 400);
		return scene;
	}

}
