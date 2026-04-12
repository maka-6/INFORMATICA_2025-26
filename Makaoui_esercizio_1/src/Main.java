/*
* Autore: Makaoui Youness
* Data: 23/03/2026
* Luogo: Lab 53bis
* Versione: 1.0
* Descrizione:
*/

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] catena = {"Materia prima" , "Materia prima"};

        Preparazione p1 = new Preparazione("Preparazione");
        Assemblaggio a1 = new Assemblaggio("Assemblaggio");
        Impaziente i1 = new Impaziente("Lavoratore pazzo");

        System.out.println("\nStato linea di produzione: " + Arrays.toString(catena));

        System.out.println("Lavoratore pazzo in arrivo...");
        i1.start();
        System.out.println("\nAvvio produzione....\n");
        p1.start();

        try {
            p1.join();
            for (int i = 0; i < catena.length; i++) {
                catena[i] = "Pezzi pronti";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStato linea di produzione: " + Arrays.toString(catena));

        System.out.println("\nAvvio assemblaggio....\n");
        a1.start();

        try {
            a1.join();
            for (int i = 0; i < catena.length; i++) {
                catena[i] = "Prodotto assemblato";
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nStato linea di produzione: " + Arrays.toString(catena));

    }
}