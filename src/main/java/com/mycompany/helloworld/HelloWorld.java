
package com.mycompany.helloworld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author oscaro
 */

public class HelloWorld {
    
     private static final String FILE_NAME = "names.txt"; // File to store names
     private static String name;

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        
        boolean flag = true;
        String answer;
        
        while(flag){
        System.out.println("Please, introduce your name to welcome you: ");
        name = getValidEntrance(tec);
        
        saveNameToFile(name);
        
        System.out.println("Hello World to " + name + ", Welcome to The Java Programming World!!");
    
        System.out.print("Would you like to introduce another person?? (y/n): ");
                answer = tec.nextLine().toLowerCase();
                while (!answer.equals("y") && !answer.equals("n")) {
                    System.out.println("Invalid Answer. Please, introduce 'y' or 'n'.");
                    System.out.print("Would you like to introduce another person? (y/n): ");
                    answer= tec.nextLine().toLowerCase();
                }
                
              if (answer.equalsIgnoreCase("n")) {
//                System.out.println("See you later " + name + " . . .");
                flag = false;
               }
              
            } 
        
          displayNames(); // Display all names stored in the file
          
          // Ask if the user wants to edit a name
    System.out.print("Would you like to edit a name? (y/n): ");
    String editAnswer = tec.nextLine().toLowerCase();
    if (editAnswer.equals("y")) {
        editNameInFile(tec); // Call the edit method
    }
    
        // Ask if the user wants to delete a name
    System.out.print("Would you like to delete a name? (y/n): ");
    String deleteAnswer = tec.nextLine().toLowerCase();
    if (deleteAnswer.equals("y")) {
        deleteNameFromFile(tec); // Call the delete method
    }
    
        tec.close();
         System.out.println("See you later " + name + " . . .");

    }
    
    
    
private static String getValidEntrance(Scanner tec) {
        String entrance = tec.nextLine();
        while (Character.isDigit(entrance.charAt(0)) || isOperador(entrance.charAt(0))) {
            System.out.println("Error!! You must Introduce a valid String, not numbers or Operators.");
            entrance = tec.nextLine();
        }
        return entrance;
    }

    private static boolean isOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '.' || c == ',' || c == ';';
    }
    
    private static void saveNameToFile(String name){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME,true))){
            
            writer.write(name);
            writer.newLine();
            
        }catch(IOException e){
                System.out.println("An error has occured while saving the name: " + e.getMessage());
            
        }
        
    }
    
      private static void displayNames() {
        System.out.println("Names stored in the file:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the names: " + e.getMessage());
        }
    }
      
      private static void editNameInFile(Scanner tec) {
    List<String> names = new ArrayList<>();
    
    // Read all names from the file
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            names.add(line);
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the names: " + e.getMessage());
        return;
    }

    // Display names to the user
    System.out.println("Names stored in the file:");
    for (int i = 0; i < names.size(); i++) {
        System.out.println((i + 1) + ": " + names.get(i));
    }

    // Ask user which name to edit
    System.out.print("Enter the number of the name you want to edit: ");
    int index = tec.nextInt() - 1; // Convert to zero-based index
    tec.nextLine(); // Consume the newline character

    if (index < 0 || index >= names.size()) {
        System.out.println("Invalid number. No name was edited.");
        return;
    }

    // Ask for the new name
    System.out.print("Enter the new name: ");
    String newName = getValidEntrance(tec);

    // Update the name in the list
    names.set(index, newName);

    // Write the updated list back to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for (String name : names) {
            writer.write(name);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("An error occurred while saving the updated names: " + e.getMessage());
    }

    System.out.println("Name updated successfully.");
}
      
      private static void deleteNameFromFile(Scanner tec) {
    List<String> names = new ArrayList<>();
    
    // Read all names from the file
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            names.add(line);
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the names: " + e.getMessage());
        return;
    }

    // Display names to the user
    System.out.println("Names stored in the file:");
    for (int i = 0; i < names.size(); i++) {
        System.out.println((i + 1) + ": " + names.get(i));
    }

    // Ask user which name to delete
    System.out.print("Enter the number of the name you want to delete: ");
    int index = tec.nextInt() - 1; // Convert to zero-based index
    tec.nextLine(); // Consume the newline character

    if (index < 0 || index >= names.size()) {
        System.out.println("Invalid number. No name was deleted.");
        return;
    }

    // Remove the name from the list
    names.remove(index);

    // Write the updated list back to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for (String name : names) {
            writer.write(name);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("An error occurred while saving the updated names: " + e.getMessage());
    }

    System.out.println("Name deleted successfully.");
}

}
