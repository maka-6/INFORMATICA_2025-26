/*
 * Autore: Makaoui Youness
 * Data: 30/10/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Dado con memoria dei lanci
 */

import java.util.Random;

public class Bussolotto {

    private Dado[] dado;

    public Bussolotto(int numeroDadi) {
        dado = new Dado[numeroDadi];
        for (int i = 0; i < numeroDadi; i++) {
            dado[i] = new Dado();
        }
    }

    public int lanciaDado(int numeroDado) {
        return dado[numeroDado - 1].lanciaDado();
    }

    public static void main(String[] args) {
        Bussolotto b = new Bussolotto(2);

        System.out.println("Lancio Dado 1 = " + b.lanciaDado(1));
        System.out.println("Lancio Dado 2 = " + b.lanciaDado(2));

        System.out.println(b.dado[0].storicoLanci());
        System.out.println(b.dado[1].storicoLanci());
    }
}


class Dado {

    int facce;
    String nome;
    Random rand = new Random();

    int[] memoria;  // array per salvare i lanci
    int indice;     // quanti lanci salvati

    // Costruttore con parametri
    Dado(int facce, String nome) {
        this.facce = facce;
        this.nome = nome;
        this.memoria = new int[20];
        this.indice = 0;
    }

    // Costruttore di default
    Dado() {
        this(6, "Dado standard");
    }

    // Metodo per lanciare il dado e memorizzare
    int lanciaDado() {
        int numero = rand.nextInt(facce) + 1;
        if (indice < memoria.length) {
            memoria[indice] = numero;
            indice++;
        }
        return numero;
    }

    // Storico dei lanci
    String storicoLanci() {
        if (indice == 0) {
            return "Nessun lancio effettuato per " + nome;
        }
        String s = "Storico lanci di " + nome + ": ";
        for (int i = 0; i < indice; i++) {
            s += memoria[i];
            if (i < indice - 1) s += ", ";
        }
        return s;
    }

    // Reset
    void azzeraMemoria() {
        indice = 0;
        System.out.println("✅ Memoria azzerata per " + nome);
    }

    int numeroLanci() {
        return indice;
    }
}
