/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models;

import java.util.ArrayList;
import java.util.HashMap;

import miniNET.Constants.RelationshipConstant;
import miniNET.Models.Connections.ClassmateConnection;
import miniNET.Models.Connections.FriendConnection;
import miniNET.Models.Connections.ParentConnection;

public class ChildProfile extends PersonProfile {
	private AdultProfile parentA;
	private AdultProfile parentB;

	public ChildProfile() {
	}

	public ChildProfile(String name, String image, String status, String gender, int age) {
		this.setConnections(new HashMap<String, ArrayList<PersonProfile>>());
		this.getConnections().put(RelationshipConstant.PARENT,new ArrayList<PersonProfile>());
		this.setName(name);
		this.setImage(image);
		this.setStatus(status);
		this.setGender(gender);
		this.setAge(age);
		this.parentA = new AdultProfile();
		this.parentB = new AdultProfile();

	}

	private void addParent(AdultProfile parent1, AdultProfile parent2) throws Exception {
		this.setConnectionManipulator(new ParentConnection(parent1, parent2, this));
		this.getConnectionManipulator().add();
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

	@Override
	public void addRelationship(String relationType, PersonProfile relatedPerson) throws Exception {
		switch (relationType) {
		case RelationshipConstant.FRIENDSHIP:
			this.setConnectionManipulator(new FriendConnection(this, relatedPerson));
			this.connectionManipulator.add();
			break;
		case RelationshipConstant.PARENT:
			if (parentA == null) {
				parentA = (AdultProfile) relatedPerson;
				System.out.println(parentA.getName());
			} else {
				parentB = (AdultProfile) relatedPerson;
				System.out.println(parentB.getName());
			}
			if (parentA != null && parentB != null)
				addParent(parentA, parentB);
			break;
		case RelationshipConstant.CLASSMATE:
			this.setConnectionManipulator(new ClassmateConnection(this, relatedPerson));
			this.connectionManipulator.add();
			break;
		default:
			break;
		}

	}

	@Override
	public void removeRelationship(String relationType, PersonProfile relatedPerson) {
		switch (relationType) {
		case RelationshipConstant.FRIENDSHIP:
			this.setConnectionManipulator(new FriendConnection(this, relatedPerson));
			this.connectionManipulator.remove();
			break;
		case RelationshipConstant.PARENT:
			if(parentA.getName().equals(relatedPerson.getName()))
			{
				this.setConnectionManipulator(new ParentConnection(parentA,parentB,this));
				this.connectionManipulator.remove();
			}
			break;
		case RelationshipConstant.CLASSMATE:
			this.setConnectionManipulator(new ClassmateConnection(this, relatedPerson));
			this.connectionManipulator.remove();
			break;
		default:
			break;
		}

	}
}
