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
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

// TODO: aggiungere una homepage decente
//  aggiungere carrello
//  aggiungere pagamento e indirizzo, memorizzare i dati.

public class UI extends JFrame {

    private JButton loginButton = new JButton("Login");
    private JButton signupButton = new JButton("Sing Up");
    private JPanel homePage = new JPanel();
    private Warehouse warehouse;
    User user = new User();

    CardLayout cardLayout = new CardLayout(); // gestione delle pagine
    JPanel container = new JPanel(cardLayout);

    public UI() {
        super("GameStop");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Applica font globale PRIMA di creare/renderizzare componenti
        setGlobalFont();

        // MENU
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem logoutItem = new JMenuItem("Logout");

        logoutItem.addActionListener(e -> {
            user = null;
            System.out.println("Logout effettuato");
            cardLayout.show(container, "menu");
            // qui puoi cambiare schermata manualmente se vuoi
        });

        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // schermate
        container.add(menuPanel(), "menu");
        container.add(LoginUIPage(), "login");
        container.add(signupUIPage(), "signup");
        container.add(userInfoUI(), "userInfo");
        homePage = HomePageUI();
        container.add(homePage, "home");
        // container.add(ProductPageUI(), "product");

        cardLayout.show(container, "home");

        signupButton.addActionListener(e -> {
            cardLayout.show(container, "signup");
        });

        loginButton.addActionListener(e -> {
            cardLayout.show(container, "login");
        });

        add(container, BorderLayout.CENTER);
        setVisible(true);
    }

    private void setGlobalFont() {
        try {
            // Carica font da resources/assets/fonts
            Font customFont = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("assets/fonts/Nunito/static/Nunito-Regular.ttf")
            ).deriveFont(16f);

            // Registra font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            // Applica a tutti i componenti Swing
            Enumeration<Object> keys = UIManager.getDefaults().keys();

            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);

                if (value instanceof Font) {
                    UIManager.put(key, customFont);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel menuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.add(signupButton);
        panel.add(loginButton);

        return panel;
    }

    public JPanel LoginUIPage() {

        JPanel outer = new JPanel(new GridBagLayout()); // centra tutto

        JPanel loginPage = new JPanel(new GridLayout(8, 1, 5, 10));

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        emailField.setPreferredSize(new Dimension(80, 25));
        passwordField.setPreferredSize(new Dimension(80, 25));

        JTextArea feedback = new JTextArea();
        feedback.setPreferredSize(new Dimension(80, 25));
        feedback.setEditable(false);
        feedback.setBorder(null);
        feedback.setLineWrap(true);
        feedback.setWrapStyleWord(true);

        loginPage.add(image);
        loginPage.add(emailLabel);
        loginPage.add(emailField);
        loginPage.add(passwordLabel);
        loginPage.add(passwordField);
        loginPage.add(feedback);
        feedback.setBorder(null);

        JButton back = new JButton("Back");
        JButton login = new JButton("Login");
        loginPage.add(new JLabel()); // spazio vuoto

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        buttonPanel.add(login);
        loginPage.add(buttonPanel);

        back.addActionListener(e -> {
            cardLayout.show(container, "menu");
            emailField.setText("");
            passwordField.setText("");
            feedback.setText("");
        });
        login.addActionListener(e -> {
           Login log = new Login(emailField.getText(), passwordField.getText());
           User logged = log.loginVerify();
           if (logged != null) {
               user = logged;
               user.loadCart();
               container.add(HomePageUI(), "home"); // 👈 ricrea con dati aggiornati
               cardLayout.show(container, "home");
               // svuoto i campi dopo il login
               emailField.setText("");
               passwordField.setText("");
           } else {
               feedback.setText("Email o password errati");
               feedback.setForeground(Color.RED);
           }
        });

        outer.add(loginPage);
        return outer;
    }

    public JPanel signupUIPage() {
        JPanel outer = new JPanel(new GridBagLayout()); // centra tutto

        JPanel registerPage = new JPanel(new GridLayout(9, 1, 10, 10));

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JTextArea feedback = new JTextArea();
        feedback.setPreferredSize(new Dimension(80, 25));
        feedback.setEditable(false);
        feedback.setBorder(null);
        feedback.setLineWrap(true);      // Va a capo automaticamente
        feedback.setWrapStyleWord(true);

        registerPage.add(image);
        registerPage.add(usernameLabel);
        registerPage.add(usernameField);
        registerPage.add(emailLabel);
        registerPage.add(emailField);
        registerPage.add(passwordLabel);
        registerPage.add(passwordField);
        registerPage.add(feedback);

        JButton back = new JButton("Back");
        JButton register = new JButton("Register");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        buttonPanel.add(register);
        registerPage.add(buttonPanel);

        back.addActionListener(e -> {
            cardLayout.show(container, "menu");
            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            feedback.setText("");
        });
        register.addActionListener(e -> {
            SingUp sing = new SingUp(usernameField.getText(), emailField.getText(), passwordField.getText());
            User logged = sing.singUpVerify();

            if (logged != null) {
                user = logged;
                user.loadCart();
                container.add(HomePageUI(), "home"); // 👈 ricrea con dati aggiornati
                cardLayout.show(container, "home");
                // svuoto i campi dopo la registrazione
                usernameField.setText("");
                emailField.setText("");
                passwordField.setText("");
            } else {
                if ( usernameField.getText().equals("") || emailField.getText().equals("") || passwordField.getText().equals("")){
                    feedback.setText("Compila tutti i campi");
                    feedback.setForeground(Color.RED);
                } else {
                    feedback.setText("Email " + emailField.getText() + " e gia registrata");
                    feedback.setForeground(Color.RED);
                }
            }
        });

        outer.add(registerPage);
        return outer;
    }

    public JPanel HomePageUI() {

        JPanel homePage = new JPanel(new BorderLayout());

        // TOP PANEL (logo)
        JPanel topPanel = new JPanel(new BorderLayout());

        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(220, 80, Image.SCALE_SMOOTH));
        JLabel image = new JLabel(icon);
        image.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        topPanel.add(image, BorderLayout.CENTER);

        homePage.add(topPanel, BorderLayout.NORTH);

        // CATALOGO
        JPanel catalog = new JPanel(new GridLayout(0, 4, 20, 20));

        catalog.setBackground(Color.RED);
        catalog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // magazzino
        warehouse = new Warehouse(loadDB());

        // carico la lista di prodotti
        for (Product p : warehouse.getProducts()) {
            catalog.add(createProductCard(p.getName(), String.valueOf(p.getPrice()), p.getImagePath()));
        }

        // SCROLL (barra a destra)
        JScrollPane scrollPane = new JScrollPane(
                catalog,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setBorder(BorderFactory.createTitledBorder("Catalogo"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        homePage.add(scrollPane, BorderLayout.WEST);

        // USER INFO
        JPanel userInfoPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon userIcon = new ImageIcon("docs/base-user-icon.png");
        userIcon.setImage(userIcon.getImage().getScaledInstance(64, 32, Image.SCALE_SMOOTH));
        userInfoPanel.add(new JLabel(userIcon));
        userInfoPanel.add(new JLabel("Benvenuto: " + user.getUsername()));
        // userInfoPanel.add(new JLabel("Email: " + user.getEmail()));
        // userInfoPanel.add(new JLabel("Carrello: "));

        JLabel iconCart = new JLabel(new ImageIcon("docs/cart.png"));
        iconCart.setPreferredSize(new Dimension(32, 32));

        userInfoPanel.add(iconCart);
        homePage.add(userInfoPanel, BorderLayout.EAST);

        return homePage;
    }

    public List<String[]> loadDB() {
        String filePath = "data/DataBase.csv";
        List<String[]> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length >= 2) {
                    products.add(parts); // [nome, prezzo, (immagine opzionale)]
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    // metodo temporaneo per creare un prodotto
    // TODO: sistemare il caricamento dei prodotti e delle immagini
    // TODO: sistemare metodo prodotto
    public JPanel createProductCard(String name, String price, String imagePath) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(250, 300));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.setBackground(Color.WHITE);
        card.setOpaque(true);

        ImageIcon icon = new ImageIcon(imagePath);
        icon.setImage(icon.getImage().getScaledInstance(220, 140, Image.SCALE_SMOOTH));

        JLabel image = new JLabel(icon);
        image.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title = new JLabel(name);
        JLabel priceLabel = new JLabel(price + " €");

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
        userInfo.setLayout(new BorderLayout());
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
