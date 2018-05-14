/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models;

import java.util.ArrayList;
import java.util.HashMap;

import miniNET.Constants.RelationshipConstant;
import miniNET.Models.Connections.ClassmateConnection;
import miniNET.Models.Connections.CollegueConnection;
import miniNET.Models.Connections.CoupleConnection;
import miniNET.Models.Connections.FriendConnection;

public class AdultProfile extends PersonProfile {

	public AdultProfile() {
	}

	public AdultProfile(String name, String image, String status, String gender, int age) {
		this.setConnections(new HashMap<String, ArrayList<PersonProfile>>());
		this.setName(name);
		this.setImage(image);
		this.setStatus(status);
		this.setGender(gender);
		this.setAge(age);
	}

	@Override
	public void addRelationship(String relationType, PersonProfile relatedPerson) throws Exception {
		switch (relationType) {
		case RelationshipConstant.FRIENDSHIP:
			this.setConnectionManipulator(new FriendConnection(this, relatedPerson));
			this.connectionManipulator.add();
			break;
		case RelationshipConstant.COUPLE:
			this.setConnectionManipulator(new CoupleConnection(this, relatedPerson));
			this.connectionManipulator.add();
			break;
		case RelationshipConstant.COLLEAGUE:
			this.setConnectionManipulator(new CollegueConnection(this, relatedPerson));
			this.connectionManipulator.add();
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
		case RelationshipConstant.COUPLE:
			this.setConnectionManipulator(new CoupleConnection(this, relatedPerson));
			this.connectionManipulator.remove();
			break;
		case RelationshipConstant.COLLEAGUE:
			this.setConnectionManipulator(new CollegueConnection(this, relatedPerson));
			this.connectionManipulator.remove();
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
