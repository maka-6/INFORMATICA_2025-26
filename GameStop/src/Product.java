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
    private int id;

    public Product(String name, double price, String description, String imagePath, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getImagePath() {
        return imagePath;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString(){
        return  name + ";" + price + ";" + description;
    }
}
