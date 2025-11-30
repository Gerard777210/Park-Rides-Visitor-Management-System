// Abstract Person class
public abstract class Person {
    // Instance variables
    private String name;
    private int age;
    private String id;

    // Default constructor
    public Person() {}

    // Parameterized constructor with validation
    public Person(String name, int age, String id) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
        this.age = age;
    }

    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        this.id = id;
    }

    // Override toString for printing
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", id='" + id + "'}";
    }

    // Equals and hashCode for proper collection operations
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}