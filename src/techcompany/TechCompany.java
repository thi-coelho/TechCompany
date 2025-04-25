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
    static List<Employee> employees = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("*** HELLO USER. WELCOME TO THE TECHCOMPANY ***"
                + "  \n -- Please, first enter the filename to read: -- ");
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
            System.out.println("\n Please select an option:");
            for (int i = 0; i < MenuOption.values().length; i++) {
                System.out.println((i + 1) + ". " + MenuOption.values()[i]);
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // clean the buffer

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
                     generateRandomEmployee(applicantNames);
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

    for (Employee emp : employees) {
        if (emp.getName().equalsIgnoreCase(name)) {
            emp.printInfo();
            found = true;
        }
    }

    if (!found) {
        System.out.println("This person is not yet registered as an employee.");
        System.out.println("Do you want to check if their name is in the applicants list? (yes/no)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            if (applicantNames.contains(name)) {
                System.out.println("Name found in Applicants_Form.txt, but not yet registered as employee.");
            } else {
                System.out.println("Name not found in applicants list either.");
            }
        }
    }
}

    public static void addNewEmployee() {
    System.out.print("Please input the Employee Name: ");
    String name = scanner.nextLine();

    Manager manager = chooseManager();
    Department department = chooseDepartment();

    Employee emp = new Employee(name, manager, department);
    employees.add(emp);

    // Exibe no console exatamente como você quer
    String formatted = name + " - " + manager.getType().name() + " - " + department.getName().name();
    System.out.println(formatted);

    // Escreve no arquivo no formato exato
    try (FileWriter writer = new FileWriter("Applicants_Form.txt", true)) {
        writer.write(formatted + System.lineSeparator());
    } catch (IOException e) {
        System.out.println("Error writing to Applicants_Form.txt: " + e.getMessage());
    }
}



    public static void generateRandomEmployee(List<String> applicantNames) {
    String[] randomNames = {"Alice Smith", "Benjamin Lee", "Charlotte Dubois", "Daniel Costa",
        "Eva Müller", "Felipe Sanchez", "Giulia Russo", "Hiroshi Tanaka"};

    Random rand = new Random();

    // Gera um nome aleatório
    String name = randomNames[rand.nextInt(randomNames.length)];

    // Gera um ManagerType aleatório
    ManagerType[] types = ManagerType.values();
    Manager manager = new Manager(types[rand.nextInt(types.length)]);

    // Gera um Department aleatório
    DepartmentType[] departments = DepartmentType.values();
    Department department = new Department(departments[rand.nextInt(departments.length)]);

    // Cria o texto formatado
    String formatted = name + " - " + manager.getType().name() + " - " + department.getName().name();

    // Adiciona à lista
    applicantNames.add(formatted);

    // Mostra no terminal
    System.out.println("Random Employee Generated:");
    System.out.println(formatted);

    // Salva no arquivo também (se quiser)
    try (FileWriter writer = new FileWriter("Applicants_Form.txt", true)) {
        writer.write(formatted + "\n");
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}

    public static Manager chooseManager() {
        System.out.println("Please select from the following Management Staff:");
        ManagerType[] types = ManagerType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return new Manager(types[choice - 1]);
    }

    public static Manager chooseRandomManager() {
        ManagerType[] types = ManagerType.values();
        return new Manager(types[new Random().nextInt(types.length)]);
    }

    public static Department chooseDepartment() {
        System.out.println("Please select the Department:");
        DepartmentType[] types = DepartmentType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return new Department(types[choice - 1]);
    }

    public static Department chooseRandomDepartment() {
        DepartmentType[] types = DepartmentType.values();
        return new Department(types[new Random().nextInt(types.length)]);
    }
}