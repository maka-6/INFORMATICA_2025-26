/*
 * Autore: Makaoui Youness
 * Data: 13/10/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Dado
 */

import java.util.Random;

class Dado {
    int facce;
    String nome;
    Random rand = new Random();

    // Costruttore con parametri
    Dado(int facce, String nome) {
        this.facce = facce;
        this.nome = nome;
    }

    // Costruttore di default (dado a 6 facce)
    Dado() {
        this.facce = 6;
        this.nome = "Dado standard";
    }

    // Metodo per lanciare il dado
    int lanciaDado() {
        return rand.nextInt(facce) + 1;
    }

    public class Main {
        public static void main(String[] args) {
            // Creo due dadi
            Dado dado1 = new Dado(6, "Dado Rosso");
            Dado dado2 = new Dado(12, "Dado Blu");

            // Lancio ciascun dado 5 volte
            System.out.println("Lanciando il " + dado1.nome + ":");
            for (int i = 0; i < 5; i++) {
                System.out.println("Lancio " + (i + 1) + ": " + dado1.lanciaDado());
            }

            System.out.println("\nLanciando il " + dado2.nome + ":");
            for (int i = 0; i < 5; i++) {
                System.out.println("Lancio " + (i + 1) + ": " + dado2.lanciaDado());
            }
        }
    }
}