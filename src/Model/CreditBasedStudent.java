package Model;

public class CreditBasedStudent extends Student {
    private int totalCredits;

    // Constructor
    public CreditBasedStudent(String userName, String userID, String email, String dob, String accountName, String password, 
                             String major, String faculty, int totalCredits) {
        super(userName, userID, email, dob, accountName, password, major, faculty);
        this.totalCredits = totalCredits;
    }

    // Getter
    public int getTotalCredits() { return totalCredits; }
}