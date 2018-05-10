package miniNET.Exceptions;

import miniNET.Models.PersonProfile;

public class NotToBeFriendsException extends Exception{
	PersonProfile person, friend;
	public NotToBeFriendsException(PersonProfile person, PersonProfile friend) {
		this.person = person;
		this.friend = friend;
	}
	public void notToBeFriendsException() {
		System.out.println("you are trying to make friend with a young child.");
	}
}
