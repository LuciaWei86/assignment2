package miniNET.Models;

import java.util.ArrayList;
import java.util.List;

import miniNET.Helper;

public abstract class PersonProfile {
	
	//Author：Fandi Wei, Student Number: s3667669
	
	private String name;
	private String image;
	private String status;
	private int age;
	private String gender;
	private ArrayList<Connection> connections;

	public PersonProfile() {
		// TODO Auto-generated constructor stub
	}
	public PersonProfile(int age, String name, String image, String status, String gender,
			ArrayList<Connection> connections) {
		this.name = name;
		this.image = image;
		this.status = status;
		this.age = age;
		this.gender = gender;
		this.connections = connections;
	}

	public PersonProfile(int age, String name, String gender, ArrayList<Connection> connections) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.connections = connections;
	}

	public PersonProfile(int age, String name, String image, String status, String gender) {
		this.name = name;
		this.image = image;
		this.status = status;
		this.age = age;
		this.gender = gender;
	}

	public PersonProfile(int age, String name, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	// add connection on person profile
	public void addConnections(Connection connection) {
		if (this.connections == null) {
			this.connections = new ArrayList<Connection>();
		}
		this.connections.add(connection);
	}

	// print out person profile
	public abstract void displayPersonProfile(PersonProfile person);

	// connect with others
	abstract void connectToPeople(PersonProfile person1, PersonProfile person2, String relationShip);

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

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}

}
