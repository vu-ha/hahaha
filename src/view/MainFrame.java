package view;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Hệ thống quản lý học tập - Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600); // Tăng kích thước để hiển thị dashboard
        setLocationRelativeTo(null); // Căn giữa màn hình

        // Thêm LoginPanel vào JFrame
        add(new LoginPanel());
    }
}