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
import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private List<Product> products;

    public Warehouse(List<String[]> pr) {

        products = new ArrayList<>();
        for(int i = 0; i < pr.size(); i++) {
            String[] strProduct = pr.get(i);
            // nome; prezzo; descrizione; imagePath; id; quantity

            // String name, double price, String description, String imagePath, int id, int quantity
            Product product = new Product(strProduct[0], Double.parseDouble(strProduct[1]), strProduct[2], strProduct[3] , Integer.parseInt(strProduct[4]), Integer.parseInt(strProduct[5]));
            addProduct(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
}
