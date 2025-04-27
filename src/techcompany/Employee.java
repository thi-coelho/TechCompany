
package techcompany;

/**
 *
 * @author: 
 * Thiago Alves Coelho - 2024287
 * 
 * The Employee class represents a single employee in the TechCompany system,
 * linking a name with a manager and a department.
 * Models an employee, tying together name, manager, and department.
 */
public class Employee {
    private String name;
    private Manager manager;
    private Department department;

    public Employee(String name, Manager manager, Department department) {
        this.name = name;
        this.manager = manager;
        this.department = department;
    }
    // allow other parts of the program to access the employee's details safely.
    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public Department getDepartment() {
        return department;
    }
    
    // displays the employee’s full record:
    public void printInfo() {
        System.out.println(name + " - " + manager.getType() + " - " + department.getName());
    }
}
