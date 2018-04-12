/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models;

import java.util.ArrayList;
import miniNET.Helper;
import miniNET.Constants.RelationshipConstant;

public class ChildProfile extends PersonProfile {
	private AdultProfile parentA;
	private AdultProfile parentB;

	public ChildProfile(int age, String name, String image, String status, String gender,
			ArrayList<Connection> connections, AdultProfile parentA, AdultProfile parentB) {
		super(age, name, image, status, gender, new ArrayList<Connection>() {
			{
				new Connection(RelationshipConstant.DEPENDENT, parentA);
			}
			{
				new Connection(RelationshipConstant.DEPENDENT, parentB);
			}
		});
		this.parentA = parentA;
		this.parentB = parentB;
		dependentConnection(parentA, parentB);
	}

	public ChildProfile(int age, String name, String image, String status, String gender, AdultProfile parentA,
			AdultProfile parentB) {

		super(age, name, image, status, gender, new ArrayList<Connection>() {
			{
				new Connection(RelationshipConstant.DEPENDENT, parentA);
			}
			{
				new Connection(RelationshipConstant.DEPENDENT, parentB);
			}
		});
		this.parentA = parentA;
		this.parentB = parentB;
		dependentConnection(parentA, parentB);
	}

	public ChildProfile(int age, String name, String gender, AdultProfile parentA,
			AdultProfile parentB) {
		super(age, name, gender, new ArrayList<Connection>() {
			{
				new Connection(RelationshipConstant.DEPENDENT, parentA);
			}
			{
				new Connection(RelationshipConstant.DEPENDENT, parentB);
			}
		});
		this.parentA = parentA;
		this.parentB = parentB;
		dependentConnection(parentA, parentB);
	}

	public ChildProfile() {
		// TODO Auto-generated constructor stub
	}

	private void dependentConnection(AdultProfile parentA, AdultProfile parentB) {
		Connection relationA = new Connection(RelationshipConstant.PARENT, this);
		Connection relationB = new Connection(RelationshipConstant.PARENT, this);
		Connection coupleConnectParentB = new Connection(RelationshipConstant.COUPLE, parentB);
		Connection coupleConnectParentA = new Connection(RelationshipConstant.COUPLE, parentA);
		parentA.addConnections(coupleConnectParentB);
		parentB.addConnections(coupleConnectParentA);
		parentA.addConnections(relationA);
		parentB.addConnections(relationB);
	}

	@Override
	public void displayPersonProfile(PersonProfile person) {
		ChildProfile child = (ChildProfile) person;
		String image = Helper.validateString(child.getImage()) ? child.getImage() : "N/A";
		String status = Helper.validateString(child.getStatus()) ? child.getStatus() : "N/A";
		System.out.println("Name: " + child.getName());
		System.out.println("Gender: " + child.getGender());
		System.out.println("Age: " + child.getAge());
		System.out.println("Profile Image:" + image);
		System.out.println("Status: " + status);
		System.out.println("Parent: " + child.parentA.getName() + " and " + child.parentB.getName());
		if (!Helper.findPersonFriendNames(child).isEmpty()) {
			System.out.println("Friends: " + Helper.findPersonFriendNames(child));
		}
	}

	@Override
	public void connectToPeople(PersonProfile person1, PersonProfile person2, String relationShip) {
		ChildProfile child1 = (ChildProfile) person1;
		ChildProfile child2 = (ChildProfile) person2;
		int ageDiff = Math.abs(child1.getAge() - child2.getAge());
		if (ageDiff <= 3 && !Helper.isFromSameFamily(child1, child2)) {
			if (relationShip.equalsIgnoreCase(RelationshipConstant.FRIENDSHIP)) {
				Connection connectionWithPerson1 = new Connection(relationShip, child2);
				Connection connectionWithPerson2 = new Connection(relationShip, child1);
				child1.addConnections(connectionWithPerson1);
				child2.addConnections(connectionWithPerson2);
			}
			System.out.println("Success");
		} else {
			System.out.println("Failed");
		}
	}

	public AdultProfile getParentA() {
		return parentA;
	}

	public void setParentA(AdultProfile parentA) {
		this.parentA = parentA;
	}

	public AdultProfile getParentB() {
		return parentB;
	}

	public void setParentB(AdultProfile parentB) {
		this.parentB = parentB;
	}
}
