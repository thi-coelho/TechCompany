
package techcompany;

/**
 *
 * @author: 
 * Thiago Alves Coelho - 2024287
 * 
 * The Manager class represents an employee’s manager type in the TechCompany
 * system.Instead of just using a plain String, it uses a ManagerType enum to ensure 
 * consistency and prevent errors when assigning manager roles.
 */
public class Manager {
      private ManagerType type;

    public Manager(ManagerType type) {
        this.type = type;
    }

    public ManagerType getType() {
        return type;
    }
}   
