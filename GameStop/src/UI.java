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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private User user = new User();
    private int numProduct;
    private JLabel numProductLabel;

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
        JMenuItem profileItem = new JMenuItem("Profilo");
        JMenuItem cartItem = new JMenuItem("Carrello");
        JMenuItem homePageItem = new JMenuItem("Home");

        logoutItem.addActionListener(e -> {
            user = null;
            System.out.println("Logout effettuato");
            cardLayout.show(container, "menu");
            // qui puoi cambiare schermata manualmente se vuoi
        });
        profileItem.addActionListener(e -> {
            cardLayout.show(container, "userInfo");
            container.revalidate();
            container.repaint();
        });
        cartItem.addActionListener(e -> {
            cardLayout.show(container, "cart");
            container.revalidate();
            container.repaint();
        });
        homePageItem.addActionListener(e -> {
            cardLayout.show(container, "home");
            container.revalidate();
            container.repaint();
        });

        menu.add(profileItem);
        menu.add(cartItem);
        menu.add(homePageItem);
        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // schermate
        container.add(menuPanel(), "menu");
        container.add(LoginUIPage(), "login");
        container.add(signupUIPage(), "signup");
        container.add(userInfoUI(), "userInfo");
        container.add(CartPageUI(), "cart");
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
        image.setCursor(new Cursor(Cursor.HAND_CURSOR));

        image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("CLICK!");
                cardLayout.show(container, "home");
                container.revalidate();
                container.repaint();
            }
        });

        // USER INFO
        JPanel userInfoPanel = new JPanel(new GridLayout(1, 0, 10, 10));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel iconCart = new JLabel(new ImageIcon("docs/cart.png"));
        iconCart.setPreferredSize(new Dimension(32, 32));
        ImageIcon userIcon = new ImageIcon("docs/user-icon-base.png");
        userIcon.setImage(userIcon.getImage().getScaledInstance(64, 32, Image.SCALE_SMOOTH));

        if (user.getCart() == null) {
            numProduct = 0;
        } else {
            numProduct = user.getCartSize();
        }

        numProductLabel = new JLabel(String.valueOf(numProduct));
        numProductLabel.setFont(new Font("Arial", Font.BOLD, 18));
        numProductLabel.setForeground(Color.BLUE);
        JLabel profileIcon;
        if (user.getIcon() == null) {
            profileIcon = new JLabel(userIcon);
        } else {
            profileIcon = user.getIcon();
        }
        profileIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        profileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(container, "userInfo");
                container.revalidate();
                container.repaint();
            }
        });

        iconCart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        iconCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(container, "cart");
                container.revalidate();
                container.repaint();
            }
        });

        userInfoPanel.add(profileIcon);
        userInfoPanel.add(new JLabel("Benvenuto: " + user.getUsername()));
        userInfoPanel.add(iconCart);
        userInfoPanel.add(numProductLabel);

        topPanel.add(new JLabel(), BorderLayout.WEST);
        topPanel.add(image, BorderLayout.CENTER);
        topPanel.add(userInfoPanel, BorderLayout.EAST);
        topPanel.setBackground(new Color(0, 168, 120));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        homePage.add(topPanel, BorderLayout.NORTH);

        // CATALOGO
        JPanel catalog = new JPanel(new GridLayout(0, 5, 20, 20));

        catalog.setBackground(new Color(165, 63, 43));
        catalog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // magazzino
        warehouse = new Warehouse(loadDB());

        // carico la lista di prodotti
        for (Product p : warehouse.getProducts()) {
            catalog.add(createProductCard(p));
        }

        // SCROLL (barra a destra)
        JScrollPane scrollPane = new JScrollPane(
                catalog,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setBorder(BorderFactory.createTitledBorder("Catalogo"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        homePage.add(scrollPane, BorderLayout.CENTER);

        return homePage;
    }

    // carica il database PRODOTTI da file csv
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

    // crea una card per ogni prodotto
    public JPanel createProductCard(Product product) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 320));
        card.setBorder(BorderFactory.createLineBorder(new Color(56, 119, 128), 4));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("CLICK!");
                cardLayout.show(container, "product");
                container.revalidate();
                container.repaint();
            }
        });
        card.setBackground(Color.WHITE);
        card.setOpaque(true);

        // Caricamento e ridimensionamento immagine
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(250, 190, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("Immagine non trovata");
        }
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title = new JLabel(product.getName());
        title.setFont(new Font("Arial", Font.BOLD, 15));
        setGlobalFont();
        title.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel priceLabel = new JLabel(String.format("%.2f €", product.getPrice()));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton buyButton = new JButton("Add to cart");
        buyButton.setBackground(new Color(240, 255, 206));
        buyButton.setFocusPainted(false);

        buyButton.addActionListener(e -> {
            if (user.getCart() == null) {
                user.setCart(new Cart());
            }
            user.addProduct(product);
            numProductLabel.setText(String.valueOf(user.getCartSize()));
            JOptionPane.showMessageDialog(this, product.getName() + " aggiunto al carrello!");
        });

        JPanel info = new JPanel(new GridLayout(3, 1, 5, 5));
        info.setBackground(new Color(204, 201, 161));
        info.add(title);
        info.add(priceLabel);
        info.add(buyButton);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);
        card.setBackground(new Color(204, 201, 161));

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
