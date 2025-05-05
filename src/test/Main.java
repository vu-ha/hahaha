package test;

import javax.swing.*;
import Controller.*;
import view.*;

public class Main {
    public static void main(String[] args) {
    	CreateUser createUser = new CreateUser();
        createUser.createUsers();
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
