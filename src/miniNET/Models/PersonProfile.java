package miniNET.Models;

import java.util.ArrayList;
import java.util.HashMap;
import miniNET.Helper;
import miniNET.Models.Connections.ConnectionManipulator;

public abstract class PersonProfile {
	private String name;
	private String image;
	private String status;
	private String gender;
	private int age;
	private HashMap<String, ArrayList<PersonProfile>> connections;
	ConnectionManipulator connectionManipulator;

	public PersonProfile() {
	}

	public PersonProfile(String name, String image, String status, String gender, int age) {
		this.name = name;
		this.image = image;
		this.status = status;
		this.gender = gender;
		this.age = age;
	}

	// getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public HashMap<String, ArrayList<PersonProfile>> getConnections() {
		return connections;
	}

	public void setConnections(HashMap<String, ArrayList<PersonProfile>> connections) {
		this.connections = connections;
	}

	// add connection on person profile
	public void addConnections(String relationType, ArrayList<PersonProfile> connection) {
		if (this.connections == null) {
			this.connections = new HashMap<String, ArrayList<PersonProfile>>();
		}
		this.connections.put(relationType, connection);
	}

	// print out person profile
	public void displayPersonProfile(PersonProfile person) {
		System.out.println("Name: " + person.getName());
		if (!Helper.isEmptyString(image)) {
			System.out.println("Image:" + image);
		} else {
			System.out.println("Image: No Image");
		}
		System.out.println("Gender: " + person.getGender());
		System.out.println("Age: " + person.getAge());
		System.out.println("Status: " + status);
		if (!Helper.isEmptyString(status)) {
			System.out.println("Status:" + status);
		} else {
			System.out.println("N/A");
		}
		if (!connections.isEmpty()) {
			for (String key : connections.keySet()) {
				System.out.print(key + ": ");
				for (int i = 0; i < connections.get(key).size(); i++) {
					System.out.print(connections.get(key).get(i).getName() + " ");
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	public abstract void addRelationship(String relationType, PersonProfile relatedPerson) throws Exception;

	public abstract void removeRelationship(String relationType, PersonProfile relatedPerson);

	public void setConnectionManipulator(ConnectionManipulator connectionManipulator) {
		this.connectionManipulator = connectionManipulator;
	}

	public ConnectionManipulator getConnectionManipulator() {
		return connectionManipulator;
	}

	public ConnectionManipulator setConnectionManipulator() {
		return connectionManipulator;
	}
}
