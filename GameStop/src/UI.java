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

    // bottoni principali del menu iniziale
    private JButton loginButton = new JButton("Login");
    private JButton signupButton = new JButton("Sing Up");

    // pannelli e dati principali dell'app
    private JPanel homePage = new JPanel();
    private Warehouse warehouse;
    private User user;

    // numero prodotti nel carrello mostrato vicino all'icona
    private int numProduct;
    private JLabel numProductLabel;

    // gestione delle pagine con CardLayout
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    public UI() {
        super("GameStop");

        // impostazioni base della finestra
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // applico il font globale prima di creare le schermate
        setGlobalFont();

        // creo la barra menu in alto
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem profileItem = new JMenuItem("Profilo");
        JMenuItem cartItem = new JMenuItem("Carrello");
        JMenuItem homePageItem = new JMenuItem("Home");

        // logout: tolgo l'utente e torno al menu iniziale
        logoutItem.addActionListener(e -> {
            user = null;
            System.out.println("Logout effettuato");
            cardLayout.show(container, "menu");
        });

        // apre la pagina profilo
        profileItem.addActionListener(e -> {
            cardLayout.show(container, "userInfo");
            container.revalidate();
            container.repaint();
        });

        // apre la pagina carrello
        cartItem.addActionListener(e -> {
            cardLayout.show(container, "cart");
            container.revalidate();
            container.repaint();
        });

        // torna alla home
        homePageItem.addActionListener(e -> {
            cardLayout.show(container, "home");
            container.revalidate();
            container.repaint();
        });

        // aggiungo le voci al menu
        menu.add(profileItem);
        menu.add(cartItem);
        menu.add(homePageItem);
        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // aggiungo tutte le schermate al container
        container.add(menuPanel(), "menu");
        container.add(loginUIPage(), "login");
        container.add(signupUIPage(), "signup");
        container.add(userInfoUI(), "userInfo");
        container.add(cartPageUI(), "cart");

        // creo la home e la aggiungo
        homePage = homePageUI();
        container.add(homePage, "home");

        // schermata iniziale
        cardLayout.show(container, "menu");

        // bottone registrazione
        signupButton.addActionListener(e -> {
            cardLayout.show(container, "signup");
        });

        // bottone login
        loginButton.addActionListener(e -> {
            cardLayout.show(container, "login");
        });

        add(container, BorderLayout.CENTER);
        setVisible(true);
    }

    private void setGlobalFont() {
        try {
            // carico il font personalizzato
            Font customFont = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("assets/fonts/Nunito/static/Nunito-Regular.ttf")
            ).deriveFont(19f);

            // registro il font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            // applico il font a tutti i componenti Swing
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(68, 79, 121));

        // logo in alto
        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_SMOOTH));

        JLabel image = new JLabel(icon);
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));

        panel.add(image, BorderLayout.NORTH);

        // pannello dei bottoni al centro
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupButton.setMaximumSize(new Dimension(220, 42));
        loginButton.setMaximumSize(new Dimension(220, 42));

        // stile bottone signup
        styleMenuButton(signupButton);
        buttonPanel.add(signupButton);

        // spazio tra i bottoni
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 15)));

        // stile bottone login
        styleMenuButton(loginButton);
        buttonPanel.add(loginButton);

        // centro i bottoni nella schermata
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(buttonPanel);

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private void styleMenuButton(JButton button) {
        // stile comune dei bottoni del menu
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(68, 79, 121));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setPreferredSize(new Dimension(220, 45));
        button.setMaximumSize(new Dimension(220, 45));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(56, 119, 128), 2),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));
    }

    public JPanel loginUIPage() {

        // pannello esterno per centrare il login
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(new Color(68, 79, 121));

        // pannello con i campi del login
        JPanel loginPage = new JPanel(new GridLayout(8, 1, 5, 10));
        loginPage.setBackground(new Color(68, 79, 121));

        // logo
        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        // campi email e password
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        emailField.setPreferredSize(new Dimension(80, 25));
        passwordField.setPreferredSize(new Dimension(80, 25));

        // area feedback per mostrare errori di login
        JTextArea feedback = new JTextArea();
        feedback.setPreferredSize(new Dimension(80, 25));
        feedback.setEditable(false);
        feedback.setBorder(null);
        feedback.setLineWrap(true);
        feedback.setWrapStyleWord(true);
        feedback.setBackground(new Color(68, 79, 121));
        feedback.setForeground(new Color(97, 0, 0));

        // aggiungo componenti alla pagina login
        loginPage.add(image);
        loginPage.add(emailLabel);
        loginPage.add(emailField);
        loginPage.add(passwordLabel);
        loginPage.add(passwordField);
        loginPage.add(feedback);
        feedback.setBorder(null);

        JButton back = new JButton("Back");
        JButton login = new JButton("Login");

        // spazio vuoto prima dei bottoni
        loginPage.add(new JLabel());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(68, 79, 121));
        buttonPanel.add(back);
        buttonPanel.add(login);
        loginPage.add(buttonPanel);

        // torno al menu e pulisco i campi
        back.addActionListener(e -> {
            cardLayout.show(container, "menu");
            emailField.setText("");
            passwordField.setText("");
            feedback.setText("");
        });

        // controllo login
        login.addActionListener(e -> {
            Login log = new Login(emailField.getText(), passwordField.getText());
            User logged = log.loginVerify();

            // se il login va bene salvo l'utente e carico i suoi dati
            if (logged != null) {
                user = logged;

                // carico carrello e indirizzi salvati dal file
                user.loadCart();
                user.loadCheckoutInfos();

                // ricreo la home con i dati aggiornati dell'utente
                container.add(homePageUI(), "home");
                cardLayout.show(container, "home");

                // pulisco i campi dopo il login
                emailField.setText("");
                passwordField.setText("");
            } else {
                // messaggio in caso di login sbagliato
                feedback.setText("Email o password errati");
                feedback.setForeground(Color.RED);
            }
        });

        outer.add(loginPage);
        return outer;
    }

    public JPanel signupUIPage() {
        // pannello esterno per centrare la registrazione
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(new Color(68, 79, 121));

        // pannello con i campi registrazione
        JPanel registerPage = new JPanel(new GridLayout(9, 1, 10, 10));
        registerPage.setBackground(new Color(68, 79, 121));

        // logo
        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(180, 50, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(icon);

        // campi registrazione
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // feedback per errori di registrazione
        JTextArea feedback = new JTextArea();
        feedback.setPreferredSize(new Dimension(80, 25));
        feedback.setEditable(false);
        feedback.setBorder(null);
        feedback.setLineWrap(true);
        feedback.setWrapStyleWord(true);
        feedback.setBackground(new Color(68, 79, 121));
        feedback.setForeground(new Color(97, 0, 0));

        // aggiungo componenti alla pagina registrazione
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
        buttonPanel.setBackground(new Color(68, 79, 121));
        buttonPanel.add(back);
        buttonPanel.add(register);
        registerPage.add(buttonPanel);

        // torno al menu e pulisco i campi
        back.addActionListener(e -> {
            cardLayout.show(container, "menu");
            usernameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            feedback.setText("");
        });

        // controllo registrazione
        register.addActionListener(e -> {
            SingUp sing = new SingUp(usernameField.getText(), emailField.getText(), passwordField.getText());
            User logged = sing.singUpVerify();

            // se la registrazione va bene salvo l'utente e vado alla home
            if (logged != null) {
                user = logged;
                user.loadCart();

                // ricreo la home con l'utente appena registrato
                container.add(homePageUI(), "home");
                cardLayout.show(container, "home");

                // pulisco i campi dopo la registrazione
                usernameField.setText("");
                emailField.setText("");
                passwordField.setText("");
            } else {
                // controllo i casi di errore più comuni
                if (usernameField.getText().equals("") || emailField.getText().equals("") || passwordField.getText().equals("")) {
                    feedback.setText("Compila tutti i campi");
                    feedback.setForeground(Color.RED);
                } else if (passwordField.getText().length() < 8) {
                    feedback.setText("La password debole, minimo 8 caratteri");
                    feedback.setForeground(Color.RED);
                } else if (emailField.getText().contains("@") && emailField.getText().contains(".")) {
                    feedback.setText("Email " + emailField.getText() + " e gia registrata");
                    feedback.setForeground(Color.RED);
                } else {
                    feedback.setText("Email non valida");
                    feedback.setForeground(Color.RED);
                }
            }
        });

        outer.add(registerPage);
        return outer;
    }

    public JPanel homePageUI() {

        JPanel homePage = new JPanel(new BorderLayout());

        // top panel con logo e info utente
        JPanel topPanel = new JPanel(new BorderLayout());

        // logo centrale
        ImageIcon icon = new ImageIcon("docs/logo.png");
        icon.setImage(icon.getImage().getScaledInstance(220, 80, Image.SCALE_SMOOTH));
        JLabel image = new JLabel(icon);
        image.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // cliccando sul logo torno alla home
        image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("CLICK!");
                cardLayout.show(container, "home");
                container.revalidate();
                container.repaint();
            }
        });

        // pannello a destra con profilo, nome utente e carrello
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // icona carrello
        ImageIcon cartIcon = new ImageIcon("docs/cart.png");
        cartIcon.setImage(cartIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        JLabel iconCart = new JLabel(cartIcon);

        // icona utente di default
        ImageIcon userIcon = new ImageIcon("docs/user-icon-base.png");
        userIcon.setImage(userIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        // se non c'e' utente o carrello, il numero prodotti e' 0
        if (user == null || user.getCart() == null) {
            numProduct = 0;
        } else {
            numProduct = user.getCartSize();
        }

        // label numero prodotti nel carrello
        numProductLabel = new JLabel(String.valueOf(numProduct));
        numProductLabel.setFont(new Font("Arial", Font.BOLD, 18));
        numProductLabel.setForeground(new Color(244, 152, 130));

        JLabel profileIcon;

        // se non c'e' utente uso icona base, altrimenti icona dell'utente
        if (user == null || user.getIcon() == null) {
            profileIcon = new JLabel(userIcon);
        } else {
            profileIcon = user.getIcon();
        }

        profileIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // clic sull'icona profilo
        profileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // se non sono loggato vado al login
                if (user == null) {
                    cardLayout.show(container, "login");
                } else {
                    // altrimenti ricreo e apro la pagina profilo aggiornata
                    container.add(userInfoUI(), "userInfo");
                    cardLayout.show(container, "userInfo");
                }

                container.revalidate();
                container.repaint();
            }
        });

        iconCart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // clic sull'icona carrello
        iconCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // se non sono loggato vado al login
                if (user == null) {
                    cardLayout.show(container, "login");
                } else {
                    // altrimenti ricreo e apro il carrello aggiornato
                    container.add(cartPageUI(), "cart");
                    cardLayout.show(container, "cart");
                }

                container.revalidate();
                container.repaint();
            }
        });

        // aggiungo icona profilo e testo benvenuto
        userInfoPanel.add(profileIcon);

        JLabel welcomeLabel = new JLabel(user != null ? "Benvenuto: " + user.getUsername() : "Non loggato");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);

        userInfoPanel.add(welcomeLabel);
        userInfoPanel.add(iconCart);
        userInfoPanel.setBackground(new Color(68, 79, 121));
        userInfoPanel.add(numProductLabel);

        // costruisco il top panel
        topPanel.add(new JLabel(), BorderLayout.WEST);
        topPanel.add(image, BorderLayout.CENTER);
        topPanel.add(userInfoPanel, BorderLayout.EAST);
        topPanel.setBackground(new Color(68, 79, 121));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        homePage.add(topPanel, BorderLayout.NORTH);

        // pannello catalogo prodotti
        JPanel catalog = new JPanel(new GridLayout(0, 5, 20, 20));

        catalog.setBackground(new Color(165, 63, 43));
        catalog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // carico il magazzino dal database
        warehouse = new Warehouse(loadDB());

        // creo una card per ogni prodotto
        for (Product p : warehouse.getProducts()) {
            catalog.add(createHomeProductCard(p));
        }

        // scroll del catalogo
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

    // carica il database prodotti da file csv
    public List<String[]> loadDB() {
        String filePath = "data/DataBase.csv";
        List<String[]> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // formato: nome; prezzo; descrizione; imagePath; id; quantity
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                // aggiungo la riga letta alla lista prodotti
                products.add(parts);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    // crea una card per ogni prodotto della home
    public JPanel createHomeProductCard(Product product) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 320));
        card.setBorder(BorderFactory.createLineBorder(new Color(41, 44, 44), 1));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // clic sulla card prodotto
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("CLICK!");
                container.add(productPageUI(product), "product");
                cardLayout.show(container, "product");
                container.revalidate();
                container.repaint();
            }
        });

        card.setBackground(Color.WHITE);
        card.setOpaque(true);

        // immagine del prodotto
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(250, 190, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("Immagine non trovata");
        }
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // nome prodotto
        JLabel title = new JLabel(product.getName());
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(68, 79, 121));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // prezzo prodotto
        JLabel priceLabel = new JLabel(String.format("%.2f €", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel.setForeground(new Color(211, 84, 55));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // bottone per aggiungere al carrello
        JButton buyButton = new JButton("Add to cart");
        buyButton.setBackground(new Color(0, 183, 251));
        buyButton.setFocusPainted(false);

        // aggiungo il prodotto al carrello e aggiorno il numero prodotti
        buyButton.addActionListener(e -> {
            if (user.getCart() == null) {
                user.setCart(new Cart());
            }
            user.addProduct(product);
            numProductLabel.setText(String.valueOf(user.getCartSize()));
            JOptionPane.showMessageDialog(this, product.getName() + " aggiunto al carrello!");
        });

        // pannello inferiore della card con nome, prezzo e bottone
        JPanel info = new JPanel(new GridLayout(3, 1, 5, 5));
        info.setBackground(new Color(255, 255, 255));
        info.add(title);
        info.add(priceLabel);
        info.add(buyButton);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);
        card.setBackground(new Color(255, 255, 255));

        // imposto il font globale
        setGlobalFont();

        return card;
    }

    // pagina profilo utente
    public JPanel userInfoUI() {
        JPanel userInfo = new JPanel(new BorderLayout());
        userInfo.setBackground(new Color(245, 247, 250));

        // header della pagina profilo
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(68, 79, 121));
        topPanel.setBorder(BorderFactory.createEmptyBorder(22, 35, 22, 35));

        JLabel title = new JLabel("Il tuo profilo");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));

        // bottone per tornare alla home
        JButton homeButtonTop = new JButton("Home");
        homeButtonTop.setFocusPainted(false);
        homeButtonTop.setBackground(new Color(0, 183, 251));
        homeButtonTop.setForeground(Color.WHITE);
        homeButtonTop.setFont(new Font("Arial", Font.BOLD, 14));
        homeButtonTop.setCursor(new Cursor(Cursor.HAND_CURSOR));

        homeButtonTop.addActionListener(e -> {
            cardLayout.show(container, "home");
            container.revalidate();
            container.repaint();
        });

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(homeButtonTop, BorderLayout.EAST);
        userInfo.add(topPanel, BorderLayout.NORTH);

        // pannello centrale
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 247, 250));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        // card bianca del profilo
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(41, 44, 44), 1),
                BorderFactory.createEmptyBorder(35, 55, 35, 55)
        ));

        // se non c'e' nessun utente loggato mostro messaggio semplice
        if (user == null) {
            JLabel message = new JLabel("Nessun utente loggato");
            message.setFont(new Font("Arial", Font.BOLD, 26));
            message.setForeground(new Color(68, 79, 121));
            message.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel subtitle = new JLabel("Accedi per visualizzare il tuo profilo");
            subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
            subtitle.setForeground(new Color(95, 95, 95));
            subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton loginButtonProfile = new JButton("Vai al login");
            loginButtonProfile.setFocusPainted(false);
            loginButtonProfile.setBackground(new Color(0, 183, 251));
            loginButtonProfile.setForeground(Color.WHITE);
            loginButtonProfile.setFont(new Font("Arial", Font.BOLD, 15));
            loginButtonProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
            loginButtonProfile.setAlignmentX(Component.CENTER_ALIGNMENT);

            // mando l'utente alla pagina login
            loginButtonProfile.addActionListener(e -> {
                cardLayout.show(container, "login");
                container.revalidate();
                container.repaint();
            });

            card.add(message);
            card.add(Box.createRigidArea(new Dimension(0, 8)));
            card.add(subtitle);
            card.add(Box.createRigidArea(new Dimension(0, 25)));
            card.add(loginButtonProfile);

            centerPanel.add(card);
            userInfo.add(centerPanel, BorderLayout.CENTER);
            return userInfo;
        }

        JLabel profileIcon;

        // icona utente: se non c'e' uso quella base
        if (user.getIcon() == null) {
            ImageIcon icon = new ImageIcon("docs/user-icon-base.png");
            icon.setImage(icon.getImage().getScaledInstance(260, 260, Image.SCALE_SMOOTH));
            profileIcon = new JLabel(icon);
        } else {
            profileIcon = user.getIcon();
        }

        profileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // nome utente grande
        JLabel usernamePreview = new JLabel(user.getUsername());
        usernamePreview.setFont(new Font("Arial", Font.BOLD, 28));
        usernamePreview.setForeground(new Color(68, 79, 121));
        usernamePreview.setAlignmentX(Component.CENTER_ALIGNMENT);

        // email utente
        JLabel emailPreview = new JLabel(user.getEmail());
        emailPreview.setFont(new Font("Arial", Font.PLAIN, 16));
        emailPreview.setForeground(new Color(95, 95, 95));
        emailPreview.setAlignmentX(Component.CENTER_ALIGNMENT);

        // separatore grafico
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(390, 1));
        separator.setForeground(new Color(220, 225, 235));

        // box con riepilogo informazioni
        JPanel infoBox = new JPanel(new GridLayout(3, 1, 0, 12));
        infoBox.setBackground(Color.WHITE);
        infoBox.setMaximumSize(new Dimension(390, 150));

        JLabel usernameInfo = new JLabel("Username: " + user.getUsername());
        JLabel emailInfo = new JLabel("Email: " + user.getEmail());
        JLabel cartInfo = new JLabel("Prodotti nel carrello: " + (user.getCart() != null ? user.getCartSize() : 0));

        usernameInfo.setFont(new Font("Arial", Font.BOLD, 16));
        emailInfo.setFont(new Font("Arial", Font.BOLD, 16));
        cartInfo.setFont(new Font("Arial", Font.BOLD, 16));

        usernameInfo.setForeground(new Color(45, 55, 75));
        emailInfo.setForeground(new Color(45, 55, 75));
        cartInfo.setForeground(new Color(211, 84, 55));

        infoBox.add(usernameInfo);
        infoBox.add(emailInfo);
        infoBox.add(cartInfo);

        // bottone per tornare alla home
        JButton homeButton = new JButton("Torna alla Home");
        homeButton.setFocusPainted(false);
        homeButton.setBackground(new Color(0, 183, 251));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font("Arial", Font.BOLD, 15));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeButton.addActionListener(e -> {
            cardLayout.show(container, "home");
            container.revalidate();
            container.repaint();
        });

        // costruisco la card profilo
        card.add(profileIcon);
        card.add(Box.createRigidArea(new Dimension(0, 16)));
        card.add(usernamePreview);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(emailPreview);
        card.add(Box.createRigidArea(new Dimension(0, 25)));
        card.add(separator);
        card.add(Box.createRigidArea(new Dimension(0, 25)));
        card.add(infoBox);
        card.add(Box.createRigidArea(new Dimension(0, 28)));
        card.add(homeButton);

        setGlobalFont();

        centerPanel.add(card);
        userInfo.add(centerPanel, BorderLayout.CENTER);

        return userInfo;
    }

    public JPanel productPageUI(Product product) {
        JPanel productPage = new JPanel(new BorderLayout());
        productPage.setBackground(new Color(245, 247, 250));

        // header
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(68, 79, 121));
        topPanel.setBorder(BorderFactory.createEmptyBorder(22, 35, 22, 35));

        JLabel title = new JLabel(product.getName());
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JButton homeButton = new JButton("Home");
        homeButton.setFocusPainted(false);
        homeButton.setBackground(new Color(0, 183, 251));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        homeButton.addActionListener(e -> {
            cardLayout.show(container, "home");
            container.revalidate();
            container.repaint();
        });

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(homeButton, BorderLayout.EAST);
        productPage.add(topPanel, BorderLayout.NORTH);

        // contenuto centrale
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 247, 250));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        JPanel card = new JPanel(new BorderLayout(30, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(41, 44, 44), 1),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        // immagine prodotto
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(420, 320));

        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(400, 280, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("Immagine non trovata");
            imageLabel.setForeground(Color.GRAY);
        }

        // info prodotto
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setForeground(new Color(68, 79, 121));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setForeground(new Color(70, 70, 70));
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(null);
        descriptionArea.setMaximumSize(new Dimension(430, 120));
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel(String.format("%.2f €", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 26));
        priceLabel.setForeground(new Color(211, 84, 55));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel quantityLabel = new JLabel("Disponibili: " + product.getQuantity());
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 15));
        quantityLabel.setForeground(new Color(95, 95, 95));
        quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton addButton = new JButton("Add to cart");
        addButton.setFocusPainted(false);
        addButton.setBackground(new Color(0, 183, 251));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 15));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        addButton.addActionListener(e -> {
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Devi effettuare il login per aggiungere prodotti al carrello");
                cardLayout.show(container, "login");
                return;
            }

            if (user.getCart() == null) {
                user.setCart(new Cart());
            }

            user.addProduct(product);

            if (numProductLabel != null) {
                numProductLabel.setText(String.valueOf(user.getCartSize()));
            }

            JOptionPane.showMessageDialog(this, product.getName() + " aggiunto al carrello!");
        });

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        infoPanel.add(descriptionArea);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        infoPanel.add(quantityLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        infoPanel.add(addButton);

        card.add(imageLabel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);

        centerPanel.add(card);
        productPage.add(centerPanel, BorderLayout.CENTER);

        return productPage;
    }


    public JPanel cartPageUI() {
        JPanel cartPage = new JPanel(new BorderLayout());
        cartPage.setBackground(new Color(245, 247, 250));

        // header della pagina carrello
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(68, 79, 121));
        topPanel.setBorder(BorderFactory.createEmptyBorder(22, 35, 22, 35));

        JLabel title = new JLabel("Il tuo carrello");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));

        // bottone home
        JButton homeButtonTop = new JButton("Home");
        homeButtonTop.setFocusPainted(false);
        homeButtonTop.setBackground(new Color(0, 183, 251));
        homeButtonTop.setForeground(Color.WHITE);
        homeButtonTop.setFont(new Font("Arial", Font.BOLD, 14));
        homeButtonTop.setCursor(new Cursor(Cursor.HAND_CURSOR));

        homeButtonTop.addActionListener(e -> {
            cardLayout.show(container, "home");
            container.revalidate();
            container.repaint();
        });

        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(homeButtonTop, BorderLayout.EAST);
        cartPage.add(topPanel, BorderLayout.NORTH);

        // se non c'e' utente o il carrello e' vuoto mostro schermata vuota
        if (user == null || user.getCart() == null || user.getCart().getProducts().isEmpty()) {
            JPanel emptyPanel = new JPanel(new GridBagLayout());
            emptyPanel.setBackground(new Color(245, 247, 250));

            JPanel emptyCard = new JPanel();
            emptyCard.setLayout(new BoxLayout(emptyCard, BoxLayout.Y_AXIS));
            emptyCard.setBackground(Color.WHITE);
            emptyCard.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(41, 44, 44), 1),
                    BorderFactory.createEmptyBorder(35, 55, 35, 55)
            ));

            JLabel emptyTitle = new JLabel(user == null ? "Accedi per usare il carrello" : "Carrello vuoto");
            emptyTitle.setFont(new Font("Arial", Font.BOLD, 26));
            emptyTitle.setForeground(new Color(68, 79, 121));
            emptyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel emptyText = new JLabel(user == null ? "Prima fai il login" : "Aggiungi qualche gioco dal catalogo");
            emptyText.setFont(new Font("Arial", Font.PLAIN, 16));
            emptyText.setForeground(new Color(95, 95, 95));
            emptyText.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton actionButton = new JButton(user == null ? "Vai al login" : "Vai al catalogo");
            actionButton.setFocusPainted(false);
            actionButton.setBackground(new Color(0, 183, 251));
            actionButton.setForeground(Color.WHITE);
            actionButton.setFont(new Font("Arial", Font.BOLD, 15));
            actionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // se non sono loggato vado al login, altrimenti torno al catalogo
            actionButton.addActionListener(e -> {
                cardLayout.show(container, user == null ? "login" : "home");
                container.revalidate();
                container.repaint();
            });

            emptyCard.add(emptyTitle);
            emptyCard.add(Box.createRigidArea(new Dimension(0, 8)));
            emptyCard.add(emptyText);
            emptyCard.add(Box.createRigidArea(new Dimension(0, 25)));
            emptyCard.add(actionButton);

            emptyPanel.add(emptyCard);
            cartPage.add(emptyPanel, BorderLayout.CENTER);

            return cartPage;
        }

        // contenitore principale del carrello
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(245, 247, 250));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // pannello con tutte le righe dei prodotti
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setBackground(new Color(245, 247, 250));

        double total = 0;

        // creo una riga per ogni prodotto nel carrello
        for (Product product : user.getCart().getProducts()) {
            JPanel productRow = new JPanel(new BorderLayout(15, 0));
            productRow.setBackground(Color.WHITE);
            productRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            productRow.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(41, 44, 44), 1),
                    BorderFactory.createEmptyBorder(12, 12, 12, 18)
            ));

            // immagine prodotto nel carrello
            JLabel imageLabel = new JLabel();
            imageLabel.setPreferredSize(new Dimension(110, 80));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            try {
                ImageIcon icon = new ImageIcon(product.getImagePath());
                Image img = icon.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                imageLabel.setText("N/D");
                imageLabel.setForeground(Color.GRAY);
            }

            // informazioni prodotto
            JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 4));
            infoPanel.setBackground(Color.WHITE);

            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 19));
            nameLabel.setForeground(new Color(68, 79, 121));

            JLabel quantityLabel = new JLabel("Quantità: " + product.getQuantity());
            quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
            quantityLabel.setForeground(new Color(95, 95, 95));

            JLabel priceLabel = new JLabel(String.format("Prezzo: %.2f €", product.getPrice()));
            priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
            priceLabel.setForeground(new Color(211, 84, 55));

            infoPanel.add(nameLabel);
            infoPanel.add(quantityLabel);
            infoPanel.add(priceLabel);

            // calcolo subtotale del prodotto
            double subtotal = product.getPrice() * product.getQuantity();
            total += subtotal;

            // parte destra della riga con subtotale e bottone rimuovi
            JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 8));
            rightPanel.setBackground(Color.WHITE);

            JLabel subtotalLabel = new JLabel(String.format("%.2f €", subtotal));
            subtotalLabel.setFont(new Font("Arial", Font.BOLD, 18));
            subtotalLabel.setForeground(new Color(68, 79, 121));
            subtotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            JButton removeButton = new JButton("Rimuovi");
            removeButton.setFocusPainted(false);
            removeButton.setBackground(new Color(165, 63, 43));
            removeButton.setForeground(Color.WHITE);
            removeButton.setFont(new Font("Arial", Font.BOLD, 13));
            removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // rimuovo il prodotto e aggiorno la pagina carrello
            removeButton.addActionListener(e -> {
                user.removeProduct(product);

                // aggiorno il numero vicino all'icona del carrello
                if (numProductLabel != null) {
                    numProductLabel.setText(String.valueOf(user.getCartSize()));
                }

                // ricreo il carrello aggiornato
                container.add(cartPageUI(), "cart");
                cardLayout.show(container, "cart");
                container.revalidate();
                container.repaint();
            });

            rightPanel.add(subtotalLabel);
            rightPanel.add(removeButton);

            productRow.add(imageLabel, BorderLayout.WEST);
            productRow.add(infoPanel, BorderLayout.CENTER);
            productRow.add(rightPanel, BorderLayout.EAST);

            productsPanel.add(productRow);
            productsPanel.add(Box.createRigidArea(new Dimension(0, 14)));
        }

        // scroll per la lista prodotti
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(245, 247, 250));
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);

        // pannello in basso con totale e checkout
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(new Color(68, 79, 121));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));

        JLabel totalLabel = new JLabel(String.format("Totale: %.2f €", total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalLabel.setForeground(Color.WHITE);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFocusPainted(false);
        checkoutButton.setBackground(new Color(0, 183, 251));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        checkoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // checkout dell'ordine
        checkoutButton.addActionListener(e -> {
            // prendo gli indirizzi salvati dell'utente
            ArrayList<CheckoutInfo> addresses = user.getCheckoutInfos();

            // se la lista non esiste creo una lista vuota
            if (addresses == null) {
                addresses = new ArrayList<>();
            }

            // tendina con gli indirizzi
            JComboBox<String> addressBox = new JComboBox<>();

            // aggiungo gli indirizzi gia' salvati alla tendina
            for (CheckoutInfo info : addresses) {
                addressBox.addItem(info.getFullName() + " - " + info.getAddress() + ", " + info.getCity());
            }

            // ultima opzione per inserire un nuovo indirizzo
            addressBox.addItem("Aggiungi nuovo indirizzo");

            // finestra per scegliere indirizzo
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    addressBox,
                    "Seleziona indirizzo di spedizione",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            // se l'utente annulla, non faccio nulla
            if (choice != JOptionPane.OK_OPTION) {
                return;
            }

            CheckoutInfo selectedInfo;

            // se ha scelto un indirizzo esistente lo prendo dalla lista
            if (addressBox.getSelectedIndex() < addresses.size()) {
                selectedInfo = addresses.get(addressBox.getSelectedIndex());
            } else {
                // altrimenti creo i campi per un nuovo indirizzo
                JTextField fullNameField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField cityField = new JTextField();
                JTextField zipCodeField = new JTextField();
                JTextField countryField = new JTextField();

                // pannello con i campi indirizzo
                JPanel checkoutPanel = new JPanel(new GridLayout(5, 2, 10, 10));
                checkoutPanel.add(new JLabel("Nome completo:"));
                checkoutPanel.add(fullNameField);
                checkoutPanel.add(new JLabel("Indirizzo:"));
                checkoutPanel.add(addressField);
                checkoutPanel.add(new JLabel("Città:"));
                checkoutPanel.add(cityField);
                checkoutPanel.add(new JLabel("CAP:"));
                checkoutPanel.add(zipCodeField);
                checkoutPanel.add(new JLabel("Paese:"));
                checkoutPanel.add(countryField);

                // finestra per inserire nuovo indirizzo
                int result = JOptionPane.showConfirmDialog(
                        this,
                        checkoutPanel,
                        "Nuovo indirizzo",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // se annulla non completo il checkout
                if (result != JOptionPane.OK_OPTION) {
                    return;
                }

                // creo l'oggetto indirizzo con i dati inseriti
                selectedInfo = new CheckoutInfo(
                        fullNameField.getText().trim(),
                        addressField.getText().trim(),
                        cityField.getText().trim(),
                        zipCodeField.getText().trim(),
                        countryField.getText().trim()
                );

                // controllo che tutti i campi siano compilati
                if (!selectedInfo.isComplete()) {
                    JOptionPane.showMessageDialog(this, "Compila tutti i campi dell'indirizzo");
                    return;
                }

                // salvo il nuovo indirizzo nel file e nella lista utente
                if (!user.saveCheckoutInfo(selectedInfo)) {
                    JOptionPane.showMessageDialog(this, "Errore durante il salvataggio dell'indirizzo");
                    return;
                }
            }

            // messaggio finale ordine completato
            JOptionPane.showMessageDialog(
                    this,
                    "Ordine completato!\nSpedizione a: "
                            + selectedInfo.getFullName() + "\n"
                            + selectedInfo.getAddress() + ", "
                            + selectedInfo.getCity()
            );

            // svuoto il carrello dopo il checkout
            user.checkout();

            // aggiorno numero prodotti nel carrello
            if (numProductLabel != null) {
                numProductLabel.setText(String.valueOf(user.getCartSize()));
            }

            // ricreo la pagina carrello aggiornata
            container.add(cartPageUI(), "cart");
            cardLayout.show(container, "cart");
            container.revalidate();
            container.repaint();
        });

        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(checkoutButton, BorderLayout.EAST);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(totalPanel, BorderLayout.SOUTH);

        cartPage.add(contentPanel, BorderLayout.CENTER);

        return cartPage;
    }

}