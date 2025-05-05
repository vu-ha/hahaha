package Model;

public class LoginResult {
    private boolean success;
    private String message;
    private User user;

    public LoginResult(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public User getUser() { return user; }
}
