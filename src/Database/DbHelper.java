package Database;

import java.net.BindException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.hsqldb.HsqlException;
import org.hsqldb.Server;

import miniNET.Models.AdultProfile;
import miniNET.Models.ChildProfile;
import miniNET.Models.PersonProfile;
import miniNET.Models.YoungChildProfile;

public class DbHelper {

	Server hsqlServer = null;
	Connection connection = null;
	ResultSet rs = null;
	private HashMap<String, PersonProfile> personStorage = new HashMap<String, PersonProfile>();;

	private void insertDb() throws SQLException {

		connection.prepareStatement("insert into people (name,image, status,gender,age)"
				+ "values ('Alex Smith', '','student at RMIT', 'M', 21);").execute();
		connection.prepareStatement("insert into people (name,image, status,gender,age)"
				+ "values ('Ben Turner', 'BenPhoto.jpg', 'manager at Coles','M', 35);").execute();
		connection.prepareStatement("insert into people (name,image, status,gender,age)"
				+ "values ('Hannah White', 'Hannah.png', 'student at PLC','F', 14);").execute();
		connection.prepareStatement("insert into people (name,image, status,gender,age)"
				+ "values ('Zoe Foster', '', 'Founder of ZFX','M', 28);").execute();
		connection.prepareStatement("insert into people (name,image, status,gender,age)"
				+ "values ('Mark Turner', 'Mark.jpeg', '','M', 2);").execute();

	}

	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public void shutdownDb() {
		if (hsqlServer != null) {
			hsqlServer.stop();
		}
	}

	public void makeDbConnection() throws SQLException, ClassNotFoundException, BindException, HsqlException {

		ResultSet rs = null;
		hsqlServer = new Server();
		hsqlServer.shutdown();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		// making a connection
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
		connection.prepareStatement("drop table people if exists;").execute();
		connection
				.prepareStatement(
						"create table people (name varchar(20)  not null, image varchar(20), status varchar(20), gender varchar(10), age integer not null);")
				.execute();
		insertDb();
		connection.commit();
	}

	public HashMap<String, PersonProfile> viewData() throws Exception {
		rs = connection.prepareStatement("select * from peoples;").executeQuery();

		while (rs.next()) {
			PersonProfile person = null;
			int age = rs.getInt(5);
			if (age < 3) {
				person = new YoungChildProfile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), age);
			} else if (age <= 16) {
				person = new ChildProfile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), age);

			} else {
				person = new AdultProfile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), age);
			}
			personStorage.put(rs.getString(1), person);

		}
		return personStorage;


	}
}
