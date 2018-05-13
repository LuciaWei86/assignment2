package miniNET.Models.Connections;

import java.util.ArrayList;

import miniNET.Constants.RelationshipConstant;
import miniNET.Exceptions.NotToBeClassmatesException;
import miniNET.Models.PersonProfile;
import miniNET.Models.YoungChildProfile;

public class ClassmateConnection implements ConnectionManipulator {
	private PersonProfile person;
	private PersonProfile classmate;

	public ClassmateConnection(PersonProfile person, PersonProfile classmate) {
		this.person = person;
		this.classmate = classmate;
		if (!person.getConnections().containsKey(RelationshipConstant.CLASSMATE)) {
			person.getConnections().put(RelationshipConstant.CLASSMATE, new ArrayList<PersonProfile>());
		}
	}

	@Override
	public void add() throws NotToBeClassmatesException {
		if (classmate instanceof YoungChildProfile) {
			throw new NotToBeClassmatesException();

		} else {
			person.getConnections().get(RelationshipConstant.CLASSMATE).add(classmate);
			if (!classmate.getConnections().containsKey(RelationshipConstant.CLASSMATE)) {
				classmate.getConnections().put(RelationshipConstant.CLASSMATE, new ArrayList<PersonProfile>());
			}
			classmate.getConnections().get(RelationshipConstant.CLASSMATE).add(person);
		}
	}

	@Override
	public void remove() {
		person.getConnections().get(RelationshipConstant.CLASSMATE).remove(classmate);
		classmate.getConnections().get(RelationshipConstant.CLASSMATE).remove(person);
	}
}
