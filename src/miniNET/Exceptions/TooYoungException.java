package miniNET.Exceptions;

import miniNET.Models.PersonProfile;

public class TooYoungException extends Exception{
	PersonProfile person;
	public TooYoungException(PersonProfile person) {
		this.person = person;
	}
	public void tooYoungException() {
		System.out.println("you are trying to make friend with a young child.");
	}
}
