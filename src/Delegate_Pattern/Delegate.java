package Delegate_Pattern;

public class Delegate {
    /**
     * In the calls below it can be view as if manager actually does the work with manager.work().
     * However, manager does not perform the work himself! Instead, he first sets Employee with the usage of his
     * "mutator method' and then, inside the method manage.work() he doesn't perform any work, but calls
     * method work() of the object that was set via mutator method, employee in this case.
     */
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.setEmployee(new Accountant());
        manager.work();

        manager.setEmployee(new SoftwareDeveloper());
        manager.work();

        manager.setEmployee(new QA());
        manager.work();

    }

}

/**
 * Manager doesn't do the work himself. Instead he just keeps a reference to the object of employee.
 * Then, he uses mutator method to fulfill the reference to employee object. So when he receives any object of type Employee
 * such as Accountant, SoftwareDeveloper or QA. Then, method work() is the one that actually forces object of type Employee to work
 * For example, if employee.getClass().getName() == Accountant, then according to rules of polymorphism that work will be done specifically by Accountant.
 * <p>
 * The Manager class is called the DELEGATOR. That is because he doesn't do any work himself, but rather delegates it to the Employees.
 * Employees classes such as Accountant, SoftwareDeveloper or QA are called DELEGATED classes since they are being delegated the work to do.
 */
class Manager {
    Employee employee;

    // mutator method
    public void setEmployee(Employee empl) {
        this.employee = empl;
    }

    public void work() {
        employee.work();
    }
}

/**
 * Implementation of the classes Accountant, SoftwareDeveloper or QA is highly desirable to be done via the INTERFACE, such as Employee.
 * This is because our manager is actually not going to change in any way.
 * Instead, we can simply update existing Employees or create a new one and pass it
 * as an argument to the mutator method of the Manager, to work with the new Employee Class.
 */
interface Employee {
    void work();
}

class Accountant implements Employee {
    @Override
    public void work() {
        System.out.println("Accountant is working on the IS & BS");
    }
}


class SoftwareDeveloper implements Employee {
    @Override
    public void work() {
        System.out.println("Software Developer is writing code");
    }
}


class QA implements Employee {
    @Override
    public void work() {
        System.out.println("QA is testing code");
    }
}

