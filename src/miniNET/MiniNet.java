package miniNET;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import miniNET.GUI.Menu;
import miniNET.GUI.NoDbAlertGUI;

public class MiniNet extends Application {

	Stage window;

	@Override
	public void start(Stage primaryStage) {

		Driver driver = new Driver();
		window = primaryStage;
		window.setTitle("MiniNet");
		window.setScene(new Menu(window, driver).startScene());
		window.setResizable(false);
		window.show();
		try {
			driver.initialData();
		} catch (Exception e) {
			NoDbAlertGUI alert = new NoDbAlertGUI();
			alert.noDbAlert();
			e.printStackTrace();
			window.close();
		}
	}

	public static void main(String[] args) {
		// Driver dc = new Driver();
		// dc.initialData();
		Application.launch(args);

	}

}