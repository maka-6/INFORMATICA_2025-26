/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 19/04/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */

public class User {

    private String username;
    private String email;
    private String password;
    private Cart cart;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        cart = null;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public void addProduct(Product product) {
        cart.addProduct(product);
    }
    public void removeProduct(Product product) {
        cart.removeProduct(product);
    }

    public void loadCart() {
    }

    @Override
    public String toString() {
        return "Utente: " + "username = " + username + "\n email = " + email + "\n password = " + password;
    }
}
