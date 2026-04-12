/*
 * Autore: Makaoui Youness
 * Data: 23/03/2026
 * Luogo: Lab 53bis
 * Versione: 1.0
 * Descrizione:
 */

public class Preparazione extends Thread{

    public Preparazione(String name){
        super(name);
    }

    @Override
    public void run(){

        System.out.println(getName() + ": Preparazione dei pezzi...");

    }
}