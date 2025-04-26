/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package techcompany;
import java.io.*;
import java.util.*;
/**
 *
 * @author: 
 * Thiago Alves Coelho - 2024287
 * 
 */
public class TechCompany {

    static List<String> applicantNames = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("*** HELLO USER. WELCOME TO THE TECHCOMPANY ***"
                + "\n-- Please, first enter the filename to read: -- ");
        String filename = scanner.nextLine();

        if (readApplicantsFromFile(filename)) {
            System.out.println("File read successfully.\n");
            showMenu();
        } else {
            System.out.println("Failed to read file.");
        }
    }

    public static boolean readApplicantsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                applicantNames.add(line.trim());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static void showMenu() {
    while (true) {
        System.out.println("\nPlease select an option:");
        for (int i = 0; i < MenuOption.values().length; i++) {
            System.out.println((i + 1) + ". " + MenuOption.values()[i]);
        }

        int choice = -1;   
        boolean valid = false;

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
                return;
        }
    }
}

    public static void sortApplicants() {
        Collections.sort(applicantNames, String.CASE_INSENSITIVE_ORDER);
        System.out.println("\n--- First 20 Sorted Applicants ---");
        for (int i = 0; i < Math.min(20, applicantNames.size()); i++) {
            System.out.println((i + 1) + ". " + applicantNames.get(i));
        }
    }

    public static void searchApplicant() {
        System.out.print("Enter the name to search: ");
        String name = scanner.nextLine();

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

    public static void addNewEmployee() {
        System.out.print("Please input the Employee Name: ");
        String name = scanner.nextLine();

        Manager manager = chooseManager();
        Department department = chooseDepartment();

        String formatted = name + " - " + manager.getType().name() + " - " + department.getName().name();
        applicantNames.add(formatted);
        System.out.println("Employee added: " + formatted);
        displayApplicantList();

        // Save in file
        try (FileWriter writer = new FileWriter("Applicants_Form.txt", true)) {
            writer.write(formatted + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to Applicants_Form.txt: " + e.getMessage());
        }
    }
    
    public static void displayApplicantList() {
        System.out.println("\n--- Updated Applicants List ---");
        for (int i = 0; i < applicantNames.size(); i++) {
        System.out.println((i + 1) + ". " + applicantNames.get(i));
    }
}
    public static void generateRandomEmployee() {
        String[] randomNames = {"Alice Smith", "Benjamin Lee", "Charlotte Dubois", "Daniel Costa",
            "Eva Müller", "Felipe Sanchez", "Giulia Russo", "Hiroshi Tanaka", "James Anderson",
            "Emily Johnson", "Michael Smith", "Sophia Brown", "William Davis", "Olivia Wilson",
            "Benjamin Martinez", "Emma Taylor", "Daniel White", "Abigail Thomas"};

        Random rand = new Random();
        String name = randomNames[rand.nextInt(randomNames.length)];

        ManagerType[] types = ManagerType.values();
        Manager manager = new Manager(types[rand.nextInt(types.length)]);

        DepartmentType[] departments = DepartmentType.values();
        Department department = new Department(departments[rand.nextInt(departments.length)]);

        String formatted = name + " - " + manager.getType().name() + " - " + department.getName().name();
        applicantNames.add(formatted);
        System.out.println("Random Employee Generated: " + formatted);
        displayApplicantList();

        try (FileWriter writer = new FileWriter("Applicants_Form.txt", true)) {
            writer.write(formatted + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error writing to Applicants_Form.txt: " + e.getMessage());
        }
    }

    public static Manager chooseManager() {
        System.out.println("Please select from the following Management Staff:");
        ManagerType[] types = ManagerType.values();
        for (int i = 0; i < types.length; i++) {
        System.out.println((i + 1) + ". " + types[i]);
        }

        int choice = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter your choice (1-" + types.length + "): ");
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

    public static Department chooseDepartment() {
        System.out.println("Please select the Department:");
        DepartmentType[] types = DepartmentType.values();
        for (int i = 0; i < types.length; i++) {
        System.out.println((i + 1) + ". " + types[i]);
        }

        int choice = -1;
        boolean valid = false;

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
