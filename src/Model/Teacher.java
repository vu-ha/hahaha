package Model;

public class Teacher extends User {
    private String department;

    // Constructor
    public Teacher(String userName, String userID, String email, String dob, String accountName, String password, 
                   String department) {
        super(userName, userID, email, dob, accountName, password, Role.TEACHER);
        this.department = department;
    }

    // Getter
    public String getDepartment() { return department; }
}