/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 18/02/2026
 * Luogo: Home
 * Versione: 1.0
 * Descrizione: Esercitazione sui segmenti
 */

import java.util.Scanner;

public class Segmento {
    private Punto p1, p2;
    private String name;

    Segmento(Punto p1, Punto p2, String name){
        this.p1 = p1;
        this.p2 = p2;
        this.name = name;
    }

    Segmento(){
        // segmento di punti a e b nel origine
        p1 = new Punto("Punto A");
        p2 = new Punto("Punto B");
        name = "Segmento semplice";
    }

    // distanza dal punto medio del segmento all'origine
    // non ho molto ben capito quale punto devo prendere per la distanza
    double distanceOrigin(){
        return new Punto().distance(this.p1.puntoMedio(this.p2));
    }

    int equalSegments(Segmento s){
        if ( s.p1.equals(this.p1) && s.p2.equals(this.p2) ){
            return 0; // sono uguali e nella stessa posizione
        } else if ( s.p1.distance(s.p2) == this.p1.distance(this.p2) ) {
            return -1; // sono ugualmente lunghi ma in posizioni differenti
        }
        return 1; // sono completamente diversi
    }

    // a quanto pare per i float/double e meglio usare il compare di java
    int compareTo(Segmento s){
        double d1, d2;
        d1 = distanceOrigin();
        d2 = s.distanceOrigin();
        return Double.compare(d1, d2); // ritorna i valori -1 0 1
    }

    // metodo per compilare il segmento
    void compileSegment(){
        Punto puntoA = new Punto();
        Punto puntoB = new Punto();

        Scanner sc = new Scanner(System.in);

        System.out.println("Inserisci nome punto A: ");
        puntoA.setName(sc.nextLine());
        System.out.println("Inserisci x: ");
        puntoA.setX(sc.nextFloat());
        System.out.println("Inserisci y: ");
        puntoA.setY(sc.nextFloat());
        sc.nextLine(); // per rimuovere il carattere \n

        System.out.println("Inserisci nome punto B: ");
        puntoB.setName(sc.nextLine());
        System.out.println("Inserisci x: ");
        puntoB.setX(sc.nextFloat());
        System.out.println("Inserisci y: ");
        puntoB.setY(sc.nextFloat());
        sc.nextLine(); // per rimuovere il carattere \n

        this.p1 = puntoA;
        this.p2 = puntoB;
    }

    @Override public String toString(){
        return name + " (" + p1.toString() + " - " + p2.toString() + ")";
    }
}
