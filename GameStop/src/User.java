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
import java.util.List;
import java.util.ArrayList;

public class User {

    private String username;
    private String email;
    private String password;
    private Cart cart;
    private JLabel icon;
    private ArrayList<CheckoutInfo> checkoutInfos;

    public User(String username, String email, String password, String path) {
        this.username = username;
        this.email = email;
        this.password = password;
        cart = null;

        // ridimensiono icona utente
        ImageIcon userIcon = new ImageIcon(path);
        userIcon.setImage(userIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        icon = new JLabel(userIcon);

        checkoutInfos = new ArrayList<>();

    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public JLabel getIcon() {
        return icon;
    }

    public Cart getCart() {
        return cart;
    }
    public int getCartSize() {
        if (cart == null) {
            return 0;
        }

        return cart.getCartSize();
    }

    public boolean addProduct(Product product) {
        if (cart == null) {
            cart = new Cart();
        }

        cart.addProduct(product);
        return saveCart();
    }

    public boolean removeProduct(Product product) {
        if (cart == null) {
            return false;
        }

        cart.removeProduct(product);
        return saveCart();
    }

    public void loadCart() {
        cart = new Cart();

        try (BufferedReader br = new BufferedReader(new FileReader("data/Carts.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                if (data.length >= 7 && data[0].equals(this.username)) {
                    String productName = data[1];
                    double price = Double.parseDouble(data[2]);
                    int quantity = Integer.parseInt(data[3]);
                    String imagePath = data[4];
                    int id = Integer.parseInt(data[5]);
                    String description = data[6];

                    Product product = new Product(productName, price, description, imagePath, id, quantity);
                    cart.addProduct(product, quantity);
                }
            }

        } catch (Exception e) {
            System.out.println("Errore durante il caricamento del carrello");
        }
    }

    public boolean saveCart() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/Carts.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                if (data.length > 0 && !data[0].equals(this.username)) {
                    lines.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Errore durante la lettura del carrello");
            return false;
        }

        if (cart != null) {
            for (Product product : cart.getProducts()) {
                lines.add(username + ";"
                        + product.getName() + ";"
                        + product.getPrice() + ";"
                        + product.getQuantity() + ";"
                        + product.getImagePath() + ";"
                        + product.getId() + ";"
                        + product.getDescription());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/Carts.csv"))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del carrello");
            return false;
        }

        return true;
    }

    public boolean checkout() {
        cart = new Cart();
        return saveCart();
    }

    public ArrayList<CheckoutInfo> getCheckoutInfos() {
        return checkoutInfos;
    }

    public void setCheckoutInfos(ArrayList<CheckoutInfo> checkoutInfos) {
        this.checkoutInfos = checkoutInfos;
    }

    public void addCheckoutInfo(CheckoutInfo info) {
        checkoutInfos.add(info);
    }

    public void loadCheckoutInfos() {
        checkoutInfos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/CheckoutInfo.csv"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                if (data.length >= 6 && data[0].equals(this.username)) {
                    CheckoutInfo info = new CheckoutInfo(
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5]
                    );

                    checkoutInfos.add(info);
                }
            }

        } catch (IOException e) {
            System.out.println("Nessun indirizzo salvato");
        }
    }



    public boolean saveCheckoutInfo(CheckoutInfo info) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/CheckoutInfo.csv", true))) {
            bw.write(username + ";" + info.toString());
            bw.newLine();

            checkoutInfos.add(info);

        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio dell'indirizzo");
            return false;
        }

        return true;
    }



    @Override
    public String toString() {
        return "Utente: " + "username = " + username + "\n email = " + email + "\n password = " + password;
    }
}
