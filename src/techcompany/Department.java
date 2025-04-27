
package techcompany;

/**
 *
 * @author: 
 * Thiago Alves Coelho - 2024287
 * 
 * The Department class represents the department to which an applicant 
 * or employee belongs in the TechCompany system.
 * Instead of storing departments as simple Strings (like "HR", "IT", "Sales"),
 * I model them as typed objects using a DepartmentType enum.
 */
public class Department {
     private DepartmentType name;

    public Department(DepartmentType name) {
        this.name = name;
    }

    public DepartmentType getName() {
        return name;
    }
}
