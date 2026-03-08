/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione: Classe Prenotazione che gestisce le prenotazioni dei clienti
 */

public class Prenotazione {

    private Cliente client;
    private Data date;
    private int code = 0;
    private String name;
    private int roomNumber;

    public Prenotazione( Cliente client,  Data date, String name, int code, int roomNumber ) {
        this.code = code;
        this.name = name;
        this.client = client;
        this.date = date;
        this.roomNumber = roomNumber;
    }

    Data getData() {
        return date;
    }
    public String getName() {
        return name;
    }
    public int getCode() {
        return code;
    }
    public Cliente getClient() {
        return client;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Prenotazione N.: ").append(code).append("\n");
        sb.append("A nome di: ").append(name).append("\n");
        sb.append("Data: ").append(date).append("\n");
        sb.append("Camera: ").append(roomNumber).append("\n");
        sb.append("Cliente: ");
        sb.append(client.getName()).append(" ").append(client.getSurname());

        return sb.toString();
    }
}
