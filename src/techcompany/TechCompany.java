package techcompany;

import java.io.*; // Import classes for file reading and writing
import java.util.*; // Import utility classes like Scanner, List, Random
/**
 *
 * @author: 
 * Thiago Alves Coelho - 2024287
 * 
 */
public class TechCompany {

    static List<String> applicantNames = new ArrayList<>(); // List to store all applicant names and records
    static Scanner scanner = new Scanner(System.in); // Scanner to read user input

     public static void main(String[] args){
        // Display welcome message and ask for the filename
        System.out.print("*** HELLO USER. WELCOME TO THE TECHCOMPANY ***" 
                + "\n-- Please, first enter the filename to read: -- ");
        String filename;

        do {
            filename = scanner.nextLine(); // Gets the name of the file
            
            // Try to read applicants from the given file
            if (readApplicantsFromFile(filename)) {
                System.out.println("File read successfully.\n");
                showMenu(); // If successful, show the main menu
            } else {
                System.out.println("Failed to read file. Please enter a valid filename: ");
            }
            
        } while (!filename.equals("Applicants_Form.txt"));
        scanner.close(); // Close the scanner.
    }

     // Method to read applicant names from a file
    public static boolean readApplicantsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                applicantNames.add(line.trim()); // Add each line (trimmed) to the list
            }
            return true;    // File read successfully
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage()); // Handle file reading errors
            return false;
        }
    }

    // Main menu method
    public static void showMenu() {
    while (true) {
        // Display menu options dynamically from Enum MenuOption
        System.out.println("\nPlease select an option:");
        for (int i = 0; i < MenuOption.values().length; i++) {
            System.out.println((i + 1) + ". " + MenuOption.values()[i]);
        }

        int choice = -1;   
        boolean valid = false;
         // Loop until the user provides a valid option
        while (!valid) {
            System.out.print("Enter your choice (1-" + MenuOption.values().length + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= MenuOption.values().length) {
                    valid = true; 
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + MenuOption.values().length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
             // Perform action based on user's choice
            switch (MenuOption.values()[choice - 1]) {
            case SORT:
                sortApplicants();
                break;
            case SEARCH:
                searchApplicant();
                break;
            case ADD_RECORDS:
                addNewEmployee();
                break;
            case GENERATE_RANDOM:
                generateRandomEmployee();
                break;
            case EXIT:
                System.out.println("Thank you for using our program. Goodbye!");
                return; // Exit the program
            }
        }
    }
    // Method to sort applicants alphabetically (case-insensitive)
    public static void sortApplicants() {
        Collections.sort(applicantNames, String.CASE_INSENSITIVE_ORDER);
        System.out.println("\n--- First 20 Sorted Applicants ---");
        for (int i = 0; i < Math.min(20, applicantNames.size()); i++) {
            System.out.println((i + 1) + ". " + applicantNames.get(i));
        }
    }
    // Method to search for an applicant by name
    public static void searchApplicant() {
        String name;
        while (true) {
        System.out.print("Enter the name to search: ");
        name = scanner.nextLine().trim();
        System.out.println(" \n");

        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty. Please try again.");
        } else if (!name.matches("[a-zA-Z ]+")) {
            System.out.println("Error: Name must contain only letters and spaces. Please try again.");
        } else {
            break; // Valid input
            }
        }

        boolean found = false;
        for (String record : applicantNames) {
        if (record.toLowerCase().contains(name.toLowerCase())) {
            System.out.println("Found: " + record);
            found = true;
            }
        }

        if (!found) {
        System.out.println("Name not found in the applicants list.");
        }
    }
    // Method to add a new employee manually
    public static void addNewEmployee() {
        String name;
        while (true) {
            System.out.print("Please input the Employee Name: ");
            name = scanner.nextLine().trim(); 
            System.out.println(" \n");
        
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty. Please try again.");
            continue;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            System.out.println("Error: Name must contain only letters and spaces. Please try again.");
            continue;
        }

        break;  // Valid input
        }
        // Ask user to choose Manager and Department
        Manager manager = chooseManager();
        Department department = chooseDepartment();
        
        // Format the new record
        String formatted = name + " - " + manager.getType().name() + " - " + department.getName().name();
        applicantNames.add(formatted); // Add to the list
        System.out.println("Employee: " + name + " was added to the list.");
        System.out.println(formatted);
        displayApplicantList(); // Display updated list
        
        // Append new employee to the file
        try (FileWriter writer = new FileWriter("Applicants_Form.txt", true)) {
        writer.write(formatted + System.lineSeparator());
        } catch (IOException e) {
        System.out.println("Error writing to Applicants_Form.txt: " + e.getMessage());
        }
    }
    // Method to display the full list of applicants
    public static void displayApplicantList() {
        System.out.println("\n--- Updated Applicants List ---");
        for (int i = 0; i < applicantNames.size(); i++) {
        System.out.println((i + 1) + ". " + applicantNames.get(i));
        }
    }
    // Method to generate and add a random employee
    public static void generateRandomEmployee() {
        // Predefined names for random generation
        String[] randomNames = {"Alice Smith", "Benjamin Lee", "Charlotte Dubois", "Daniel Costa",
            "Eva Müller", "Felipe Sanchez", "Giulia Russo", "Hiroshi Tanaka", "James Anderson",
            "Emily Johnson", "Michael Smith", "Sophia Brown", "William Davis", "Olivia Wilson",
            "Benjamin Martinez", "Emma Taylor", "Daniel White", "Abigail Thomas"};

        Random rand = new Random();
        String name = randomNames[rand.nextInt(randomNames.length)];
         // Randomly select Manager and Department
        ManagerType[] types = ManagerType.values();
        Manager manager = new Manager(types[rand.nextInt(types.length)]);

        DepartmentType[] departments = DepartmentType.values();
        Department department = new Department(departments[rand.nextInt(departments.length)]);
        // Create and add the new random record
        String formatted = name + " - " + manager.getType().name() + " - " + department.getName().name();
        applicantNames.add(formatted);
        System.out.println("Random Employee Generated: " + formatted);
        displayApplicantList();
        
        // Save to file
        try (FileWriter writer = new FileWriter("Applicants_Form.txt", true)) {
            writer.write(formatted + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to Applicants_Form.txt: " + e.getMessage());
        }
    }
    // Helper method to choose a Manager
    public static Manager chooseManager() {
        System.out.println("Please select from the following Management Staff:");
        ManagerType[] types = ManagerType.values();
        for (int i = 0; i < types.length; i++) {
        System.out.println((i + 1) + ". " + types[i]);
        }

        int choice = -1;
        boolean valid = false;
        
        // Validate user's choice
        while (!valid) {
            System.out.print("Enter your choice (1-" + types.length + "): ");
            System.out.println(" \n");
            try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= types.length) {
                valid = true;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + types.length + ".");
            }
            }   catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return new Manager(types[choice - 1]);
    }
    // Helper method to choose a Department
    public static Department chooseDepartment() {
        System.out.println("Please select the Department:");
        System.out.println(" \n");
        DepartmentType[] types = DepartmentType.values();
        for (int i = 0; i < types.length; i++) {
        System.out.println((i + 1) + ". " + types[i]);
        }

        int choice = -1;
        boolean valid = false;
         
        // Validate user's choice
        while (!valid) {
            System.out.print("Enter your choice (1-" + types.length + "): ");
            try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= types.length) {
                valid = true;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + types.length + ".");
                }
            } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return new Department(types[choice - 1]);
    }
}
