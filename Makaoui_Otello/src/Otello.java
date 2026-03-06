/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione: Classe Otello che contiene le prenotazioni
 */

import javax.swing.*;

public class Otello {

    private final Prenotazione[] books;
    private final String name;

    public Otello( Prenotazione[] books, String name ) {
        this.books = books;
        this.name = name;
    }

    /*
    public Otello() {
        books = new Prenotazione[1];
        books[0] = new Prenotazione();
        name = "Retroverse Hotel";
    }
    */

    void stampaPrenotazioni() {
        System.out.println("Prenotazioni del " + name);
        for (Prenotazione book : books) {
            System.out.println(book);
        }
    }

}