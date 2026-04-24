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

// TODO: aggiunta feedback nel login,
//  aggiungere una homepage decente
//  aggiungere databse prodotti
//  aggiungere carrello
//  aggiungere pagamento e indirizzo, memorizzare i dati.

public class UI extends JFrame {

    private JButton loginButton = new JButton("Log in");
    private JButton signupButton = new JButton("Sing Up");
    private JPanel homePage = new JPanel();
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
        container.add(userInfoUI(), "userInfo");
        homePage = HomePageUI();
        container.add(homePage, "home");
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

        JPanel outer = new JPanel(new GridBagLayout()); // centra tutto

        JPanel loginPage = new JPanel(new GridLayout(7, 1, 5, 10));

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        emailField.setPreferredSize(new Dimension(80, 25));
        passwordField.setPreferredSize(new Dimension(80, 25));

        loginPage.add(image);
        loginPage.add(emailLabel);
        loginPage.add(emailField);
        loginPage.add(passwordLabel);
        loginPage.add(passwordField);

        JButton back = new JButton("Back");
        JButton login = new JButton("Login");
        loginPage.add(new JLabel()); // spazio vuoto

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        buttonPanel.add(login);
        loginPage.add(buttonPanel);

        back.addActionListener(e -> {
            cardLayout.show(container, "menu");
        });
        login.addActionListener(e -> {
           Login log = new Login(emailField.getText(), passwordField.getText());
           User logged = log.loginVerify();
           if (logged != null) {
               user = logged;
               user.loadCart();
               container.add(HomePageUI(), "home"); // 👈 ricrea con dati aggiornati
               cardLayout.show(container, "home");
           }
        });

        outer.add(loginPage);
        return outer;
    }

    public JPanel signupUIPage() {
        JPanel outer = new JPanel(new GridBagLayout()); // centra tutto

        JPanel registerPage = new JPanel(new GridLayout(8, 1, 10, 10));

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        registerPage.add(image);
        registerPage.add(usernameLabel);
        registerPage.add(usernameField);
        registerPage.add(emailLabel);
        registerPage.add(emailField);
        registerPage.add(passwordLabel);
        registerPage.add(passwordField);

        JButton back = new JButton("Back");
        JButton register = new JButton("Register");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        buttonPanel.add(register);
        registerPage.add(buttonPanel);

        back.addActionListener(e -> {
            cardLayout.show(container, "menu");
        });
        register.addActionListener(e -> {
            SingUp sing = new SingUp(usernameField.getText(), emailField.getText(), passwordField.getText());
            User logged = sing.singUpVerify();

            if (logged != null) {
                user = logged;
                user.loadCart();
                container.add(HomePageUI(), "home"); // 👈 ricrea con dati aggiornati
                cardLayout.show(container, "home");
            }
        });

        outer.add(registerPage);
        return outer;
    }

    public JPanel HomePageUI() {

        JPanel homePage = new JPanel(new BorderLayout());

        // ancora da definire
        JPanel leftPanel = new JPanel(new BorderLayout());

        homePage.add(leftPanel, BorderLayout.WEST);

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 80, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        leftPanel.add(image, BorderLayout.NORTH);

        // catalogo prodotti
        JPanel catalog = new JPanel(new GridLayout(0, 3, 20, 20));

        JScrollPane scrollPane = new JScrollPane(catalog);

        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        catalog.add(createProductCard("gta 5", "59.99"));
        homePage.add(scrollPane, BorderLayout.CENTER);

        // info account e carrello
        JPanel userInfoPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        userInfoPanel.add(new JLabel("Nome: " + user.getUsername()));
        userInfoPanel.add(new JLabel("Email: " + user.getEmail()));
        userInfoPanel.add(new JLabel("Carrello: "));
        homePage.add(userInfoPanel, BorderLayout.EAST);

        // barra di ricerca
        JPanel topPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Logout");
        menu.add(item);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        item.addActionListener(e -> {
            user = null;
            cardLayout.show(container, "menu");
        });
        topPanel.add(menuBar);
        homePage.add(topPanel, BorderLayout.NORTH);

        return homePage;
    }

    // metodo temporaneo per creare un prodotto
    // TODO: sistemare il caricamento dei prodotti e delle immagini
    // TODO: sistemare metodo prodotto
    public JPanel createProductCard(String name, String price) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 250));

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(160, 80, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);
        image.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title = new JLabel(name);
        JLabel priceLabel = new JLabel(price);

        JButton buyButton = new JButton("Add to cart");

        JPanel info = new JPanel(new GridLayout(3, 1));
        info.add(title);
        info.add(priceLabel);
        info.add(buyButton);

        card.add(image, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);

        return card;
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
