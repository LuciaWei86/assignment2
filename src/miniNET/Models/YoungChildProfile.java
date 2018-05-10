package miniNET.Models;

import java.util.ArrayList;
import java.util.HashMap;

import miniNET.Helper;

public class YoungChildProfile extends ChildProfile {

	public YoungChildProfile(String name,String image, String status,  String gender, int age) {
		this.setConnections(new HashMap<String,ArrayList<PersonProfile>>());
		this.setName(name);
		this.setImage(image);
		this.setStatus(status);
		this.setGender(gender);
		this.setAge(age);
	}

}
