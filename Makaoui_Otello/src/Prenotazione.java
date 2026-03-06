/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione: Classe Prenotazione che gestisce le prenotazioni dei clienti
 */

public class Prenotazione {

    Cliente client;
    Data date;

    int code;
    String name;

    /*
    public Prenotazione() {
        code = 1;
        name = "Rossi";
        clients = new Cliente[1];
        clients[0] = new Cliente();
    }
    */

    Prenotazione( Cliente client,  Data date, String name, int code ) {
        this.code = code;
        this.name = name;
        this.client = client;
        this.date = date;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Prenotazione N.: ").append(code).append("\n");
        sb.append("A nome di: ").append(name).append("\n");
        sb.append("Clienti: ");
        sb.append(client.getName()).append(" ").append(client.getSurname()).append(", ");

        return sb.toString();
    }
}
