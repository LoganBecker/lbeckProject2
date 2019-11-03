package petdatabase;

import java.io.IOException;
import java.util.Scanner;

public class PetDatabaseSaveMain {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        PetDatabaseMaker pets = new PetDatabaseMaker();     //creates new database
        
        int choice;                                         //initializes int choice

        System.out.println();
        do {                                                //do while choice is not 4
            showMenu();
            choice = Integer.parseInt(s.nextLine().trim());
            switch (choice) {
                case 1: {   
                    System.out.println();
                    pets.viewPets();            //displays pets in pets
                    break;
                }
                case 2: {
                    System.out.println();
                    pets.addPets();             //adds pets
                    break;
                }
                case 3: {
                    System.out.println();
                    pets.removePets();      //removes pets
                    break;
                }
                case 4: {
                    try{
                    pets.writeToFile();         //writes pets to file
                    
                    } catch (IOException e){               //if it cannot write to file prints error message
                        
                        System.out.println("Could not write to file ");
                    }
                    System.out.println("\nGoodbye!\n");
                    System.exit(0);                         //exits
                }
                default:
                    System.out.println("\nInvalid choice!\n");      //if choice not 1 through 4 prints invalid choice
            }
        } while (choice != 4);
    }

    private static void showMenu() {                        //displays menu for user
        System.out.print("What would you like to do?\n"
                + "1. View all pets\n"
                + "2. Add new pets\n"
                + "3. Remove a pet\n"
                + "4. Exit program\n"
                + "Your choice: ");
    }
}
