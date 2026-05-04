/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 19/04/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private List<Product> products;

    public Warehouse(List<String[]> pr) {

        products = new ArrayList<>();
        for(int i = 0; i < pr.size(); i++) {
            List<String> strProduct = List.of(pr.get(i));
            Product product = new Product(strProduct.get(0), Double.parseDouble(strProduct.get(1)), "", strProduct.get(2));
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
