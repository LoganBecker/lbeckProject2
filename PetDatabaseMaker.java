//Author: Logan Becker
//Professor: Gregory Silver
//Date: 11/3

package petdatabase;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PetDatabaseMaker {

    private String fName;           //initializes string for file name

    private ArrayList<Pet> pets;    //initializes array list to hold pets

    private int length;             //initilaizes a size for the array list

    public PetDatabaseMaker() {             //creates a new pet database and text file for the pets array list to write on
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the input/output file name for pet data: ");    //prompts user to enter file name or creates new file if it does not exist

        fName = s.nextLine().trim();    //file name is user input                

        pets = new ArrayList<>();       //creates new array list

        length = 0;                     //initializes size as 0
    }

    public void load() throws FileNotFoundException {        //loads file
        Scanner s;

        try {
            s = new Scanner(new File(fName));           //creates new file named after fName user inputs

            while (s.hasNextLine()) {                   //while there is text on next line 

                String line = s.nextLine().trim();      //line is cast as string

                String[] petInfo = line.split(" ");     //array of strings is created and two fields are split by white space char

                String name = petInfo[0];               //name is first string in petInfo array

                int age = Integer.parseInt(petInfo[1]);     //age is second value in petInfo array, cast to be integer

                pets.add(new Pet(name, age));               //constructs new pet using two fields in array for parameters 
            }
            s.close();
        } catch (FileNotFoundException e) {                             //new exception is thrown if file does not exist

            System.out.println("Error: Cannot find " + fName + ".\n");  //prints exception 
            System.exit(1);
        }
    }

    public void viewPets() {                       //method to view all pets in .txt file

        if (pets.isEmpty()) {

            System.out.println("Error: Sorry, there are no pets to show!\n");   //prints no pets to show if there aare not pets in array list of pets

        } else {

            System.out.println("+-----------------------+");
            System.out.printf("| %2s | %-10s | %3s |\n", "ID", "NAME", "AGE");          //formatted table for pets 
            System.out.println("+-----------------------+");

            for (int i = 0; i < length; i++) {
                System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).getName(), pets.get(i).getAge()); //prints formatted pets in table
            }
            System.out.println("+-----------------------+\n"
                    + length + " rows in set.\n");                  //prints how many pets are in database
        }
    }

    public void addPets() {                                 //adds pets
        Scanner s = new Scanner(System.in);                //
        String line;

        do {
            System.out.print("add pet (name, age): ");
            line = s.nextLine().trim();
            if (line.equalsIgnoreCase("done")) {
                break;
            }

            if (length >= 5) {
                System.out.println("Error: Database is full.\n");
                return;
            }

            String name;
            int age;
            while (line.split(" ").length != 2 || isDigit(line.split(" ")[0]) || (Integer.parseInt(line.split(" ")[1]) < 1 || Integer.parseInt(line.split(" ")[1]) > 20)) {
                if (line.split(" ").length != 2) {
                    System.out.println("Error: " + line + " is not a valid input.");    //if input is not valid e.g. if not in name(space)age format prints tells user input is not valid
                    System.out.print("add pet (name, age): ");
                    line = s.nextLine().trim();
                } else if (isDigit(line.split(" ")[0])) {
                    System.out.println("Error: " + line.split(" ")[0] + " is not a valid input.");  //if age in name field prints not valid input
                    System.out.print("add pet (name, age): ");
                    line = s.nextLine().trim();
                } else if (Integer.parseInt(line.split(" ")[1]) < 1 || Integer.parseInt(line.split(" ")[1]) > 20) {
                    System.out.println("Error: " + line.split(" ")[1] + " is not a valid age.");                    //if age is not between 1 and 20 prints invalid age
                    System.out.print("add pet (name, age): ");
                    line = s.nextLine().trim();
                }
            }

            String[] petInfo = line.split(" ");             //creates array of strings for pet info name and age

            name = petInfo[0];                              //name is first value in array

            age = Integer.parseInt(petInfo[1]);             //second value is age and is cast to int

            pets.add(new Pet(name, age));                   //adds new pet using name and age fields
            length++;
        } while (!line.equalsIgnoreCase("done"));               //executes until input is "done"

        System.out.println(length + " pets added.\n");          //prints number of pets added
    }

    public void removePets() {                              //removes pet(s)
        viewPets();

        Scanner s = new Scanner(System.in);                     //new scanner

        System.out.print("Enter the pet ID to remove: ");       //prompts user for id to remove

        int index = Integer.parseInt(s.nextLine().trim());         //gets index from line

        if (index < 0 || index > length) {                  //if index is less than 0 or greater than 5 prints error message

            System.out.println("Error: ID " + index + " does not exist.\n");

        } else {
            String name = pets.get(index).getName();     //gets removed pet name 

            int age = pets.get(index).getAge();          // gets removed pet age

            pets.remove(index);                         //removes pet with corresponding index

            length--;

            System.out.println(name + " " + age + " is removed.\n");    //prints what pet was removed
        }
    }

    public void writeToFile() throws IOException {      //writes to file and throws IOException

        FileWriter fw;      //initializes file writer

        PrintWriter pw;     //initializes print writer

        try {
            fw = new FileWriter(new File(fName));           //creates new file to write on

            pw = new PrintWriter(fw);                       //creates new print writer 

            for (Pet pet : pets) {                          //for pet in pets

                pw.write(pet.getName() + " " + pet.getAge() + "\n");    //writes name and age then new line
            }

            fw.close();     //closes file writer

            pw.close();     //closes print writer

        } catch (IOException e) {   //catches IOException

            System.out.println("Error: Writing to file " + fName + ": " + e.getMessage());  //if cannot write file prints error message
        }
    }

    private boolean isDigit(String s) throws NumberFormatException {    //checks for digit
        try {
            Integer.parseInt(s);    //casts string to int if can be cast to int

            return true;            //returns true

        } catch (NumberFormatException e) {     //catches NumberFormatException

            return false;           //returns false if not a digit
        }
    }

}
