package miniNET.Models.Connections;

import java.util.ArrayList;

import miniNET.Constants.RelationshipConstant;
import miniNET.Models.PersonProfile;

public class SiblingConnection implements ConnectionManipulator{
	
	private PersonProfile person;
	private PersonProfile sibling;
	
	public SiblingConnection(PersonProfile person, PersonProfile sibling)
	{
		this.person = person;
		this.sibling = sibling;
		if(!this.person.getConnections().containsKey(RelationshipConstant.SIBLING))
		{
			this.person.getConnections().put(RelationshipConstant.SIBLING, new ArrayList<PersonProfile>());
		}
	}


	@Override
	public void add() throws Exception {
		person.getConnections().get(RelationshipConstant.SIBLING).add(sibling);
		if(!sibling.getConnections().containsKey(RelationshipConstant.SIBLING)){
			sibling.getConnections().put(RelationshipConstant.SIBLING, new ArrayList<PersonProfile>());
		}
		sibling.getConnections().get(RelationshipConstant.SIBLING).add(person);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
