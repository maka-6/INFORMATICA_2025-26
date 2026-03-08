/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione:
 */

public class Main {
    public static void main(String[] args) {

        Cliente[] clients = new Cliente[10];

        for (int i = 0; i < 10; i++) {
            clients[i] = new Cliente();
        }

        Otello otello = new Otello("Free Ote");
        otello.stampaPrenotazioni();
        new Menu(otello);
    }
}