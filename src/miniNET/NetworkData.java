/*
 * Author: s3681944 Qi Jin
 *  
 *  */
package miniNET;

import java.util.ArrayList;
import java.util.List;

import miniNET.Constants.RelationshipConstant;
import miniNET.Constants.StatusConstant;
import miniNET.Models.AdultProfile;
import miniNET.Models.ChildProfile;
import miniNET.Models.Connection;
import miniNET.Models.PersonProfile;

public class NetworkData {
	private List<PersonProfile> network = new ArrayList<PersonProfile>();

	public NetworkData() {
		this.loadData();
	}
	
	public List<PersonProfile> allData(){
		return this.network;
	}

	PersonProfile Jack = new AdultProfile(20, "Jack", "M");
	PersonProfile Marry = new AdultProfile(26, "Marry", "F", "marry.photo", StatusConstant.FREELANCE);
	PersonProfile Alice = new ChildProfile(14, "Alice", "F", "alice.photo", StatusConstant.STUDENT, (AdultProfile) Jack,
			(AdultProfile) Marry);
	PersonProfile Bob = new AdultProfile(20, "Bob", "M", "", StatusConstant.LOOKINGFORJOB);
	PersonProfile Cathy = new AdultProfile(26, "Cathy", "F");
	PersonProfile Don = new ChildProfile(12, "Don", "M", "", "", (AdultProfile) Bob, (AdultProfile) Cathy);
	PersonProfile Alam = new AdultProfile(71, "Alam", "F");
	PersonProfile Barton = new AdultProfile(66, "Barton", "M", "barton.photo", StatusConstant.LOOKINGFORJOB);
	PersonProfile Berry = new ChildProfile(2, "Berry", "F", (AdultProfile) Alam, (AdultProfile) Barton);
	PersonProfile Hana = new AdultProfile(23, "Hana", "F", "", StatusConstant.WORKING);
	PersonProfile Jill = new AdultProfile(26, "Jill", "M");
	PersonProfile Alex = new ChildProfile(16, "Alex", "M", "", "", (AdultProfile) Hana, (AdultProfile) Jill);

	
	public void loadData() {
		loadJack();
		loadMarry();
		loadAlice();
		loadBob();
		loadCathy();
		loadDon();
		loadAlam();
		loadBarton();
		loadBerry();
	}

	public void add(PersonProfile person){
		network.add(person);
	}
	
	private void loadJack() {
		Jack.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Cathy));
		Jack.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Bob));
		network.add(Jack);
	}

	private void loadMarry() {
		Marry.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Cathy));
		Marry.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Bob));
		Marry.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Hana));
		Marry.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Alam));
		network.add(Marry);
	}
	
	private void loadCathy() {
		Cathy.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Marry));
		Cathy.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Jack));
		network.add(Cathy);
	}

	private void loadDon() {
		Don.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Alice));
		network.add(Don);
	}
	
	private void loadBob() {
		network.add(Bob);
	}
	
	private void loadAlam() {
		network.add(Alam);
	}
	private void loadBarton() {
		network.add(Barton);
	}
	private void loadBerry() {
		network.add(Berry);
	}
	private void loadAlice() {
		Alice.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Alex));
		Alice.addConnections(new Connection(RelationshipConstant.FRIENDSHIP, Don));
		network.add(Alice);
	}

	public List<PersonProfile> getNetwork() {
		return network;
	}

	public void setNetwork(List<PersonProfile> network) {
		this.network = network;
	}

}
