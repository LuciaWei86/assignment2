package miniNET;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import miniNET.GUI.Menu;

public class MiniNet extends Application {

	Stage window;

	@Override
	public void start(Stage primaryStage) throws IOException {

		Driver driver = new Driver();
		window = primaryStage;
		window.setTitle("MiniNet");
		window.setScene(new Menu(window, driver).startScene());
		window.setResizable(false);
		window.show();
		try {
			driver.initialData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// Driver dc = new Driver();
		// dc.initialData();
		Application.launch(args);

	}

}