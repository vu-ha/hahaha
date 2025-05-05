package Model;

public class YearBasedStudent extends Student {
    private String academicYear;

    // Constructor
    public YearBasedStudent(String userName, String userID, String email, String dob, String accountName, String password, 
                           String major, String faculty, String academicYear) {
        super(userName, userID, email, dob, accountName, password, major, faculty);
        this.academicYear = academicYear;
    }

    // Getter
    public String getAcademicYear() { return academicYear; }
}