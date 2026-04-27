/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 19/04/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */

import javax.swing.*;

public class Product {

    private String name;
    private double price;
    private String description;
    private String imagePath;

    public Product(String name, double price, String description, String imagePath) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
