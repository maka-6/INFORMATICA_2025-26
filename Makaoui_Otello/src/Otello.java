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
    private Prenotazione[][] reservations = new Prenotazione[730][140];
    private final String name;

    public Otello( String name ) {
        this.name = name;
    }

    // TODO: completare il metodo stampaPrenotazioni
    public void stampaPrenotazioni() {
        System.out.println("Prenotazioni del " + name);
        for (int i = 0; i < reservations.length; i++) {
            for (int j = 0; j < reservations[i].length; j++) {
                if (reservations[i][j] != null) {
                    System.out.println(reservations[i][j]);
                }
            }
        }
    }

    /*
    public Prenotazione[] getReservations() {
        return reservations;
    }
    */

    // salva e controlla le stanze prenotate
    public boolean bookRoom ( Prenotazione reservation ) {


        Data date = reservation.getData();

        int dayIndex = date.getGiornoAnno() - 1;
        int roomIndex = reservation.getRoomNumber() - 1;

        if (reservations[dayIndex][roomIndex] == null) {
            //System.out.println("Stanza " + reservation.getRoomNumber() + " prenotata per il " + date.getDay() + " " + date.getMonth() + " " + date.getYear());
            reservations[dayIndex][roomIndex] = reservation;
            return true;
        }

        return false;
    }

    public Prenotazione[][] getReservations() {
        return reservations;
    }
}