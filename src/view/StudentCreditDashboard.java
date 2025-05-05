package view;
import javax.swing.*;
import java.awt.*;
import Model.*;

public class StudentCreditDashboard extends JPanel {
    public StudentCreditDashboard(User user) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thanh menu ở góc trên cùng bên trái
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton trainingProgramButton = new JButton("Chương trình đào tạo sinh viên");
        JButton registrationButton = new JButton("Đăng ký học tập");
        JButton scheduleButton = new JButton("Thời khóa biểu");
        JButton tentativeScheduleButton = new JButton("Thời khóa biểu dự kiến");

        menuPanel.add(trainingProgramButton);
        menuPanel.add(registrationButton);
        menuPanel.add(scheduleButton);
        menuPanel.add(tentativeScheduleButton);
        add(menuPanel, BorderLayout.NORTH);

        // Thông tin sinh viên
        CreditBasedStudent student = (CreditBasedStudent) user;
        String info = String.format(
            "Thông tin sinh viên:\n" +
            "Họ và tên: %s\n" +
            "Mã sinh viên: %s\n" +
            "Ngành học: %s\n" +
            "Khoa: %s\n" +
            "Tổng số tín chỉ: %d",
            student.getUserName(), student.getUserID(), student.getMajor(),
            student.getFaculty(), student.getTotalCredits()
        );
        JTextArea infoArea = new JTextArea(info);
        infoArea.setEditable(false);
        infoArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JScrollPane(infoArea), BorderLayout.CENTER);

        // Placeholder cho chức năng nút (có thể mở rộng sau)
        trainingProgramButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng Chương trình đào tạo sinh viên chưa được triển khai."));
        registrationButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng Đăng ký học tập chưa được triển khai."));
        scheduleButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng Thời khóa biểu chưa được triển khai."));
        tentativeScheduleButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng Thời khóa biểu dự kiến chưa được triển khai."));
    }
}
