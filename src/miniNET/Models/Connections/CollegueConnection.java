/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models.Connections;

import java.util.ArrayList;

import miniNET.Constants.RelationshipConstant;
import miniNET.Exceptions.NotToBeColleaguesException;
import miniNET.Models.AdultProfile;
import miniNET.Models.PersonProfile;

public class CollegueConnection implements ConnectionManipulator {

	private PersonProfile person;
	private PersonProfile colleague;

	public CollegueConnection(PersonProfile person, PersonProfile colleague) {
		this.person = person;
		this.colleague = colleague;
		if (!person.getConnections().containsKey(RelationshipConstant.COLLEAGUE)) {
			person.getConnections().put(RelationshipConstant.COLLEAGUE, new ArrayList<PersonProfile>());
		}
	}

	@Override
	public void add() throws NotToBeColleaguesException {
		if (colleague instanceof AdultProfile) {
			person.getConnections().get(RelationshipConstant.COLLEAGUE).add(colleague);
			if (!colleague.getConnections().containsKey(RelationshipConstant.COLLEAGUE)) {
				colleague.getConnections().put(RelationshipConstant.COLLEAGUE, new ArrayList<PersonProfile>());
			}
			colleague.getConnections().get(RelationshipConstant.COLLEAGUE).add(person);
		} else {
			throw new NotToBeColleaguesException();
		}
	}

	@Override
	public void remove() {
		person.getConnections().get(RelationshipConstant.COLLEAGUE).remove(colleague);
		colleague.getConnections().get(RelationshipConstant.COLLEAGUE).remove(person);
	}

}
