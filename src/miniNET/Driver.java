/*
 * Author: s3681944 Qi Jin
 *  
 *  */

package miniNET;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.BindException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.hsqldb.HsqlException;

import Database.DbHelper;
import miniNET.Constants.RelationshipConstant;
import miniNET.Exceptions.NoParentException;
import miniNET.GUI.NoFileAlertGUI;
import miniNET.Models.*;

public class Driver {
	private HashMap<String, PersonProfile> personStorage = new HashMap<String, PersonProfile>();;
	ArrayList<String[]> relationData = new ArrayList<String[]>();
	ArrayList<String> personName = new ArrayList<String>();
	private DbHelper dbHelper = new DbHelper();

	public void initialData() throws ClassNotFoundException, HsqlException, SQLException, BindException {
		String[] pTextData = null;
		String[] rTextData = null;
		PersonProfile person = null;
		// read relations.txt from data seed

		BufferedReader getRelationFromFile;
		try {
			getRelationFromFile = new BufferedReader(new FileReader("src/miniNET/DataSeed/relations.txt"));

			String currentLine;
			while ((currentLine = getRelationFromFile.readLine()) != null) {
				rTextData = currentLine.split(",");
				relationData.add(rTextData);
			}

			getRelationFromFile.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			BufferedReader getPeopleFromFile = new BufferedReader(new FileReader("src/miniNET/DataSeed/people.txt"));
			String currentLine2;
			while ((currentLine2 = getPeopleFromFile.readLine()) != null) {
				pTextData = currentLine2.split(",");
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
		} catch (Exception e) {
			NoFileAlertGUI alert = new NoFileAlertGUI();
			alert.noFileAlert();
			dbHelper.makeDbConnection();
			personStorage = dbHelper.viewData();
			dbHelper.closeConnection();
			dbHelper.shutdownDb();
		}

		try {
			addRelationToPerson();
		} catch (NoParentException e) {
			e.noParentException();
		}
	}

	private void addRelationToPerson() throws NoParentException {
		ArrayList<PersonProfile> childWithNoParent = new ArrayList<PersonProfile>();
		for (String[] r : relationData) {
			for (String name : personStorage.keySet()) {
				if (name.equalsIgnoreCase(r[0].trim())) {
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

		for (PersonProfile p : personStorage.values()) {
			if (!(p instanceof AdultProfile))
				if (p.getConnections().containsKey(RelationshipConstant.PARENT)
						&& p.getConnections().get(RelationshipConstant.PARENT).size() < 2) {
					childWithNoParent.add(p);
				}
		}
		if (childWithNoParent.size() > 0) {
			for (PersonProfile child : childWithNoParent) {
				deletePerson(child);
			}
			throw new NoParentException(childWithNoParent);
		}
	}

	public void deletePerson(PersonProfile person) {
		ArrayList<PersonProfile> childrenList = new ArrayList<PersonProfile>();
		for (String type : person.getConnections().keySet()) {
			for (PersonProfile relatedPerson : person.getConnections().get(type)) {
				person.removeRelationship(type, relatedPerson);
				if (type == RelationshipConstant.CHILD) {
					childrenList.add(relatedPerson);
				}
			}
		}
		personStorage.remove(person.getName());
		if (childrenList.size() > 0) {
			for (PersonProfile child : childrenList) {
				for (String type : child.getConnections().keySet()) {
					for (PersonProfile relatedPerson : child.getConnections().get(type)) {
						child.removeRelationship(type, relatedPerson);
					}
				}
				personStorage.remove(child.getName());
			}
		}
	}

	public PersonProfile addPerson(String name, String image, String status, String gender, int personAge) {
		PersonProfile person;
		for (String pn : personStorage.keySet()) {
			if (name.equalsIgnoreCase(pn)) {
				return null;
			}
		}
		if (personAge < 3) {
			person = new YoungChildProfile(name, image, status, gender, personAge);
			personStorage.put(name, person);
			return person;
		} else if (personAge <= 16) {
			person = new ChildProfile(name, image, status, gender, personAge);
			personStorage.put(name, person);
			return person;
		} else {
			person = new AdultProfile(name, image, status, gender, personAge);
			personStorage.put(name, person);
			return person;
		}
	}

	public HashMap<String, PersonProfile> loadPersonStorage() {
		return personStorage;
	}

	public PersonProfile findPersonByName(String name) {
		return personStorage.get(name);
	}
}