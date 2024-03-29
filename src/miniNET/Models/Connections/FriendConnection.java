/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET.Models.Connections;

import java.util.ArrayList;

import miniNET.Exceptions.NotToBeFriendsException;
import miniNET.Exceptions.TooYoungException;
import miniNET.Models.AdultProfile;
import miniNET.Models.ChildProfile;
import miniNET.Models.PersonProfile;
import miniNET.Models.YoungChildProfile;
import miniNET.Helper;
import miniNET.Constants.RelationshipConstant;

public class FriendConnection implements ConnectionManipulator {
	private PersonProfile person;
	private PersonProfile friend;

	public FriendConnection(PersonProfile person, PersonProfile friend) {
		this.person = person;
		this.friend = friend;
		if (!person.getConnections().containsKey(RelationshipConstant.FRIENDSHIP)) {
			person.getConnections().put(RelationshipConstant.FRIENDSHIP, new ArrayList<PersonProfile>());
		}
	}

	@Override
	public void add() throws NotToBeFriendsException, TooYoungException {
		if (person instanceof AdultProfile && friend instanceof ChildProfile) {
			throw new NotToBeFriendsException(person, friend);
		} else if (friend instanceof AdultProfile && person instanceof ChildProfile) {
			throw new NotToBeFriendsException(person, friend);
		} else if (friend instanceof YoungChildProfile || person instanceof YoungChildProfile) {
			throw new TooYoungException(person);
		} else if (person instanceof ChildProfile && friend instanceof ChildProfile
				&& !Helper.isAgeValid(person, friend)) {
			throw new NotToBeFriendsException(person, friend);
		} else {
			person.getConnections().get(RelationshipConstant.FRIENDSHIP).add(friend);
			if (!friend.getConnections().containsKey(RelationshipConstant.FRIENDSHIP)) {
				friend.getConnections().put(RelationshipConstant.FRIENDSHIP, new ArrayList<PersonProfile>());
			}
			friend.getConnections().get(RelationshipConstant.FRIENDSHIP).add(person);
			// System.out.println(person.getConnections().keySet());
		}
	}

	@Override
	public void remove() {
		friend.getConnections().get(RelationshipConstant.FRIENDSHIP).remove(person);
		if (friend.getConnections().get(RelationshipConstant.FRIENDSHIP).isEmpty()) {
			friend.getConnections().remove(RelationshipConstant.FRIENDSHIP);
		}
	}

}
