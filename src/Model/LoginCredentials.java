package Model;

public class LoginCredentials {
    private final String accountName;
    private final String password;

    public LoginCredentials(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public String getAccountName() { return accountName; }
    public String getPassword() { return password; }
}

