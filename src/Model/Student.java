package Model;

public abstract class Student extends User {
    private String major;
    private String faculty;

    // Constructor
    public Student(String userName, String userID, String email, String dob, String accountName, String password, 
                   String major, String faculty) {
        super(userName, userID, email, dob, accountName, password, Role.STUDENT);
        this.major = major;
        this.faculty = faculty;
    }

    // Getter
    public String getMajor() { return major; }
    public String getFaculty() { return faculty; }
}
