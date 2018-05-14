/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models.Connections;

import java.util.ArrayList;

import miniNET.Constants.RelationshipConstant;
import miniNET.Models.PersonProfile;

public class ParentConnection implements ConnectionManipulator {
	
	private PersonProfile parent1;
	private PersonProfile parent2;
	private PersonProfile child;
	
	public ParentConnection(PersonProfile parent1, PersonProfile parent2, PersonProfile child) 
	{
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child = child;
		this.child.getConnections().put(RelationshipConstant.PARENT, new ArrayList<PersonProfile>());

	}

	@Override
	public void add() throws Exception {
		if(!parent1.getConnections().containsKey(RelationshipConstant.COUPLE)){
			parent1.setConnectionManipulator(new CoupleConnection(parent1, parent2));		
		}
		else 
			if(!parent1.getConnections().containsKey(RelationshipConstant.CHILD))
			{
				parent1.getConnections().put(RelationshipConstant.CHILD, new ArrayList<PersonProfile>());
			}
			if(!parent2.getConnections().containsKey(RelationshipConstant.CHILD))
			{
				parent2.getConnections().put(RelationshipConstant.CHILD, new ArrayList<PersonProfile>());
			}
			if(parent1.getConnections().get(RelationshipConstant.CHILD).size()>0)
			{
				for(PersonProfile sibling: parent1.getConnections().get(RelationshipConstant.CHILD)) {
					child.setConnectionManipulator(new SiblingConnection(child,sibling));
					child.getConnectionManipulator().add();
				}
			}
			parent1.getConnections().get(RelationshipConstant.CHILD).add(child);
			parent2.getConnections().get(RelationshipConstant.CHILD).add(child);
			child.getConnections().get(RelationshipConstant.PARENT).add(parent1);
			child.getConnections().get(RelationshipConstant.PARENT).add(parent2);

	}

	@Override
	public void remove() {
		parent1.getConnections().get(RelationshipConstant.CHILD).remove(child);
		parent2.getConnections().get(RelationshipConstant.CHILD).remove(child);
	}

}
