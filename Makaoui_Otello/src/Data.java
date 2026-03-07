/*
 * Autore: Makaoui youness
 * Classe: 4G
 * Data: 06/03/2026
 * Versione: 1.0
 * Luogo: Lab 53bis
 * Descrizione: Classe Data che contiene la data di prenotazione
 */

public class Data {

    private final String day, month, year;

    public Data( String day, String month, String year ){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Data() {
        day = "1";
        month = "gennaio";
        year = "2026";
    }


    // metodo non usato
    // restituisce il giorno dell'anno da 1-365
    public int getGiornoAnno( Data data ) {
        int giorno = 1;

        for (int i = 0; i < Integer.parseInt(data.month); i++) {
            for (int j = 0; j < Integer.parseInt(data.day); j++) {
                giorno++;
            }
        }

        return giorno;
    }

    @Override
    public String toString(){
        return day + "/" + month + "/" + year;
    }
}