/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET;

import miniNET.Models.ChildProfile;
import miniNET.Models.PersonProfile;

public class Helper {

	public static boolean isExistedRelation(PersonProfile person, PersonProfile relatedPerson) {
		for (String relation : person.getConnections().keySet()) {
			for (PersonProfile alreadyConnectedPerson : person.getConnections().get(relation)) {
				if (alreadyConnectedPerson.equals(relatedPerson))
					return true;
			}
		}
		return false;
	}

	public static boolean isEmptyString(String str) {
		boolean isEmpty = str == null || str.trim().length() == 0;
		return isEmpty;
	}

	public static boolean isChild(int age) {
		if (age <= 16) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isFromSameFamily(ChildProfile child1, ChildProfile child2) {
		boolean fromSameFamily = child1.getParentA().getName().equalsIgnoreCase(child2.getParentA().getName())
				|| child1.getParentA().getName().equalsIgnoreCase(child2.getParentB().getName());
		return fromSameFamily;
	}

	 public static boolean isAgeValid(PersonProfile person, PersonProfile
	 friend) {
	 boolean isAgeValid = Math.abs(person.getAge() - friend.getAge()) <= 3 &&
	 person.getAge() > 2
	 && friend.getAge() > 2;
	 return isAgeValid;
	 }

}
