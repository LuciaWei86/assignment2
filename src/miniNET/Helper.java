/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET;
import miniNET.Models.ChildProfile;

public class Helper {

	public static boolean validateString(String status) {
		if (status != null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmptyString(String str){
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
