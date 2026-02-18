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
        name = "Segmento semplice degenere";
    }

    // distanza dal punto medio del segmento all'origine
    // non ho molto ben capito quale punto devo prendere per la distanza
    double distanceOrigin(){
        return new Punto().distance(puntoMedio());
    }

    int equalSegments(Segmento b){
        if ( b.p1.equals(this.p1) && b.p2.equals(this.p2) ){
            return 0; // sono uguali e nella stessa posizione
        } else if ( b.p1.distance(b.p2) == this.p1.distance(this.p2) ) {
            return -1; // sono ugualmente lunghi ma in posizioni differenti
        }
        return 1; // sono completamente diversi
    }

    // a quanto pare per i float/double e meglio usare il compare di java
    int compareTo(Segmento b){
        double d1, d2;
        d1 = distanceOrigin();
        d2 = b.distanceOrigin();
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

    Punto puntoMedio(){
        return this.p1.puntoMedio(this.p2);
    }

    double coefficienteAngolare(){
        double num, den;
        num = (this.p2.getY()-this.p1.getY());
        den = (this.p2.getX()-this.p1.getX());
        if ( den == 0 ) return  Double.POSITIVE_INFINITY; // ho scoperto questo attributo specifico per questi errori
        return num/den;
    }

    // inclinazione rispetto all'Asse delle ascisse
    // in gradi e usa la funzione arctan ATAN2 per gestire i casi verticali e orizzontali
    double inclinazioneAscisse(){
        double dy = p2.getY() - p1.getY();
        double dx = p2.getX() - p1.getX();
        return Math.toDegrees(Math.atan2(dy, dx));
    }

    Segmento traslato(double deltaX){
        Punto newP1 = new Punto(p1.getX() + deltaX, p1.getY(), p1.getName());
        Punto newP2 = new Punto(p2.getX() + deltaX, p2.getY(), p2.getName());
        return new Segmento(newP1, newP2, this.name + " traslato");
    }


    /*
    double angoloSegmenti(Segmento b){
        // vettori direzionali
        double dx1 = p2.getX() - p1.getX();
        double dy1 = p2.getY() - p1.getY();
        double dx2 = b.p2.getX() - b.p1.getX();
        double dy2 = b.p2.getY() - b.p1.getY();

        return Math.toDegrees(Math.acos(dx1*dx2 + dy1*dy2)/(Math.sqrt(dx1*dx1 + dy1*dy1)*Math.sqrt(dx2*dx2 + dy2*dy2)))
    }
    */

    // per segmenti non ordinati
    boolean segmentiConsecutivi(Segmento b){
        if ( b.p2.equals(this.p1) || b.p2.equals(this.p2) || b.p1.equals(this.p1) || b.p1.equals(this.p2) )
            return true;
        return false;
    }

    boolean segmentoDegenere(){
        if ( p1.equals(p2) )
            return true;
        return false;
    }

    @Override public String toString(){
        return name + " (" + p1.toString() + " - " + p2.toString() + ")";
    }
}
