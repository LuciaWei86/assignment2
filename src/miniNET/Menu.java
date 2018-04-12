package miniNET;

import java.util.List;
import java.util.Scanner;

public class Menu {
	
	//Author：Fandi Wei, Student Number: s3667669
	
	private Scanner sc = new Scanner(System.in);
	private int option;
	Driver runDriver = new Driver();

	public void displayMenu() {
		System.out.println("=================================================");
		System.out.println("MiniNet Menu");
		System.out.println("=================================================");
		System.out.println("1. List everyone");
		System.out.println("2. Add a person into network");
		System.out.println("3. Select a person and display the profile");
		System.out.println("4. Update profile of the selected person");
		System.out.println("5. Delete the selected person");
		System.out.println("6. Connect two person in a meaningful way");
		System.out.println("7. Check two person whether are direct friends?");
		System.out.println("8. Find out the name of a person's children");
		System.out.println("9. Find out the names of a person's parents");
		System.out.println("10. Exit");
		System.out.println();
		System.out.println("Enter an option: _");
	}

	public void runMenu() {
		boolean valid = true;
		do {
			displayMenu();
			try {
				valid = false;

				option = sc.nextInt();
				switch (option) {
				case 1:
					runDriver.listEveryone();
					break;
				case 2:
					System.out.println("Insert name:");
					runDriver.addPersonToNetwork();
					break;
				case 3:
					System.out.println("Insert a name:");
					String name = sc.next();
					runDriver.displayPersonProfile(name);
					break;
				case 4:
					System.out.println("Insert a name:");
					runDriver.updateSelectedPerson(sc.next());
					break;
				case 5:
					System.out.println("Insert the name you want to delete:");
					runDriver.deleteSelectedPerson(sc.next());
					break;
				case 6:
					System.out.println("Insert one name:");
					String name1 = sc.next();
					System.out.println("Insert another name:");
					String name2 = sc.next();
					System.out.println("Insert the relation between these two people:");
					String relation = sc.next();
					runDriver.connectTwoPerson(name1, name2, relation);
					System.out.println("Connection between " + name1 + " and " + name2 + " has been added.");
					break;
				case 7:
					System.out.println("Insert one name:");
					String name3 = sc.next();
					System.out.println("Insert another name:");
					String name4 = sc.nextLine();
					boolean flag = runDriver.checkDirectFriendship(name3, name4);
					if (flag) {
						System.out.println(name3 + " and " + name4 + " are direct friends.");
					} else {
						System.out.println(name3 + " and " + name4 + " are not direct friends.");
					}
					break;
				case 8:
					System.out.println("Insert name:");
					List<String> names = runDriver.findoutPersonsChildrenNames(sc.next());
					if (names != null) {
						for (String nameChild : names) {
							System.out.println("Children names: " + nameChild);
						}
					} else {
						System.out.println("Has no child in network records");
					}
					break;
				case 9:
					System.out.println("Insert name:");
					List<String> namesP = runDriver.findoutPersonsParentsName(sc.next());
					if (namesP != null) {
						for (String nameParent : namesP) {
							System.out.println("Children names: " + nameParent);
						}
					} else {
						System.out.println("Has no parent in network records");
					}
					break;
				case 10:
					System.out.println("Thanks for using MiniNet, see you next time!");
					valid = true;
					break;
				default:
					System.out.println("Illegal input. Please insert a number from menu list");
					break;
				}
			} catch (Exception e) {
				System.out.println("Illegal input. Please insert a number from menu list");
				valid = false;
				sc.next();
			}
		} while (option != 10 || !valid);
	}

}

