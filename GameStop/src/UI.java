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

    private JButton loginButton = new JButton("Log in");
    private JButton signupButton = new JButton("Sing Up");
    User user = new User();

    CardLayout cardLayout = new CardLayout(); // gestione delle pagine
    JPanel container = new JPanel(cardLayout);


    public UI() {
        super("GameStop");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // schermate
        container.add(menuPanel(), "menu");
        container.add(LoginUIPage(), "login");
        container.add(signupUIPage(), "signup");
        container.add(HomePageUI(), "home");
        container.add(userInfoUI(), "userInfo");
        // container.add(ProductPageUI(), "product");

        cardLayout.show(container, "menu");

        signupButton.addActionListener(e -> {
            cardLayout.show(container, "signup");
        });

        loginButton.addActionListener(e -> {
            cardLayout.show(container, "login");
        });

        add(container, BorderLayout.CENTER);
        setVisible(true);
    }

    public JPanel menuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(signupButton);
        panel.add(loginButton);

        return panel;
    }

    public JPanel LoginUIPage() {

        JPanel loginPage = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        emailField.setPreferredSize(new Dimension(80, 25));
        passwordField.setPreferredSize(new Dimension(80, 25));

        loginPage.add(emailLabel);
        loginPage.add(emailField);
        loginPage.add(passwordLabel);
        loginPage.add(passwordField);

        JButton login = new JButton("Login");
        loginPage.add(new JLabel()); // spazio vuoto
        loginPage.add(login);

        login.addActionListener(e -> {
           Login log = new Login(emailField.getText(), passwordField.getText());
           if (log.loginVerify(user)) {
               cardLayout.show(container, "home");
           }
        });

        return loginPage;
    }

    public JPanel signupUIPage() {
        JPanel registerPage = new JPanel(new GridLayout(8, 1, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        registerPage.add(usernameLabel);
        registerPage.add(usernameField);
        registerPage.add(emailLabel);
        registerPage.add(emailField);
        registerPage.add(passwordLabel);
        registerPage.add(passwordField);

        JButton register = new JButton("Register");
        registerPage.add(new JLabel());
        registerPage.add(register);

        register.addActionListener(e -> {
            SingUp sing = new SingUp(usernameField.getText(), emailField.getText(), passwordField.getText());
            if (sing.singUpVerify(user)) {
                cardLayout.show(container, "home");
            }
        });

        return registerPage;
    }

    public JPanel HomePageUI() {

        JPanel homePage = new JPanel();



        return homePage;
    }

    public JPanel userInfoUI() {
        JPanel userInfo = new JPanel();
        return userInfo;
    }

    public JPanel ProductPageUI() {
        JPanel productPage = new JPanel();
        return productPage;
    }

    public JPanel CartPageUI() {
        JPanel cartPage = new JPanel();
        return cartPage;
    }

}
