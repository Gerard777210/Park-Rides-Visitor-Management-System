import java.time.LocalDate;

// Employee class (inherits Person)
public class Employee extends Person {
    // Instance variables
    private String employeeId;
    private String position; // e.g., Operator
    private boolean isAvailable = true;
    private LocalDate hireDate;

    // Default constructor
    public Employee() {}

    // Parameterized constructor
    public Employee(String name, int age, String id, String employeeId, String position) {
        super(name, age, id);
        this.employeeId = employeeId;
        this.position = position;
        this.hireDate = LocalDate.now();
    }

    // Parameterized constructor with hire date
    public Employee(String name, int age, String id, String employeeId, String position, LocalDate hireDate) {
        super(name, age, id);
        this.employeeId = employeeId;
        this.position = position;
        this.hireDate = hireDate;
    }

    // Getter & Setter
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    // Business methods
    public boolean canOperateRide(Ride ride) {
        return isAvailable && ride != null;
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, id='%s', employeeId='%s', position='%s', available=%s}",
                getName(), getAge(), getId(), employeeId, position, isAvailable);
    }
}