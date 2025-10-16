/*
 * Autore: Makaoui Youness
 * Data: 16/10/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Dado con memoria dei lanci
 */

import java.util.Random;

class Dado2 {
    int facce;
    String nome;
    int[] memoria;       // array per memorizzare i lanci
    int indice;          // posizione corrente nella memoria
    Random rand = new Random();

    // Costruttore con parametri
    Dado2(int facce, String nome, int capacitaMemoria) {
        this.facce = facce;
        this.nome = nome;
        this.memoria = new int[capacitaMemoria];
        this.indice = 0;
    }

    // Costruttore di default (dado a 6 facce con memoria 20)
    Dado2() {
        this.facce = 6;
        this.nome = "Dado standard";
        this.memoria = new int[20];
        this.indice = 0;
    }

    // Metodo per lanciare il dado e memorizzare il risultato
    int lanciaDado() {
        int risultato = rand.nextInt(facce) + 1;
        if (indice < memoria.length) {
            memoria[indice] = risultato;
            indice++;
        } else {
            System.out.println("⚠️ Memoria piena per " + nome);
        }
        return risultato;
    }

    // Metodo per costruire una stringa con tutti i lanci effettuati
    String storicoLanci() {
        if (indice == 0) {
            return "Nessun lancio effettuato per " + nome;
        }
        String s = "Storico lanci di " + nome + ": ";
        for (int i = 0; i < indice; i++) {
            s += memoria[i];
            if (i < indice - 1) {
                s += ", ";
            }
        }
        return s;
    }

    // Metodo per azzerare la memoria
    void azzeraMemoria() {
        indice = 0;
        System.out.println("✅ Memoria azzerata per " + nome);
    }

    // Metodo per sapere quanti lanci sono stati effettuati
    int numeroLanci() {
        return indice;
    }
}

class Main {
    public static void main(String[] args) {
        // Creo due dadi con memoria
        Dado2 dado1 = new Dado2(6, "Dado Rosso", 10);
        Dado2 dado2 = new Dado2(12, "Dado Blu", 15);

        // Lancio ciascun dado 5 volte
        System.out.println("Lanciando il " + dado1.nome + ":");
        for (int i = 0; i < 5; i++) {
            System.out.println("Lancio " + (i + 1) + ": " + dado1.lanciaDado());
        }

        System.out.println("\nLanciando il " + dado2.nome + ":");
        for (int i = 0; i < 5; i++) {
            System.out.println("Lancio " + (i + 1) + ": " + dado2.lanciaDado());
        }

        // Mostro storico e numero lanci
        System.out.println("\n" + dado1.storicoLanci());
        System.out.println("Numero lanci " + dado1.nome + ": " + dado1.numeroLanci());

        System.out.println("\n" + dado2.storicoLanci());
        System.out.println("Numero lanci " + dado2.nome + ": " + dado2.numeroLanci());

        // Azzero la memoria e controllo
        System.out.println("\n--- Reset memoria ---");
        dado1.azzeraMemoria();
        System.out.println(dado1.storicoLanci());
        System.out.println("Numero lanci " + dado1.nome + ": " + dado1.numeroLanci());
    }
}
