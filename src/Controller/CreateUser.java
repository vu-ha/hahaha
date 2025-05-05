package Controller;
import java.sql.*;
import java.util.logging.Logger;
import Model.*;


public class CreateUser {
    private static final Logger LOGGER = Logger.getLogger(CreateUser.class.getName());

    // Tạo và lưu 5 YearBasedStudent, 5 CreditBasedStudent, 5 Teacher
    public void createUsers() {
        try (Connection conn = Database.getConnection()) {
            // Tạo 5 YearBasedStudent
            for (int i = 1; i <= 5; i++) {
                String id = "STU_YB" + String.format("%03d", i);
                YearBasedStudent student = new YearBasedStudent(
                    "Year Student " + i, id, "yearstudent" + i + "@example.com", "2000-01-0" + i,
                    "yearstudent" + i, "password" + i, "Computer Science", "Faculty of Engineering", "K6" + i
                );
                insertUser(conn, student);
                insertStudent(conn, student, "YEAR_BASED", student.getAcademicYear(), null);
                LOGGER.info("Created YearBasedStudent: " + student.getUserID());
            }

            // Tạo 5 CreditBasedStudent
            for (int i = 1; i <= 5; i++) {
                String id = "STU_CB" + String.format("%03d", i);
                CreditBasedStudent student = new CreditBasedStudent(
                    "Credit Student " + i, id, "creditstudent" + i + "@example.com", "2000-02-0" + i,
                    "creditstudent" + i, "password" + i, "Mathematics", "Faculty of Science", 80 + i * 10
                );
                insertUser(conn, student);
                insertStudent(conn, student, "CREDIT_BASED", null, student.getTotalCredits());
                LOGGER.info("Created CreditBasedStudent: " + student.getUserID());
            }

            // Tạo 5 Teacher
            for (int i = 1; i <= 5; i++) {
                String id = "TEA" + String.format("%03d", i);
                Teacher teacher = new Teacher(
                    "Teacher " + i, id, "teacher" + i + "@example.com", "1970-01-0" + i,
                    "teacher" + i, "teacherpass" + i, "Department " + i
                );
                insertUser(conn, teacher);
                insertTeacher(conn, teacher);
                LOGGER.info("Created Teacher: " + teacher.getUserID());
            }
        } catch (SQLException e) {
            LOGGER.severe("Error creating users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Chèn User vào bảng users
    private void insertUser(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO users (user_id, user_name, email, dob, account_name, password, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getDob());
            stmt.setString(5, user.getAccountName());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getRole().name());
            stmt.executeUpdate();
        }
    }

    // Chèn Student vào bảng students
    private void insertStudent(Connection conn, Student student, String studentType, 
                             String academicYear, Integer totalCredits) throws SQLException {
        String sql = "INSERT INTO students (user_id, major, faculty, student_type, academic_year, total_credits) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getUserID());
            stmt.setString(2, student.getMajor());
            stmt.setString(3, student.getFaculty());
            stmt.setString(4, studentType);
            stmt.setString(5, academicYear);
            if (totalCredits != null) {
                stmt.setInt(6, totalCredits);
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    // Chèn Teacher vào bảng teachers
    private void insertTeacher(Connection conn, Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (user_id, department) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getUserID());
            stmt.setString(2, teacher.getDepartment());
            stmt.executeUpdate();
        }
    }
}