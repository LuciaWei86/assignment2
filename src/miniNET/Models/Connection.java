
package miniNET.Models;

public class Connection {
	
	//Author：Fandi Wei, Student Number: s3667669
	
	public String connectionType;
	public PersonProfile personProfile;

	public Connection(String connectionType, PersonProfile personProfile) {
		this.connectionType = connectionType;
		this.personProfile = personProfile;
	}

	//getter and setter
	public PersonProfile getPerson() {
		return personProfile;
	}

	public void setPerson(PersonProfile personProfile) {
		this.personProfile = personProfile;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
}
