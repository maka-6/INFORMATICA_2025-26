/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 25/02/2026
 * Luogo: xx
 * Versione: 1.0
 * Descrizione: Esercitazione sui file CSV, Classe Campionato
 */

import java.io.*;

public class Campionato {

    private Partita[] partite;
    private int count;

    public Campionato(int maxPartite) {
        partite = new Partita[maxPartite];
        count = 0;
    }

    public void addPartita(Partita partita) {
        if (count < partite.length) {
            partite[count++] = partita;
        } else {
            System.out.println("Limite partite raggiunto!");
        }
    }

    public void loadFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                // CSV: squadraCasa,squadraOspite,goalCasa,goalOspite,dd-mm-yyyy
                String[] parti = riga.split(",");
                Squadra team1 = new Squadra(parti[0]);
                Squadra team2 = new Squadra(parti[1]);
                int goal1 = Integer.parseInt(parti[2]);
                int goal2 = Integer.parseInt(parti[3]);

                String[] dataParts = parti[4].split("-");
                int day = Integer.parseInt(dataParts[0]);
                int month = Integer.parseInt(dataParts[1]);
                int year = Integer.parseInt(dataParts[2]);
                Data data = new Data(day, month, year);

                Partita p = new Partita(data, team1, team2, goal1, goal2);
                addPartita(p);
            }
        } catch (IOException e) {
            System.out.println("Errore lettura file: " + e.getMessage());
        }
    }

    public void saveToCSV(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < count; i++) {
                Partita p = partite[i];
                String line = p.getHomeTeam().getName() + "," +
                        p.getGuestTeam().getName() + "," +
                        p.getGoalHome() + "," +
                        p.getGoalGuest() + "," +
                        p.getDate().getDay() + "-" +
                        p.getDate().getMonth() + "-" +
                        p.getDate().getYear();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore scrittura file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(partite[i]).append("\n");
        }
        return sb.toString();
    }
}