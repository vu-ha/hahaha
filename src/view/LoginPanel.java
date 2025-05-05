package view;
import javax.swing.*;
import java.awt.*;
import Model.*;
import Controller.*;
import java.util.logging.Logger;

public class LoginPanel extends JPanel {
    private JRadioButton yearBasedStudentRadio;
    private JRadioButton creditBasedStudentRadio;
    private JRadioButton teacherRadio;
    private JTextField accountNameField;
    private JPasswordField passwordField;
    private JLabel errorLabel; // Thêm label để hiển thị lỗi
    private static final Logger LOGGER = Logger.getLogger(LoginPanel.class.getName());
    public LoginPanel() {
        // Sử dụng BoxLayout để sắp xếp theo chiều dọc
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề "ĐĂNG NHẬP" với biểu tượng người dùng
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.RED);
        titlePanel.add(titleLabel);
        add(titlePanel);

        // Khoảng cách
        add(Box.createVerticalStrut(10));

        // Hướng dẫn
        JLabel instructionLabel = new JLabel("Vui lòng chọn vai trò đăng nhập:");
        add(instructionLabel);

        // Nhóm Radio Buttons cho vai trò
        yearBasedStudentRadio = new JRadioButton("Sinh viên niên chế", true);
        creditBasedStudentRadio = new JRadioButton("Sinh viên tín chỉ");
        teacherRadio = new JRadioButton("Giảng viên");

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(yearBasedStudentRadio);
        roleGroup.add(creditBasedStudentRadio);
        roleGroup.add(teacherRadio);

        // Thêm các radio buttons vào panel
        add(yearBasedStudentRadio);
        add(creditBasedStudentRadio);
        add(teacherRadio);

        // Khoảng cách
        add(Box.createVerticalStrut(10));

        // Trường nhập tài khoản
        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel accountLabel = new JLabel("Tài khoản");
        accountLabel.setPreferredSize(new Dimension(80, 25));
        accountNameField = new JTextField(20);
        accountPanel.add(accountLabel);
        accountPanel.add(accountNameField);
        add(accountPanel);

        // Trường nhập mật khẩu và label lỗi
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Mật khẩu");
        passwordLabel.setPreferredSize(new Dimension(80, 25));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(passwordPanel);
        add(errorLabel); // Thêm label lỗi ngay dưới trường mật khẩu

        // Khoảng cách
        add(Box.createVerticalStrut(10));

        // Nút đăng nhập
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginButton);

        // Xử lý sự kiện nút đăng nhập
        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String userType = yearBasedStudentRadio.isSelected() ? "Sinh viên niên chế" :
                         creditBasedStudentRadio.isSelected() ? "Sinh viên tín chỉ" : "Giáo viên";
        String accountName = accountNameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Kiểm tra đầu vào
        if (accountName.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Vui lòng nhập tài khoản và mật khẩu.");
            return;
        }
        errorLabel.setText(""); // Xóa thông báo lỗi cũ

        // Gọi LoginCommand để xử lý xác thực
        LoginCredentials credentials = new LoginCredentials(accountName, password);
        LoginCommand loginCommand = new LoginCommand(userType, credentials);
        LoginResult result = loginCommand.execute();

        // Hiển thị kết quả
        if (result.isSuccess()) {
            User user = result.getUser();
            LOGGER.info("User type: " + userType + ", User class: " + user.getClass().getName());
            if (userType.equals("Sinh viên tín chỉ") && user instanceof CreditBasedStudent) {
                LOGGER.info("Switching to StudentCreditDashboard for user: " + user.getUserID());
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                topFrame.setContentPane(new StudentCreditDashboard(user));
                topFrame.revalidate();
                topFrame.repaint();
            } else {
                // Xóa trường nhập sau khi đăng nhập thành công cho các vai trò khác
                accountNameField.setText("");
                passwordField.setText("");
            }
        } else {
            errorLabel.setText(result.getMessage()); // Hiển thị lỗi ngay bên dưới mật khẩu
        }
    }
}