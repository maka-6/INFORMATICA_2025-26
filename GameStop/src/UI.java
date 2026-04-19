/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 19/04/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    public UI() {
        super("GameStop");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public JPanel HomePage() {
        JPanel homePage = new JPanel();

        return homePage;
    }

    public JPanel LoginPage() {
        JPanel loginPage = new JPanel(new FlowLayout());

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        // emailField.contains("@");


        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        loginPage.add(emailLabel);
        loginPage.add(emailField);
        loginPage.add(passwordLabel);
        loginPage.add(passwordField);
        loginPage.add(loginButton);
        loginPage.add(registerButton);

        return loginPage;
    }

    public JPanel RegisterPage() {
        JPanel registerPage = new JPanel();

        // emailField.contains("@");

        return registerPage;
    }

}
