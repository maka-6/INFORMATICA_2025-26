/*
* Autore: Makaoui Youness
* Data: 28/09/2025
* Classe: 4G
* Luogo:
* Descrizione: Data
*/


public class Makaoui_Data {

    int day, month, year;

    // costruttore
    Makaoui_Data(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // comparazione di due interi
    int compareNum ( int num1, int num2 ) {
        if (num1 > num2)
            return 1;
        else if (num1 == num2)
            return 0;

        return 2;
    }

    // compara la data del utente con una secondaria
    int compareDate ( Makaoui_Data date2 ) {

        // controllo dell'anno
        if (compareNum( year, date2.year ) == 1) {
            System.out.println("La data: " + toString() + " Viene dopo: " + date2.toString());
            return 1;
        } else if (compareNum( year, date2.year ) == 2) {
            System.out.println("La data: " + toString() + " Viene prima di: " + date2.toString());
            return 2;
        }

        // controllo del mese
        if (compareNum( month, date2.month ) == 1) {
            System.out.println("La data: " + toString() + " Viene dopo: " + date2.toString());
            return 1;
        } else if (compareNum( month, date2.month ) == 2) {
            System.out.println("La data: " + toString() + " Viene prima di: " + date2.toString());
            return 2;
        }

        // controllo del giorno
        if ( compareNum( day, date2.day ) == 1 ) {
            System.out.println("La data: " + toString() + " Viene dopo: " + date2.toString());
            return 1;
        } else if ( compareNum( day, date2.day ) == 2) {
            System.out.println("La data: " + toString() + " Viene prima di: " + date2.toString());
            return 2;
        }

        // le date sono identiche
        System.out.println("Le date sono identiche");
        return 0;
    }


    @Override
    public String toString () {
        return day + "/" + month + "/" + year;
    }

}