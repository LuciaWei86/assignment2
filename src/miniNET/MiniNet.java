package miniNET;

import java.net.BindException;
import java.sql.SQLException;

import org.hsqldb.HsqlException;

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
		new Menu(window, driver);
		window.setScene(Menu.startScene());
		window.setResizable(false);
		window.show();
		try {
			driver.initialData();
		} catch (ClassNotFoundException e) {
			handleDbException();
			e.printStackTrace();
		} catch (HsqlException e1) {
			handleDbException();
			e1.printStackTrace();
		} catch (SQLException e2) {
			handleDbException();
			e2.printStackTrace();
		} catch (BindException e3) {
			handleDbException();
			e3.printStackTrace();
		}
	}

	private void handleDbException() {
		NoDbAlertGUI alert = new NoDbAlertGUI();
		alert.noDbAlert();
		window.close();
	}

	public static void main(String[] args) {
		// Driver dc = new Driver();
		// dc.initialData();
		Application.launch(args);

	}

}