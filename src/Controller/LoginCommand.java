package Controller;
import java.sql.*;
import java.util.logging.Logger;
import Model.*;
public class LoginCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", 
            "[%1$tF %1$tT] [%4$-7s] %5$s %n");
    }

    private final String userType;
    private final LoginCredentials credentials;

    public LoginCommand(String userType, LoginCredentials credentials) {
        this.userType = userType;
        this.credentials = credentials;
    }

    public LoginResult execute() {
        String inputAccountName = credentials.getAccountName();
        String inputPassword = credentials.getPassword();
        LOGGER.info("Executing login: userType=" + userType + ", accountName=" + inputAccountName + ", password=" + inputPassword);

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT u.user_id, u.user_name, u.role, s.student_type, s.major, s.faculty, s.total_credits " +
                        "FROM users u LEFT JOIN students s ON u.user_id = s.user_id " +
                        "WHERE LOWER(u.account_name) = LOWER(?) AND u.password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, inputAccountName);
                stmt.setString(2, inputPassword);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");
                    String studentType = rs.getString("student_type");
                    String userName = rs.getString("user_name");
                    String userID = rs.getString("user_id");
                    String major = rs.getString("major");
                    String faculty = rs.getString("faculty");
                    int totalCredits = rs.getInt("total_credits");

                    LOGGER.info("Fetched data: role=" + role + ", studentType=" + studentType + ", userName=" + userName);

                    boolean isValid = false;
                    User user = null;
                    if (userType.equals("Giáo viên") && role.equalsIgnoreCase("TEACHER")) {
                        isValid = true;
                        user = new User(userName, userID, "", "", inputAccountName, inputPassword, Role.TEACHER);
                    } else if (userType.equals("Sinh viên niên chế") && 
                               role.equalsIgnoreCase("STUDENT") && 
                               "YEAR_BASED".equalsIgnoreCase(studentType)) {
                        isValid = true;
                        user = new User(userName, userID, "", "", inputAccountName, inputPassword, Role.STUDENT);
                    } else if (userType.equals("Sinh viên tín chỉ") && 
                               role.equalsIgnoreCase("STUDENT") && 
                               "CREDIT_BASED".equalsIgnoreCase(studentType)) {
                        isValid = true;
                        user = new CreditBasedStudent(userName, userID, "", "", inputAccountName, inputPassword, 
                                                     major, faculty, totalCredits);
                        LOGGER.info("Created CreditBasedStudent: " + userID);
                    }

                    if (isValid) {
                        LOGGER.info("Login successful for user: " + inputAccountName + " (" + userType + ")");
                        return new LoginResult(true, "", user);
                    } else {
                        LOGGER.warning("Invalid user type for account: " + inputAccountName + ", role=" + role + ", studentType=" + studentType);
                        return new LoginResult(false, "Loại người dùng không khớp với tài khoản. Vai trò: " + role + ", Loại SV: " + studentType, null);
                    }
                } else {
                    LOGGER.warning("Login failed for account: " + inputAccountName);
                    return new LoginResult(false, "Tài khoản hoặc mật khẩu không đúng. Vui lòng kiểm tra lại.", null);
                }
            }
        } catch (SQLException ex) {
            LOGGER.severe("Database error during login: " + ex.getMessage());
            ex.printStackTrace();
            return new LoginResult(false, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), null);
        }
    }
}