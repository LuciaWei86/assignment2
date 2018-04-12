/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET;

import miniNET.Constants.RelationshipConstant;
import miniNET.Models.ChildProfile;
import miniNET.Models.Connection;
import miniNET.Models.PersonProfile;

public class Helper {

	public static boolean validateString(String status) {
		if (status != null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isChild(int age) {
		if (age <= 16) {
			return true;
		} else {
			return false;
		}
	}

	public static String findPersonFriendNames(PersonProfile personProfile) {
		if (personProfile.getConnections() == null || personProfile.getConnections().isEmpty()) {
			return "";
		}
		String names = "";
		for (Connection connection : personProfile.getConnections()) {
			if (connection.connectionType.equalsIgnoreCase(RelationshipConstant.FRIENDSHIP)) {
				names = names + " " + connection.personProfile.getName();
			}
		}
		return names;
	}

	public static String findPersonChildrenNames(PersonProfile personProfile) {
		if (personProfile.getConnections() == null || personProfile.getConnections().isEmpty()) {
			return "";
		}
		String names = "";
		for (Connection connection : personProfile.getConnections()) {
			if (connection.connectionType.equalsIgnoreCase(RelationshipConstant.PARENT)) {
				names = names + " " + connection.personProfile.getName();
			}
		}
		return names;
	}

	public static boolean isFromSameFamily(ChildProfile child1, ChildProfile child2) {
		boolean fromSameFamily = child1.getParentA().getName().equalsIgnoreCase(child2.getParentA().getName())
				|| child1.getParentA().getName().equalsIgnoreCase(child2.getParentB().getName());
		return fromSameFamily;
	}

	public static boolean isAgeValid(ChildProfile child1, ChildProfile child2) {
		boolean isAgeValid = Math.abs(child1.getAge() - child2.getAge()) <= 3 && child1.getAge() > 2
				&& child2.getAge() > 2;
		return isAgeValid;
	}

	public static boolean isYes(String string) {
		if (string.equalsIgnoreCase("y") || string.equalsIgnoreCase("yes")) {
			return true;
		} else {
			return false;
		}
	}
}
