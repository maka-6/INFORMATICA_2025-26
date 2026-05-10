/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 19/04/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */

import java.util.Collection;
import java.util.LinkedHashMap;

public class Cart {

    // uso una HashMap per memorizzare la quantita di prodotti presi in carrello
    private LinkedHashMap<String, Product> products;

    public Cart() {
        products = new LinkedHashMap<>();
    }

    public void addProduct(Product product) {
        String key = product.getName();

        if (products.containsKey(key)) {
            products.get(key).increaseQuantity();
        } else {
            product.setQuantity(1);
            products.put(key, product);
        }
    }


    public void addProduct(Product product, int quantity) {
        String key = product.getName();

        if (products.containsKey(key)) {
            Product existingProduct = products.get(key);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            product.setQuantity(quantity);
            products.put(key, product);
        }
    }

    public void removeProduct(Product product) {
        String key = product.getName();

        if (!products.containsKey(key)) {
            return;
        }

        Product existingProduct = products.get(key);
        existingProduct.decreaseQuantity();

        if (existingProduct.getQuantity() <= 0) {
            products.remove(key);
        }
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public int getCartSize() {
        int total = 0;

        for (Product product : products.values()) {
            total += product.getQuantity();
        }

        return total;
    }

    public double getTotalPrice() {
        double total = 0;

        for (Product product : products.values()) {
            total += product.getPrice() * product.getQuantity();
        }

        return total;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
