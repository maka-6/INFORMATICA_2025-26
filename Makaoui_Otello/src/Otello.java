/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione: Classe Otello che contiene le prenotazioni
 */

public class Otello {

    // private final Prenotazione[] reservations;
    private Prenotazione[][][] reservations = new Prenotazione[2][12][];
    private boolean[][][] bookedRooms = new boolean[2][12][];
    private final String name;

    int currentYear = 2026;

    public Otello( String name ) {
        this.name = name;
    }

    public Otello( String name, Prenotazione[][][] reservations ) {
        this.name = name;
        this.reservations = reservations;
    }

    /*
    public Otello() {
        reservations = new Prenotazione[1];
        reservations[0] = new Prenotazione();
        name = "Retroverse Hotel";
    }
    */

    // TODO: completare il metodo stampaPrenotazioni
    public void stampaPrenotazioni() {
        System.out.println("Prenotazioni del " + name);
    }

    /*
    public Prenotazione[] getReservations() {
        return reservations;
    }
    */

    public Prenotazione[][][] getReservations() {
        return reservations;
    }

    // salva e controlla le stanze prenotate
    public boolean bookRoom ( String day, String month, String year, int roomNumber ) {

        System.out.println("Stanza " + roomNumber + " prenotata per " + day + "/" + month + "/" + year);
        return true;
    }

}