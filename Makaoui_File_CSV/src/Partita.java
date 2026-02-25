/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 25/02/2026
 * Luogo: xx
 * Versione: 1.0
 * Descrizione: Esercitazione sui file CSV, Classe Partita
 */


public class Partita {

    Data date;
    private Squadra homeTeam, guestTeam;
    private int goalHome, goalGuest;

    public Partita(Data date, Squadra homeTeam, Squadra guestTeam, int goalHome, int goalGuest) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.goalHome = goalHome;
        this.goalGuest = goalGuest;
    }

    public Data getDate() {
        return date;
    }
    public Squadra getHomeTeam() {
        return homeTeam;
    }
    public Squadra getGuestTeam() {
        return guestTeam;
    }
    public int getGoalHome() {
        return goalHome;
    }
    public int getGoalGuest() {
        return goalGuest;
    }

    @Override
    public String toString() {
        return  homeTeam.getName() + " [" + goalHome + ":" + goalGuest + "] " + guestTeam.getName() + " Date: " + date;
    }
}
