/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 25/02/2026
 * Luogo: xx
 * Versione: 1.0
 * Descrizione: Esercitazione sui file CSV, Classe Data
 */

public class Data {

    private int day, month, year;
    public Data(Data data) {
        this.day = data.day;
        this.month = data.month;
        this.year = data.year;
    }

    public Data(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
