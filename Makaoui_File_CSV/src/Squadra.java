/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 25/02/2026
 * Luogo: xx
 * Versione: 1.0
 * Descrizione: Esercitazione sui file CSV, Classe Squadra
 */

public class Squadra {

    private String name;
    private int points;

    public Squadra(String name) {
        this.name = name;
        points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + " Points: " + points;
    }
}
