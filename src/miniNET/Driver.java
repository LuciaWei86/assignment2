/*
 * Author: s3681944 Qi Jin
 *  
 *  */

package miniNET;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import miniNET.Models.*;
import miniNET.Constants.RelationshipConstant;
import miniNET.Helper;

public class Driver {
	NetworkData networkData = new NetworkData();
	Scanner sc = new Scanner(System.in);

	// 0. list everyone
	public void listEveryone() {
		List<PersonProfile> network = networkData.allData();
		for (PersonProfile p : network) {
			System.out.println("Name: " + p.getName());
		}
	}

	// 1. add a person into the network
	public void addPersonToNetwork() {
		List<PersonProfile> network = networkData.allData();
		try {
			System.out.println("Please enter person's name: ");
			String name = sc.nextLine();
			System.out.println("Please enter person's gender: F/M");
			String gender = sc.nextLine();
			System.out.println("Add image on profile? Y/N");
			boolean imageFlag = Helper.isYes(sc.nextLine());
			String image = imageFlag ? sc.nextLine() : "";
			System.out.println("Add status on profile? Y/N");
			boolean statusFlag = Helper.isYes(sc.nextLine());
			String status = statusFlag ? sc.nextLine() : "";
			System.out.println("Please enter person's age: ");
			int age = sc.nextInt();
			if (age <= 16) {
				System.out.println("Please enter person's parent name: ");
				String parentAName = sc.nextLine();
				AdultProfile parentA = (AdultProfile) selectAPersonByName(parentAName);
				System.out.println("Please enter person's another parent name: ");
				String parentBName = sc.nextLine();
				AdultProfile parentB = (AdultProfile) selectAPersonByName(parentBName);
				PersonProfile personProfile = new ChildProfile(age, name, image, status, gender, parentA, parentB);
				network.add(personProfile);
			} else {
				PersonProfile personProfile = new AdultProfile(age, name, image, status, gender);
				network.add(personProfile);
			}
			System.out.println("Added " + name + " on network.");

		} catch (Exception e) {
			System.out.println("Failed Add a Person.");
		}

	}

	// 2. select a person by name
	public PersonProfile selectAPersonByName(String name) {
		List<PersonProfile> network = networkData.allData();
		for (PersonProfile p : network) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	// 3. display the profile of the selected person
	public void displayPersonProfile(String name) {
		PersonProfile personProfile = selectAPersonByName(name);
		if (personProfile == null) {
			System.out.println("This person does not exist on our network");
			return;
		}
		if (Helper.isChild(personProfile.getAge())) {
			personProfile.displayPersonProfile(personProfile);
		} else {
			personProfile.displayPersonProfile(personProfile);
		}
	}

	// 4. update the profile of selected person
	public void updateSelectedPerson(String name) {
		List<PersonProfile> network = networkData.allData();
		boolean flag = false;
		boolean valid = false;
		for (PersonProfile p : network) {
			if (p.getName().equalsIgnoreCase(name)) {
				flag = true;
				int selectedItem;
				String updated;
				do {
					System.out.println("Which info do you want to update? ");
					System.out.println("1. Age ");
					System.out.println("2. Status");
					System.out.println("3. Imgae");
					System.out.println("4. Exit");
					selectedItem = sc.nextInt();
					valid = true;
					switch (selectedItem) {
					case 1:
						System.out.println("Input updated age: ");
						int newAge = sc.nextInt();
						p.setAge(newAge);
						break;
					case 2:
						System.out.println("Input updated status: ");
						updated = sc.nextLine();
						p.setStatus(updated);
						break;
					case 3:
						System.out.println("Input updated iamge: ");
						updated = sc.nextLine();
						p.setImage(updated);
						break;
					case 4:
						return;
					default:
						valid = false;
						System.out.println("Error : Choice " + selectedItem + " Does not exist.");
						System.out.println("Please select one that exists.");
					}
				} while (selectedItem != 4 || !valid);
			}
		}
		if (!flag) {
			System.out.println("Sorry, this person does not exits");
		} else {
			System.out.println("Updated");
		}
	}

	// 5. delete the selected person
	public void deleteSelectedPerson(String name) {
		PersonProfile personProfile = selectAPersonByName(name);
		if (personProfile == null) {
			System.out.println("This person does not exist on our network");
			return;
		}
		boolean flag = false;
		List<PersonProfile> network = networkData.allData();
		for (PersonProfile p : network) {
			if (p.getName().equals(personProfile.getName())) {
				network.remove(p);
				flag = true;
				return;
			}
		}
		if (!flag) {
			System.out.println("Sorry, this person does not exits");
		} else {
			System.out.println("Deleted");
		}
	}

	// 6. connect two person in a meaningful way
	public void connectTwoPerson(String person1Name, String person2Name, String relationShip) {
		PersonProfile person1 = selectAPersonByName(person1Name);
		PersonProfile person2 = selectAPersonByName(person2Name);
		if (person1 == null || person2 == null) {
			System.out.println("This person does not exist on our network");
			return;
		}
		if (Helper.isChild(person1.getAge())) {
			ChildProfile child1 = (ChildProfile) person1;
			ChildProfile child2 = (ChildProfile) person2;
			child1.connectToPeople(child1, child2, relationShip);
		} else {
			AdultProfile adult1 = (AdultProfile) person1;
			AdultProfile adult2 = (AdultProfile) person2;
			adult1.connectToPeople(adult1, adult2, relationShip);
		}
	}

	// 7. Find out whether a personProfile is a direct friend of another
	public boolean checkDirectFriendship(String person1Name, String person2Name) {
		PersonProfile person1 = selectAPersonByName(person1Name);
		if (person1 == null) {
			System.out.println(person1Name + " does not exist on our network");
			return false;
		}
		if (person1.getConnections() == null || person1.getConnections().isEmpty()) {
			System.out.println(person1Name + " has no connection");
		}
		for (Connection connection : person1.getConnections()) {
			if (connection.connectionType == RelationshipConstant.FRIENDSHIP
					&& connection.personProfile.getName().equals(person2Name)) {
				return true;
			}
		}
		return false;
	}

	// 8. find out the name of a person's children
	public List<String> findoutPersonsChildrenNames(String personName) {
		PersonProfile personProfile = selectAPersonByName(personName);
		if (personProfile == null) {
			System.out.println("This person does not exist on our network");
			return null;
		}
		List<String> nameList = new ArrayList<String>();
		if (personProfile.getConnections() != null) {
			for (Connection connection : personProfile.getConnections()) {
				if (connection.connectionType == RelationshipConstant.DEPENDENT) {
					nameList.add(connection.personProfile.getName());
				}
			}
		}
		return nameList;
	}

	// 9. find out the names of a person's parents
	public List<String> findoutPersonsParentsName(String personName) {
		PersonProfile personProfile = selectAPersonByName(personName);
		if (personProfile == null) {
			System.out.println("This person does not exist on our network");
			return null;
		}
		List<String> nameList = new ArrayList<String>();
		if (personProfile.getConnections() != null) {
			for (Connection connection : personProfile.getConnections()) {
				if (connection.connectionType == RelationshipConstant.PARENT) {
					nameList.add(connection.personProfile.getName());
				}
			}
		}
		return nameList;
	}

}