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
        Cliente[] clients = new Cliente[10];

        for (int i = 0; i < 10; i++) {
            clients[i] = new Cliente();
            prenotazioni[i] = new Prenotazione( clients[i], new Data(), "Mario", i );
        }

        Otello otello = new Otello( prenotazioni, "Free Ote" );

        new Menu(otello);
    }
}