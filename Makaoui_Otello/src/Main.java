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


        Prenotazione[] prenotazioni = new Prenotazione[10];
        for (int i = 0; i < 10; i++) {
            prenotazioni[i] = new Prenotazione();
        }
        Otello otello = new Otello( prenotazioni, "Free Ote" );
        otello.stampaPrenotazioni();
        new Menu(otello);
    }
}