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


    // restituisce il giorno dell'anno da 1-365
    public int getGiornoAnno() {
        int giorno = 1;

        if ( year.equals("2027") ) {
            giorno = 366;
        }
        int numberMonth = 0;
        switch ( month ) {
            case "gennaio":
                numberMonth = 1;
                break;
            case "febbraio":
                numberMonth = 2;
                break;
            case "marzo":
                numberMonth = 3;
                break;
            case "aprile":
                numberMonth = 4;
                break;
            case "maggio":
                numberMonth = 5;
                break;
            case "giugno":
                numberMonth = 6;
                break;
            case "luglio":
                numberMonth = 7;
                break;
            case "agosto":
                numberMonth = 8;
                break;
            case "settembre":
                numberMonth = 9;
                break;
            case "ottobre":
                numberMonth = 10;
                break;
            case "novembre":
                numberMonth = 11;
                break;
            case "dicembre":
                numberMonth = 12;
                break;
        }

        for (int i = 0; i < numberMonth; i++) {
            for (int j = 0; j < Integer.parseInt(day); j++) {
                giorno++;
            }
        }

        return giorno;
    }

    @Override
    public String toString(){
        return day + "/" + month + "/" + year;
    }

    public String getDay() {
        return day;
    }
    public String getMonth() {
        return month;
    }
    public String getYear() {
        return year;
    }
}