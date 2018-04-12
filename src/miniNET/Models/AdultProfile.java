/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models;

import java.util.ArrayList;

import miniNET.Helper;

public class AdultProfile extends PersonProfile {
	public AdultProfile(int age, String name, String image, String status, String gender,
			ArrayList<Connection> connections) {
		super(age, name, image, status, gender, connections);
	}

	public AdultProfile() {
	}

	public AdultProfile(int age, String name, String status, String image, String gender) {
		super(age, name, image, status, gender);
	}

	public AdultProfile(int age, String name, String gender) {
		super(age, name, gender);
	}

	public AdultProfile(int age, String name, String gender, ArrayList<Connection> connections) {
		super(age, name, gender, connections);
	}

	@Override
	public void displayPersonProfile(PersonProfile adult) {
		AdultProfile person = (AdultProfile) adult;
		String image = Helper.validateString(person.getImage()) ? person.getImage() : "N/A";
		String status = Helper.validateString(person.getStatus()) ? person.getStatus() : "N/A";
		System.out.println("Name: " + person.getName());
		System.out.println("Gender: " + person.getGender());
		System.out.println("Age: " + person.getAge());
		System.out.println("Profile Image:" + image);
		System.out.println("Status: " + status);
		if (!Helper.findPersonFriendNames(person).isEmpty()) {
			System.out.println("Friends: " + Helper.findPersonFriendNames(person));
		}
		if (!Helper.findPersonChildrenNames(person).isEmpty()) {
			System.out.println("Children: " + Helper.findPersonChildrenNames(person));
		}
	}

	@Override
	public void connectToPeople(PersonProfile person1, PersonProfile person2, String relationShip) {
		Connection connectionWithPerson1 = new Connection(relationShip, person2);
		Connection connectionWithPerson2 = new Connection(relationShip, person1);
		if (person1.getConnections() == null) {
			ArrayList<Connection> connections = new ArrayList<Connection>();
			person1.setConnections(connections);
		}
		if (person2.getConnections() == null) {
			ArrayList<Connection> connections = new ArrayList<Connection>();
			person2.setConnections(connections);
		}
		person1.addConnections(connectionWithPerson1);
		person2.addConnections(connectionWithPerson2);
	}
}
