/*
 * Author: s3681944 Qi Jin
 *  
 *  */

package miniNET;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import miniNET.Models.*;
import miniNET.Constants.RelationshipConstant;
import miniNET.Helper;

public class Driver {
	private HashMap<String, PersonProfile> personStorage = new HashMap<String, PersonProfile>();;
	ArrayList<String[]> relationData = new ArrayList<String[]>();
	ArrayList<String> personName = new ArrayList<String>();

	public void initialData() throws IOException {
		String[] pTextData = null;
		String[] rTextData = null;
		PersonProfile person = null;
		// read relations.txt from data seed
		try {
			BufferedReader getRelationFromFile = new BufferedReader(
					new FileReader("src/miniNET/DataSeed/relations.txt"));
			String currentLine;
			while ((currentLine = getRelationFromFile.readLine()) != null) {
				rTextData = currentLine.split(",");
				relationData.add(rTextData);
			}

			getRelationFromFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader getPeopleFromFile = new BufferedReader(new FileReader("src/miniNET/DataSeed/people.txt"));
			String currentLine;
			while ((currentLine = getPeopleFromFile.readLine()) != null) {
				pTextData = currentLine.split(",");
				for (int i = 0; i < pTextData.length; i++) {
					pTextData[i] = pTextData[i].replace("\"", "");
				}
				int age = Integer.parseInt(pTextData[4].trim());
				if (age < 3) {
					person = new YoungChildProfile(pTextData[0].trim(), pTextData[1].trim(), pTextData[2].trim(),
							pTextData[3].trim(), age);
				} else if (age <= 16) {
					person = new ChildProfile(pTextData[0].trim(), pTextData[1].trim(), pTextData[2].trim(),
							pTextData[3].trim(), age);

				} else {
					person = new AdultProfile(pTextData[0].trim(), pTextData[1].trim(), pTextData[2].trim(),
							pTextData[3].trim(), age);
				}
				// store current person and name
				personName.add(pTextData[0].trim());
				personStorage.put(pTextData[0].trim(), person);
			}
			getPeopleFromFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			addRelationToPerson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 for (String name: personStorage.keySet()) {
			 personStorage.get(name).displayPersonProfile(personStorage.get(name));
		 }

	}
	private void addRelationToPerson(){
		for(String[] r: relationData){
			for(String name: personStorage.keySet()){
				if(name.equalsIgnoreCase(r[0].trim())){
					if (r[2].trim().equals("parent") && (personStorage.get(r[0].trim()) instanceof AdultProfile)) {
						String temp = r[0];
						r[0] = r[1].trim();
						r[1] = temp.trim();
						name = r[0];
					}
					try {
						personStorage.get(name).addRelationship(r[2].trim(), personStorage.get(r[1].trim()));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
	}
	
	
	public PersonProfile addPerson(String name, String image, String status, String gender, int personAge) {
		PersonProfile person;
		for (String pn : personName) {
			if (name.equalsIgnoreCase(pn)){
				return null;
			}
		}
		if (personAge < 3) {
			person = new YoungChildProfile(name, image, status, gender, personAge);
			return person;
		} else if (personAge <= 16) {
			person = new ChildProfile(name, image, status, gender, personAge);
			return person;
		} else {
			person = new AdultProfile(name, image, status, gender, personAge);
			return person;
		}
	}

	// private void addRelationData() {
	//
	// for (String[] st : relationData) {
	//
	// for (String name : connection.keySet()) {
	//
	// // for parent relation
	// if (name.equals(st[0].trim())) {
	//
	// if (st[2].trim().equals("parent") && (connection.get(st[0].trim())
	// instanceof Adult)) {
	// String temp = st[0];
	// st[0] = st[1].trim();
	// st[1] = temp.trim();
	// name = st[0];
	//
	// }
	// // System.out.println(st[0]+" "+st[1]+" "+st[2]);
	// // System.out.println(name+ " "+connection.get(name)
	// // instanceof
	// // Child);
	// try {
	// connection.get(name).addRelationship(st[2].trim(),
	// connection.get(st[1].trim()));
	// } catch (NotToBeFriendsException e) {
	// // TODO Auto-generated catch block
	// e.notToBeFriendsException();
	// } catch (TooYoungException e) {
	// e.tooYoungException();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	//
	// // warning message for the child doesn't have the parents relation
	// for (PersonProfile pr : connection.values()) {
	// if (!(pr instanceof Adult))
	// if (pr.getRelationship().get("parent").size() < 2) {
	// System.out.println(pr.getName() + " doesn't have parents\n");
	// throw new NoParentsException();
	// }
	// }
	// }

	//
	// public void deletePersonProfile(PersonProfile currentPersonProfile) {
	//
	// for (String relationType :
	// currentPersonProfile.getRelationship().keySet()) {
	// System.out.println(relationType);
	// for (PersonProfile relatedPersonProfile :
	// currentPersonProfile.getRelationship().get(relationType)) {
	// currentPersonProfile.removeRelationship(relationType,
	// relatedPersonProfile);
	// if (relationType == "child") {
	// connection.remove(relatedPersonProfile.getName());
	// }
	// }
	// }
	// connection.remove(currentPersonProfile.getName());
	// for (String sr : connection.keySet()) {
	// connection.get(sr).displayProfile();
	// }
	// }
	//
	// public HashMap<String, PersonProfile> getconnection() {
	// return connection;
	// }
	//
	// public PersonProfile getconnectionObj(String key) {
	//
	// return connection.get(key);
	// }
	// NetworkData networkData = new NetworkData();
	// Scanner sc = new Scanner(System.in);
	//


}