package miniNET.Models.Connections;

import java.util.ArrayList;

import miniNET.Constants.RelationshipConstant;
import miniNET.Exceptions.NoAvailableException;
import miniNET.Exceptions.NotToBeCoupledException;
import miniNET.Models.AdultProfile;
import miniNET.Models.PersonProfile;

public class CoupleConnection implements ConnectionManipulator {

	private PersonProfile person;
	private PersonProfile partner;

	public CoupleConnection(PersonProfile person, PersonProfile partner) {
		this.person = person;
		this.partner = partner;
		this.person.getConnections().put("couple", new ArrayList<PersonProfile>());
	}

	@Override
	public void add() throws NoAvailableException, NotToBeCoupledException {
		if (!(partner instanceof AdultProfile)) {
			throw new NotToBeCoupledException(person, partner);
		} else if (this.partner.getConnections().containsKey(RelationshipConstant.COUPLE)) {
			throw new NoAvailableException(partner, person);
		} else {
//			System.out.println(person.getConnections().keySet());
			person.getConnections().get(RelationshipConstant.COUPLE).add(partner);
			partner.getConnections().put(RelationshipConstant.COUPLE, new ArrayList<PersonProfile>());
			partner.getConnections().get(RelationshipConstant.COUPLE).add(person);
		}

	}

	@Override
	public void remove() {
		partner.getConnections().remove(RelationshipConstant.COUPLE);

		if (person.getConnections().containsKey(RelationshipConstant.CHILD)) {
			for (PersonProfile child : person.getConnections().get(RelationshipConstant.CHILD)) {
				for (String relationType : child.getConnections().keySet()) {
					for (PersonProfile childRelation : child.getConnections().get(relationType))
						child.removeRelationship(relationType, childRelation);
				}

			}
			partner.getConnections().remove(RelationshipConstant.CHILD);
		}

	}

}
