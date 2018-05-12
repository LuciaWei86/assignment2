package miniNET.Models;

import java.util.ArrayList;
import java.util.HashMap;

import miniNET.Helper;
import miniNET.Constants.RelationshipConstant;
import miniNET.Models.Connections.FriendConnection;
import miniNET.Models.Connections.ParentConnection;

public class YoungChildProfile extends PersonProfile {
	private AdultProfile parentA;
	private AdultProfile parentB;

	public YoungChildProfile(String name, String image, String status, String gender, int age) {
		this.setConnections(new HashMap<String, ArrayList<PersonProfile>>());
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

	@Override
	public void addRelationship(String relationType, PersonProfile relatedPerson) throws Exception {
		if (relationType.equals("parent")) {

			if (parentA.getName() == null) {
				parentA = (AdultProfile) relatedPerson;
			} else {
				parentB = (AdultProfile) relatedPerson;
			}

			if (parentA.getName() != null && parentB.getName() != null)
				addParent(parentA, parentB);
		}

	}

	@Override
	public void removeRelationship(String relationType, PersonProfile relatedPerson) {
		switch (relationType) {
		case RelationshipConstant.PARENT:
			if(parentA.getName().equals(relatedPerson.getName()))
			{
				this.setConnectionManipulator(new ParentConnection(parentA,parentB,this));
				this.connectionManipulator.remove();
			}
			break;
		default:
			break;
		}

	}

}
