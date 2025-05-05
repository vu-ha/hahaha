package Model;

public class User {
    private String userName;
    private String userID;
    private String email;
    private String dob;
    private String accountName;
    private String password;
    private Role role;

    // Constructor
    public User(String userName, String userID, String email, String dob, String accountName, String password, Role role) {
        this.userName = userName;
        this.userID = userID;
        this.email = email;
        this.dob = dob;
        this.accountName = accountName;
        this.password = password;
        this.role = role;
    }

    // Getter
    public String getUserName() { return userName; }
    public String getUserID() { return userID; }
    public String getEmail() { return email; }
    public String getDob() { return dob; }
    public String getAccountName() { return accountName; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
}
